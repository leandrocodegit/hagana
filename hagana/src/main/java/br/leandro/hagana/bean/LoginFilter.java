package br.leandro.hagana.bean;


import br.leandro.hagana.entidade.Usuario;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter,Serializable {

    private static final long serialVersionUID = 87456444545634321L;
    
    
    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Usuario user = null;
        HttpSession sess = ((HttpServletRequest) request).getSession(false);
        
        

        if (sess != null) {
            user = (Usuario) sess.getAttribute("usuarioLogado");
            
        }

        if (user == null) {

            String contextPath = ((HttpServletRequest) request)
                    .getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath
                    + "/faces/login/login.xhtml");
        } else {

              chain.doFilter(request, response);  
           
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
