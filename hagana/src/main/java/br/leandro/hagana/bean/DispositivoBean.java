/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.DispositivoDAO;
import br.leandro.hagana.controler.FabricanteDAO;
import br.leandro.hagana.entidade.Dispositivo;
import br.leandro.hagana.entidade.Fabricante;
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
public class DispositivoBean implements Serializable {

    private static final long serialVersionUID = 15564855655321L;

    private HtmlDataTable dataTable;
    public Dispositivo dispositivo;
    private Fabricante fabricante;
    private String ipAnterior;

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

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public List<Dispositivo> getDispositivoList() {
        return ClienteDAO.getInstance().findAll(SessionContext.getInstance().getClienteSelecionado()).getDispositivoList();
    }

    public List<Fabricante> getFabricantes() {
        return FabricanteDAO.getInstance().getFabricantes();
    }

    public List<Local> getLocalList() {
        return ClienteDAO.getInstance().findAll(SessionContext.getInstance().getClienteSelecionado()).getLocalList();
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public void selecionarDispositivo() {
        this.dispositivo = (Dispositivo) dataTable.getRowData();
        System.out.println(dispositivo.getNome());

    }

    public void DHCP() {

        if (dispositivo.isDhcp()) {
            ipAnterior = dispositivo.getIp();
            dispositivo.setIp("DHCP");
        } else {
            dispositivo.setIp(ipAnterior);
        }
    }

    public void event() {

        fabricante = new Fabricante();

        dispositivo.setFabricanteFK(fabricante);

    }

    public void deletar() {
        if (dispositivo != null) {
            DispositivoDAO.getInstance().delete(dispositivo.getIddispositivo());
            message("Sucesso!", "Removido dispositivo.");
        }
    }

    public void atualizar() {

        dispositivo.setNome(dispositivo.getNome().substring(0, 1).toUpperCase() + dispositivo.getNome().substring(1).toLowerCase());

        if (dispositivo.getNome().length() < 6) {
            dispositivo.setNome(dispositivo.getNome().toUpperCase());
        }

        if (dispositivo.isDhcp()) {
            dispositivo.setIp("DHCP");
        }

        DispositivoDAO.getInstance().atualizar(dispositivo);
        message("Sucesso!", "Atualizado.");
    }

    public void adicionar() {

        dispositivo.setDataCriacao(new Date());
        dispositivo.setUsuarioFK(SessionContext.getInstance().getUsuarioLogado());
        dispositivo.setClienteFK(SessionContext.getInstance().getClienteSelecionado());

        dispositivo.setNome(dispositivo.getNome().substring(0, 1).toUpperCase() + dispositivo.getNome().substring(1).toLowerCase());

        if (dispositivo.getNome().length() < 6) {
            dispositivo.setNome(dispositivo.getNome().toUpperCase());
        }

        if (dispositivo.isDhcp()) {
            dispositivo.setIp("DHCP");
        }

        if (DispositivoDAO.getInstance().insert(dispositivo) != null) {

            message("Sucesso!", "Adicionado.");
            // criarPasta(cli);
        } else {
            message("Falha!", "Erro ao adicionar.");
        }
    }

    public void limpar() {
        dispositivo = new Dispositivo();
        dispositivo.setPortaTCP(0);
        dispositivo.setPortaWEB(80);
        System.out.println("Limpo");
    }

    public void message(String mensagem, String conteudo) {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensagem, conteudo));
    }

}
