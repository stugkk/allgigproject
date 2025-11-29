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
import model.Usu;

/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
//carrega um evento do banco pelo ID e enviar os dados para a JSP evento.jsp.
@WebServlet("/evento")
public class Evento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null) {
            response.sendRedirect(request.getContextPath() + "/menuprincipal/pesquisarevento/home.jsp");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);

            EventoDAO dao = new EventoDAO();
            model.Evento evento = dao.buscarPorId(id);

            if (evento == null) {
                response.sendRedirect(request.getContextPath() + "/menuprincipal/pesquisarevento/home.jsp");
                return;
            }
            Usu usuario = (Usu) request.getSession().getAttribute("usuario");
            request.setAttribute("usuario", usuario);
            request.setAttribute("evento", evento);
            request.getRequestDispatcher("menuprincipal/evento/evento.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erro ao carregar o evento", e);
        }
    }
}