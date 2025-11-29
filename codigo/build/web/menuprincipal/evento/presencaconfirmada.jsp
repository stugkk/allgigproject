<%-- 
    Document   : presencaconfirmada
    Created on : 25 de nov. de 2025, 23:09:18
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../../style/feed.css" rel="stylesheet" type="text/css"/>
        <link href="../../style/geral.css" rel="stylesheet" type="text/css"/>
        <style>

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
.btn-link {
    display: inline-block;
    padding: 10px 20px;
    background-color: #6f1d8a;
    color: #fff;
    text-decoration: none;
    border-radius: 10px;
    font-weight: bold;
    transition: 0.3s;
}

.btn-link:hover {
    background-color: #581d66;
    transform: scale(1.03);
}
        </style>
    </head>
    <body>
        <form class="feed-container">
        <div class="perfil-box">
            
            <h1 class="titulo">Presença Confirmada!</h1><br>
            <p>    <a href="#"  onclick="abrirHome()" class="btn-link">← Voltar ao Feed</a>
        </div>
        </form>
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
