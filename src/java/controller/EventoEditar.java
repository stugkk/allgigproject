/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.EventoDAO;

/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
//usado para editar o evento
@WebServlet(name = "EventoEditar", urlPatterns = {"/EventoEditar"})
public class EventoEditar extends HttpServlet {



    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            EventoDAO dao = new EventoDAO();
            model.Evento ev = dao.buscarPorId(id);

            req.setAttribute("evento", ev);
            req.getRequestDispatcher("menuprincipal/meuseventos/atualizarEvento.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

