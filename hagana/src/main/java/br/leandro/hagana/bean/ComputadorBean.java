/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.ComputadorDAO;
import br.leandro.hagana.controler.DispositivoDAO;
import br.leandro.hagana.entidade.Computador;
import br.leandro.hagana.entidade.Local;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

/**
 *
 * @author leand
 */
@ManagedBean
@ViewScoped
public class ComputadorBean implements Serializable {

    private static final long serialVersionUID = 15564855655321L;

    private HtmlDataTable dataTable;
    public Computador computador;

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

    public Computador getComputador() {
        return computador;
    }

    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    public List<Computador> getComputadorList() {
        return ClienteDAO.getInstance().findCliente(SessionContext.getInstance().getClienteSelecionado()).getComputadorList();
    }

    public List<Local> getLocalList() {
        return ClienteDAO.getInstance().findCliente(SessionContext.getInstance().getClienteSelecionado()).getLocalList();
    }

    public void selecionarComputador() {
        computador = (Computador) dataTable.getRowData();
        computador.setCaptureSenha(SessionContext.getInstance().getUsuarioLogado().getVerSenhas());
    }

    public void deletar() {
        if (computador != null) {
            DispositivoDAO.getInstance().delete(computador.getIdcomputador());
            message("Sucesso!", "Removido computador.");
        }
    }

    public void atualizar() {

        if (computador.isDhcp()) {
            computador.setIp("DHCP");
        }

        computador.setNome(computador.getNome().substring(0, 1).toUpperCase() + computador.getNome().substring(1).toLowerCase());

        ComputadorDAO.getInstance().atualizar(computador);
        message("Sucesso!", "Atualizado.");
    }

    public void adicionar() {

        computador.setIdcomputador(0);
        computador.setDataCriacao(new Date());
        computador.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        computador.setClienteFK(SessionContext.getInstance().getClienteSelecionado());

        computador.setNome(computador.getNome().substring(0, 1).toUpperCase() + computador.getNome().substring(1).toLowerCase());

        if (computador.isDhcp()) {
            computador.setIp("DHCP");
        }

        Computador gravar = new Computador();
        gravar = computador;

        if (ComputadorDAO.getInstance().insert(gravar) != null) {

            message("Sucesso!", "Adicionado.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao adicionar.");
        }
        limpar();
    }

    public void limpar() {
        if (computador == null) {
            computador = new Computador();
        }
        computador.setLocalFK(new Local());
        computador.setCaptureSenha(true);
    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
