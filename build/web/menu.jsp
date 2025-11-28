<%-- 
    Document   : menu
    Created on : 17 de nov. de 2025, 21:21:38
    Author     : guilh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bem-Vindo Jps</title>
         <link href="style/geral.css" rel="stylesheet" type="text/css"/>
         <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
         <style>
.perfil-box {
    width: 330px;
    padding: 25px 20px;
    border-radius: 16px;
    background: rgba(255,255,255,0.10);
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
    box-shadow: 0 0 15px rgba(0,0,0,0.35);
    text-align: center;
    font-family: 'Roboto', sans-serif;
    color: #fff;
    margin: 0 auto;
}


.titulo {
    font-size: 26px;
    font-weight: 800;
    margin-bottom: 5px;
    letter-spacing: 1px;
}

.subtitulo {
    font-size: 14px;
    font-weight: 300;
    opacity: 0.85;
    margin-top: -5px;
}


.logo {
    width: 130px;
    margin-top: 12px;
    margin-bottom: 10px;
}


.bemvindo {
    font-size: 16px;
    margin-bottom: 15px;
    font-weight: 500;
}


.btn-continuar {
    display: inline-block;
    background: #8a2be2;
    padding: 10px 22px;
    color: white;
    border-radius: 8px;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
    transition: 0.2s;
}

.btn-continuar:hover {
    transform: scale(1.06);
    background: #7020b8;
}

         </style>
    </head>
   
<body>
 
<div class="perfil-box">
    <h1 class="titulo">ALL GIG!</h1>
    <h2 class="subtitulo">O evento mais proximo de voce</h2>

    <%
        if ("sim".equals(session.getAttribute("autorizado"))) {
    %>

    <img src="imagens/logo7.png" class="logo">

    <p class="bemvindo">Bem-vindo(a)!</p>

    <a href="menuprincipal/index.jsp" class="btn-continuar">Continuar</a>

    <%
        } else {
            response.sendRedirect("index.html");
        }
    %>
    </div>
    </body>
