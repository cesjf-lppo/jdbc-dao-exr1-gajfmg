/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lppo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author suporte
 */
@WebServlet(name = "ControllerAnuncio", urlPatterns = {"/Listar.html", "/Inserir.html"})
public class ControllerAnuncio extends HttpServlet {


    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("Listar.html")) {
            List<Anuncio> anuncios = new ArrayList<>();
            AnuncioDAO dao = new AnuncioDAO();
            anuncios = dao.listarTodos();

            request.setAttribute("anuncios", anuncios);
            request.getRequestDispatcher("/WEB-INF/Listar.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("Inserir.html")) {
            request.getRequestDispatcher("/WEB-INF/Inserir.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getRequestURI().contains("Inserir.html")){
            Anuncio novoAnuncio = new Anuncio();
            novoAnuncio.setNome(request.getParameter("nome"));
            novoAnuncio.setDescricao(request.getParameter("descricao"));
            novoAnuncio.setPreco(Float.parseFloat(request.getParameter("preco")));
            
            AnuncioDAO dao = new AnuncioDAO();
            dao.criar(novoAnuncio);
            
            response.sendRedirect("Listar.html");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
