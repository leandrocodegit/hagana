/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.DeviceImp;
import br.leandro.hagana.entidade.Dispositivo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author leand
 */
public class DispositivoDAO {

    private static DispositivoDAO instance;
    protected EntityManager entityManager;

    public static DispositivoDAO getInstance() {
        if (instance == null) {
            instance = new DispositivoDAO();
        }

        return instance;
    }

    private DispositivoDAO() {
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

    public List<Dispositivo> getdDispositivosList() {
        Query query = entityManager.createNamedQuery("Dispositivo.findAll");
        return query.getResultList();
    }

    public List<Dispositivo> pesquisar(String value) {
        Query query = entityManager.createNamedQuery("Dispositivo.findByNome");
        query.setParameter("nome", value + "%");
        System.out.println(query);
        return query.getResultList();
    }

    public Dispositivo insert(Dispositivo dispositivo) { 
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(dispositivo);

            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            // entityManager.close();
        }
        return dispositivo;
    }

    public void delete(Integer id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Dispositivo dispositivo = entityManager.find(Dispositivo.class, id);
            entityManager.remove(dispositivo);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

    public void atualizar(Dispositivo dispositivo) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(dispositivo);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

    public static void main(String[] args) {

        Dispositivo dispositivo = new Dispositivo();
        
        DispositivoDAO.getInstance().insert(dispositivo);

    }
}
