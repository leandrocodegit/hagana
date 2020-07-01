/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
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
import static javax.management.Query.lt;
import javax.sound.midi.Receiver;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author leand
 */
public class Main {

    private static DatagramSocket socket = null;

    public static void main(String[] args) throws IOException {

        String bkdbdoc = "C:\\Program Files (x86)\\Advanced Port Scanner\\advanced_port_scanner_console.exe /r:192.168.0.1-192.168.0.255";
        try {
            Runtime.getRuntime().exec("cmd /c start " + bkdbdoc);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
