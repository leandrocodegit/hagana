/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Dispositivo.findByIddispositivo", query = "SELECT d FROM Dispositivo d WHERE d.iddispositivo = :iddispositivo"),
    @NamedQuery(name = "Dispositivo.findByTipo", query = "SELECT d FROM Dispositivo d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "Dispositivo.findByIp", query = "SELECT d FROM Dispositivo d WHERE d.ip = :ip"),
    @NamedQuery(name = "Dispositivo.findByNome", query = "SELECT d FROM Dispositivo d WHERE d.nome = :nome"),
    @NamedQuery(name = "Dispositivo.findByPortaTCP", query = "SELECT d FROM Dispositivo d WHERE d.portaTCP = :portaTCP"),
    @NamedQuery(name = "Dispositivo.findByPortaWEB", query = "SELECT d FROM Dispositivo d WHERE d.portaWEB = :portaWEB"),
    @NamedQuery(name = "Dispositivo.findByOutrasPortas", query = "SELECT d FROM Dispositivo d WHERE d.outrasPortas = :outrasPortas"),
    @NamedQuery(name = "Dispositivo.findByModelo", query = "SELECT d FROM Dispositivo d WHERE d.modelo = :modelo"),
    @NamedQuery(name = "Dispositivo.findBySenha", query = "SELECT d FROM Dispositivo d WHERE d.senha = :senha"),
    @NamedQuery(name = "Dispositivo.findBySenhaPadrao", query = "SELECT d FROM Dispositivo d WHERE d.senhaPadrao = :senhaPadrao"),
    @NamedQuery(name = "Dispositivo.findByIpPadrao", query = "SELECT d FROM Dispositivo d WHERE d.ipPadrao = :ipPadrao")})
public class Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddispositivo")
    private Integer iddispositivo;
    @Size(max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Size(max = 45)
    @Column(name = "IP")
    private String ip;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 8)
    @Column(name = "portaTCP")
    private String portaTCP;
    @Size(max = 8)
    @Column(name = "portaWEB")
    private String portaWEB;
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
    @Column(name = "senhaPadrao")
    private String senhaPadrao;
    @Size(max = 45)
    @Column(name = "ipPadrao")
    private String ipPadrao;
    @JoinColumn(name = "conta_FK", referencedColumnName = "conta")
    @ManyToOne(optional = false)
    private Cliente contaFK;
    @JoinColumn(name = "data_FK", referencedColumnName = "iddata")
    @ManyToOne
    private Data dataFK;
    @JoinColumn(name = "fabricante_FK", referencedColumnName = "idfabricante")
    @ManyToOne
    private Fabricante fabricanteFK;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPortaTCP() {
        return portaTCP;
    }

    public void setPortaTCP(String portaTCP) {
        this.portaTCP = portaTCP;
    }

    public String getPortaWEB() {
        return portaWEB;
    }

    public void setPortaWEB(String portaWEB) {
        this.portaWEB = portaWEB;
    }

    public String getOutrasPortas() {
        return outrasPortas;
    }

    public void setOutrasPortas(String outrasPortas) {
        this.outrasPortas = outrasPortas;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaPadrao() {
        return senhaPadrao;
    }

    public void setSenhaPadrao(String senhaPadrao) {
        this.senhaPadrao = senhaPadrao;
    }

    public String getIpPadrao() {
        return ipPadrao;
    }

    public void setIpPadrao(String ipPadrao) {
        this.ipPadrao = ipPadrao;
    }

    public Cliente getContaFK() {
        return contaFK;
    }

    public void setContaFK(Cliente contaFK) {
        this.contaFK = contaFK;
    }

    public Data getDataFK() {
        return dataFK;
    }

    public void setDataFK(Data dataFK) {
        this.dataFK = dataFK;
    }

    public Fabricante getFabricanteFK() {
        return fabricanteFK;
    }

    public void setFabricanteFK(Fabricante fabricanteFK) {
        this.fabricanteFK = fabricanteFK;
    }

    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

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
    public String toString() {
        return "br.leandro.hagana.entidade.Dispositivo[ iddispositivo=" + iddispositivo + " ]";
    }
    
}
