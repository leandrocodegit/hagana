/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Device;
import java.util.ArrayList;
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

    public EntityManager getEntityManager() {
        EntityManagerFactory factory
                = Persistence.createEntityManagerFactory("com.gennis_hagana_war_PU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }

        return entityManager;
    }

    public List<Cliente> getclientes() {
        Query query = getEntityManager().createNamedQuery("Cliente.findAll");
        return query.getResultList();
    }
 
    public Cliente findCliente(Cliente c) {
 
        EntityManager em = getEntityManager();
        // Query query = em.createNamedQuery("Cliente.findByConta");
        // query.setParameter("conta", c.getConta()); 
        Cliente cliente = (Cliente) em.find(Cliente.class, c.getConta());

        try {
            em.refresh(cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        return cliente;
    }

    public List<Cliente> pesquisar(String value) {
        Query query = getEntityManager().createNamedQuery("Cliente.findByNome");
        query.setParameter("nome", value + "%");
        query.setParameter("conta", value + "%");

        return query.getResultList();
    }
    
    
    public Integer getCount() {
        Cliente cliente = new Cliente();
        cliente.setConta("9246");
        Query query = getEntityManager().createNamedQuery("Dispositivo.findByCount");
        query.setParameter("cliente", cliente.getConta());
        query.setParameter("tipo", "4");

        return (Integer) query.getSingleResult();
    }
    
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();
        //System.out.println(dao.getCount(""));
        dao.getCount();
    }

    public Cliente insert(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            if (cliente.getConta() == null) {
                em.persist(cliente);
            } else {
                em.merge(cliente);
            }
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
           // em.close();
        }
        return cliente;
    }

    public void delete(String conta) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, conta);
            em.remove(cliente);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
          //  em.close();
        }
    }

    public void atualizar(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
           // em.close();
        }
    }

    public List<Device> getDevicesList(Cliente c) {

        List<Device> devicesList = new ArrayList<>();
        Query query = getEntityManager().createNamedQuery("Cliente.findByConta");
        query.setParameter("conta", c.getConta());
        Cliente cliente = (Cliente) query.getSingleResult();

        for (int i = 0; i < cliente.getLinkList().size(); i++) {
            devicesList.add((Device) cliente.getLinkList().get(i));
        }
        for (int i = 0; i < cliente.getRedeList().size(); i++) {
            devicesList.add((Device) cliente.getRedeList().get(i));
        }

        return devicesList;
    }

    public List<Device> getDevicesListAll(Cliente c) {

        List<Device> devicesList = new ArrayList<>();
        Query query = getEntityManager().createNamedQuery("Cliente.findByConta");
        query.setParameter("conta", c.getConta());
        Cliente cliente = (Cliente) query.getSingleResult();

        for (int i = 0; i < cliente.getLinkList().size(); i++) {
            devicesList.add((Device) cliente.getLinkList().get(i));
        }
        for (int i = 0; i < cliente.getRedeList().size(); i++) {
            devicesList.add((Device) cliente.getRedeList().get(i));
        }
        for (int i = 0; i < cliente.getDispositivoList().size(); i++) {
            devicesList.add((Device) cliente.getDispositivoList().get(i));
        }
        for (int i = 0; i < cliente.getComputadorList().size(); i++) {
            devicesList.add((Device) cliente.getComputadorList().get(i));
        }
        return devicesList;
    }
 

}
