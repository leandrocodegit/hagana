/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Device;
import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

/**
 *
 * @author leand
 */
@ManagedBean
@ViewScoped
public class DashboradBean implements Serializable {

    private MindmapNode root;
    private Device device;
    private MindmapNode selectedNode;
    private HashMap<String, MindmapNode> rede = new HashMap<String, MindmapNode>();
    private HashMap<String, MindmapNode> link = new HashMap<String, MindmapNode>();
    private HashMap<String, Object> dispositivos = new HashMap<String, Object>();

    @PostConstruct
    public void init() {
        try {
            if (SessionContext.getInstance().getClienteSelecionado() == null) {               
               FacesContext.getCurrentInstance().getExternalContext().redirect("clientes.xhtml");
            } 
        } catch (Exception ex) {

        }

    }

    public DashboradBean() {
        root = new DefaultMindmapNode("Internet".toUpperCase(), "", "ff006b", false);

        Cliente cliente = SessionContext.getInstance().getClienteSelecionado();

        if (cliente != null) {
            for (int i = 0; i < cliente.getLinkList().size(); i++) {

                MindmapNode lk;
                if (i == 0) {
                    lk = new DefaultMindmapNode(cliente.getLinkList().get(i).getOperadora().toUpperCase(), cliente.getLinkList().get(i).getPortaUPLink(), "00add1", true);
                } else {
                    lk = new DefaultMindmapNode(cliente.getLinkList().get(i).getOperadora().toUpperCase(), cliente.getLinkList().get(i).getPortaUPLink(), "cdd9db", true);
                }

                link.put(cliente.getLinkList().get(i).getPortaUPLink(), lk);
                root.addNode(lk);
                dispositivos.put(cliente.getLinkList().get(i).getPortaUPLink(), cliente.getLinkList().get(i));

            }

            for (int i = 0; i < cliente.getRedeList().size(); i++) {

                String color = "5b0386";
                if (cliente.getRedeList().get(i).getTipo() == 11 || cliente.getRedeList().get(i).getTipo() == 12) {
                    color = "364257";
                }

                MindmapNode rd = new DefaultMindmapNode(cliente.getRedeList().get(i).getNome().toUpperCase(), cliente.getRedeList().get(i).getPortaUPLink(), color, true);
                rede.put(cliente.getRedeList().get(i).getPortaUPLink(), rd);

                if (cliente.getRedeList().get(i).getPort_conect().contains("L")) {
                    MindmapNode md = link.get(cliente.getRedeList().get(i).getPort_conect());
                    if (md != null) {
                        md.addNode(rd);
                    }
                } else {
                    if (!cliente.getRedeList().get(i).getPort_conect().equals("")) {
                        MindmapNode md = rede.get(cliente.getRedeList().get(i).getPort_conect());
                        System.out.println("Switch " + cliente.getRedeList().get(i).getNome());
                        if (md != null) {
                            md.addNode(rd);
                        }
                    }
                }
                dispositivos.put(cliente.getRedeList().get(i).getPortaUPLink(), cliente.getRedeList().get(i));

            }

            for (int i = 0; i < cliente.getDispositivoList().size(); i++) {

                MindmapNode rd = new DefaultMindmapNode(cliente.getDispositivoList().get(i).getNome().toUpperCase(), cliente.getDispositivoList().get(i) + "D", "00b39c", true);

                MindmapNode md = rede.get(cliente.getDispositivoList().get(i).getPort_conect());
                System.out.println("Dispositivo " + cliente.getDispositivoList().get(i).getNome().toUpperCase());

                if (md != null) {
                    md.addNode(rd);
                }
                dispositivos.put(cliente.getDispositivoList().get(i) + "D", cliente.getDispositivoList().get(i));

            }

            for (int i = 0; i < cliente.getComputadorList().size(); i++) {

                MindmapNode rd = new DefaultMindmapNode("PC - " + cliente.getComputadorList().get(i).getNome().toUpperCase(), cliente.getComputadorList().get(i) + "C", "ff4800", true);

                MindmapNode md = rede.get(cliente.getComputadorList().get(i).getPort_conect());
                System.out.println("Computador " + cliente.getComputadorList().get(i).getNome());

                if (md != null) {
                    md.addNode(rd);
                }
                dispositivos.put(cliente.getComputadorList().get(i) + "C", cliente.getComputadorList().get(i));

            }
        }

    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public MindmapNode getRoot() {
        return root;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
        device = (Device) dispositivos.get(selectedNode.getData().toString());
    }

    public void onNodeSelect(SelectEvent<MindmapNode> event) {
        device = (Device) dispositivos.get(event.getObject().getData().toString());
    }

    public void onNodeDblselect(SelectEvent<MindmapNode> event) {

        this.selectedNode = event.getObject();
        device = (Device) dispositivos.get(event.getObject().getData().toString());
    }

    public void message() {

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Selecione um cliente", "para ver o map"));
    }
}
