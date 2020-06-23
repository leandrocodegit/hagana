/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Device;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct; 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author leand
 */
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 12855655321L;

    private HtmlDataTable dataTable;
    private List<Cliente> clienteList;
    private List<Device> deviceList;
    private Cliente cliente;
    private String pesquisa; 

    @PostConstruct
    public void init() { 
        clienteList = ClienteDAO.getInstance().getclientes();
        
        if (SessionContext.getInstance().getClienteSelecionado() == null) {
            message("Atenção!", "Selecionar um cliente para começar.");
        }
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

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<Device> getDeviceList() {
        return ClienteDAO.getInstance().getDevicesList(SessionContext.getInstance().getClienteSelecionado());
    }

    public void buscar() {
        clienteList = ClienteDAO.getInstance().pesquisar(pesquisa);
       

        if (pesquisa.equals("")) {
            clienteList = ClienteDAO.getInstance().getclientes();
        }
        if (clienteList.size() == 0) {
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
        if (cliente != null) {
            
            if(deletaPasta()){
            ClienteDAO.getInstance().delete(cliente.getConta());
            clienteList = ClienteDAO.getInstance().getclientes();
           
            message("Sucesso!", "Removido conta.");
            }
            else{
                message("Falha!", "Erro ao remover arquivos.");
            }
        }
    }

    public void atualizar() {
        ClienteDAO.getInstance().atualizar(cliente);
        message("Sucesso!", "Atualizado.");
    }

    public void adicionar() {

        cliente.setDataCriacao(new Date());
        cliente.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        if (ClienteDAO.getInstance().insert(cliente) != null) {

            clienteList = ClienteDAO.getInstance().getclientes();
            criarPasta();
            message("Sucesso!", "Adicionado.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao gravar.");
        }
    }

    public void limpar() {
        cliente = new Cliente();
    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

    public boolean deletaPasta() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String diretorio = ec.getRealPath("/") + "restrict\\arquivos\\contas\\" + cliente.getConta();
        File path = new File(diretorio + "\\imagens");        

        if (path.exists()) {            
        
        File file; 
        String[] children = path.list();

        for (int i = 0; i < children.length; i++) {
            file = new File(path.getPath(), children[i]);
            file.delete();
        }
        file = new File(path.getPath());
        file.delete();
        
        path = new File(diretorio + "\\arquivos");        
        children = path.list();

        for (int i = 0; i < children.length; i++) {
            file = new File(path.getPath(), children[i]);
            file.delete();
        }
        file = new File(path.getPath());
        file.delete();
        
        file = new File(diretorio);
        file.delete();
        
            return true;
        }
        return false;
    }

    public void criarPasta() {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        String diretorio = ec.getRealPath("/") + "restrict\\arquivos\\contas\\" + cliente.getConta();

        File arq = new File(diretorio);

        if (!arq.exists()) {

            arq.mkdir();

            arq = new File(diretorio + "\\imagens");
            arq.mkdir();

            arq = new File(diretorio + "\\arquivos");
            arq.mkdir();

        }

    }

}
