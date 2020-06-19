/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.entidade;

import br.leandro.hagana.util.Data;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leand
 */
@Entity
@Table(name = "dispositivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dispositivo.findAll", query = "SELECT d FROM Dispositivo d"),
    @NamedQuery(name = "Cliente.findPesquisa", query = "SELECT d FROM Dispositivo d WHERE d.nome LIKE :nome OR d.modelo LIKE :modelo OR d.ip LIKE :ip"),
    @NamedQuery(name = "Dispositivo.findByIddispositivo", query = "SELECT d FROM Dispositivo d WHERE d.iddispositivo = :iddispositivo"),
    @NamedQuery(name = "Dispositivo.findByTipo", query = "SELECT d FROM Dispositivo d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "Dispositivo.findByIp", query = "SELECT d FROM Dispositivo d WHERE d.ip = :ip"),
    @NamedQuery(name = "Dispositivo.findByNome", query = "SELECT d FROM Dispositivo d WHERE d.nome = :nome"),
    @NamedQuery(name = "Dispositivo.findByPortaTCP", query = "SELECT d FROM Dispositivo d WHERE d.portaTCP = :portaTCP"),
    @NamedQuery(name = "Dispositivo.findByPortaWEB", query = "SELECT d FROM Dispositivo d WHERE d.portaWEB = :portaWEB"),
    @NamedQuery(name = "Dispositivo.findByOutrasPortas", query = "SELECT d FROM Dispositivo d WHERE d.outrasPortas = :outrasPortas"),
    @NamedQuery(name = "Dispositivo.findByModelo", query = "SELECT d FROM Dispositivo d WHERE d.modelo = :modelo"),
    @NamedQuery(name = "Dispositivo.findBySenha", query = "SELECT d FROM Dispositivo d WHERE d.senha = :senha")})
public class Dispositivo extends Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddispositivo")
    private Integer iddispositivo;
    @Size(max = 45)
    @Column(name = "tipo")
    private Integer tipo;
    @Size(max = 45)
    @Column(name = "IP")
    private String ip;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 8)
    @Column(name = "portaTCP")
    private Integer portaTCP;
    @Size(max = 8)
    @Column(name = "portaWEB")
    private Integer portaWEB;
    @Size(max = 45)
    @Column(name = "outrasPortas")
    private String outrasPortas;
    @Size(max = 45)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 45)
    @Column(name = "senha")
    private String senha;
    @Size(max = 45)
    @Column(name = "login")
    private String login;
    @Column(name = "port_conect")
    private String port_conect;
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @JoinColumn(name = "cliente_FK", referencedColumnName = "conta")
    @ManyToOne(optional = false)
    private Cliente clienteFK;
    @JoinColumn(name = "fabricante_FK", referencedColumnName = "idfabricante")
    @ManyToOne
    private Fabricante fabricanteFK;
    @JoinColumn(name = "local_FK", referencedColumnName = "idlocal")
    @ManyToOne
    private Local localFK;
    @JoinColumn(name = "usuario_FK", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuarioFK;

    public Dispositivo() {
    }

    public Dispositivo(Integer iddispositivo) {
        this.iddispositivo = iddispositivo;
    }

    public Integer getIddispositivo() {
        return iddispositivo;
    }

    public void setIddispositivo(Integer iddispositivo) {
        this.iddispositivo = iddispositivo;
    }

    @Override
    public Integer getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPortaTCP() {
        return portaTCP;
    }

    public void setPortaTCP(Integer portaTCP) {
        this.portaTCP = portaTCP;
    }

    public Integer getPortaWEB() {
        return portaWEB;
    }

    public void setPortaWEB(Integer portaWEB) {
        this.portaWEB = portaWEB;
    }

    public String getOutrasPortas() {
        return outrasPortas;
    }

    public void setOutrasPortas(String outrasPortas) {
        this.outrasPortas = outrasPortas;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPort_conect() {
        return port_conect;
    }

    @Override
    public void setPort_conect(String port_conect) {
        this.port_conect = port_conect;
    }

    @Override
    public Date getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public String getPortaUPLink() {
        return iddispositivo + "D";
    }

    @Override
    public Cliente getClienteFK() {
        return clienteFK;
    }

    @Override
    public void setClienteFK(Cliente clienteFK) {
        this.clienteFK = clienteFK;
    }

    @Override
    public Fabricante getFabricanteFK() {
        return fabricanteFK;
    }

    @Override
    public void setFabricanteFK(Fabricante fabricanteFK) {
        this.fabricanteFK = fabricanteFK;
    }

    @Override
    public Local getLocalFK() {
        return localFK;
    }

    @Override
    public void setLocalFK(Local localFK) {
        this.localFK = localFK;
    }

    @Override
    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

    @Override
    public void setUsuarioFK(Usuario usuarioFK) {
        this.usuarioFK = usuarioFK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddispositivo != null ? iddispositivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dispositivo)) {
            return false;
        }
        Dispositivo other = (Dispositivo) object;
        if ((this.iddispositivo == null && other.iddispositivo != null) || (this.iddispositivo != null && !this.iddispositivo.equals(other.iddispositivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String getDataFormat() {
        return Data.formatDateddMMYYYYhhmm(dataCriacao);
    }

    @Override
    public void createDHCP() {

        if (dhcp) {
            ipAnterior = ip;
            ip = "DHCP";
        } else {
            ip = ipAnterior;
        }
    }
 
    @Override
    public String toString() {
        return "Dispositivo";
    }

}
