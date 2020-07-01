/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author leand
 */
public class TCP implements Serializable {

    private static final long serialVersionUID = 12345855655321L;

    private String keyID;
    private String mensage;     
    private boolean arp;
    private boolean ping;
    private boolean scan;
    private boolean close;
    private boolean conect;
    private boolean disconect;
    private boolean key;
    private boolean keepLive;
    private boolean keepLiveRequest;   
    private boolean send; 
    private boolean comandSend;
    private boolean comandReturn; 
    private List<LineComand> lines;

    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }

    public boolean isArp() {
        return arp;
    }

    public void setArp(boolean arp) {
        this.arp = arp;
    }

    public boolean isPing() {
        return ping;
    }

    public void setPing(boolean ping) {
        this.ping = ping;
    }

    public boolean isScan() {
        return scan;
    }

    public void setScan(boolean scan) {
        this.scan = scan;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public boolean isConect() {
        return conect;
    }

    public void setConect(boolean conect) {
        this.conect = conect;
    }

    public boolean isDisconect() {
        return disconect;
    }

    public void setDisconect(boolean disconect) {
        this.disconect = disconect;
    }

    public boolean isKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public boolean isKeepLive() {
        return keepLive;
    }

    public void setKeepLive(boolean keepLive) {
        this.keepLive = keepLive;
    }

    public boolean isKeepLiveRequest() {
        return keepLiveRequest;
    }

    public void setKeepLiveRequest(boolean keepLiveRequest) {
        this.keepLiveRequest = keepLiveRequest;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public boolean isComandSend() {
        return comandSend;
    }

    public void setComandSend(boolean comandSend) {
        this.comandSend = comandSend;
    }

    public boolean isComandReturn() {
        return comandReturn;
    }

    public void setComandReturn(boolean comandReturn) {
        this.comandReturn = comandReturn;
    }

    public List<LineComand> getLines() {
        return lines;
    }

    public void setLines(List<LineComand> lines) {
        this.lines = lines;
    }
    
    

    
}
