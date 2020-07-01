/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Device;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

/**
 *
 * @author leand
 */
@ManagedBean
@RequestScoped
public class DashboradBean implements Serializable {

    private MindmapNode root;
    private MindmapNode selectedNode;
    private MindmapNode comutador;
    private Device device;
    private List<Device> devicesList;
    private boolean linkSet = true;
    private HashMap<String, MindmapNode> rede = new HashMap<String, MindmapNode>();
    private HashMap<String, MindmapNode> link = new HashMap<String, MindmapNode>();
    private HashMap<String, Object> dispositivos = new HashMap<String, Object>();

    @PostConstruct
    public void init() {
        try {
            if (SessionContext.getInstance().getClienteSelecionado() == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("clientes.xhtml");
            }

            if (devicesList.isEmpty() || devicesList == null) {
                message();
            } else {
                SessionContext.getInstance().refreshcliente();
                load();
            }

        } catch (Exception ex) {

        }

    }

    public DashboradBean() {
        load();
    }

    private void load() {
        root = new DefaultMindmapNode("Internet".toUpperCase(), "", "8ee203", false);

        Cliente cliente = SessionContext.getInstance().getClienteSelecionado();

        if (cliente != null) {

            devicesList = ClienteDAO.getInstance().getDevicesListAll(cliente);

            //Cria lista com todos dipositivos do tipo MindmapNode
            for (int i = 0; i < devicesList.size(); i++) {

                String color = "c60061";
                boolean select = true;

                MindmapNode deviceModel = new DefaultMindmapNode(devicesList.get(i).getNome().toUpperCase(), devicesList.get(i).getPortaUPLink(), color, select);
                dispositivos.put(devicesList.get(i).getPortaUPLink(), devicesList.get(i));
                rede.put(devicesList.get(i).getPortaUPLink(), deviceModel);
                root.addNode(deviceModel);

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

    public MindmapNode getComutador() {
        return comutador;
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
        context.addMessage(null, new FacesMessage("Advertência!", "O map para essa conta não foi configurado corretamente!"));

    }
}
