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
    @NamedQuery(name = "Link.findByHost", query = "SELECT d FROM Link d WHERE d.ip = :ip"),
    @NamedQuery(name = "Link.findByOperadora", query = "SELECT d FROM Link d WHERE d.operadora = :operadora"),
    @NamedQuery(name = "Link.findByVelocidade", query = "SELECT d FROM Link d WHERE d.velocidade = :velocidade"),
    @NamedQuery(name = "Link.findByTipo", query = "SELECT d FROM Link d WHERE d.tipoRede = :tipoRede"),
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
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "operadora")
    private Integer operadora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "velocidade")
    private Integer velocidade;
    @Column(name = "tipoRede")
    private Integer tipoRede;
    @Size(max = 45)
    @Column(name = "login")
    private String login;
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

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getOperadora() {
        return operadora;
    }

    public void setOperadora(Integer operadora) {
        this.operadora = operadora;
    }

    public Integer getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Integer velocidade) {
        this.velocidade = velocidade;
    }

    @Override
    public Integer getTipo() {
        return 0;
    }

    public Integer getTipoRede() {
        return tipoRede;
    }

    public void setTipoRede(Integer tipoRede) {
        this.tipoRede = tipoRede;
    }
    

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
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
        return toLink();
    }
 
    @Override
    public String getPort_conect() {
        return "Provedor";
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
    public String getDataFormat() {
        return Data.formatDateddMMYYYYhhmm(dataCriacao);
    }
 
    public String toLink() {

        String name = "";

        switch (operadora) {
            case 1:
                name = "Vivo";
                break;
            case 2:
                name = "NET";
                break;
            case 3:
                name = "Tim Live";
                break;
            case 4:
                name = "Claro";
                break;
            case 5:
                name = "Vogel";
                break;
                            case 6:
                name = "WCS";
                break;
                            case 7:
                name = "Telium";
                break;
                            case 8:
                name = "Outra";
                break;
        }
                      
        return name ;
    }
    
     @Override
    public String toString() {
        return "Link";
    }

}
