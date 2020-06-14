/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.controler.FabricanteDAO;
import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Dispositivo;
import br.leandro.hagana.entidade.Fabricante;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

/**
 *
 * @author leand
 */
@ManagedBean
public class DispositivoBean implements Serializable {

    private static final long serialVersionUID = 13245855655321L;

    private HtmlDataTable dataTable;
    public Dispositivo dispositivo = new Dispositivo();
    public Dispositivo dispositivoFom;
    private List<Dispositivo> dispositivoList;
    private List<Fabricante> fabricantes;
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

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public Dispositivo getDispositivoFom() {
        return dispositivoFom;
    }

    public void setDispositivoFom(Dispositivo dispositivoFom) {
        this.dispositivoFom = dispositivoFom;
    }

    public void setDispositivo(Dispositivo dispositivo) {

        this.dispositivo = (Dispositivo) dataTable.getRowData();
        this.dispositivoFom = dispositivo;
    }

    public List<Dispositivo> getDispositivoList() {
        return ClienteDAO.getInstance().findAll(SessionContext.getInstance().getClienteSelecionado()).getDispositivoList();
    }

    public List<Fabricante> getFabricantes() {
        return FabricanteDAO.getInstance().getFabricantes();
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public void event() {

        fabricante = new Fabricante();

        dispositivo.setFabricanteFK(fabricante);

    }

    public void deletar() {

    }

    public void atualizar() {

    }

    public void adicionar() {

    }

    public void limpar() {
        dispositivo = new Dispositivo();
    }

}
