/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.entidade.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author leand
 */
@ManagedBean
@RequestScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 12855655321L;
    
    private HtmlDataTable dataTable;
    private List<Cliente> clienteList;
    private Cliente cliente;
    private String pesquisa;
    
     @PostConstruct
    public void init() {
        clienteList = ClienteDAO.getInstance().getclientes();
    }

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = (Cliente) dataTable.getRowData();
    }

  
    
    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        clienteList = ClienteDAO.getInstance().pesquisar(query);
        System.out.println(clienteList.size() + " " + query);
        for(int i = 0; i < clienteList.size(); i++) {
            System.out.println(i);
            results.add(clienteList.get(i).toString());
        }
        if(clienteList.size() == 0){
            results.add("Nenhum resultado");
        }
         
        return results;
    }
 
    
      public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public void deletar()
    {
        
    }
    public void atualizar()
    {
        
    }
    public void adicionar()
    {
        
        cliente.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        if ( ClienteDAO.getInstance().insert(cliente) != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente adicionado com sucesso! "));
           // criarPasta(cli);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro  ao  gravar!", " "));
        } 
    }
    public void limpar()
    {
        cliente = new Cliente();
         
    }

}
