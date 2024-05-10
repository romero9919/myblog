package net.musecom.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String action = req.getServletPath();
        if("/".equals(action) || "/login".equals(action) || "/login.jsp".equals(action) || "/blist".equals(action)){
        	
            chain.doFilter(request, response);
        
        } else{
        
        	Object isLoggedObj = req.getSession().getAttribute("isLoggedIn");
            
        	if(isLoggedObj != null){
            
        		boolean isLoggedIn = (Boolean) isLoggedObj;
                if(isLoggedIn){
                    chain.doFilter(request, response);
                    return;
                }
            }

            String path = req.getContextPath()+ "/";
            resp.sendRedirect(path);
        }
		
	}

}
