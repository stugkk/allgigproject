<%-- 
    Created on : 17 de nov. de 2025, 
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Evento" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Feed de Eventos</title>
  <link href="${pageContext.request.contextPath}/style/geral.css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/style/feed.css" rel="stylesheet" />
    <style>

   html[data-bs-theme="dark"] body {
  background-color: #581d66 !important;
  color: white !important;
}

html[data-bs-theme="light"] body {
  background-color: white !important;
  color: black !important;
}
/* Remove estilo de link */
.link-card {
    text-decoration: none;
    color: inherit;
    display: block;
}


.link-card .feed-card {
    transition: transform .2s ease-in-out, box-shadow .2s ease-in-out;
    cursor: pointer;
}

.link-card .feed-card:hover {
    transform: scale(1.02);
    box-shadow: 0 8px 20px rgba(0,0,0,0.3);
}
</style>
</head>

<body>
    <form>
<div class="feed-container">

    <%
        List<Evento> lista = (List<Evento>) request.getAttribute("listaEventos");

        if (lista == null || lista.isEmpty()) {
    %>

        <h2>Nenhum evento encontrado.</h2>

    <% 
        } else { 

            for (Evento ev : lista) {
    %>
   <br><br><br>
   <a href="${pageContext.request.contextPath}/evento?id=<%= ev.getId() %>" class="link-card">
   <div class="feed-card">
        <div class="card">
 
            <% if (ev.getImagemBase64() != null) { %>
                <img src="data:image/jpeg;base64,<%= ev.getImagemBase64() %>"  class="feed-image"/>
            <% } %>

            <div class="titulo" class="titulo"><%= ev.getTitulo() %></div>
            <div class="categoria"class="categoria">Categoria: <%= ev.getCategoria() %></div>

            <div class="descricao"class="descricao"><%= ev.getDescricao() %></div>

            <div class="preco" class="preco">R$ <%= ev.getPreco() %></div>

            <div class="data" class="data">
                <b>Data:</b> <%= ev.getDataHora() %>
            </div>

        </div>
            
</div>
    <%
            }
        }
    %>

</div>
    </form>
    <script>
   /*codigo para receber o comando do botao no index principal */
            window.addEventListener("message", event => {
                if (event.data.theme) {
                    document.documentElement.setAttribute("data-bs-theme", event.data.theme);
                }
            });
            </script>
</body>
</html>