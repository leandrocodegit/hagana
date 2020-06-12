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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leand
 */
@Entity
@Table(name = "data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Data.findAll", query = "SELECT d FROM Data d"),
    @NamedQuery(name = "Data.findByIddata", query = "SELECT d FROM Data d WHERE d.iddata = :iddata"),
    @NamedQuery(name = "Data.findByData", query = "SELECT d FROM Data d WHERE d.data = :data")})
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddata")
    private Integer iddata;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @OneToMany(mappedBy = "dataFK")
    private List<Computador> computadorList;
    @OneToMany(mappedBy = "dataFK")
    private List<Rede> redeList;
    @OneToMany(mappedBy = "dataFK")
    private List<Dispositivo> dispositivoList;
    @OneToMany(mappedBy = "dataFK")
    private List<Foto> fotoList;
    @OneToMany(mappedBy = "dataFK")
    private List<Link> dominioList;
    @OneToMany(mappedBy = "dataFK")
    private List<Arquivo> arquivoList;

    public Data() {
    }

    public Data(Integer iddata) {
        this.iddata = iddata;
    }

    public Data(Integer iddata, Date data) {
        this.iddata = iddata;
        this.data = data;
    }

    public Integer getIddata() {
        return iddata;
    }

    public void setIddata(Integer iddata) {
        this.iddata = iddata;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @XmlTransient
    public List<Computador> getComputadorList() {
        return computadorList;
    }

    public void setComputadorList(List<Computador> computadorList) {
        this.computadorList = computadorList;
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
    public List<Link> getDominioList() {
        return dominioList;
    }

    public void setDominioList(List<Link> dominioList) {
        this.dominioList = dominioList;
    }

    @XmlTransient
    public List<Arquivo> getArquivoList() {
        return arquivoList;
    }

    public void setArquivoList(List<Arquivo> arquivoList) {
        this.arquivoList = arquivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddata != null ? iddata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        if ((this.iddata == null && other.iddata != null) || (this.iddata != null && !this.iddata.equals(other.iddata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.leandro.hagana.entidade.Data[ iddata=" + iddata + " ]";
    }
    
}
