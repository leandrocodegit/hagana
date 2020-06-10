/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Local;
import br.leandro.hagana.entidade.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author leand
 */
public class UsuarioDao {
    
        private static UsuarioDao instance;
    protected EntityManager entityManager;

    public static UsuarioDao getInstance() {
        if (instance == null) {
            instance = new UsuarioDao();
        }

        return instance;
    }

    private UsuarioDao() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("com.gennis_hagana_war_PU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }
 
    public Usuario findUser(Usuario usuario) {
        Query query = entityManager.createNamedQuery("Usuario.findByUser");
        query.setParameter("user", usuario.getUser());
        query.setParameter("password", usuario.getPassword());
        Usuario user = (Usuario) query.getSingleResult();
        return user;
    }
    
    public static void main(String[] args) {
        
        UsuarioDao usuarioDao = new UsuarioDao();
        Usuario usuario = new Usuario();
        usuario.setUser("1");
        usuario.setPassword("1");
        
        System.out.println(usuarioDao.findUser(usuario).getNome());
        
        
    }
    
}
