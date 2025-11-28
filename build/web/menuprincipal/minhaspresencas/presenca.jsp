<%-- 
    Document   : presenca
    Created on : 26 de nov. de 2025, 20:59:54
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Presenca"%>

<%@page import="model.Usu"%>

<%@page import="java.util.List"%>
<%@page import="model.DAO.PresencaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Usuário logado
    Usu usuario = (Usu) session.getAttribute("usuarioLogado");

    if (usuario == null) {
        response.sendRedirect("../login/login.jsp");
        return;
    }

    int idUsu = usuario.getId();

    PresencaDAO dao = new PresencaDAO();
    List<Presenca> lista = dao.listarPorUsuario(idUsu);
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Minhas Participações</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <link href="../style/geral.css" rel="stylesheet" type="text/css"/> 

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
        <h2>Minhas Participações</h2>
        
        <table class="modern-table">
            <tr>
                <th>ID Presença</th>
                <th>Evento</th>
                <th>Data/Hora da Inscrição</th>
                <th>Apagar</th>
                <th>Ampliar</th>
            </tr>

            
            <%
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                for (Presenca p : lista) {
            %>
                <tr>
                    <td><%= p.getIdPresenca() %></td>
                    <td><%= p.getEventoNome() %></td>
                    <td><%= sdf.format(p.getDataHoraInscricao()) %></td>

                    <!-- Botão deletar -->
                    <td>
                        <a href="removerPresenca.jsp?id=<%= p.getIdPresenca() %>" 
   onclick="return confirm('Deseja realmente remover esta presença?')">
    🗑
</a>
                    </td>
                    <td>
      <a href="<%= request.getContextPath() %>/EventoCarregar?id=<%= p.getIdEvento() %>">🔎</a>

                    </td>
                </tr>
            <% } %>

            <% if (lista.isEmpty()) { %>
                <tr>
                    <td colspan="4">Nenhuma presença encontrada.</td>
                </tr>
            <% } %>
        </table>
    </div>

</body>
</html>
