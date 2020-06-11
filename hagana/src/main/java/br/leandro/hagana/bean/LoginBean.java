/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;
 
import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.UsuarioDao;
import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Usuario;
import java.io.Serializable; 
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Leandro Code
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 855655321L;

    
    private Usuario usuario;
    private String nomeUsuario = "";
    private String senha = "";

    @PostConstruct
    public void init() {  

        usuario = new Usuario();
    }
    
    

   
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {

        return usuario;
    }
    
    public Usuario getUsuarioLogado() {

        return SessionContext.getInstance().getUsuarioLogado();
    }


    public String logar() {

        Usuario u = new Usuario();
        u.setUser("1");
        u.setPassword("1");
        
        usuario = UsuarioDao.getInstance().findUser(u);
        
        if (usuario != null && usuario.getBloqueado() == false) {

            
           
            FacesMessage msg = new FacesMessage("Login ", usuario.getNome());
            FacesContext.getCurrentInstance().addMessage(null, msg);

           SessionContext.getInstance().getSession().setAttribute("usuarioLogado", usuario);          
                            
            return "/restrict/clientes.xhtml?faces-redirect=true";
        } else {

           
            FacesMessage msg = new FacesMessage("Login ", " Falha nas credênciais!");
            FacesContext.getCurrentInstance().addMessage(null, msg);

          
            return "login.xhtml";
        }

    }


    public String sair() {

        br.leandro.hagana.bean.SessionContext.getInstance().encerrarSessao();
        return "/login/login.xhtml?faces-redirect=true";
    }


    SessaoBean sessaoBean = new SessaoBean();

    public String getDadosCliente() {
        
        Cliente cliente = (Cliente) sessaoBean.getSession().getAttribute("clienteSelecionado");

        if (cliente != null) {
            return  cliente.getNome() + " - " +  cliente.getConta();
        } else {
            return "Selecione um cliente aqui";
        }

    }
    
        public String getDadosUsuario() {

        Usuario user = SessionContext.getInstance().getUsuarioLogado();

        if (user != null) {
            return user.getNome().substring(0, user.getNome().indexOf(" "));
        } else {
            return "";
        }
    }

}
