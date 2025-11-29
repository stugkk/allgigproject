<%-- 
    Document   : meuseventos
    Created on : 26 de nov. de 2025, 23:40:24
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="model.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../../style/geral.css" rel="stylesheet" type="text/css"/>
        <title>Meus Eventos</title>
        <style>
             h2{ color:#ffffff
            
                }
        body {
           background-color: #8a5696;
            font-family: roboto;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background: #eee;
        }

        .container {
            width: 95%;
            margin: auto;
            text-align: center;
        }

        a.delete-btn {
            color: red;
            font-size: 20px;
            text-decoration: none;
        }

        .table-container {
        width: 95%;
        margin: 20px auto;
        overflow-x: auto;
        }

        .modern-table {
        width: 100%;
        border-collapse: collapse;
        background: #ffffff;
        border-radius: 12px;
        overflow: hidden;
        box-shadow: 0px 8px 25px rgba(0, 0, 0, 0.35);
        font-family: "Segoe UI", Arial, sans-serif;
        }

     
        .modern-table thead {
        background: #4b0082; 
        color: #fff;
        }

        .modern-table th {
        padding: 14px 16px;
        text-align: left;
        font-size: 15px;
        font-weight: 600;
        letter-spacing: 0.3px;
        }

        .modern-table td {
        padding: 12px 16px;
        font-size: 14px;
        color: #333;
        border-bottom: 1px solid #e8e8e8;
        }

      
        .modern-table tbody tr:hover {
        background-color: #f7f2ff;
        transition: 0.2s ease-in-out;
        }

    
        .table-empty {
        padding: 20px;
        text-align: center;
        font-size: 15px;
        color: #555;
        }

        .btn-delete {
        padding: 8px 14px;
        background: #ff4444;
        color: white;
        border-radius: 6px;
        text-decoration: none;
        font-size: 13px;
        transition: 0.2s;
        }

        .btn-delete:hover {
        background: #cc0000;
        }

    
        @media(max-width: 768px) {
        .modern-table th, .modern-table td {
        padding: 10px;
        font-size: 13px;
        }
}
        </style>
    </head>
    <body>
        <div class="table-container">
       <table class="modern-table">
    <tr>
        <th>Id</th>
        <th>Título</th>
        <th>Data</th>
        <th>Categoria</th>
        <th>Preço</th>
        <th>Ver</th>
        <th>Editar</th>
        <th>Deletar</th>
    </tr>

<% 
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    List<Evento> lista = (List<Evento>) request.getAttribute("listaEventos");
    for (Evento e : lista) {
%>
    <tr>
        <td><%= e.getId() %></td>
        <td><%= e.getTitulo() %></td>
        <td><%= e.getDataHora() %></td>
        <td><%= e.getCategoria() %></td>
        <td>R$ <%= e.getPreco() %></td>

        <!-- VER EVENTO -->
        <td>
            <a href="EventoCarregar?id=<%= e.getId() %>">🔎</a>
        </td>

        <!-- EDITAR EVENTO -->
        <td>
            <a href="/Allgig/EventoEditar?id=<%= e.getId() %>">✏️</a>
        </td>

    
        <td>
            <a href="/Allgig/menuprincipal/meuseventos/removerEvento.jsp?id=<%= e.getId() %>" onclick="return confirm('Deseja remover este evento?')"> 🗑️</a>
        </td>
    </tr>
<% } %>
        </div>
</table>    </body>
</html>
