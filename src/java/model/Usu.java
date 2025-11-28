/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Blob;
import java.sql.Date;
import java.time.LocalDateTime;
/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
public class Usu {
    private int id; 
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha; 
    private Date nasc;
    private boolean organizador;
    private Blob fotoperfil;       
    private String fotoperfilbase64; 


   //getters
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getSenha() {
        return this.senha;
    }

    public Date getNasc() {
        return this.nasc;
    }


    public boolean isOrganizador() {
        return this.organizador;
    }
     public Blob getFotoperfil() {
        return this.fotoperfil;
    }

    public String getFotoperfilbase64() {
        return this.fotoperfilbase64;
    }
   
    
    
//setters
    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setNome(String p_nome) {
        this.nome = p_nome;
    }

    public void setCpf(String p_cpf) {
        this.cpf = p_cpf;
    }

    public void setEmail(String p_email) {
        this.email = p_email;
    }

    public void setTelefone(String p_telefone) {
        this.telefone = p_telefone;
    }

    public void setSenha(String p_senha) {
        this.senha = p_senha;
    }

    public void setNasc(Date p_nasc) {
        this.nasc = p_nasc;
    }


    public void setOrganizador(boolean p_organizador) {
        this.organizador = p_organizador;
    }

    public void setFotoperfil(Blob p_fotoperfil) {
        this.fotoperfil = p_fotoperfil;
    }

    public void setFotoperfilbase64(String p_fotoperfilbase64) {
        this.fotoperfilbase64 = p_fotoperfilbase64;
    }

    public void setFotoBase64(String encodeToString) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
    

