/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import static br.leandro.hagana.entidade.Dominio_.porta;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 *
 * @author leand
 */
public class Main {

    public static void main(String[] args) throws UnknownHostException, IOException {

        String ipAddress = "192.168.0.254";
        InetAddress inet = InetAddress.getByName(ipAddress);

        System.out.println("Sending Ping Request to " + ipAddress);
        System.out.println(inet.isReachable(1000) ? "Host is reachable" : "Host is NOT reachable");

        boolean retv = false;
        try {
            InputStream ins = Runtime.getRuntime().exec("ping -n 1 -w 2000 " + ipAddress).getInputStream();
            Thread.sleep(3000);
            byte[] prsbuf = new byte[ins.available()];
            ins.read(prsbuf);
            String parsstr = new StringTokenizer(new String(prsbuf), "%")
                    .nextToken().trim();
            System.out.println(parsstr);
            if (!parsstr.endsWith("100")) {
                retv = true;
            }
        } catch (Exception e) {
            retv = false;
        }

    }

}
