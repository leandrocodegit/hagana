/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.FabricanteDAO;
import br.leandro.hagana.controler.DAO;
import br.leandro.hagana.entidade.Fabricante;
import br.leandro.hagana.entidade.Link;
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
public class LinkBean implements Serializable {

    private static final long serialVersionUID = 15564855655321L;

    private HtmlDataTable dataTable;
    public Link link;
    private Fabricante fabricante;
    private DAO dao = new DAO();

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

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public List<Link> getLinkList() {
        return SessionContext.getInstance().getClienteSelecionado().getLinkList();
    }

    public List<Local> getLocalList() {
        return SessionContext.getInstance().getClienteSelecionado().getLocalList();
    }

    public List<Fabricante> getFabricantes() {
        return FabricanteDAO.getInstance().getFabricantes();
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public void selecionarLink() {
        link = (Link) dataTable.getRowData();
        link.setCaptureSenha(SessionContext.getInstance().getUsuarioLogado().getVerSenhas());

    }

    public void deletar() {
        if (link != null) {
            dao.delete(link, link.getIdlink());
            SessionContext.getInstance().refreshcliente();
            message("Sucesso!", "Removido link.");
        }
    }

    public void atualizar() {

        link.setNome(link.getNome().substring(0, 1).toUpperCase() + link.getNome().substring(1).toLowerCase());

        if (link.getNome().length() < 6) {
            link.setNome(link.getNome().toUpperCase());
        }

        dao.atualizar(link);
        SessionContext.getInstance().refreshcliente();
        message("Sucesso!", "Atualizado.");
    }

    public void adicionar() {

        link.setIdlink(0);
        link.setDataCriacao(new Date());
        link.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        link.setClienteFK(SessionContext.getInstance().getClienteSelecionado());
        

        link.setNome(link.getNome().substring(0, 1).toUpperCase() + link.getNome().substring(1).toLowerCase());

        if (link.getNome().length() < 6) {
            link.setNome(link.getNome().toUpperCase());
        }

        if (dao.insert(link) != null) {
            SessionContext.getInstance().refreshcliente();
            limpar();
            message("Sucesso!", "Adicionado.");
            // criarPasta(cli);
        } else {
            //  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha!", "Erro ao adicionar."));           
        }
    }

    public void limpar() {

        link = new Link();

        link.setLocalFK(new Local());
        link.setPorta(80);
        link.setCaptureSenha(true);

    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
