/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Dispositivo;
import br.leandro.hagana.entidade.Usuario;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author leand
 */
public class DAO {
 
private static DAO instance;
    protected EntityManager entityManager;

    public static DAO getInstance() {
        if (instance == null) {
            instance = new DAO();
        }

        return instance;
    }

    private DAO() {
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
 
   synchronized public Object insert(Object device) { 
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(device);

            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
            return null;
            
        } finally {
            // entityManager.close();
        }
        return device;
    }

   synchronized public void delete(Object tipeClass, Integer id) {
        EntityManager entityManager = getEntityManager();         
        try {
            entityManager.getTransaction().begin();
            Object dispositivo = entityManager.find(tipeClass.getClass(), id);
            entityManager.remove(dispositivo);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

   synchronized public void atualizar(Object device) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(device);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

    public static void main(String[] args) {

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setIddispositivo(2);
        Usuario usuario = new Usuario();
        usuario.setIdusuario(1);
        Cliente cliente = new Cliente();
        cliente.setConta("0001");
        dispositivo.setNome("Novo asasas");
        dispositivo.setUsuarioFK(usuario);
        dispositivo.setClienteFK(cliente);
        dispositivo.setDataCriacao(new Date());
        
     //  DAO.getInstance().delete(dispositivo,36);
      
        DAO.getInstance().atualizar(dispositivo);
    }
}
