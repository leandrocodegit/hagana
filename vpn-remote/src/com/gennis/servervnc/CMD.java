/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leand
 */
public class CMD {

    private static List<LineComand> lines;

    public static void main(String[] args) throws IOException {

        File file = new File("");

        System.out.println(file.getAbsolutePath());

        Process p;
        BufferedReader input;
        StringBuffer cmdOut = new StringBuffer();

        ProcessBuilder builder = new ProcessBuilder(
                "cmd ", "/c", "console.lnk /r:192.168.0.1-192.168.0.255");
        builder.redirectErrorStream(true);
        p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }

            if (line.contains("alive")) {

                System.out.println(line);
                String x = line.replace(" | ", ";");
                String split[] = x.split(";");

                System.out.println("IP " + split[4]);
                System.out.println("Host name " + split[1]);
                System.out.println("MAC " + split[split.length - 1]);
                System.out.println("Fabricante " + split[split.length - 2]);
                System.out.println("");

            }

            //   System.out.println(x);
        }

    }

    public static void comand(String commandLine) throws IOException {

        lines = new ArrayList<LineComand>();
        LineComand lineComand;

        Process p;
        BufferedReader input;
        StringBuffer cmdOut = new StringBuffer();

        ProcessBuilder builder = new ProcessBuilder(
                "cmd ", "/c", commandLine);
        builder.redirectErrorStream(true);
        p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        TCP request = new TCP();
        request.setSend(true);

        System.out.println(commandLine);

        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }

            if (commandLine.contains("ping")) {
                if (line.contains("Resposta") || line.contains("Esgotado")) {
                    lineComand = new LineComand();
                    request.setPing(true);
                    lines.add(lineComand);
                    request.setMensage(line);

                }
            } else if (commandLine.contains("console")) {
                if (line.contains("live")) {
                    lineComand = new LineComand();
                    String x = line.replace(" | ", ";");

                    String split[] = x.split(";");

                    lineComand.setIp(split[4]);
                    lineComand.setHost(split[1]);
                    lineComand.setMac(split[split.length - 1]);
                    lineComand.setFabricante(split[split.length - 2]);

                    x = x.replace(";;", ";");
                    x = x.replace(";;;", ";");
                    x = x.replace(";;;;", ";");

                    x = x.replace(";", "    ");

                    lineComand.setLine(x);
                    request.setScan(true);
                    lines.add(lineComand);
                    request.setMensage(x);

                    TCP keep = new TCP();
                    keep.setSend(true);
                    keep.setMensage("Executando scaner da rede...");
                    TelaInstanceCliente.getClienteServidor().enviarMensagemServidor(keep);

                }
            } else if (commandLine.contains("arp")) {
                lineComand = new LineComand();

                if (line.contains("interface")) {
                    break;
                }

                String dhcp = "estático";

                if (line.contains("mico")) {
                    dhcp = "dinâmico";
                }

                lineComand.setDhcp(dhcp);
                lineComand.setLine(line);

                request.setArp(true);
                lines.add(lineComand);
            }

        }

        request.setLines(lines);
        TelaInstanceCliente.getClienteServidor().enviarMensagemServidor(request);

    }
 

}
