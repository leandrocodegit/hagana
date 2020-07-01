/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gennis.servervnc;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author leand
 */
public class Main {
    
    public static void main(String[] args) throws UnknownHostException, SocketException {
        
      
      //  String = args[0]);
        
        try {
          //  ServerSocket serverSocket = new ServerSocket(9000);
             
            
            TelaInstanceCliente.getMonitor().setVisible(true);
            TelaInstanceCliente.getTela().setVisible(true);
            
            
            
            System.out.println("Init client");
            
            ClienteServidor c2 = TelaInstanceCliente.getClienteServidor();
            
            
            Thread t2 = new Thread(c2);
            
            
            t2.start();
            
            
            VerificaConexao verificaConexao = new VerificaConexao(c2);
            
            Thread t1 = new Thread(verificaConexao);
            
            t1.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Essa aplicação já se encontra em execução!");
            System.exit(0);
        }
 
         
        return;
        
    }
    
    
    
    /*
    private static boolean rastreadorApp(int processPid) {
        MonitoredHost host;
        Set vms;
        try {
            host = MonitoredHost.getMonitoredHost(new HostIdentifier((String)null));
            vms = host.activeVms();
        } catch (java.net.URISyntaxException sx) {
            throw new InternalError(sx.getMessage());
        } catch (MonitorException mx) {
            throw new InternalError(mx.getMessage());
        }
        MonitoredVm mvm = null;
        String processName = null;
        try{
            mvm = host.getMonitoredVm(new VmIdentifier(String.valueOf(processPid)));
            processName = MonitoredVmUtil.commandLine(mvm);
            processName = processName.substring(processName.lastIndexOf("\\") + 1,processName.length());
            mvm.detach();
        } catch (Exception ex) {
            
        }
        for (Object vmid: vms) {
            if (vmid instanceof Integer) {
                int pid = ((Integer) vmid).intValue();
                String name = vmid.toString();
                try {
                     mvm = host.getMonitoredVm(new VmIdentifier(name));
                     name =  MonitoredVmUtil.commandLine(mvm);
                     name = name.substring(name.lastIndexOf("\\")+1,name.length());
                     mvm.detach();
                     if ((name.equalsIgnoreCase(processName)) && (processPid != pid))
                         return false;
                } catch (Exception x) {
                     // ignore
                }
            }
        }
        
        return true;
	}
   */
    
     
}
