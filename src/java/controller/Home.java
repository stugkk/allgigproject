/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.EventoDAO;
import model.Evento;

/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */

@WebServlet("/home")
public class Home extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        EventoDAO dao = new EventoDAO();
        List<Evento> lista = null;

        try {
            lista = dao.consultaGeral();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

        // Envia lista para o JSP
        request.setAttribute("listaEventos", lista);

        // Caminho 
        request.getRequestDispatcher("menuprincipal/pesquisarevento/home.jsp")
            .forward(request, response);
    }
}