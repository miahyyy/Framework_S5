package com.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

public class FrontServlet extends HttpServlet {

    // @Override
    // public void service(HttpServletRequest request, HttpServletResponse response)
    //         throws ServletException, IOException {
    //     String path = request.getServletPath();
    //     String realPath = getServletContext().getRealPath(path);

    //     java.io.File file = new java.io.File(realPath);

    //     if (file.exists() && !file.isDirectory()) {
    //         // La ressource existe, on la forward
    //         request.getRequestDispatcher(path).forward(request, response);
    //     } else {
    //         // La ressource n'existe pas, on affiche l'URL demandée
    //         response.setContentType("text/plain");
    //         PrintWriter out = response.getWriter();
    //         out.println("URL demandée: " + request.getRequestURL().toString());
    //         System.out.println("URL demandée: " + request.getRequestURL().toString());
    //     }
    // }

    private RequestDispatcher defaultDispatcher;
    
    @Override
    public void init() {
        defaultDispatcher = getServletContext().getNamedDispatcher("default");
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        boolean resourceExists = getServletContext().getResource(path) != null;

        if (resourceExists) {
            defaultDispatcher.forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String uri = request.getRequestURI();
                String responseBody = """
                    <html>
                        <head><title>Erreur 404</title></head>
                        <body>
                            <h1>Erreur 404</h1>
                            <p>L'URL demandée est introuvable: <strong>%s</strong></p>
                        </body>
                    </html>
                    """.formatted(uri);
                out.println(responseBody);
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        service(request, response);
    }
}