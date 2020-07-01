/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;

import br.leandro.hagana.bean.SessionContext;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MONITORAMENTO
 */

public class ClienteServidor implements Runnable {

    private String host;
    private int porta;
    private boolean conectado = false;
    //Usado como o ID da conexão no servidor
    private String macID;
    private Socket socket; 
    private TCP tcp = new TCP();
    

    RecebedorUTF r;
    Thread t;

    private PrintStream saida;

    public ClienteServidor(String host, int porta) {
        this.host = host;
        this.porta = porta; 
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public PrintStream getSaida() {
        return saida;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getMacID() {
        return macID;
    }

    public void setMacID(String macID) {
        this.macID = macID;
    }

    public TCP getTcp() {
        return tcp;
    }

    public void setTcp(TCP tcp) {
        this.tcp = tcp;
    }


    //Estabelece uma conexão socket com servidor
     public void conectar() {

        try {
            conectado = false;
            
            while (conectado == false) {

                System.out.println("Conetando");
                socket = new Socket();
                try {

                    
                    InetSocketAddress ip = new InetSocketAddress(host, porta);

                    //Aguarda 5s até que a conexão seja estabelecida
                    //Cado ultrapasse 5s tenta conectar novamente
                    socket.connect(ip, 5000);

                    break;
                } catch (Exception ex) {
                    conectado = false;
                    break;
                }

            }
            // thread para receber mensagens do servidor
            // lê msgs do teclado e manda pro servidor
          
            ServerInstanceCliente.getInstanceUTF(this);

            //Marca como conectado o soket
            conectado = true;
            
             enviarMensagemServidor(getMAC());

            
        } catch (Exception ex) {
            conectado = false;
            //System.out.println("Erro ao conectar");
        }

    }

    //Requera o endereço MAC do compuador para enviar ao servidor
    public TCP getMAC() {

        TCP request = new TCP();
        request.setKey(conectado);
        try {
             
            request.setKeyID("TOM-CAT");            

        } catch (Exception ex) {
            Logger.getLogger(ClienteServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return request;
    }

    //Envia mensagem ao servidor
    public void enviarMensagemServidor(TCP request) {

        ObjectOutputStream os = null;      
        try {
            
            if (socket != null) {
                os = new ObjectOutputStream(this.socket.getOutputStream());
                os.writeObject(request);
                System.out.println("Mensagem enviada");
            } 

        } catch (IOException ex) {
            conectado = false;
        }

    }

    public void testConect(TCP request) {

        ObjectOutputStream os = null;
        try {

            if (socket != null) {
                os = new ObjectOutputStream(this.socket.getOutputStream());
                os.writeObject(request);
            } 

        } catch (IOException ex) {
            conectado = false;
        }

    }

    @Override
    public void run() {
        conectar();
    }

    public void sendMSG(String msg) {

        saida.println(msg);

    }

}
