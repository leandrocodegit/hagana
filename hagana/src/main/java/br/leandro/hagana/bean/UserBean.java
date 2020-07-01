/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.DAO;
import br.leandro.hagana.controler.UsuarioDao;
import br.leandro.hagana.entidade.Usuario;
import java.io.Serializable;
import java.util.List;
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
public class UserBean implements Serializable {

    private static final long serialVersionUID = 123984529855321L;

    private HtmlDataTable dataTable;
    private List<Usuario> userList;
    private DAO dao = new DAO();
    private Usuario user;

    public HtmlDataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    public List<Usuario> getUserList() {
        return UsuarioDao.getInstance().getUsuarios();
    }

    public void setUserList(List<Usuario> userList) {
        this.userList = userList;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void selecionarUsuario() {
        user = (Usuario) dataTable.getRowData();
        System.out.println("Perfil " + user.getPerfil());
    }

    public void deletar() {
        if (user != null) {
            dao.delete(user, user.getIdusuario());
            message("Sucesso!", "Removido usuário.");
        }
    }

    public void atualizar() {

        if (user.getPerfil() == 2) {
            user.setNome(user.getNome().substring(0, 1).toUpperCase() + user.getNome().substring(1).toLowerCase());

            if (user.getNome().length() < 6) {
                user.setNome(user.getNome().toUpperCase());
            }
            message("Sucesso!", "Usuário atualizado.");
        } else {
            message("Restrição!", "Não é permitido alterar o perfil administrador!");
            dao.atualizar(user);
        }
        dao.atualizar(user);
    }

    public void adicionar() {

        user.setPerfil(2);

        user.setNome(user.getNome().substring(0, 1).toUpperCase() + user.getNome().substring(1).toLowerCase());

        if (user.getNome().length() < 6) {
            user.setNome(user.getNome().toUpperCase());
        }

        if (dao.insert(user) != null) {
            limpar();
            message("Sucesso!", "Usuário dicionado.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao adicionar usuário.");
        }

        limpar();
    }

    public void limpar() {
        user = new Usuario();
    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
