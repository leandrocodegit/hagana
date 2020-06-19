/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import br.leandro.hagana.entidade.Cliente;
import br.leandro.hagana.entidade.Device;
import br.leandro.hagana.entidade.Dispositivo;
import br.leandro.hagana.entidade.Local;
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
        query.setParameter("nome", value + "%");
        query.setParameter("conta", value + "%");
        return query.getResultList();
    }

    public Cliente insert(Cliente cliente) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (cliente.getConta() == null) {
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
            //  entityManager.close();
        }
    }

    public void atualizar(Cliente cliente) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        } finally {
            //  entityManager.close();
        }
    }
    
    public List<Device> getDevicesList(Cliente c) {
        Device device;
        List<Device> devicesList = new ArrayList<>();
        Query query = entityManager.createNamedQuery("Cliente.findByConta");
        query.setParameter("conta", c.getConta());
        Cliente cliente = (Cliente) query.getSingleResult();
        
        for (int i = 0; i < cliente.getLinkList().size(); i++) {
            devicesList.add((Device)cliente.getLinkList().get(i));
        }
        for (int i = 0; i < cliente.getRedeList().size(); i++) {
            devicesList.add((Device)cliente.getRedeList().get(i));
        }
        
        
     
        return devicesList;
    }
    
    public List<Device> getDevicesListAll(Cliente c) {
     
        List<Device> devicesList = new ArrayList<>();
        Query query = entityManager.createNamedQuery("Cliente.findByConta");
        query.setParameter("conta", c.getConta());
        Cliente cliente = (Cliente) query.getSingleResult();
        
        for (int i = 0; i < cliente.getLinkList().size(); i++) {
            devicesList.add((Device)cliente.getLinkList().get(i));
        }
        for (int i = 0; i < cliente.getRedeList().size(); i++) {
            devicesList.add((Device)cliente.getRedeList().get(i));
        }
        for (int i = 0; i < cliente.getDispositivoList().size(); i++) {
            devicesList.add((Device)cliente.getDispositivoList().get(i));
        }
        for (int i = 0; i < cliente.getComputadorList().size(); i++) {
            devicesList.add((Device)cliente.getComputadorList().get(i));
        }
        return devicesList;
    }
    

    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente();
        cliente.setConta("0001");

        System.out.println(clienteDAO.pesquisar("00").size());
        System.out.println(clienteDAO.pesquisar("00").get(0).getRedeList().size());

    }

}
