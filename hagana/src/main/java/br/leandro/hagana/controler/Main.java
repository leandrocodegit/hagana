/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author leand
 */
public class Main {

   private static DatagramSocket socket = null;
 
    public static void main(String[] args) throws IOException {
        broadcast("Hello", InetAddress.getByName("255.255.255.255"));
    }
 
    public static void broadcast(
      String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);
 
        byte[] buffer = broadcastMessage.getBytes();
        System.out.println(buffer.toString());
        for (int i = 0; i < buffer.length; i++) {
            System.out.println(buffer[i]);
        }
        
        Scanner scanner = new Scanner(socket.get);
 
        DatagramPacket packet 
          = new DatagramPacket(buffer, buffer.length, address, 4445);
        
        socket.send(packet);
        socket.close();
    }
    
    
    List<InetAddress> listAllBroadcastAddresses() throws SocketException {
    List<InetAddress> broadcastList = new ArrayList<>();
    Enumeration<NetworkInterface> interfaces 
      = NetworkInterface.getNetworkInterfaces();
    while (interfaces.hasMoreElements()) {
        NetworkInterface networkInterface = interfaces.nextElement();
 
        if (networkInterface.isLoopback() || !networkInterface.isUp()) {
            continue;
        }
 
        networkInterface.getInterfaceAddresses().stream() 
          .map(a -> a.getBroadcast())
          .filter(Objects::nonNull)
          .forEach(broadcastList::add);
    }
    return broadcastList;
 
}