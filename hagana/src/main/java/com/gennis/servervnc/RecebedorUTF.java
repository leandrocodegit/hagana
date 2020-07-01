/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MONITORAMENTO
 */
public class RecebedorUTF implements Runnable {

    private ObjectInputStream is;
    private ClienteServidor servidor;
    private TCP tcp = new TCP();

    //Recebe uma instancia ativa od socket servidor
    public RecebedorUTF(ClienteServidor clienteServidor) {
        try {

            this.servidor = clienteServidor;
        } catch (Exception ex) {
            Logger.getLogger(RecebedorUTF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public TCP getTcp() {
        return tcp;
    }

    @Override
    public void run() {

        while (true) {
            if (servidor.isConectado()) {
                try {

                    //Marca com pronto para receber dos de entrada de mouse e teclado
                    is = new ObjectInputStream(servidor.getSocket().getInputStream());

                    tcp = (TCP) is.readObject();

                    if (tcp.isComandReturn()) {

                         
                      //  System.out.println("ID " + tcp.getIdSession());
 

                        ServerInstanceCliente.mergeIDMAP(tcp);

                        continue;
                    }

                } catch (SocketException ex) {

                } catch (IOException ex) {

                } catch (Exception ex) {

                }

            } else {
                System.out.println("Destroy TUF8");
                servidor.conectar();
                //return;
            }
        }
    }

}
