/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO.EventoDAO;
import model.Usu;

/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
//pega os eventos criados pelo id do usuario logado
@WebServlet(name = "MeusEventos", urlPatterns = {"/MeusEventos"})
public class MeusEventos extends HttpServlet {

     protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Usu usuario = (Usu) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            EventoDAO dao = new EventoDAO();
            List<model.Evento> lista = dao.listarPorOrganizador(usuario.getId());

            req.setAttribute("listaEventos", lista);
            req.getRequestDispatcher("menuprincipal/meuseventos/meuseventos.jsp").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("erro", "Erro ao carregar seus eventos: " + e.getMessage());
            req.getRequestDispatcher("erro.jsp").forward(req, resp);
        }
    }
}
