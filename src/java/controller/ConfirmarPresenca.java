/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
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
@WebServlet("/ConfirmarPresenca")
public class ConfirmarPresenca extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            System.out.println("Sessão usuario = " + request.getSession().getAttribute("usuarioLogado"));

            System.out.println("idEvento = " + request.getParameter("idEvento"));
            System.out.println("idUsu = " + request.getParameter("idUsu"));
            int idUsu = Integer.parseInt(request.getParameter("idUsu"));
            int idEvento = Integer.parseInt(request.getParameter("idEvento"));

            EventoDAO dao = new EventoDAO();
            dao.darPresenca(idUsu, idEvento);

            // Volta para resença confirmada
            response.sendRedirect(request.getContextPath() + "/menuprincipal/evento/presencaconfirmada.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}