/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
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
            // this.servidor = servidor;
            this.servidor = clienteServidor;
        } catch (Exception ex) {
            Logger.getLogger(RecebedorUTF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {

        while (true) {
            if (servidor.isConectado()) {
                try {

                    //Marca com pronto para receber dos de entrada de mouse e teclado
                    is = new ObjectInputStream(servidor.getSocket().getInputStream());

                    tcp = (TCP) is.readObject();

                    if (tcp.isPing()) {
                        System.out.println("PING " + tcp.getLines().size());
                        System.out.println(tcp.getMensage());

                        for (int i = 0; i < tcp.getLines().size(); i++) {
                            System.out.println(tcp.getLines().get(i).getLine());
                        }
                        continue;
                    }

                    if (tcp.isScan()) {
                        System.out.println("CONSOLE " + tcp.getLines().size());
                        for (int i = 0; i < tcp.getLines().size(); i++) {
                            System.out.println(tcp.getLines().get(i).getLine());
                        }
                        continue;
                    }

                    if (tcp.isArp()) {
                        System.out.println("ARP " + tcp.getLines().size());
                        System.out.println(tcp.getMensage());
                        for (int i = 0; i < tcp.getLines().size(); i++) {
                            System.out.println(tcp.getLines().get(i).getLine());
                        }
                        continue;
                    }

                } catch (SocketException ex) {

                } catch (IOException ex) {

                } catch (Exception ex) {

                }

            } else {
                System.out.println("Destroy TUF8");
                return;
            }
        }
    }

}
