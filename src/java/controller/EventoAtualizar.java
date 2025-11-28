package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import model.Evento;
import model.DAO.EventoDAO;

/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
@WebServlet(name="EventoAtualizar", urlPatterns={"/EventoAtualizar"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) 
public class EventoAtualizar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");

            // Campos recebidos
            int id = Integer.parseInt(req.getParameter("id"));
            String titulo = req.getParameter("titulo");
            String categoria = req.getParameter("categoria");
            String descricao = req.getParameter("descricao");

            String cep = req.getParameter("cep");
            String logradouro = req.getParameter("logradouro");
            String bairro = req.getParameter("bairro");
            String localidade = req.getParameter("localidade");
            String estado = req.getParameter("estado");
            int numero = Integer.parseInt(req.getParameter("numero"));

            String dataHora = req.getParameter("dataHora");
            String precoStr = req.getParameter("preco");
            double preco = (precoStr == null || precoStr.isEmpty()) ? 0.0 : Double.parseDouble(precoStr);

            // Parte do arquivo 
            Part imagemPart = req.getPart("imagem"); 

            // Cria o objeto Evento 
            Evento ev = new Evento();
            ev.setId(id);
            ev.setTitulo(titulo);
            ev.setCategoria(categoria);
            ev.setDescricao(descricao);
            ev.setCep(cep);
            ev.setLogradouro(logradouro);
            ev.setBairro(bairro);
            ev.setLocalidade(localidade);
            ev.setEstado(estado);
            ev.setNumero(numero);
            ev.setPreco(preco);

            // Converte data/hora corretamente 
            if (dataHora != null && !dataHora.isEmpty()) {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime dt = LocalDateTime.parse(dataHora, fmt);
                ev.setDataHora(dt);
            }

            // Chama o DAO 
            EventoDAO dao = new EventoDAO();
            dao.atualizar(ev, imagemPart);

            // Redirect para sucesso 
            resp.sendRedirect("menuprincipal/meuseventos/sucesso.jsp");

        } catch (Exception e) {
            e.printStackTrace(); 
            req.setAttribute("erro", "Erro ao atualizar evento: " + e.getMessage());
            req.getRequestDispatcher("erro.jsp").forward(req, resp);
        }
    }
}