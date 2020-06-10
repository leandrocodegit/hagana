/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import br.leandro.hagana.controler.ClienteDAO;
import br.leandro.hagana.entidade.Cliente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author leand
 */
@Named
@ApplicationScoped
public class ClienteService {

    private List<Cliente> clientesList;

    @PostConstruct
    public void init() {
        clientesList = ClienteDAO.getInstance().getclientes();
    }

    public List<Cliente> getClientesList() {
        return clientesList;
    }

}
