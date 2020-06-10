/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Dispositivo;
import br.leandro.hagana.entidade.Local;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author leand
 */
public class ClienteDAO {
    
    private static ClienteDAO instance;
    protected EntityManager entityManager;

    public static ClienteDAO getInstance() {
        if (instance == null) {
            instance = new ClienteDAO();
        }

        return instance;
    }

    private ClienteDAO() {
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
    
    public List<Cliente> getclientes() {
        Query query = entityManager.createNamedQuery("Cliente.findAll");        
        return query.getResultList();
    }

    public Cliente findAll(Cliente c) {
        Query query = entityManager.createNamedQuery("Cliente.findByConta");
        query.setParameter("conta", c.getConta());
        Cliente cliente = (Cliente) query.getSingleResult();
        return cliente;
    }
    public List<Cliente> pesquisar(String value) {
        Query query = entityManager.createNamedQuery("Cliente.findByNome");
        query.setParameter("nome",value + "%");
        query.setParameter("conta",value + "%");
        System.out.println(query);
        return query.getResultList();
    }
    
     public Cliente insert(Cliente cliente) {
    EntityManager entityManager = getEntityManager();
    try {
      entityManager.getTransaction().begin();
      if(cliente.getConta() == null) {
        entityManager.persist(cliente);
      } else {
        entityManager.merge(cliente);
      }
      entityManager.flush();
      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
    } finally {
     // entityManager.close();
    }
    return cliente;
  }
     public void delete(String conta) {
    EntityManager entityManager = getEntityManager();
    try {
      entityManager.getTransaction().begin();
      Cliente cliente = entityManager.find(Cliente.class, conta);
      entityManager.remove(cliente);
      entityManager.flush();
      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      entityManager.getTransaction().rollback();
    } finally {
      entityManager.close();
    }
  }
    
    public static void main(String[] args) {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setConta("0001");
        
        System.out.println(clienteDAO.pesquisar("00").size());
        
        
    }
    
}
