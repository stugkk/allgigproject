<%-- 
    Document   : evento
    Created on : 25 de nov. de 2025, 20:23:39
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>

<%
    model.Usu usuario = (model.Usu) session.getAttribute("usuarioLogado");
 
    
    Evento ev = (Evento) request.getAttribute("evento");
%>


<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.Evento"%>
<%@page import="model.Usu"%>

<%

    if (ev == null) {
        out.print("<h2>Evento não encontrado.</h2>");
        return;
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title><%= ev.getTitulo() %> - Detalhes do Evento</title>

    <link href="${pageContext.request.contextPath}/style/geral.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/evento.css" rel="stylesheet">
    <link href="../../style/geral.css" rel="stylesheet" type="text/css"/>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: #f8f4fa;
            padding: 20px;
        }

        .evento-container {
            max-width: 900px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 15px;
            box-shadow: 0px 4px 12px rgba(0,0,0,0.1);
        }

        .evento-imagem {
            width: 100%;
            border-radius: 12px;
            margin-bottom: 20px;
        }

        .titulo {
            font-size: 2.2rem;
            font-weight: bold;
            color: #581d66;
        }

        .categoria {
            font-size: 1rem;
            color: #888;
            margin-bottom: 10px;
        }

        .descricao {
            margin: 15px 0;
            font-size: 1.1rem;
            line-height: 1.6;
        }

        .bloco-info {
           
            background: #581b67;
            padding: 15px;
            border-radius: 10px;
            margin-top: 20px;
        }

        .presenca-btn {
            background: #581d66;
            color: white;
            padding: 14px 25px;
            border: none;
            border-radius: 10px;
            font-size: 1.1rem;
            cursor: pointer;
            margin-top: 15px;
            width: 100%;
            transition: 0.3s;
        }

        .presenca-btn:hover {
            background: #7c2d8e;
        }

        .voltar {
            display: block;
            text-align: center;
            margin-top: 25px;
            font-size: 1rem;
            color: #581d66;
            text-decoration: none;
        }

    </style>
</head>

<body>
<%
    String idE = request.getParameter("idEvento");
    out.print("ID recebido: " + idE);
%>
<div class="evento-container">

    <%-- foto evento --%>
    <% if (ev.getImagemBase64() != null) { %>
        <img src="data:image/jpeg;base64,<%= ev.getImagemBase64() %>"
             class="evento-imagem">
    <% } %>

    <div class="titulo"><%= ev.getTitulo() %></div>
    <div class="categoria">Categoria: <%= ev.getCategoria() %></div>
    <div class="descricao"><%= ev.getDescricao() %></div>
    <div class="bloco-info">
        <b>Data e Hora:</b> <%= ev.getDataHora() %><br>
        <b>Preço:</b> R$ <%= ev.getPreco() %><br><br>

        <b>Local:</b><br>
        <%= ev.getLogradouro() %>, nº <%= ev.getNumero() %><br>
        <%= ev.getBairro() %> – <%= ev.getLocalidade() %> / <%= ev.getEstado() %><br>
        CEP: <%= ev.getCep() %>
    </div>
<%= usuario %>
    <%-- FORMULÁRIO DE PRESENÇA — envia ao servlet --%>
   <form action="ConfirmarPresenca" method="post">
    <input type="hidden" name="idUsu" value="<%= usuario != null ? usuario.getId() : -1 %>" />
    <input type="hidden" name="idEvento" value="${evento.id}" />

    <button type="submit" class="btn btn-success">
        Confirmar presença
    </button>
</form>


    <a href="#" class="btn-link" onclick="abrirHome()">← Voltar ao Feed</a>
    
</div>
<script>
function abrirHome() {
    const frame = document.getElementById("conteudo");
    if (frame) {
        frame.src = "/Allgig/home"; // chama o servlet Home que devolve home.jsp
    } else {
        window.location.href = "/Allgig/home"; // fallback caso não exista iframe
    }
}
</script>
</body>
</html>