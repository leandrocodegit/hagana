/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author leand
 */
public class Broadcast {

    private static DatagramSocket socket = null;

    public static void main(String[] args) throws IOException {

        broadcast("Hello", InetAddress.getByName("192.168.0.10"));

    }

    public static void broadcast(String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();
        System.out.println(buffer.toString());

        InetAddress group = InetAddress.getByName("192.168.0.10");

        DatagramPacket packet
                = new DatagramPacket(buffer, buffer.length, group, 4445);

        socket.send(packet);

        for (int i = 0; i < 5; i++) {
            byte[] buf = new byte[256];
            System.out.println("Aguardando resposta...");
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String received = new String(packet.getData());
            System.out.println("Quote of the Moment: " + received);
        }

    }

    static List<InetAddress> listAllBroadcastAddresses() throws SocketException {
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

    protected DatagramSocket createSocket() throws IOException {
        DatagramSocket s = new DatagramSocket();
        
      
        return s;
    }

}
