/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO.UsuDAO;
import model.Usu;

/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
//exibe
@WebServlet("/perfil")
public class Perfil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        


        // ID salvo na sessão após login
        Integer idUser = (Integer) request.getSession().getAttribute("idUsuario");
        

System.out.println("ID na sessão = " + idUser);// <- só pra garantir se o id está na sessao

            if (idUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
            }
        UsuDAO dao = new UsuDAO();
        Usu usu = null;

        try {
            usu = dao.buscarPorId(idUser);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                               "Erro ao buscar dados do usuário.");
            return;
        }

        request.setAttribute("usuario", usu);
        request.getRequestDispatcher("menuprincipal/meuperfil/perfil.jsp").forward(request, response);
    }
}

