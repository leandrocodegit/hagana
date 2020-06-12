/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Rede;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
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
    private MindmapNode comutador;

    private MindmapNode selectedNode;
    private HashMap<String, MindmapNode> rede = new HashMap<String, MindmapNode>();
    private HashMap<String, MindmapNode> link = new HashMap<String, MindmapNode>();

    public DashboradBean() {
        root = new DefaultMindmapNode("Internet", "Google WebSite", "FFFFFF", false);

        Cliente cliente = SessionContext.getInstance().getClienteSelecionado();

        for (int i = 0; i < cliente.getLinkList().size(); i++) {

            MindmapNode lk = new DefaultMindmapNode(cliente.getLinkList().get(i).getOperadora(), "IP Numbers", "feff07", true);
            link.put(cliente.getLinkList().get(i).getPortaUPLink(), lk);
            root.addNode(lk);

        }

        for (int i = 0; i < cliente.getRedeList().size(); i++) {
            
            String color = "0068ff";
            if(cliente.getRedeList().get(i).getTipo() == 2)
                color = "04d3f9";

            MindmapNode rd = new DefaultMindmapNode(cliente.getRedeList().get(i).getNome(), "IP Numbers", color, true);
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
                        comutador = rd;
                    }
                }
            }

        }

        for (int i = 0; i < cliente.getDispositivoList().size(); i++) {

            MindmapNode rd = new DefaultMindmapNode(cliente.getDispositivoList().get(i).getNome(), "IP Numbers", "00e774", false);

            MindmapNode md = rede.get(cliente.getDispositivoList().get(i).getPort_conect());
            System.out.println("Dispositivo " + cliente.getDispositivoList().get(i).getNome());

            if (md != null) {
                md.addNode(rd);
            }
             
        }
        
        for (int i = 0; i < cliente.getComputadorList().size(); i++) {

            MindmapNode rd = new DefaultMindmapNode("PC - " + cliente.getComputadorList().get(i).getNome(), "Computadores", "f9c104", false);

            MindmapNode md = rede.get(cliente.getComputadorList().get(i).getPort_conect());
            System.out.println("Computador " + cliente.getComputadorList().get(i).getNome());

            if (md != null) {
                md.addNode(rd);
            }
             
        }
       

    }

    public MindmapNode getRoot() {
        
       // if(comutador != null)
           // return comutador;
        
        return root;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        
        this.selectedNode = selectedNode;
    }

    public void onNodeSelect(SelectEvent<MindmapNode> event) {
        MindmapNode node = event.getObject();
        comutador = null;

        //populate if not already loaded
        if (node.getChildren().isEmpty()) {
            Object label = node.getLabel();

            if (label.equals("NS(s)")) {
                for (int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542", false));
                }
            } else if (label.equals("IPs")) {
                for (int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("1.1.1." + i, "IP Number: 1.1.1." + i, "fce24f", false));
                }
            } else if (label.equals("Malware")) {
                for (int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("Malware-" + random, "Malicious Software: " + random, "3399ff", false));
                }
            }
        }
    }

    public void onNodeDblselect(SelectEvent<MindmapNode> event) {
        this.selectedNode = event.getObject();
    }
}
