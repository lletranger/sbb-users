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
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getRequestURI();

        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
        response.setHeader("Expires", "0");
        HttpSession session = request.getSession(false);

        if(session == null && !url.contains("index")) {
            response.sendRedirect(request.getContextPath() + "/index");
            response.setHeader("message", "Session Timeout.");
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //do nothing
    }

    @Override
    public void destroy() {
        //do nothing
    }
}