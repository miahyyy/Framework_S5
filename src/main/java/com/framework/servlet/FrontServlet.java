package com.framework.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class FrontServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getRequestURL());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       service(request, response);
    } 
}
