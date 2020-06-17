/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.entidade;

import br.leandro.hagana.util.Data;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author leand
 */
public class Device implements DeviceImp {

    private String nome;
    private Integer tipo;
    private Local localFK;
    private String ip;
    private String port_conect;
    private Date dataCriacao;
    private String dataFormat;
    private String login;
    private String senha;
    private String modelo;
    boolean dhcp;
    private String portaUPLink;
    private Usuario usuarioFK;
    private Fabricante fabricanteFK;
    private Cliente clienteFK;

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

    public String getDataFormat() {
        return Data.getDateAtualBrasil();
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
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

    public String getPortaUPLink() {
        return portaUPLink;
    }

    public void setPortaUPLink(String portaUPLink) {
        this.portaUPLink = portaUPLink;
    }

    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

    public void setUsuarioFK(Usuario usuarioFK) {
        this.usuarioFK = usuarioFK;
    }

    public Fabricante getFabricanteFK() {
        return fabricanteFK;
    }

    public void setFabricanteFK(Fabricante fabricanteFK) {
        this.fabricanteFK = fabricanteFK;
    }

    public Cliente getClienteFK() {
        return clienteFK;
    }

    public void setClienteFK(Cliente clienteFK) {
        this.clienteFK = clienteFK;
    }

    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer setId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
