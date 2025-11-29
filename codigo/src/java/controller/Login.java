package controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO.UsuDAO;
import model.Usu;

/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuDAO dao = new UsuDAO();
        Usu usu = null;

        try {
            //autentica e obtém dados básicos do usuário 
            usu = dao.logar(email, senha); //retorna usu ou null
            if (usu == null) {
                // login inválido
                request.setAttribute("erro", "Email ou senha incorretos!");
                request.getRequestDispatcher("index.html").forward(request, response);
                return;
            }

      

            //cria sessão e guarda o usuário
            HttpSession sessao = request.getSession(); 

            sessao.setAttribute("idUsuario", usu.getId());
            
            String base64Foto = dao.carregarFotoBase64(usu.getId()); //buscar a foto e converter para base64 
            sessao.setAttribute("fotoBase64", base64Foto);
            sessao.setAttribute("fotoMime", "image/jpeg"); 
            sessao.setAttribute("usuarioLogado", usu);

            //redireciona para a home
            response.sendRedirect("menuprincipal/index.jsp");

        } catch (ClassNotFoundException e) {
            throw new ServletException("Driver JDBC não encontrado.", e);
        } catch (Exception e) {
            throw new ServletException("Erro ao efetuar login.", e);
        }
    }
}
