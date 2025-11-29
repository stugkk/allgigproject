/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import config.ConectaDB;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Blob;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.sql.Date;
import java.util.Base64;
import javax.servlet.http.HttpSession;

import model.Usu;
/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
@WebServlet("/Cadastrarusu")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 5MB
public class CadastrarUsuServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            // recebe os campos

            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            String senha = request.getParameter("senha");
            String nascStr = request.getParameter("nasc");
            boolean organizador = request.getParameter("organizador") != null;

            Date nasc = null;

            if (nascStr != null && !nascStr.isEmpty()) {
                nasc = Date.valueOf(nascStr);  // agora funciona 100%
            }



            // foto

            Part filePart = request.getPart("foto");
            Blob fotoBlob = null;
            String fotoBase64 = null;

            if (filePart != null && filePart.getSize() > 0) {
                InputStream in = filePart.getInputStream();
                byte[] bytes = in.readAllBytes();

                fotoBlob = new javax.sql.rowset.serial.SerialBlob(bytes);
                fotoBase64 = Base64.getEncoder().encodeToString(bytes);
            }

            // cria o objeto usu

            Usu usu = new Usu();
            usu.setNome(nome);
            usu.setCpf(cpf);
            usu.setEmail(email);
            usu.setTelefone(telefone);
            usu.setSenha(senha);
            usu.setNasc(nasc);
            usu.setOrganizador(organizador);
            usu.setFotoperfil(fotoBlob);
            usu.setFotoperfilbase64(fotoBase64);

            // comando sql pra salvar

            String sql = "INSERT INTO usu (nome, cpf, email, telefone, senha, nasc, organizador, fotoperfil) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = ConectaDB.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, usu.getNome());
                stmt.setString(2, usu.getCpf());
                stmt.setString(3, usu.getEmail());
                stmt.setString(4, usu.getTelefone());
                stmt.setString(5, usu.getSenha());
                stmt.setDate(6, usu.getNasc());
                stmt.setBoolean(7, usu.isOrganizador());

                if (usu.getFotoperfil() != null) {
                    stmt.setBlob(8, usu.getFotoperfil());
                } else {
                    stmt.setNull(8, java.sql.Types.BLOB);
                }

                stmt.executeUpdate();
            }

            // redireciona para a tela 
            HttpSession session = request.getSession();
session.setAttribute("autorizado", "sim");

request.setAttribute("usuario", usu);
request.getRequestDispatcher("/usucadastrar/usu.jsp").forward(request, response);

        } catch (SQLException e) {
            response.getWriter().println("Erro SQL: " + e.getMessage());
        } catch (Exception e) {
            response.getWriter().println("Erro geral: " + e.getMessage());
        }
    }
}
