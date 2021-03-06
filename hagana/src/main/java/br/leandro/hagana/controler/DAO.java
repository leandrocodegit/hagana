/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author leand
 */
public class DAO {
 
    protected EntityManager entityManager;
 
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

    public void delete(Object tipeClass, Object id) {
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

     public Object atualizar(Object device) {
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
        