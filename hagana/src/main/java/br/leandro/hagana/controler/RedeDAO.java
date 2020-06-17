/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

 
import br.leandro.hagana.entidade.Rede;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author leand
 */
public class RedeDAO {

 private static RedeDAO instance;
    protected EntityManager entityManager;

    public static RedeDAO getInstance() {
        if (instance == null) {
            instance = new RedeDAO();
        }

        return instance;
    }

    private RedeDAO() {
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

    public List<Rede> getdDispositivosList() {
        Query query = entityManager.createNamedQuery("Rede.findAll");
        return query.getResultList();
    }

    public List<Rede> pesquisar(String value) {
        Query query = entityManager.createNamedQuery("Rede.findByNome");
        query.setParameter("nome", value + "%");
        System.out.println(query);
        return query.getResultList();
    }

    public Rede insert(Rede rede) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(rede);

            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            // entityManager.close();
        }
        return rede;
    }

    public void delete(Integer id) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Rede rede = entityManager.find(Rede.class, id);
            entityManager.remove(rede);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

    public void atualizar(Rede rede) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(rede);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }

}
