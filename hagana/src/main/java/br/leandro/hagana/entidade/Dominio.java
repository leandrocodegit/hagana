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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leand
 */
@Entity
@Table(name = "dominio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dominio.findAll", query = "SELECT d FROM Dominio d"),
    @NamedQuery(name = "Dominio.findByIddominio", query = "SELECT d FROM Dominio d WHERE d.iddominio = :iddominio"),
    @NamedQuery(name = "Dominio.findByPorta", query = "SELECT d FROM Dominio d WHERE d.porta = :porta"),
    @NamedQuery(name = "Dominio.findByHost", query = "SELECT d FROM Dominio d WHERE d.host = :host"),
    @NamedQuery(name = "Dominio.findByOperadora", query = "SELECT d FROM Dominio d WHERE d.operadora = :operadora"),
    @NamedQuery(name = "Dominio.findByVelocidade", query = "SELECT d FROM Dominio d WHERE d.velocidade = :velocidade"),
    @NamedQuery(name = "Dominio.findByTipo", query = "SELECT d FROM Dominio d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "Dominio.findByUsuario", query = "SELECT d FROM Dominio d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "Dominio.findBySenha", query = "SELECT d FROM Dominio d WHERE d.senha = :senha")})
public class Dominio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddominio")
    private Integer iddominio;
    @Column(name = "porta")
    private Integer porta;
    @Size(max = 45)
    @Column(name = "host")
    private String host;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "operadora")
    private String operadora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "velocidade")
    private String velocidade;
    @Column(name = "tipo")
    private Integer tipo;
    @Size(max = 45)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 45)
    @Column(name = "senha")
    private String senha;
    @JoinColumn(name = "conta_FK", referencedColumnName = "conta")
    @ManyToOne
    private Cliente contaFK;
    @JoinColumn(name = "data_FK", referencedColumnName = "iddata")
    @ManyToOne
    private Data dataFK;
    @JoinColumn(name = "usuario_FK", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuarioFK;

    public Dominio() {
    }

    public Dominio(Integer iddominio) {
        this.iddominio = iddominio;
    }

    public Dominio(Integer iddominio, String operadora, String velocidade) {
        this.iddominio = iddominio;
        this.operadora = operadora;
        this.velocidade = velocidade;
    }

    public Integer getIddominio() {
        return iddominio;
    }

    public void setIddominio(Integer iddominio) {
        this.iddominio = iddominio;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

    public void setUsuarioFK(Usuario usuarioFK) {
        this.usuarioFK = usuarioFK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddominio != null ? iddominio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dominio)) {
            return false;
        }
        Dominio other = (Dominio) object;
        if ((this.iddominio == null && other.iddominio != null) || (this.iddominio != null && !this.iddominio.equals(other.iddominio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.leandro.hagana.entidade.Dominio[ iddominio=" + iddominio + " ]";
    }
    
}
