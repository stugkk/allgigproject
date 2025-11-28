/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;


import com.mysql.cj.jdbc.Blob;
import config.ConectaDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import model.Usu;
import config.ConectaDB;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

/**
 *
 * @author guilh
 */
public class UsuDAO {

    public boolean cadastrar(Usu usu) {

        String sql = "INSERT INTO usu (nome, email, telefone, senha, nasc, organizador, cpf) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null; 
        
        try {
            // Conecta ao Banco de Dados 
            conn = ConectaDB.conectar();
            // Prepara a instrução sql
            pstmt = conn.prepareStatement(sql);       
            pstmt.setString(1, usu.getNome());       
            pstmt.setString(2, usu.getEmail());       
            pstmt.setString(3, usu.getTelefone());   
            pstmt.setString(4, usu.getSenha());
            
            Date dataNascimento = usu.getNasc();
            java.sql.Date sqlDate = new java.sql.Date(dataNascimento.getTime());
            pstmt.setDate(5, sqlDate);
            
           
            pstmt.setBoolean(6, usu.isOrganizador()); 
            
          
            pstmt.setString(7, usu.getCpf());          
            
            
            int row = pstmt.executeUpdate();
            
            return row > 0; // Retorna true se a inserção foi bem-sucedida
            
        } catch (SQLException | ClassNotFoundException ex) {
    ex.printStackTrace(); 
    System.err.println("ERRO ao cadastrar usuário: " + ex.getMessage());
    return false;
}   
            finally {
            
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.err.println("ERRO ao fechar recursos: " + e.getMessage());
            }
        }
    }


public Usu logar(String login, String senha) throws ClassNotFoundException {
    String sql = "SELECT * FROM usu WHERE email = ? AND senha = ?";

    try (Connection conn = ConectaDB.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, login);
        ps.setString(2, senha);

        ResultSet rs = (ResultSet) ps.executeQuery();

        if (rs.next()) {
            Usu usu = new Usu();
            usu.setId(rs.getInt("id"));
            usu.setNome(rs.getString("nome"));
            usu.setEmail(rs.getString("email"));
            usu.setSenha(rs.getString("senha"));
            usu.setFotoperfilbase64(rs.getString("fotoperfil")); 

            return usu;
        }

        return null;

    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

public String carregarFotoBase64(int idUsuario) {
    String sql = "SELECT fotoperfil FROM usu WHERE id = ?";

    try (Connection conn = ConectaDB.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Blob blob = (Blob) rs.getBlob("fotoperfil");
            if (blob == null) return null;

            byte[] bytes = blob.getBytes(1, (int) blob.length());

            // retorna base64
            return Base64.getEncoder().encodeToString(bytes);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
//busca o usuario pelo id
public Usu buscarPorId(int id) throws SQLException, ClassNotFoundException {

    Usu usu = null;

    Connection conn = ConectaDB.conectar();
    String sql = "SELECT * FROM usu WHERE id = ?";

    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, id);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {

        usu = new Usu();

        usu.setId(rs.getInt("id"));
        usu.setNome(rs.getString("nome"));
        usu.setEmail(rs.getString("email"));
        usu.setTelefone(rs.getString("telefone"));
        usu.setSenha(rs.getString("senha"));
        usu.setNasc(rs.getDate("nasc"));
        usu.setCpf(rs.getString("cpf"));
        usu.setOrganizador(rs.getBoolean("organizador"));

        System.out.println("Buscando usuário com ID = " + id);

        // FOTO para Base64 
        Blob blob = (Blob) rs.getBlob("fotoperfil");

        if (blob != null && blob.length() > 0) {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            usu.setFotoperfilbase64(Base64.getEncoder().encodeToString(bytes));
        } else {
            usu.setFotoperfilbase64(null); 
        }
    }

    rs.close();
    stmt.close();
    conn.close();

    return usu;
}
//atualiza a foto
public void atualizarUsuario(Usu usuario, InputStream fotoStream) throws SQLException, ClassNotFoundException {
    String sql;

    // Se veio foto nova inclui no update
    if (fotoStream != null) {
        sql = "UPDATE usu SET nome=?, cpf=?, email=?, telefone=?, senha=?, nasc=?, organizador=?, fotoperfil=? WHERE id=?";
    } else {
        sql = "UPDATE usu SET nome=?, cpf=?, email=?, telefone=?, senha=?, nasc=?, organizador=? WHERE id=?";
    }

    try (Connection conn = ConectaDB.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getCpf());
        stmt.setString(3, usuario.getEmail());
        stmt.setString(4, usuario.getTelefone());
        stmt.setString(5, usuario.getSenha());
        stmt.setDate(6, usuario.getNasc());
        stmt.setBoolean(7, usuario.isOrganizador());

        if (fotoStream != null) {
            stmt.setBlob(8, fotoStream);
            stmt.setInt(9, usuario.getId());
        } else {
            stmt.setInt(8, usuario.getId());
        }

        stmt.executeUpdate();
    }
}
//deleta o usuario
public void delete(int id) throws SQLException, ClassNotFoundException {

    Connection conn = ConectaDB.conectar();

    //deleta primeiro as presencas
    String sqlPresenca = "DELETE FROM presenca WHERE idUsu = ?";
    PreparedStatement stmt1 = conn.prepareStatement(sqlPresenca);
    stmt1.setInt(1, id);
    stmt1.executeUpdate();
    stmt1.close();

    //delete usuario
    String sqlUsu = "DELETE FROM usu WHERE id = ?";
    PreparedStatement stmt2 = conn.prepareStatement(sqlUsu);
    stmt2.setInt(1, id);
    stmt2.executeUpdate();
    stmt2.close();

    conn.close();

    System.out.println("Usuario e presencas deletados com sucesso! ID: " + id);
}

}
