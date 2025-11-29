/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evento;
import model.DAO.EventoDAO;
/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
@WebServlet("/EventoCarregar")
public class EventoCarregar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // pega o id vindo da URL
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.isEmpty()) {
                response.getWriter().println("ID do evento não informado.");
                return;
            }

            int idEvento = Integer.parseInt(idParam);

            EventoDAO dao = new EventoDAO();
            Evento evento = dao.buscarPorId(idEvento);

            if (evento == null) {
                response.getWriter().println("Evento não encontrado.");
                return;
            }

            // envia o objeto para a JSP
            request.setAttribute("evento", evento);

            // encaminha para página de detalhes
            request.getRequestDispatcher("/menuprincipal/evento/evento.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao carregar evento: " + e.getMessage());
        }
    }
}