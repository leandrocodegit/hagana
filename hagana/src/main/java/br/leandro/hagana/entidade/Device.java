/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.entidade;

import java.util.Date;

/**
 *
 * @author leand
 */
public class Device {
    
    private String nome;
    private Integer tipo;
    private Local localFK;
    private String ip;
    private String port_conect;
    private Date dataCriacao;
    private Usuario usuarioFK;

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

    public Local getLocalFK() {
        return localFK;
    }

    public void setLocalFK(Local localFK) {
        this.localFK = localFK;
    }
 
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

    public void setUsuarioFK(Usuario usuarioFK) {
        this.usuarioFK = usuarioFK;
    }

     
}
