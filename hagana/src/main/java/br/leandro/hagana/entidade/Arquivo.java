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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "arquivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arquivo.findAll", query = "SELECT a FROM Arquivo a"),
    @NamedQuery(name = "Arquivo.findByIdarquivo", query = "SELECT a FROM Arquivo a WHERE a.idarquivo = :idarquivo"),
    @NamedQuery(name = "Arquivo.findByNome", query = "SELECT a FROM Arquivo a WHERE a.nome = :nome"),
    @NamedQuery(name = "Arquivo.findByUrl", query = "SELECT a FROM Arquivo a WHERE a.url = :url"),
    @NamedQuery(name = "Arquivo.findByTipo", query = "SELECT a FROM Arquivo a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Arquivo.findByFileName", query = "SELECT a FROM Arquivo a WHERE a.fileName = :fileName"),
    @NamedQuery(name = "Arquivo.findByRead", query = "SELECT a FROM Arquivo a WHERE a.read = :read")})
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarquivo")
    private Integer idarquivo;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 45)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "read")
    private Boolean read;
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @OneToMany(mappedBy = "arquivoFK")
    private List<Rede> redeList;
    @JoinColumn(name = "usuario_FK", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuarioFK;

    public Arquivo() {
    }

    public Arquivo(Integer idarquivo) {
        this.idarquivo = idarquivo;
    }

    public Arquivo(Integer idarquivo, String tipo, String fileName) {
        this.idarquivo = idarquivo;
        this.tipo = tipo;
        this.fileName = fileName;
    }

    public Integer getIdarquivo() {
        return idarquivo;
    }

    public void setIdarquivo(Integer idarquivo) {
        this.idarquivo = idarquivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @XmlTransient
    public List<Rede> getRedeList() {
        return redeList;
    }

    public void setRedeList(List<Rede> redeList) {
        this.redeList = redeList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarquivo != null ? idarquivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arquivo)) {
            return false;
        }
        Arquivo other = (Arquivo) object;
        if ((this.idarquivo == null && other.idarquivo != null) || (this.idarquivo != null && !this.idarquivo.equals(other.idarquivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.leandro.hagana.entidade.Arquivo[ idarquivo=" + idarquivo + " ]";
    }
    
}
