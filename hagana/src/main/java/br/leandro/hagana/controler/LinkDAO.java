/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Link;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author leand
 */
public class LinkDAO {

private static LinkDAO instance;
    protected EntityManager entityManager;

    public static LinkDAO getInstance() {
        if (instance == null) {
            instance = new LinkDAO();
        }

        return instance;
    }

    private LinkDAO() {
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

    public List<Link> getdDispositivosList() {
        Query query = entityManager.createNamedQuery("Link.findAll");
        return query.getResultList();
    }

    public List<Link> pesquisar(String value) {
        Query query = entityManager.createNamedQuery("Link.findByNome");
        query.setParameter("nome", value + "%");
        System.out.println(query);
        return query.getResultList();
    }

    public Link insert(Link link) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(link);

            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            // entityManager.close();
        }
        return link;
    }

    public void delete(Integer id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Link link = entityManager.find(Link.class, id);
            entityManager.remove(link);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

    public void atualizar(Link link) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(link);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

}
