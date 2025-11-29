/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.DAO.UsuDAO;
import model.Usu;
/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
@WebServlet("/AtualizarPerfil")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class AtualizarPerfilServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // pega usuario na sessao
        HttpSession sessao = request.getSession();
        Usu usuario = (Usu) sessao.getAttribute("usuarioLogado");

        if (usuario == null) {
            response.sendRedirect("index.html");
            return;
        }

        try {
            System.out.println("Sessão usuário: " + sessao.getAttribute("usuarioLogado"));
            // recebe os campos
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            String senha = request.getParameter("senha");
            String nascStr = request.getParameter("nasc");
            boolean organizador = "on".equals(request.getParameter("organizador"));

            java.sql.Date nascimento = java.sql.Date.valueOf(nascStr);

            // foto de perfil
            Part filePart = request.getPart("fotoperfil");
            InputStream fotoStream = null;
            if (filePart != null && filePart.getSize() > 0) {
                fotoStream = filePart.getInputStream();
            }

            // atualizar objeto usu
            usuario.setNome(nome);
            usuario.setCpf(cpf);
            usuario.setEmail(email);
            usuario.setTelefone(telefone);
            usuario.setSenha(senha);
            usuario.setNasc(nascimento);
            usuario.setOrganizador(organizador);

            // chama o dao
            UsuDAO dao = new UsuDAO();
            dao.atualizarUsuario(usuario, fotoStream);

            //atualiza sessao
            sessao.setAttribute("usuarioLogado", usuario);

            // redireciona
            response.sendRedirect("menuprincipal/meuperfil/sucesso.jsp");

        } catch (SQLException e) {
            response.getWriter().println("Erro SQL: " + e.getMessage());
        } catch (Exception e) {
            response.getWriter().println("Erro geral: " + e.getMessage());
        }
    }
}
