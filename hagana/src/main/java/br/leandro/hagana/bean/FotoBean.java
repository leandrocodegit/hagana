/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.DAO;
import br.leandro.hagana.entidade.Foto;
import br.leandro.hagana.entidade.Local;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
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

    @PostConstruct
    public void init() {

        if (SessionContext.getInstance().getClienteSelecionado() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("clientes.xhtml");
            } catch (Exception ex) {

            }
        }
    }
    
    public List<Foto> getFotoList() {
        return ClienteDAO.getInstance().findAll(SessionContext.getInstance().getClienteSelecionado()).getFotoList();
    }
 
    public void deletar() {
        if (foto != null) {
            DAO.getInstance().delete(foto, foto.getIdfoto());
            message("Sucesso!", "Removido foto.");
        }
    }

    public void atualizar() {

        foto.setNome(foto.getNome().substring(0, 1).toUpperCase() + foto.getNome().substring(1).toLowerCase());

        if (foto.getNome().length() < 6) {
            foto.setNome(foto.getNome().toUpperCase());
        }
 
        DAO.getInstance().atualizar(foto);
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

        if (DAO.getInstance().insert(gravar) != null) {

            message("Sucesso!", " foto adicionada.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao salvar foto.");
        }        
    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}

