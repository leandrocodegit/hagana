/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.util;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author leand
 */
public class Data {
    
    private static DateTimeFormatter formatterDateSQL = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/YY hh:mm");
    private static DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY"); 
     private static SimpleDateFormat simpleDateFormatSQL = new SimpleDateFormat("yyyy-MM-dd"); 
    private static SimpleDateFormat simpleHoraFormat = new SimpleDateFormat("HH:mm"); 
    private static LocalDate data = LocalDate.now();
    private static LocalDateTime horaTime = LocalDateTime.now();
    
    public static String getDateAtualBrasil(){        
        return horaTime.format(formatterDate);
    }
    
    public static String getDateAtualSQL(){        
        return data.format(formatterDateSQL);
    }
    
    public static String formatDataddMMYYYY(LocalDate data){        
        return data.format(formatterDate);
    }
    public static String formatDateddMMYYYY(Date data){        
        return simpleDateFormat.format(data);
    }
    public static String formatHora(LocalDateTime data){        
        return data.format(formatterHora);
    }
    public static String formatHoraHHmm(Date data){ 
        return simpleHoraFormat.format(data);
    }
    
    public static String formatDataSQL(LocalDate data){        
        return data.format(formatterDateSQL);
    }
    public static String formatDataSQL(Date data){        
        return simpleDateFormatSQL.format(data);
    }
}
