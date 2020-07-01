/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 *
 * @author leand
 */
public class MulticastPublisher {

    
    public static void main(String[] args) throws SocketException, IOException {
        
         InetSocketAddress isAddress = new InetSocketAddress( "192.168.0.10", 18418 );

        byte[] buffer = "NOTIFICATION".getBytes();

        DatagramPacket packet = new DatagramPacket( buffer, buffer.length, isAddress );

        new DatagramSocket().send( packet );
        
    }

}
