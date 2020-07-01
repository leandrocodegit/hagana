package com.gennis.servervnc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
 
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenadro Oliveira
 * Responsavel por testar a conexão e solicitar uma nova cano houver perda
 */
public class VerificaConexao implements Runnable {

    private static boolean conectador = false;
    private ClienteServidor servidor;
    private TCP objetoTest;
     

    public VerificaConexao(ClienteServidor servidor) {
        this.servidor = servidor;
        objetoTest = new TCP();
        objetoTest.setKeepLive(true);
        objetoTest.setKeepLiveRequest(true);
        objetoTest.setKeyID(servidor.getMAC().getKeyID());
    }

     

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VerificaConexao.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (servidor.getSocket().isConnected()) {
                //Envia um objeto de teste
                //Caso dispara uma excetion deconecta o socket e chama um nova conexão
               servidor.testConect(objetoTest);   
              //  System.out.println("Keep alive " + servidor.isConectado());
     
                while (servidor.isConectado() == false) {
  
                    //Imprime na tela desconectado
                
                    try {
                       //Close no socket atual
                        servidor.getSocket().close();
                        //Chama um nova conexão
                        servidor.conectar();   
                        //Imprime conectado  TelaInstanceCliente.getApp().setStatus(true,"Conectado");                        
                         
                        break;
                    } catch (Exception ex) {
                        //System.out.println("Verificar conexao");
                    }
                }

            }

        }
    }

   

}
