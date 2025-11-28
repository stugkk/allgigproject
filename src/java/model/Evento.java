/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDateTime;
import java.sql.Blob;   
/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */
public class Evento {

    private int id;
    private int idOrganizador;      // FK para tabela usuario
    private String titulo;
    private String categoria;
    private String descricao;
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String estado;
    private int numero;
    private LocalDateTime dataHora;
    private Double preco;
    private boolean status;
    private Blob imagem;       
    private String imagemBase64; 
    
    
    
//getters
    public String getCep() {
        return this.cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getLocalidade() {
        return this.localidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public int getNumero() {
        return this.numero;
    }


    public int getId() {
        return this.id;
    }

    public int getIdOrganizador() {
        return this.idOrganizador;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public Double getPreco() {
        return this.preco;
    }

    public boolean isStatus() {
        return this.status;
    }

    public Blob getImagem() {
        return this.imagem;
    }

    public String getImagemBase64() {
        return this.imagemBase64;
    }

//setters

    public void setId(int p_id) {
        this.id = p_id;
    }

    public void setIdOrganizador(int p_idOrganizador) {
        this.idOrganizador = p_idOrganizador;
    }

    public void setCep(String p_cep) {
        this.cep = p_cep;
    }

    public void setLogradouro(String p_logradouro) {
        this.logradouro = p_logradouro;
    }

    public void setBairro(String p_bairro) {
        this.bairro = p_bairro;
    }

    public void setLocalidade(String p_localidade) {
        this.localidade = p_localidade;
    }

    public void setEstado(String p_estado) {
        this.estado = p_estado;
    }

    public void setNumero(int p_numero) {
        this.numero = p_numero;
    }


    public void setTitulo(String p_titulo) {
        this.titulo = p_titulo;
    }

    public void setCategoria(String p_categoria) {
        this.categoria = p_categoria;
    }

    public void setDescricao(String p_descricao) {
        this.descricao = p_descricao;
    }

    public void setDataHora(LocalDateTime p_dataHora) {
        this.dataHora = p_dataHora;
    }

    public void setPreco(Double p_preco) {
        this.preco = p_preco;
    }

    public void setStatus(boolean p_status) {
        this.status = p_status;
    }

    public void setImagem(Blob p_imagem) {
        this.imagem = p_imagem;
    }

    public void setImagemBase64(String p_imagemBase64) {
        this.imagemBase64 = p_imagemBase64;
    }

    public void setDataHoraString(String dataHora) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}