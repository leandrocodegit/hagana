/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.leandro.hagana.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Leandro Code
 */
@ManagedBean(name = "sessaoBean")
@SessionScoped
public class SessaoBean {
    
    
    FacesContext context = FacesContext.getCurrentInstance();   
    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);  
     
       
    //métodos auxiliares que podem te ajudar:  
    public FacesContext getFacesContext() {  
          return FacesContext.getCurrentInstance();  
       }  
          
       public HttpSession getSession() {  
          return (HttpSession) getFacesContext().getExternalContext().getSession(false);  
       }  
          
       public HttpServletRequest getRequestSession() {  
          return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();  
       }  
    
}
