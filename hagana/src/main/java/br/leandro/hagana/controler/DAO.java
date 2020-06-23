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

    public EntityManager getEntityManager() {
        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("com.gennis_hagana_war_PU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }
    
    synchronized public Object insert(Object device) {

        EntityManager em = getEntityManager();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(device);

            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
            return null;

        } finally {
          //  em.close();
        }
        return device;
    }

    synchronized public void delete(Object tipeClass, Integer id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Object dispositivo = entityManager.find(tipeClass.getClass(), id);
            em.remove(dispositivo);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
           // em.close();
        }
    }

    synchronized public Object atualizar(Object device) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(device);
            em.getTransaction().commit();
            
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return null;
        } finally {
          //  em.close();
        }
        return device;
    } 
} 
        