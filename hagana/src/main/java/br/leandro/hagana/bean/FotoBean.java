/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.DAO;
import br.leandro.hagana.entidade.Foto;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author leand
 */
@ManagedBean
@ViewScoped
public class FotoBean implements Serializable {

    private static final long serialVersionUID = 15564855655321L;
    public Foto foto;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private DAO dao = new DAO();

    @PostConstruct
    public void init() {
 
        
        
        if (SessionContext.getInstance().getClienteSelecionado() == null) {
            try {
                SessionContext.getInstance().setClienteSelecionado(clienteDAO.findAll(SessionContext.getInstance().getClienteSelecionado()));  
                clienteDAO.getEntityManager().close();
                FacesContext.getCurrentInstance().getExternalContext().redirect("clientes.xhtml");
            } catch (Exception ex) {

            }
        }
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public List<Foto> getFotoList() {
        return clienteDAO.findAll(SessionContext.getInstance().getClienteSelecionado()).getFotoList();
    }

    public void deletar(Integer id) {
        foto = new Foto();
        foto.setIdfoto(id);
        System.out.println("Removendo foto " + id);

        if (deletaFotoDir()) {
            dao.delete(foto, id);
            message("Sucesso!", "Removido foto.");
        } else {
            message("Erro!", "Falha ao remover arquivo.");
        }

    }

    public void atualizar() {

        foto.setNome(foto.getNome().substring(0, 1).toUpperCase() + foto.getNome().substring(1).toLowerCase());

        if (foto.getNome().length() < 6) {
            foto.setNome(foto.getNome().toUpperCase());
        }

        dao.atualizar(foto);
        message("Sucesso!", "Atualizado foto.");
    }

    public void adicionar() {

        foto.setIdfoto(null);
        foto.setDataCriacao(new Date());
        foto.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        foto.setClienteFK(SessionContext.getInstance().getClienteSelecionado());

        foto.setNome(foto.getNome().substring(0, 1).toUpperCase() + foto.getNome().substring(1).toLowerCase());

        if (foto.getNome().length() < 6) {
            foto.setNome(foto.getNome().toUpperCase());
        }

        Foto gravar = new Foto();
        gravar = foto;

        if (dao.insert(gravar) != null) {

            message("Sucesso!", " foto adicionada.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao salvar foto.");
        }
    }

    //Deleta foto do diretorio
    public boolean deletaFotoDir() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String diretorio = ec.getRealPath("/") + "restrict\\arquivos\\contas\\" + SessionContext.getInstance().getClienteSelecionado().getConta();
        File path = new File(diretorio + "\\imagens");

        if (path.exists() && foto != null) {
 
            File file = new File(path.getPath(), String.valueOf(foto.getIdfoto()) + ".jpg");
            file.delete();
            file = new File(path.getPath(), "_" + String.valueOf(foto.getIdfoto()) + ".jpg");
            file.delete();

            return true;
        }
        return false;
    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
