/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author: guilherme campos 11241102449 enzo romao 11241105170
 */

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Presenca {
    
    private int id;
    private Timestamp dataHoraInscricao;
    private String eventoNome;
  private int idEvento;

//getters
    
public int getIdEvento() {
    return this.idEvento;
}
    public int getIdPresenca() { 
        return id; }
    public void setIdPresenca(int idPresenca) {
        this.id = idPresenca; }

    public Timestamp getDataHoraInscricao() { return dataHoraInscricao; }
    public void setDataHoraInscricao(Timestamp dataHoraInscricao) { this.dataHoraInscricao = dataHoraInscricao; }

    public String getEventoNome() { return eventoNome; }
    public void setEventoNome(String eventoNome) { this.eventoNome = eventoNome; }

    public String getDataFormatada() {
        if (dataHoraInscricao == null) return "";
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dataHoraInscricao);
    }
//setter
    public void setIdEvento(int id) {
        this.idEvento = id;
    }
}
