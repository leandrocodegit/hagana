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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leand
 */
@Entity
@Table(name = "computador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Computador.findAll", query = "SELECT c FROM Computador c"),
    @NamedQuery(name = "Computador.findByIdcomputador", query = "SELECT c FROM Computador c WHERE c.idcomputador = :idcomputador"),
    @NamedQuery(name = "Computador.findByNome", query = "SELECT c FROM Computador c WHERE c.nome = :nome"),
    @NamedQuery(name = "Computador.findByIos", query = "SELECT c FROM Computador c WHERE c.ios = :ios"),
    @NamedQuery(name = "Computador.findByLocal", query = "SELECT c FROM Computador c WHERE c.local = :local"),
    @NamedQuery(name = "Computador.findByIp", query = "SELECT c FROM Computador c WHERE c.ip = :ip"),
    @NamedQuery(name = "Computador.findByTipoIP", query = "SELECT c FROM Computador c WHERE c.tipoIP = :tipoIP"),
    @NamedQuery(name = "Computador.findByTv", query = "SELECT c FROM Computador c WHERE c.tv = :tv"),
    @NamedQuery(name = "Computador.findByAn", query = "SELECT c FROM Computador c WHERE c.an = :an"),
    @NamedQuery(name = "Computador.findBySenhaADM", query = "SELECT c FROM Computador c WHERE c.senhaADM = :senhaADM"),
    @NamedQuery(name = "Computador.findBySenhaRMT", query = "SELECT c FROM Computador c WHERE c.senhaRMT = :senhaRMT")})
public class Computador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomputador")
    private Integer idcomputador;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Column(name = "IOS")
    private Integer ios;
    @Size(max = 45)
    @Column(name = "local")
    private String local;
    @Size(max = 40)
    @Column(name = "IP")
    private String ip;
    @Column(name = "tipoIP")
    private Integer tipoIP;
    @Size(max = 15)
    @Column(name = "tv")
    private String tv;
    @Size(max = 15)
    @Column(name = "an")
    private String an;
    @Size(max = 45)
    @Column(name = "senhaADM")
    private String senhaADM;
    @Column(name = "port_conect")
    private String port_conect;
    @Size(max = 45)
    @Column(name = "senhaRMT")
    private String senhaRMT;
    @JoinColumn(name = "cliente_FK", referencedColumnName = "conta")
    @ManyToOne
    private Cliente clienteFK;
    @JoinColumn(name = "data_FK", referencedColumnName = "iddata")
    @ManyToOne
    private Data dataFK;
    @JoinColumn(name = "usuario_FK", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuarioFK;

    public Computador() {
    }

    public Computador(Integer idcomputador) {
        this.idcomputador = idcomputador;
    }

    public Integer getIdcomputador() {
        return idcomputador;
    }

    public void setIdcomputador(Integer idcomputador) {
        this.idcomputador = idcomputador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIos() {
        return ios;
    }

    public void setIos(Integer ios) {
        this.ios = ios;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getTipoIP() {
        return tipoIP;
    }

    public void setTipoIP(Integer tipoIP) {
        this.tipoIP = tipoIP;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getSenhaADM() {
        return senhaADM;
    }

    public void setSenhaADM(String senhaADM) {
        this.senhaADM = senhaADM;
    }

    public String getSenhaRMT() {
        return senhaRMT;
    }

    public void setSenhaRMT(String senhaRMT) {
        this.senhaRMT = senhaRMT;
    }

    public String getPort_conect() {
        return port_conect;
    }

    public void setPort_conect(String port_conect) {
        this.port_conect = port_conect;
    }

    public Cliente getClienteFK() {
        return clienteFK;
    }

    public void setClienteFK(Cliente clienteFK) {
        this.clienteFK = clienteFK;
    }

    public Data getDataFK() {
        return dataFK;
    }

    public void setDataFK(Data dataFK) {
        this.dataFK = dataFK;
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
        hash += (idcomputador != null ? idcomputador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Computador)) {
            return false;
        }
        Computador other = (Computador) object;
        if ((this.idcomputador == null && other.idcomputador != null) || (this.idcomputador != null && !this.idcomputador.equals(other.idcomputador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.leandro.hagana.entidade.Computador[ idcomputador=" + idcomputador + " ]";
    }

}
