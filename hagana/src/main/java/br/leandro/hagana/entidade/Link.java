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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leand
 */
@Entity
@Table(name = "link")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Link.findAll", query = "SELECT d FROM Link d"),
    @NamedQuery(name = "Link.findByIdlink", query = "SELECT d FROM Link d WHERE d.idlink = :idlink"),
    @NamedQuery(name = "Link.findByPorta", query = "SELECT d FROM Link d WHERE d.porta = :porta"),
    @NamedQuery(name = "Link.findByHost", query = "SELECT d FROM Link d WHERE d.host = :host"),
    @NamedQuery(name = "Link.findByOperadora", query = "SELECT d FROM Link d WHERE d.operadora = :operadora"),
    @NamedQuery(name = "Link.findByVelocidade", query = "SELECT d FROM Link d WHERE d.velocidade = :velocidade"),
    @NamedQuery(name = "Link.findByTipo", query = "SELECT d FROM Link d WHERE d.tipo = :tipo"),
    @NamedQuery(name = "Link.findByUsuario", query = "SELECT d FROM Link d WHERE d.usuario = :usuario"),
    @NamedQuery(name = "Link.findBySenha", query = "SELECT d FROM Link d WHERE d.senha = :senha")})
public class Link extends Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlink")
    private Integer idlink;
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
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @JoinColumn(name = "cliente_FK", referencedColumnName = "conta")
    @ManyToOne(optional = false)
    private Cliente clienteFK;
    @JoinColumn(name = "usuario_FK", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuarioFK;
    @JoinColumn(name = "local_FK", referencedColumnName = "idlocal")
    @ManyToOne
    private Local localFK;

    public Link() {
    }

    public Link(Integer idlink) {
        this.idlink = idlink;
    }

    public Link(Integer idlink, String operadora, String velocidade) {
        this.idlink = idlink;
        this.operadora = operadora;
        this.velocidade = velocidade;
    }

    public Integer getIdlink() {
        return idlink;
    }

    public void setIdlink(Integer idlink) {
        this.idlink = idlink;
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

    @Override
    public Integer getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public Cliente getClienteFK() {
        return clienteFK;
    }

    @Override
    public void setClienteFK(Cliente clienteFK) {
        this.clienteFK = clienteFK;
    }
    public void setContaFK(Cliente clienteFK) {
        this.clienteFK = clienteFK;
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
    public Date getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
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
    public String getPortaUPLink() {

        return idlink + "L";
    }

    @Override
    public String getNome() {
        return operadora;
    }

    @Override
    public String getIp() {
        return host;
    }

    @Override
    public String getPort_conect() {
        return velocidade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlink != null ? idlink.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Link)) {
            return false;
        }
        Link other = (Link) object;
        if ((this.idlink == null && other.idlink != null) || (this.idlink != null && !this.idlink.equals(other.idlink))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.leandro.hagana.entidade.Dominio[ iddominio=" + idlink + " ]";
    }

}
