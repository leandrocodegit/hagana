/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author leand
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdusuario", query = "SELECT u FROM Usuario u WHERE u.idusuario = :idusuario"),
    @NamedQuery(name = "Usuario.findByUser", query = "SELECT u FROM Usuario u WHERE (u.idusuario = :idusuario) AND (u.password = :password)"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findByPerfil", query = "SELECT u FROM Usuario u WHERE u.perfil = :perfil"),
    @NamedQuery(name = "Usuario.findByVerSenhas", query = "SELECT u FROM Usuario u WHERE u.verSenhas = :verSenhas"),
    @NamedQuery(name = "Usuario.findByAdicionar", query = "SELECT u FROM Usuario u WHERE u.adicionar = :adicionar"),
    @NamedQuery(name = "Usuario.findByGerenciarItens", query = "SELECT u FROM Usuario u WHERE u.gerenciarItens = :gerenciarItens"),
    @NamedQuery(name = "Usuario.findByGerenciarUsuario", query = "SELECT u FROM Usuario u WHERE u.gerenciarUsuario = :gerenciarUsuario"),
    @NamedQuery(name = "Usuario.findByEnviarArquivos", query = "SELECT u FROM Usuario u WHERE u.enviarArquivos = :enviarArquivos"),
    @NamedQuery(name = "Usuario.findByBloqueado", query = "SELECT u FROM Usuario u WHERE u.bloqueado = :bloqueado"),
    @NamedQuery(name = "Usuario.findByGerenciarCliente", query = "SELECT u FROM Usuario u WHERE u.gerenciarCliente = :gerenciarCliente")})
public class Usuario implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioFK")
    private List<Cliente> clienteList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private String idusuario; 
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Column(name = "perfil")
    private Integer perfil;
    @Column(name = "verSenhas")
    private Boolean verSenhas;
    @Column(name = "adicionar")
    private Boolean adicionar;
    @Column(name = "gerenciarItens")
    private Boolean gerenciarItens;
    @Column(name = "gerenciarUsuario")
    private Boolean gerenciarUsuario;
    @Column(name = "enviarArquivos")
    private Boolean enviarArquivos;
    @Column(name = "bloqueado")
    private Boolean bloqueado;
    @Column(name = "gerenciarCliente")
    private Boolean gerenciarCliente;
    @OneToMany(mappedBy = "usuarioFK")
    private List<Computador> computadorList;
    @OneToMany(mappedBy = "usuarioFK")
    private List<Rede> redeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioFK")
    private List<Dispositivo> dispositivoList;
    @OneToMany(mappedBy = "usuarioFK")
    private List<Foto> fotoList;
    @OneToMany(mappedBy = "usuarioFK")
    private List<Link> linkList;
    @OneToMany(mappedBy = "usuarioFK")
    private List<Arquivo> arquivoList;

    public Usuario() {
    }
    
    
    
 
    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }
 
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPerfil() {
        return perfil;
    }

    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    public Boolean getVerSenhas() {
        return verSenhas;
    }

    public void setVerSenhas(Boolean verSenhas) {
        this.verSenhas = verSenhas;
    }

    public Boolean getAdicionar() {
        return adicionar;
    }

    public void setAdicionar(Boolean adicionar) {
        this.adicionar = adicionar;
    }

    public Boolean getGerenciarItens() {
        return gerenciarItens;
    }

    public void setGerenciarItens(Boolean gerenciarItens) {
        this.gerenciarItens = gerenciarItens;
    }

    public Boolean getGerenciarUsuario() {
        return gerenciarUsuario;
    }

    public void setGerenciarUsuario(Boolean gerenciarUsuario) {
        this.gerenciarUsuario = gerenciarUsuario;
    }

    public Boolean getEnviarArquivos() {
        return enviarArquivos;
    }

    public void setEnviarArquivos(Boolean enviarArquivos) {
        this.enviarArquivos = enviarArquivos;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Boolean getGerenciarCliente() {
        return gerenciarCliente;
    }

    public void setGerenciarCliente(Boolean gerenciarCliente) {
        this.gerenciarCliente = gerenciarCliente;
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
    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
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
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario";
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }
    
}
