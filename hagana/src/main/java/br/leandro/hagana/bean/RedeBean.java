/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.RedeDAO;
import br.leandro.hagana.entidade.Local;
import br.leandro.hagana.entidade.Rede;
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
public class RedeBean implements Serializable {

    private static final long serialVersionUID = 15564855655321L;

    private HtmlDataTable dataTable;
    public Rede rede;

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

    public List<Rede> getRedeList() {
        return ClienteDAO.getInstance().findAll(SessionContext.getInstance().getClienteSelecionado()).getRedeList();
    }

    public List<Local> getLocalList() {
        return ClienteDAO.getInstance().findAll(SessionContext.getInstance().getClienteSelecionado()).getLocalList();
    }

    public void selecionarRede() {
        this.rede = (Rede) dataTable.getRowData();

    }

    public void deletar() {
        if (rede != null) {
            RedeDAO.getInstance().delete(rede.getIdrede());
            message("Sucesso!", "Removido rede.");
        }
    }

    public void atualizar() {

        rede.setNome(rede.getNome().substring(0, 1).toUpperCase() + rede.getNome().substring(1).toLowerCase());

        if (rede.isDhcp()) {
            rede.setIp("DHCP");
        }

        RedeDAO.getInstance().atualizar(rede);
        message("Sucesso!", "Atualizado.");
    }

    public void adicionar() {

        rede.setDataCriacao(new Date());
        rede.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        rede.setClienteFK(SessionContext.getInstance().getClienteSelecionado());

        rede.setNome(rede.getNome().substring(0, 1).toUpperCase() + rede.getNome().substring(1).toLowerCase());

        if (rede.isDhcp()) {
            rede.setIp("DHCP");
        }

        if (RedeDAO.getInstance().insert(rede) != null) {

            message("Sucesso!", "Adicionado.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao adicionar.");
        }

    }

    public void limpar() {

        Local local = new Local();
        rede = new Rede();
        rede.setLocalFK(local);
        System.out.println("Limpo");
    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
