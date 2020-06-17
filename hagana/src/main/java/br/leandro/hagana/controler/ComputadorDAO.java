/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Computador;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author leand
 */
public class ComputadorDAO {

    private static ComputadorDAO instance;
    protected EntityManager entityManager;

    public static ComputadorDAO getInstance() {
        if (instance == null) {
            instance = new ComputadorDAO();
        }

        return instance;
    }

    private ComputadorDAO() {
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

    public List<Computador> getdDispositivosList() {
        Query query = entityManager.createNamedQuery("Computador.findAll");
        return query.getResultList();
    }

    public List<Computador> pesquisar(String value) {
        Query query = entityManager.createNamedQuery("Computador.findByNome");
        query.setParameter("nome", value + "%");
        System.out.println(query);
        return query.getResultList();
    }

    public Computador insert(Computador computador) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(computador);

            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            // entityManager.close();
        }
        return computador;
    }

    public void delete(Integer id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Computador computador = entityManager.find(Computador.class, id);
            entityManager.remove(computador);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

    public void atualizar(Computador computador) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(computador);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

}
