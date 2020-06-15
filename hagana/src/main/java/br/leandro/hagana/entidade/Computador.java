/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.entidade;

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
@Table(name = "computador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Computador.findAll", query = "SELECT c FROM Computador c"),
    @NamedQuery(name = "Computador.findByIdcomputador", query = "SELECT c FROM Computador c WHERE c.idcomputador = :idcomputador"),
    @NamedQuery(name = "Computador.findByNome", query = "SELECT c FROM Computador c WHERE c.nome = :nome"),
    @NamedQuery(name = "Computador.findByIos", query = "SELECT c FROM Computador c WHERE c.ios = :ios"),
    @NamedQuery(name = "Computador.findByIp", query = "SELECT c FROM Computador c WHERE c.ip = :ip"),
    @NamedQuery(name = "Computador.findByTipoIP", query = "SELECT c FROM Computador c WHERE c.tipoIP = :tipoIP"),
    @NamedQuery(name = "Computador.findByTv", query = "SELECT c FROM Computador c WHERE c.tv = :tv"),
    @NamedQuery(name = "Computador.findByAn", query = "SELECT c FROM Computador c WHERE c.an = :an"),
    @NamedQuery(name = "Computador.findBySenhaADM", query = "SELECT c FROM Computador c WHERE c.senhaADM = :senhaADM"),
    @NamedQuery(name = "Computador.findBySenhaRMT", query = "SELECT c FROM Computador c WHERE c.senhaRMT = :senhaRMT")})
public class Computador extends Device implements Serializable {

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
    @Column(name = "tipo")
    private Integer tipo;
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @Size(max = 45)
    @Column(name = "senhaRMT")
    private String senhaRMT;
    @JoinColumn(name = "cliente_FK", referencedColumnName = "conta")
    @ManyToOne
    private Cliente clienteFK;
    @JoinColumn(name = "usuario_FK", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuarioFK;
    @JoinColumn(name = "local_FK", referencedColumnName = "idlocal")
    @ManyToOne
    private Local localFK;

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

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIos() {
        return ios;
    }

    public void setIos(Integer ios) {
        this.ios = ios;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getTipoIP() {
        return tipoIP;
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

    @Override
    public String getPort_conect() {
        return port_conect;
    }

    @Override
    public void setPort_conect(String port_conect) {
        this.port_conect = port_conect;
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
    public Integer getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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
        return idcomputador + "C";
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
        hash += (idcomputador != null ? idcomputador.hashCode() : 0);
        return hash;
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
