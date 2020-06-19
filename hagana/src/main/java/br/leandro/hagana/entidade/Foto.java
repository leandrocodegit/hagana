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
@Table(name = "foto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foto.findAll", query = "SELECT f FROM Foto f"),
    @NamedQuery(name = "Foto.findByIdfoto", query = "SELECT f FROM Foto f WHERE f.idfoto = :idfoto"),
    @NamedQuery(name = "Foto.findByNome", query = "SELECT f FROM Foto f WHERE f.nome = :nome")})
public class Foto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfoto")
    private Integer idfoto;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @JoinColumn(name = "cliente_FK", referencedColumnName = "conta")
    @ManyToOne(optional = false)
    private Cliente clienteFK;
    @JoinColumn(name = "usuario_FK", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuarioFK;

    public Foto() {
    }

    public Foto(Integer idfoto) {
        this.idfoto = idfoto;
    }

    public Integer getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(Integer idfoto) {
        this.idfoto = idfoto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
 
    public Cliente getClienteFK() {
        return clienteFK;
    }
 
    public void setClienteFK(Cliente clienteFK) {
        this.clienteFK = clienteFK;
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
        hash += (idfoto != null ? idfoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foto)) {
            return false;
        }
        Foto other = (Foto) object;
        if ((this.idfoto == null && other.idfoto != null) || (this.idfoto != null && !this.idfoto.equals(other.idfoto))) {
            return false;
        }
        return true;
    }
     
    public String getDataFormat() {
        return Data.formatDateddMMYYYYhhmm(dataCriacao);
    }

    @Override
    public String toString() {
        return "Foto";
    }

}
