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
@Table(name = "rede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rede.findAll", query = "SELECT r FROM Rede r"),
    @NamedQuery(name = "Rede.findByIdrede", query = "SELECT r FROM Rede r WHERE r.idrede = :idrede"),
    @NamedQuery(name = "Rede.findByTipo", query = "SELECT r FROM Rede r WHERE r.tipo = :tipo"),
    @NamedQuery(name = "Rede.findByNome", query = "SELECT r FROM Rede r WHERE r.nome = :nome"),
    @NamedQuery(name = "Rede.findByIp", query = "SELECT r FROM Rede r WHERE r.ip = :ip"),
    @NamedQuery(name = "Rede.findByPortaWEB", query = "SELECT r FROM Rede r WHERE r.portaWEB = :portaWEB"),
    @NamedQuery(name = "Rede.findBySenha", query = "SELECT r FROM Rede r WHERE r.senha = :senha"),
    @NamedQuery(name = "Rede.findByModelo", query = "SELECT r FROM Rede r WHERE r.modelo = :modelo"),
    @NamedQuery(name = "Rede.findBySenhaPadrao", query = "SELECT r FROM Rede r WHERE r.senhaPadrao = :senhaPadrao"),
    @NamedQuery(name = "Rede.findByIpPadrao", query = "SELECT r FROM Rede r WHERE r.ipPadrao = :ipPadrao")})
public class Rede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrede")
    private Integer idrede;
    @Column(name = "tipo")
    private Integer tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 15)
    @Column(name = "IP")
    private String ip;
    @Size(max = 10)
    @Column(name = "portaWEB")
    private String portaWEB;
    @Size(max = 45)
    @Column(name = "senha")
    private String senha;
    @Size(max = 45)
    @Column(name = "modelo")
    private String modelo;
    @Size(max = 45)
    @Column(name = "senhaPadrao")
    private String senhaPadrao;
    @Size(max = 45)
    @Column(name = "ipPadrao")
    private String ipPadrao;
    @JoinColumn(name = "arquivo_FK", referencedColumnName = "idarquivo")
    @ManyToOne
    private Arquivo arquivoFK;
    @JoinColumn(name = "conta_FK", referencedColumnName = "conta")
    @ManyToOne(optional = false)
    private Cliente contaFK;
    @JoinColumn(name = "data_FK", referencedColumnName = "iddata")
    @ManyToOne
    private Data dataFK;
    @JoinColumn(name = "fabricante_FK", referencedColumnName = "idfabricante")
    @ManyToOne
    private Fabricante fabricanteFK;
    @JoinColumn(name = "local_FK", referencedColumnName = "idlocal")
    @ManyToOne
    private Local localFK;
    @JoinColumn(name = "usuario_FK", referencedColumnName = "idusuario")
    @ManyToOne
    private Usuario usuarioFK;

    public Rede() {
    }

    public Rede(Integer idrede) {
        this.idrede = idrede;
    }

    public Rede(Integer idrede, String nome) {
        this.idrede = idrede;
        this.nome = nome;
    }

    public Integer getIdrede() {
        return idrede;
    }

    public void setIdrede(Integer idrede) {
        this.idrede = idrede;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPortaWEB() {
        return portaWEB;
    }

    public void setPortaWEB(String portaWEB) {
        this.portaWEB = portaWEB;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    public Arquivo getArquivoFK() {
        return arquivoFK;
    }

    public void setArquivoFK(Arquivo arquivoFK) {
        this.arquivoFK = arquivoFK;
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

    public Local getLocalFK() {
        return localFK;
    }

    public void setLocalFK(Local localFK) {
        this.localFK = localFK;
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
        hash += (idrede != null ? idrede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rede)) {
            return false;
        }
        Rede other = (Rede) object;
        if ((this.idrede == null && other.idrede != null) || (this.idrede != null && !this.idrede.equals(other.idrede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.leandro.hagana.entidade.Rede[ idrede=" + idrede + " ]";
    }
    
}