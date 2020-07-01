/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;

import java.util.HashMap;

/**
 *
 * @author leand
 */
public class ServerInstanceCliente {

    private static ClienteServidor clienteServidor;
    private static HashMap<String, TCP> mapLines = new HashMap<String, TCP>();

    public static ClienteServidor getInstance() {
        if (clienteServidor == null) {
            System.out.println("Init server cliente");
            clienteServidor = new ClienteServidor("127.0.0.1", 9988);

            Thread t2 = new Thread(clienteServidor);

            t2.start();

            VerificaConexao verificaConexao = new VerificaConexao(clienteServidor);

            Thread t1 = new Thread(verificaConexao);

            t1.start();
        }

        return clienteServidor;
    }

    private static RecebedorUTF recebedorUTF;

    public static ClienteServidor getInstanceUTF(ClienteServidor C) {
        if (recebedorUTF == null) {
            System.out.println("Init recebedorUTF");

            recebedorUTF = new RecebedorUTF(clienteServidor);
            Thread t = new Thread(recebedorUTF);
            t.start();
        }

        return clienteServidor;
    }

    public static TCP getInstanceMAP(String keyID) { 
        return mapLines.get(keyID);
    }

    public static TCP addIDMAP(TCP tcp) {
        System.out.println("Create map " + tcp.getIdSession());
        mapLines.put(tcp.getIdSession(), tcp);
        return clienteServidor.getTcp();
    }
    
    public static void mergeIDMAP(TCP tcp) {
      //  System.out.println("Check update " + tcp.getIdSession());
      //  System.out.println("Check mensagem " + tcp.getMensage());
        mapLines.put(tcp.getIdSession(), tcp);
     //   System.out.println("Update " + tcp.getIdSession());
        
    }

}
