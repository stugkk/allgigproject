/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

/**
 *
 * @author guilh
 */
import config.ConectaDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Presenca;
import model.Evento;
//registra a preseça 
public class PresencaDAO {
        public boolean registrarPresenca(int idUsu, int idEvento) throws SQLException, ClassNotFoundException {

            String sql = "INSERT INTO presenca (idUsu, idEvento) VALUES (?, ?)";

            try (Connection conn = ConectaDB.conectar();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, idUsu);
                stmt.setInt(2, idEvento);

                return stmt.executeUpdate() > 0;
            }
        }
     public List<Presenca> listarPorUsuario(int idUsu) throws ClassNotFoundException {

        List<Presenca> lista = new ArrayList<>();

        String sql = "SELECT p.id, p.idEvento, p.dataHoraInscricao, e.titulo AS eventoTitulo "
           + "FROM presenca p "
           + "INNER JOIN evento e ON p.idEvento = e.id "
           + "WHERE p.idUsu = ? "
           + "ORDER BY p.dataHoraInscricao DESC";
        
        try (Connection conn = ConectaDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsu);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                Presenca p = new Presenca();
                
                p.setIdEvento(rs.getInt("idEvento"));
                p.setIdPresenca(rs.getInt("id"));
                p.setEventoNome(rs.getString("eventoTitulo"));
                p.setDataHoraInscricao(rs.getTimestamp("dataHoraInscricao"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar presenças: " + e.getMessage());
        }

        return lista;
    }


    // DELETAR PRESENÇA
public boolean remover(int id) throws ClassNotFoundException {

    String sql = "DELETE FROM presenca WHERE id = ?";

    try (Connection conn = ConectaDB.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Erro ao remover presença: " + e.getMessage());
        return false;
    }
}
    public int contarPorEvento(int idEvento) throws ClassNotFoundException {

    String sql = "SELECT COUNT(*) FROM presenca WHERE idEvento = ?";

    try (Connection conn = ConectaDB.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, idEvento);

        ResultSet rs = stmt.executeQuery();
        return rs.next() ? rs.getInt(1) : 0;

    } catch (SQLException e) {
        System.out.println("Erro ao contar presenças: " + e.getMessage());
    }

    return 0;
}
}

