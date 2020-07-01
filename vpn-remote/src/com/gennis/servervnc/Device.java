/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;
 
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author leand
 */
public class Device {

    private String nome;
    private Integer tipo; 
    private String ip;
    protected String ipAnterior;
    private String port_conect;
    private Date dataCriacao;
    private String login;
    private String senha;
    private String modelo;
    protected boolean dhcp;
    private boolean captureSenha; 
     

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
 
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpAnterior() {
        return ipAnterior;
    }

    public void setIpAnterior(String ipAnterior) {
        this.ipAnterior = ipAnterior;
    }

    public String getPort_conect() {
        return port_conect;
    }

    public void setPort_conect(String port_conect) {
        this.port_conect = port_conect;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public boolean isDhcp() {
        return dhcp;
    }

    public void setDhcp(boolean dhcp) {
        this.dhcp = dhcp;
    }

    public boolean isCaptureSenha() {
        return captureSenha;
    }

    public void setCaptureSenha(boolean captureSenha) {
        this.captureSenha = captureSenha;
    }
    
    
}
