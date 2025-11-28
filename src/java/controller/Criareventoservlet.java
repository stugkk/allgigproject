package controller;

import config.ConectaDB;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import javax.servlet.RequestDispatcher;

import model.Evento;
import model.Usu;
/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
//cria o evento com a foto
@WebServlet("/Criareventoservlet")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class Criareventoservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            
              // pega o usuario
            Usu usuarioLogado = (Usu) request.getSession().getAttribute("usuarioLogado");

        if (usuarioLogado == null) {
            response.sendRedirect("index.html"); // usuário não logado
            return;
        }

        int idOrganizador = usuarioLogado.getId();  

            // recebe os dados

            String titulo = request.getParameter("titulo");
            String categoria = request.getParameter("categoria");
            String descricao = request.getParameter("descricao");
            String cep = request.getParameter("cep");
            String logradouro = request.getParameter("logradouro");
            String bairro = request.getParameter("bairro");
            String localidade = request.getParameter("localidade");
            String estado = request.getParameter("estado");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String dataHoraStr = request.getParameter("dataHora");
            String precoStr = request.getParameter("preco");
            Double preco = Double.parseDouble(precoStr);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

          

            // imagem

            Part filePart = request.getPart("imagem");
            InputStream arquivoImagem = filePart.getInputStream();
            byte[] imagemBytes = arquivoImagem.readAllBytes();
            String imagemBase64 = Base64.getEncoder().encodeToString(imagemBytes);

            // instancia do objeto evento

            Evento evento = new Evento();
            evento.setTitulo(titulo);
            evento.setCategoria(categoria);
            evento.setDescricao(descricao);

            evento.setCep(cep);
            evento.setLogradouro(logradouro);
            evento.setBairro(bairro);
            evento.setLocalidade(localidade);
            evento.setEstado(estado);
            evento.setNumero(numero);

            evento.setIdOrganizador(idOrganizador);
            evento.setDataHora(dataHora);
            evento.setPreco(preco);
            evento.setStatus(true);
            evento.setImagemBase64(imagemBase64);

            // sql de inserção

            String sql = "INSERT INTO evento "
                    + "(idOrganizador, titulo, categoria, descricao, cep, logradouro, bairro, localidade, estado, numero, dataHora, preco, status, imagem) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (Connection conn = ConectaDB.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, evento.getIdOrganizador());
                stmt.setString(2, evento.getTitulo());
                stmt.setString(3, evento.getCategoria());
                stmt.setString(4, evento.getDescricao());
                stmt.setString(5, evento.getCep());
                stmt.setString(6, evento.getLogradouro());
                stmt.setString(7, evento.getBairro());
                stmt.setString(8, evento.getLocalidade());
                stmt.setString(9, evento.getEstado());
                stmt.setInt(10, evento.getNumero());
                stmt.setObject(11, evento.getDataHora());
                stmt.setDouble(12, evento.getPreco());
                stmt.setBoolean(13, evento.isStatus());
                stmt.setBytes(14, imagemBytes);

                stmt.executeUpdate();
            }

            request.setAttribute("evento", evento);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/menuprincipal/criarevento/sucesso.jsp");
            dispatcher.forward(request, response);



        } catch (SQLException e) {
            response.getWriter().println("Erro SQL: " + e.getMessage());
        } catch (Exception e) {
            response.getWriter().println("Erro geral: " + e.getMessage());
        }
    }
}
