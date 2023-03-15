package com.example.stackroute.foodieApplication.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String authHeader=request.getHeader("authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            throw new ServletException("Token Missing");
        }
        else {
            String token=authHeader.substring(7);
            Claims claims= Jwts.parser().setSigningKey("I_Dont_Say").parseClaimsJws(token).getBody();
            System.out.println("\n In Filter, Claims : "+claims);
            request.setAttribute("current_user_emailID",claims.get("emailID"));
            filterChain.doFilter(request,response);
        }
    }
}
