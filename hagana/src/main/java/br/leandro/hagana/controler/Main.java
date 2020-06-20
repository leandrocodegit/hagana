/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.controler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author leand
 */
public class Main {

    public static void main(String[] args) throws UnknownHostException, IOException {

        String dir = "C:\\Users\\leand\\Documents\\GitHub\\hagana\\hagana\\target\\hagana-1.0.0\\restrict\\arquivos\\contas\\0001";
        File path = new File(dir + "\\imagens");
        File file;

        System.out.println(path.isDirectory());

        String[] children = path.list();

        for (int i = 0; i < children.length; i++) {
            file = new File(path.getPath(), children[i]);
        }
        file = new File(path.getPath());
        file.delete();

    }

}
