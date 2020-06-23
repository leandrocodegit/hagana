/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Usuario;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Felipee
 */
public class SessionContext {

    private static SessionContext instance;
    private static final long serialVersionUID = 123423523562L;
    private Cliente cliente;

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }

        return instance;
    }

    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public Usuario getUsuarioLogado() {
        return (Usuario) getAttribute("usuarioLogado");
    }

    public void setUsuarioLogado(Usuario usuario) {
        setAttribute("usuarioLogado", usuario);
    }

    public Cliente getClienteSelecionado() {
        cliente = (Cliente) getAttribute("clienteSelecionado");
 
        return cliente;
    }

    public void refreshcliente() {
        cliente = (Cliente) ClienteDAO.getInstance().findCliente(cliente);
 
    }

    public void setClienteSelecionado(Cliente cliente) {
        this.cliente = cliente;
 
         setAttribute("clienteSelecionado", cliente);
         System.out.println("*********************** SET *************************");
         System.out.println("Fotos " + cliente.getFotoList().size());
         System.out.println("computador " + cliente.getComputadorList().size());
         System.out.println("Dispositivos " + cliente.getDispositivoList().size());
         System.out.println("Rede " + cliente.getRedeList().size());
 
    }

    public void encerrarSessao() {
        currentExternalContext().invalidateSession();
        currentExternalContext().responseReset();
    }

    public Object getAttribute(String nome) {
        return currentExternalContext().getSessionMap().get(nome);
    }

    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public HttpServletRequest getRequestSession() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

}
