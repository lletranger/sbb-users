package org.tsys.sbb.util;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI();

        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        response.setHeader("Expires", "0");
        HttpSession session = request.getSession(false);

        //!url.contains("login.html") check if the requested page is login page or not. you can do it a numerous way.
        // but for simpplicity here i do that
        if(session==null && !url.contains("login.html")) {
            response.sendRedirect(request.getContextPath() + "/login.html"); // here goto http://yourdoamin/login.html
            response.setHeader("message", "Session Timeout."); // you can set your preffered message.
            return; //break filter chain, requested JSP/servlet will not be executed
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}