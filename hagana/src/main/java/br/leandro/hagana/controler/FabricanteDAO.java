/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Dispositivo;
import br.leandro.hagana.entidade.Fabricante;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author leand
 */
public class FabricanteDAO {
    
    private static FabricanteDAO instance;
    protected EntityManager entityManager;

    public static FabricanteDAO getInstance() {
        if (instance == null) {
            instance = new FabricanteDAO();
        }

        return instance;
    }

    private FabricanteDAO() {
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
    
    public List<Fabricante> getFabricantes() {
        Query query = entityManager.createNamedQuery("Fabricante.findAll");
        return query.getResultList();
    }
}