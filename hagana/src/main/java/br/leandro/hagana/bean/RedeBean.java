/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.DAO;
import br.leandro.hagana.controler.RedeDAO;
import br.leandro.hagana.entidade.Arquivo;
import br.leandro.hagana.entidade.Foto;
import br.leandro.hagana.entidade.Local;
import br.leandro.hagana.entidade.Rede;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author leand
 */
@ManagedBean
@ViewScoped
public class RedeBean implements Serializable {

    private static final long serialVersionUID = 15564855655321L;

    private HtmlDataTable dataTable;
    public Rede rede;
    private Part file;
    private Arquivo arquivo; 

    @PostConstruct
    public void init() {
 
        if (SessionContext.getInstance().getClienteSelecionado() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("clientes.xhtml");
            } catch (Exception ex) {

            }
        }
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    public Rede getRede() {
        return rede;
    }

    public void setRede(Rede rede) {
        this.rede = rede;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Arquivo getArquivo() {
        return arquivo;
    }

    public void setArquivo(Arquivo arquivo) {
        this.arquivo = arquivo;
    }

    public List<Rede> getRedeList() { 
        return ClienteDAO.getInstance().findCliente(SessionContext.getInstance().getClienteSelecionado()).getRedeList();
    }

    public List<Local> getLocalList() {
        return ClienteDAO.getInstance().findCliente(SessionContext.getInstance().getClienteSelecionado()).getLocalList(); 
    }

 
    public void selecionarRede() {
        rede = (Rede) dataTable.getRowData();
        rede.setCaptureSenha(SessionContext.getInstance().getUsuarioLogado().getVerSenhas());
        System.out.println("selecionado " + rede);

    }

    public void deletar() {
        if (rede != null) {
            RedeDAO.getInstance().delete(rede.getIdrede());
            SessionContext.getInstance().refreshcliente();
            message("Sucesso!", "Removido rede.");
        }
    }

    public void atualizar() {

        rede.setNome(rede.getNome().substring(0, 1).toUpperCase() + rede.getNome().substring(1).toLowerCase());

        if (rede.isDhcp()) {
            rede.setIp("DHCP");
        }

        RedeDAO.getInstance().atualizar(rede);
        SessionContext.getInstance().refreshcliente();
        message("Sucesso!", "Atualizado.");
    }

    public void adicionar() {

        rede.setIdrede(0);
        rede.setDataCriacao(new Date());
        rede.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        rede.setClienteFK(SessionContext.getInstance().getClienteSelecionado());

        rede.setNome(rede.getNome().substring(0, 1).toUpperCase() + rede.getNome().substring(1).toLowerCase());

        if (rede.isDhcp()) {
            rede.setIp("DHCP");
        }

        Rede gravar = new Rede();
        gravar = rede;
        if (RedeDAO.getInstance().insert(rede) != null) {
            SessionContext.getInstance().refreshcliente();
            message("Sucesso!", "Adicionado.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao adicionar.");
        }

    }

    public void limpar() {

        if(rede == null)
        {
            rede = new Rede();
        }
        rede.setLocalFK(new Local());
        rede.setCaptureSenha(true);
    }

    //Salva object no bd e jpg no diretorio
    public void uploadFile() {

        //Recupera path absoluto
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String diretorio = ec.getRealPath("/");

        String diretorioCliente = diretorio + "restrict\\arquivos\\contas\\" + SessionContext.getInstance().getClienteSelecionado().getConta() + "\\arquivos\\";

        if (file != null) {
            try ( InputStream input = file.getInputStream()) {
                arquivo = new Arquivo();
                arquivo.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());

                arquivo.setDataCriacao(new Date());
                arquivo.setFileName(file.getSubmittedFileName());
                arquivo.setPrivado(true);

                deleteFileExist();
                
                if (rede.getArquivoFK() != null) {
                    arquivo = rede.getArquivoFK();
                    rede.getArquivoFK().setFileName(file.getSubmittedFileName());
                    DAO.getInstance().atualizar(arquivo);
                } else {
                    arquivo = (Arquivo) DAO.getInstance().insert(arquivo);
                    rede.setArquivoFK(arquivo);
                     DAO.getInstance().atualizar(rede);
                }
 
                if (file != null) {
                    Files.copy(input, new File(diretorioCliente, String.valueOf(file.getSubmittedFileName())).toPath());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Arquivo enviado com sucesso."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha!", " erro ao enviar arquivo."));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Deleta foto do diretorio
    public boolean deleteFileExist() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String diretorio = ec.getRealPath("/") + "restrict\\arquivos\\contas\\" + SessionContext.getInstance().getClienteSelecionado().getConta() + "\\arquivos";
        File path = new File(diretorio);
        try{
        File arq = new File(path.getPath(), rede.getArquivoFK().getFileName());
        
        if (path.exists() && rede != null) {
            arq.delete();
            return true;
        }
        }catch(NullPointerException erro){
            return true;
        }
        return false;
    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
