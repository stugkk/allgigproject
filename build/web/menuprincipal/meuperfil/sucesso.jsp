<%-- 
    Document   : index
    Created on : 23 de nov. de 2025, 22:32:30
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page import="model.Evento" %>
<!DOCTYPE html>
<html data-bs-theme="auto">
    <head>
        <title>Perfil Cadastrado com Sucesso</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="${pageContext.request.contextPath}/style/geral.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <style>
            html[data-bs-theme="dark"] body {
  background-color: #581d66 !important;
  color: white !important;
}

html[data-bs-theme="light"] body {
  background-color: white !important;
  color: black !important;
}
        </style>
    </head>

<body>
<div class="container">
    <h2>Usuário Atualizado com Sucesso!</h2>

         <a href="#"  onclick="abrirPerfil()" class="btn-link">← Voltar ao Perfil</a>

</div>
    <script>
    /*codigo para receber o comando do botao no index principal */
            window.addEventListener("message", event => {
                if (event.data.theme) {
                    document.documentElement.setAttribute("data-bs-theme", event.data.theme);
                }
            });
           
function abrirPerfil() {
    const frame = document.getElementById("conteudo");
    if (frame) {
        frame.src = "/Allgig/perfil"; // chama o servlet Home que devolve home.jsp
    } else {
        window.location.href = "/Allgig/perfil"; // fallback caso não exista iframe
    }
}

</script>
</body>
</html>