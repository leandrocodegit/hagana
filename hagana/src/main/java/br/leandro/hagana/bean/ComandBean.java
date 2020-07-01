/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.entidade.Device;
import com.gennis.servervnc.ClienteServidor;
import com.gennis.servervnc.RecebedorUTF;
import com.gennis.servervnc.TCP;
import com.gennis.servervnc.ServerInstanceCliente;
import com.gennis.servervnc.VerificaConexao;
import com.sun.jersey.spi.inject.Inject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author leand
 */
@ManagedBean
@ViewScoped
public class ComandBean {

    private List<Device> devicesList;
    private ClienteServidor clienteServidor;
    private boolean startPoll;
    private String comandValue;
    private String MACvalue;
    private TCP request;
    private int progress = 0;
    private int multiplo = 0;

    @PostConstruct
    public void init() {
        clienteServidor = ServerInstanceCliente.getInstance();

        devicesList = ClienteDAO.getInstance().getDevicesListAll(SessionContext.getInstance().getClienteSelecionado());

        //   t1.start();
        TCP reTcp = new TCP();
        reTcp.setMacConect(true);
        reTcp.setTypeclient(false);
        reTcp.setKeyID("ASDA5456RFGA5SGA5456SD4G");
        clienteServidor.enviarMensagemServidor(reTcp);

        request = new TCP();
        request.setIdSession(SessionContext.getInstance().getIDSession());
        ServerInstanceCliente.addIDMAP(request);

    }

    public List<Device> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Device> devicesList) {
        this.devicesList = devicesList;
    }

    public boolean isStartPoll() {
        return startPoll;
    }

    public void setStartPoll(boolean startPoll) {
        this.startPoll = startPoll;
    }

    public String getComandValue() {
        return comandValue;
    }

    public void setComandValue(String comandValue) {
        this.comandValue = comandValue;
    }

    public String getMACvalue() {
        return MACvalue;
    }

    public void setMACvalue(String MACvalue) {
        this.MACvalue = MACvalue;
    }

    public int getProgress() {
        return progress;
    }

    public void updateProgress() {

        if (!startPoll) {
            progress += multiplo;
        }
        if (request.getLines() != null) {
            if (!request.getLines().isEmpty()) {

                if (request.getLines().get(request.getLines().size() - 1).isStopLine() || progress > 1000) {
                    startPoll = true;
                    System.out.println("STOP");
                    progress = 100;
                }
            }

        }

    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public TCP getRequest() {

        this.request = ServerInstanceCliente.getInstanceMAP(request.getIdSession());
        return request;
    }

    public void executeARP() {

        progress = 0;
        multiplo = 10;

        reset();

        request.setArp(true);
        request.setComandLine("tracert " + comandValue);
        clienteServidor.enviarMensagemServidor(request);

    }

    public void executeScan() {

        progress = 0;
        multiplo = 1;

        reset();

        request.setScan(true);
        request.setComandLine("console.lnk /r:192.168.10.1-192.168.10.255");

        clienteServidor.enviarMensagemServidor(request);

    }

    public void executePing() {

        progress = 0;
        multiplo = 9;

        reset();

        request.setPing(true);
        request.setComandLine("ping " + comandValue + " -n 4");
        clienteServidor.enviarMensagemServidor(request);

    }

    private void reset() {

        startPoll = false;

        request.setKeyID(MACvalue);
        request.setConect(false);
        request.setComandReturn(false);
        request.setComandStop(false);
        request.setPing(false);
        request.setArp(false);
        request.setScan(false);
        request.setMensage("Executando comando...");
        request.setComandLine("");
        request.setComandSend(true);
        request.setTypeclient(false);
        request.setMacConect(false);
    }
}
