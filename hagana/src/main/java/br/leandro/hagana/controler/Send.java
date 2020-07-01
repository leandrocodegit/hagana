/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author leand
 */
public class Send implements Runnable {

    DatagramSocket socket;

    @Override
    public void run() {

        sendMessage("send");
        System.out.println("Mensagem enviada.");
        try {

            String host = "www.java2s.com";
            int port = 4445;

            byte[] message = "Java Source and Support".getBytes();

            // Get the internet address of the specified host
            InetAddress address = InetAddress.getByName(host);

            // Initialize a datagram packet with data and address
            DatagramPacket packet = new DatagramPacket(message, message.length,
                    address, port);

            // Create a datagram socket, send the packet through it, close it.
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(packet);

            int clientPort = 999;

            int buffer_size = 1024;

            byte buffer[] = new byte[buffer_size];
            DatagramSocket ds = new DatagramSocket(clientPort);

            while (true) {
                DatagramPacket p = new DatagramPacket(buffer, buffer.length);
                ds.receive(p);
                System.out.println(new String(p.getData(), 0, p.getLength()));
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void sendMessage(String message) {
        byte[] buf = message.getBytes();

        System.out.println("Enviadno mensagem...");
        
        try {

            String host = "www.java2s.com";
            int port = 4445;

            byte[] m = "Java Source and Support".getBytes();

            // Get the internet address of the specified host
            InetAddress address = InetAddress.getByName(host);

            // Initialize a datagram packet with data and address
            DatagramPacket p = new DatagramPacket(m, m.length,
                    address, port);

            // Create a datagram socket, send the packet through it, close it.
            DatagramSocket dsocket = new DatagramSocket();
            dsocket.send(p);
            dsocket.close();

        } catch (Exception e) {
            System.err.println("Sending failed. " + e.getMessage());
        }
    }
}
