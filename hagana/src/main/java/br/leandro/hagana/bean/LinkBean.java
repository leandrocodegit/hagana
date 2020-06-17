/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.DispositivoDAO;
import br.leandro.hagana.controler.FabricanteDAO;
import br.leandro.hagana.controler.LinkDAO;
import br.leandro.hagana.entidade.Dispositivo;
import br.leandro.hagana.entidade.Fabricante;
import br.leandro.hagana.entidade.Link;
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

    @PostConstruct
    public void init() {

        if (SessionContext.getInstance().getClienteSelecionado() == null){
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
        return ClienteDAO.getInstance().findAll(SessionContext.getInstance().getClienteSelecionado()).getLinkList();
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
        this.link = (Link) dataTable.getRowData(); 
        System.out.println(link.getNome());
        
    }
 
    public void deletar() {
        if(link != null){
            DispositivoDAO.getInstance().delete(link.getIdlink());
            message("Sucesso!", "Removido link.");
        }
    }

    public void atualizar() {
        
        link.setNome(link.getNome().substring(0, 1).toUpperCase() + link.getNome().substring(1).toLowerCase()); 
        
        if(link.getNome().length() < 6)
        {
         link.setNome(link.getNome().toUpperCase());
        }
 
        LinkDAO.getInstance().atualizar(link);
        message("Sucesso!", "Atualizado.");
    }

    public void adicionar() {
         
        link.setDataCriacao(new Date());
        link.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        link.setClienteFK(SessionContext.getInstance().getClienteSelecionado());
        
        link.setNome(link.getNome().substring(0, 1).toUpperCase() + link.getNome().substring(1).toLowerCase());

        if(link.getNome().length() < 6)
        {
         link.setNome(link.getNome().toUpperCase());
        }
    
        if (LinkDAO.getInstance().insert(link) != null) {
           
             message("Sucesso!", "Adicionado.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao adicionar.");
        }
    }

    public void limpar() {       
        link = new Link();
        link.setPorta(80);
    }
    
    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
