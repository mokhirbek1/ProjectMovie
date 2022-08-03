package by.makhkamov.movie.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/*"},
        initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class SecurityFilter implements Filter {
    private String indexPath;

    public void init(FilterConfig config) throws ServletException {
        indexPath = config.getInitParameter("INDEX_PATH");

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        httpResponse.sendRedirect(httpRequest.getContextPath()+indexPath);
        chain.doFilter(request, response);
    }
}
