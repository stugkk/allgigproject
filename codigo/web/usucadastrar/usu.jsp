<%-- 
    Created on : 17 de nov. de 2025, 
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuário Cadastrado com Sucesso!</title>
         <link href="style/geral.css" rel="stylesheet" type="text/css"/>
         <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
         <style>


.perfil-box {
    width: 330px;
    padding: 25px 20px;
    border-radius: 16px;
    background: rgba(255,255,255,0.10);
    box-shadow: 0 0 18px rgba(0,0,0,0.35);
    backdrop-filter: blur(10px);
    -webkit-backdrop-filter: blur(10px);
    text-align: center;
}

.titulo {
    font-size: 26px;
    font-weight: 800;
    margin-bottom: 2px;
    letter-spacing: 1px;
}

.subtitulo {
    font-size: 14px;
    font-weight: 300;
    opacity: 0.85;
    margin-top: -2px;
}

.logo {
    display: block;
    margin: 0 auto;     
    width: 130px;          
    height: auto;
}
.perfil-box p {
    margin-top: 10px;
    font-size: 16px;
    font-weight: 500;
}

.voltar-btn {
    display: inline-block;
    margin-top: 15px;
    background: #8a2be2;
    padding: 10px 25px;
    color: #fff;
    border-radius: 8px;
    font-size: 15px;
    font-weight: bold;
    text-decoration: none;
    transition: 0.25s ease;
}

.voltar-btn:hover {
    transform: scale(1.08);
    background: #7020b8;
}
         </style>
    </head>
   
<body>
    <div class="perfil-box">
        <h1 class="titulo">ALL GIG!</h1>
        <h2 class="subtitulo">O evento mais proximo de voce</h2>
        <img src="imagens/logo7.png" class="logo">

        <p>Parabéns! Cadastro concluído com sucesso!</p>

        <a href="./index.html" class="voltar-btn">Voltar</a>
    </div>
</body>
