/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.entidade.Cliente;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

/**
 *
 * @author leand
 */
@ManagedBean
@SessionScoped
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
    public Cliente getClienteSelecionado() {
        return SessionContext.getInstance().getClienteSelecionado();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = (Cliente) dataTable.getRowData();
    }
    

    public void buscar() {       
        clienteList = ClienteDAO.getInstance().pesquisar(pesquisa);

        if (pesquisa.equals("")) {
            clienteList = ClienteDAO.getInstance().getclientes();
        }
        if(clienteList.size() == 0)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Nenhum resultado encontrado!"));
        }
 
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String selecionaCliente(Cliente cliente) {

        cliente = (Cliente) dataTable.getRowData();
        SessionContext.getInstance().setClienteSelecionado(cliente);
         
        if (SessionContext.getInstance().getClienteSelecionado() != null) {

        
        }

        return "/restrict/index.xhtml?faces-redirect=true";
    }

    public void deletar() {
        if(cliente != null){
            ClienteDAO.getInstance().delete(cliente.getConta());
            clienteList = ClienteDAO.getInstance().getclientes();
        }
    }

    public void atualizar() {
        ClienteDAO.getInstance().atualizar(cliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Conta atualizada com sucesso!"));
    }

    public void adicionar() {
         
        cliente.setDataCriacao(new Date());
        cliente.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        if (ClienteDAO.getInstance().insert(cliente) != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente adicionado com sucesso! "));
            clienteList = ClienteDAO.getInstance().getclientes();
            // criarPasta(cli);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro  ao  gravar!", " "));
        }
    }

    public void limpar() {       
        cliente = new Cliente();
    }

}
