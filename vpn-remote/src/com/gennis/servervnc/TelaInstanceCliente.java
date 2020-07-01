/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;

/**
 *
 * @author leand
 */
public class TelaInstanceCliente {
    
    private static ClienteServidor clienteServidor = new ClienteServidor("192.168.0.10", 9988);
 
    private static Monitor monitor = new Monitor();
    private static Tela tela = new Tela();
     
    
    public static ClienteServidor getClienteServidor() {
        
        return clienteServidor;
    }
 
    public static Monitor getMonitor() {
        return monitor;
    }

    public static void setMonitor(Monitor monitor) {
        TelaInstanceCliente.monitor = monitor;
    }

    public static Tela getTela() {
        return tela;
    }
    
    
    
    
    
}
