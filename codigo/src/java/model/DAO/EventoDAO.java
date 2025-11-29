/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import java.sql.Blob;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Evento;
import config.ConectaDB;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.http.Part;
import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;
/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
// Insere um novo evento no banco de dados
public class EventoDAO {
    
    public void inserir(Evento e) {
    String sql = "INSERT INTO evento (idOrganizador, titulo, descricao, dataHora, preco, status) "
               + "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection con = ConectaDB.conectar();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setInt(1, e.getIdOrganizador());
        stmt.setString(2, e.getTitulo());
        stmt.setString(3, e.getDescricao());
        stmt.setTimestamp(4, Timestamp.valueOf(e.getDataHora()));
        stmt.setDouble(5, e.getPreco());
        stmt.setBoolean(6, e.isStatus());

        stmt.executeUpdate();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
    // Retorna todos os eventos cadastrados
public List<Evento> consultaGeral() throws ClassNotFoundException, SQLException {
    
    List<Evento> lista = new ArrayList<>();

    Connection conn = ConectaDB.conectar();
    String sql = "SELECT * FROM evento";

    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {
        Evento ev = new Evento();
   // Preenche os dados do evento
        ev.setId(rs.getInt("id"));
        ev.setIdOrganizador(rs.getInt("idOrganizador"));
        ev.setTitulo(rs.getString("titulo"));
        ev.setCategoria(rs.getString("categoria"));
        ev.setDescricao(rs.getString("descricao"));
        ev.setCep(rs.getString("cep"));
        ev.setLogradouro(rs.getString("logradouro"));
        ev.setBairro(rs.getString("bairro"));
        ev.setLocalidade(rs.getString("localidade"));
        ev.setEstado(rs.getString("estado"));
        ev.setNumero(rs.getInt("numero"));
        ev.setDataHora(rs.getTimestamp("dataHora").toLocalDateTime());
        ev.setPreco(rs.getDouble("preco"));

          // Converte BLOB para base64 caso exista imagem
        Blob blob = rs.getBlob("imagem");
        if (blob != null) {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            ev.setImagemBase64(Base64.getEncoder().encodeToString(bytes));
        }

        lista.add(ev);
    }

    return lista;
}
// Busca um evento pelo ID informado
public Evento buscarPorId(int id) throws SQLException, ClassNotFoundException {

    Evento ev = null;

    Connection conn = ConectaDB.conectar();
    String sql = "SELECT * FROM evento WHERE id = ?";

    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);

    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        ev = new Evento();
         // Preenche os dados do evento
        ev.setId(rs.getInt("id"));
        ev.setIdOrganizador(rs.getInt("idOrganizador"));
        ev.setTitulo(rs.getString("titulo"));
        ev.setCategoria(rs.getString("categoria"));
        ev.setDescricao(rs.getString("descricao"));
        ev.setCep(rs.getString("cep"));
        ev.setLogradouro(rs.getString("logradouro"));
        ev.setBairro(rs.getString("bairro"));
        ev.setLocalidade(rs.getString("localidade"));
        ev.setEstado(rs.getString("estado"));
        ev.setNumero(rs.getInt("numero"));
        ev.setDataHora(rs.getTimestamp("dataHora").toLocalDateTime());
        ev.setPreco(rs.getDouble("preco"));
        ev.setStatus(rs.getBoolean("status"));

        //converte
        Blob imagemBlob = rs.getBlob("imagem");
        if (imagemBlob != null && imagemBlob.length() > 0) {
            byte[] bytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
            ev.setImagemBase64(Base64.getEncoder().encodeToString(bytes));
        }
    }

    rs.close();
    stmt.close();
    conn.close();

    return ev;
}
//insere presenca na tabela "presenca" juntando as fks
public void darPresenca(int usuId, int eventoId) {

    String sql = "INSERT INTO presenca (idUsu, idEvento) VALUES (?, ?)";

    Connection con = null;
    PreparedStatement stmt = null;

    try {
        con = ConectaDB.conectar();  

        stmt = con.prepareStatement(sql);
        stmt.setInt(1, usuId);
        stmt.setInt(2, eventoId);

        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
// Lista eventos criados por um organizador específico
public List<Evento> listarPorOrganizador(int idOrganizador) 
        throws SQLException, ClassNotFoundException {

    List<Evento> lista = new ArrayList<>();

    Connection conn = ConectaDB.conectar();
    String sql = "SELECT * FROM evento WHERE idOrganizador = ? ORDER BY dataHora DESC";

    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, idOrganizador);

    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
        Evento ev = new Evento();
        ev.setId(rs.getInt("id"));
        ev.setIdOrganizador(rs.getInt("idOrganizador"));
        ev.setTitulo(rs.getString("titulo"));
        ev.setCategoria(rs.getString("categoria"));
        ev.setDescricao(rs.getString("descricao"));
        ev.setCep(rs.getString("cep"));
        ev.setLogradouro(rs.getString("logradouro"));
        ev.setBairro(rs.getString("bairro"));
        ev.setLocalidade(rs.getString("localidade"));
        ev.setEstado(rs.getString("estado"));
        ev.setNumero(rs.getInt("numero"));
        ev.setDataHora(rs.getTimestamp("dataHora").toLocalDateTime());
        ev.setPreco(rs.getDouble("preco"));
        ev.setStatus(rs.getBoolean("status"));

        Blob imagemBlob = rs.getBlob("imagem");
        if (imagemBlob != null && imagemBlob.length() > 0) {
            byte[] bytes = imagemBlob.getBytes(1, (int) imagemBlob.length());
            ev.setImagemBase64(Base64.getEncoder().encodeToString(bytes));
        }

        lista.add(ev);
    }

    rs.close();
    stmt.close();
    conn.close();

    return lista;
}
// atualiza os dados de um evento, com opção de atualizar a imagem
public void atualizar(Evento ev, Part imagemPart)
        throws SQLException, ClassNotFoundException, IOException {

    Connection conn = ConectaDB.conectar();

    String sql;

    // Se veio imagem nova, atualiza o BLOB
    if (imagemPart != null && imagemPart.getSize() > 0) {
        sql = "UPDATE evento SET titulo=?, categoria=?, descricao=?, cep=?, logradouro=?, " +
              "bairro=?, localidade=?, estado=?, numero=?, dataHora=?, preco=?, imagem=? " +
              "WHERE id=?";
    } else {
        sql = "UPDATE evento SET titulo=?, categoria=?, descricao=?, cep=?, logradouro=?, " +
              "bairro=?, localidade=?, estado=?, numero=?, dataHora=?, preco=? " +
              "WHERE id=?";
    }

    PreparedStatement stmt = conn.prepareStatement(sql);

    stmt.setString(1, ev.getTitulo());
    stmt.setString(2, ev.getCategoria());
    stmt.setString(3, ev.getDescricao());
    stmt.setString(4, ev.getCep());
    stmt.setString(5, ev.getLogradouro());
    stmt.setString(6, ev.getBairro());
    stmt.setString(7, ev.getLocalidade());
    stmt.setString(8, ev.getEstado());
    stmt.setInt(9, ev.getNumero());
    stmt.setTimestamp(10, Timestamp.valueOf(ev.getDataHora()));
    stmt.setDouble(11, ev.getPreco());

    if (imagemPart != null && imagemPart.getSize() > 0) {
        stmt.setBlob(12, imagemPart.getInputStream());
        stmt.setInt(13, ev.getId());
    } else {
        stmt.setInt(12, ev.getId());
    }

    stmt.execute();
    stmt.close();
    conn.close();
}
// Remove um evento do banco de dados pelo ID
public void remover(int idEvento) throws Exception {
    String sql = "DELETE FROM evento WHERE id = ?";

    try (Connection con = ConectaDB.conectar();
         PreparedStatement stmt = con.prepareStatement(sql)) {

        stmt.setInt(1, idEvento);
        stmt.executeUpdate();
    } catch (Exception e) {
        throw new Exception("Erro ao remover evento: " + e.getMessage());
    }
}
}