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
public class Rede extends Device implements Serializable {

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
    private Integer portaWEB;
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
    @Column(name = "port_conect")
    private String port_conect;
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    @JoinColumn(name = "arquivo_FK", referencedColumnName = "idarquivo")
    @ManyToOne
    private Arquivo arquivoFK;
    @JoinColumn(name = "cliente_FK", referencedColumnName = "conta")
    @ManyToOne(optional = false)
    private Cliente clienteFK;
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

    @Override
    public Integer getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPortaWEB() {
        return portaWEB;
    }

    public void setPortaWEB(Integer portaWEB) {
        this.portaWEB = portaWEB;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
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

    @Override
    public Date getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    @Override
    public String getPort_conect() {
        return port_conect;
    }

    @Override
    public void setPort_conect(String port_conect) {
        this.port_conect = port_conect;
    }

    public Arquivo getArquivoFK() {
        return arquivoFK;
    }

    public void setArquivoFK(Arquivo arquivoFK) {
        this.arquivoFK = arquivoFK;
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
    public Fabricante getFabricanteFK() {
        return fabricanteFK;
    }

    @Override
    public void setFabricanteFK(Fabricante fabricanteFK) {
        this.fabricanteFK = fabricanteFK;
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
    public Usuario getUsuarioFK() {
        return usuarioFK;
    }

    @Override
    public void setUsuarioFK(Usuario usuarioFK) {
        this.usuarioFK = usuarioFK;
    }

    @Override
    public String getPortaUPLink() {
        return idrede + "R";
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
