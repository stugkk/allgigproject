<%-- 
    Document   : perfilupdate
    Created on : 26 de nov. de 2025, 00:05:49
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>
<%
Usu usuario = (Usu) session.getAttribute("usuarioLogado");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Usu"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../../style/geral.css" rel="stylesheet" type="text/css"/>
        <link href="../../style/custom.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <title>Perfil Update</title>
        <style>     
         body {
        background: #4b0a63;
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        color: #fff;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .perfil-box {
        background: rgba(255, 255, 255, 0.1);
        padding: 30px;
        border-radius: 15px;
        width: 450px;
        text-align: center;
        box-shadow: 0 0 20px rgba(0,0,0,0.4);
    }

    .foto {
        width: 150px;
        height: 150px;
        border-radius: 50%;
        overflow: hidden;
        margin: 0 auto 20px auto;
        border: 4px solid #fff;
    }

    .foto img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    #preview {
    width: 150px;
    height: 150px;
    object-fit: cover;
    border-radius: 50%;
    border: 3px solid #6a0dad;
    box-shadow: 0 0 10px rgba(0,0,0,0.4);
}
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
         
       <form action="${pageContext.request.contextPath}/AtualizarPerfil" method="post" enctype="multipart/form-data">
         <div class="perfil-box">   
 <b>Nome:</b>  <input type="text" name="nome" placeholder="nome" value="${usuarioLogado.nome}"required>
        <b>Email:</b><input type="email" name="email" placeholder="email" required value="${usuarioLogado.email}">
        <b>CPF:</b><input type="text" name="cpf" placeholder="cpf" maxlength="11" minlength="11" pattern="\d{11}" value="${usuarioLogado.cpf}">
        <b>Telefone:</b><input type="text" name="telefone" placeholder="Telefone" required value="${usuarioLogado.telefone}">
       <b>Data de nascimento:</b> <input type="date" name="nasc" placeholder="nasc" placeholder="dd/mm/yyyy" pattern="\d{2}/\d{2}/\d{4}"  required value="${usuarioLogado.nasc}">
       <b>Senha:</b> <input type="password" name="senha" placeholder="senha" required value="${usuarioLogado.senha}">
       <b>Foto de perfil:</b> <p><input type="file" id="foto" name="fotoperfil" accept="image/jpeg, .jpg, .jpeg"  onchange="previewImagem()">
      <p>  <img id="preview" src="../../imagens/default-user.png" alt="Pré-visualização" style="width: 150px; height: 150px; border-radius: 50%; margin-top: 10px;">
        <p id="erroMsg" class="erro-msg"></p>
        
         <div class="buttons">
           

            <button type="reset" class="reset-btn">Limpar</button>  
            
            <div class="buttons">
            
            <p>  <label>Organizador:
            <p><input type="checkbox" name="organizador" ${usuarioLogado.organizador ? "checked" : ""}>
      </label>
      <br>
         </div> 
        </div> 
            <script>
  //prever a foto antes do envio
function previewImagem() {
    const input = document.getElementById("foto");
    const preview = document.getElementById("preview");

    const file = input.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result; // coloca a imagem no <img>
        }
        reader.readAsDataURL(file);
    }
}
</script>
    
    <button type="submit" class="submit-btn">Salvar alterações</button>
  <a href="#"  onclick="abrirPerfil()" class="btn-link">← Voltar ao Perfil</a>
</form>
 </div>
      <script>
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
