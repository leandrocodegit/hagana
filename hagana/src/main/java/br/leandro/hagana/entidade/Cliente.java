/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leand
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByConta", query = "SELECT c FROM Cliente c WHERE c.conta = :conta"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome LIKE :nome OR c.conta LIKE :conta"),
    @NamedQuery(name = "Cliente.findByEndereco", query = "SELECT c FROM Cliente c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "Cliente.findByDataCriacao", query = "SELECT c FROM Cliente c WHERE c.dataCriacao = :dataCriacao")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "conta")
    private String conta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 45)
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @OneToMany(mappedBy = "clienteFK")
    private List<Computador> computadorList;
    @JoinColumn(name = "usuarioFK", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuarioFK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaFK")
    private List<Rede> redeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaFK")
    private List<Dispositivo> dispositivoList;
    @OneToMany(mappedBy = "contaFK")
    private List<Foto> fotoList;
    @OneToMany(mappedBy = "contaFK")
    private List<Dominio> dominioList;
    @OneToMany(mappedBy = "contaFK")
    private List<Local> localList;

    public Cliente() {
    }

    public Cliente(String conta) {
        this.conta = conta;
    }

    public Cliente(String conta, String nome) {
        this.conta = conta;
        this.nome = nome;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @XmlTransient
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
    }

    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

    public void setUsuarioFK(Usuario usuarioFK) {
        this.usuarioFK = usuarioFK;
    }

    @XmlTransient
    public List<Rede> getRedeList() {
        return redeList;
    }

    public void setRedeList(List<Rede> redeList) {
        this.redeList = redeList;
    }

    @XmlTransient
    public List<Dispositivo> getDispositivoList() {
        return dispositivoList;
    }

    public void setDispositivoList(List<Dispositivo> dispositivoList) {
        this.dispositivoList = dispositivoList;
    }

    @XmlTransient
    public List<Foto> getFotoList() {
        return fotoList;
    }

    public void setFotoList(List<Foto> fotoList) {
        this.fotoList = fotoList;
    }

    @XmlTransient
    public List<Dominio> getDominioList() {
        return dominioList;
    }

    public void setDominioList(List<Dominio> dominioList) {
        this.dominioList = dominioList;
    }

    @XmlTransient
    public List<Local> getLocalList() {
        return localList;
    }

    public void setLocalList(List<Local> localList) {
        this.localList = localList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conta != null ? conta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.conta == null && other.conta != null) || (this.conta != null && !this.conta.equals(other.conta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return conta + " - " + nome;
    }
    
}
