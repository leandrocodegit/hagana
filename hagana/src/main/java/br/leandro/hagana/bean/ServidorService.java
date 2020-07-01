/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import com.gennis.servervnc.ClienteServidor;
import com.gennis.servervnc.VerificaConexao;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author leand
 */
@Named
@ApplicationScoped
public class ServidorService {

    private ClienteServidor clienteServidor;

    @PostConstruct
    public void init() {
        System.out.println("Init servidor");
        clienteServidor = new ClienteServidor("192.168.0.10", 9988);

        Thread t2 = new Thread(clienteServidor);

        t2.start();

        VerificaConexao verificaConexao = new VerificaConexao(clienteServidor);

        Thread t1 = new Thread(verificaConexao);
        
        t1.start();
    }

    public ClienteServidor getClienteServidor() {
        return clienteServidor;
    }

}
