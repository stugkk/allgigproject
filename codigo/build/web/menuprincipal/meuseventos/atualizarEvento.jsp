<%-- 
    Document   : atualizarEvento
    Created on : 27 de nov. de 2025, 00:13:07
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>
<%@page import="model.Evento"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Evento ev = (Evento) request.getAttribute("evento");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Evento</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/style/geral3.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

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
        height: 1400px;
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
<div class="container4">

    <form method="post" action="/Allgig/EventoAtualizar" enctype="multipart/form-data">

        <h1>Editar Evento</h1>

      
        <input type="hidden" name="id" value="<%= ev.getId() %>">

        <div class="input-field">

            <b><p></b>   Título:<input type="text" name="titulo" placeholder="Título" 
                   value="<%= ev.getTitulo() %>" required>
       <b><p></b> Categoria:  <input type="text" name="categoria" placeholder="Categoria"
                   value="<%= ev.getCategoria() %>" required>
        <b><p> </b>Descrição:<textarea name="descricao" placeholder="Descrição" required
                      style="width:100%; height:80px;"><%= ev.getDescricao() %></textarea>
         <b><p></b> Data: <input type="datetime-local" name="dataHora" required>

          <b><p></b> Cep: <input type="text" name="cep" placeholder="CEP"
                   value="<%= ev.getCep() %>" required>
          <b><p></b>  Rua:<input type="text" name="logradouro" placeholder="Logradouro"
                   value="<%= ev.getLogradouro() %>" required>
           <b><p></b> Bairro:<input type="text" name="bairro" placeholder="Bairro"
                   value="<%= ev.getBairro() %>" required>
          <b><p></b>  Localidade:<input type="text" name="localidade" placeholder="Cidade"
                   value="<%= ev.getLocalidade() %>" required>
         <b><p></b>  Estado:<input type="text" name="estado" placeholder="Estado"
                   value="<%= ev.getEstado() %>" required>
           <b><p></b> Número:<input type="number" name="numero" placeholder="Número"
                   value="<%= ev.getNumero() %>" required>
            <b><p></b> Preço:<input type="number" step="0.01" name="preco" placeholder="Preço"
                   value="<%= ev.getPreco() %>" required>

           <b><p></b>Imagem:<input type="file" id="imagem" name="imagem" accept="image/jpeg,.jpg,.jpeg" onchange="previewImagem()">

           <p> <img id="preview" 
                 src="data:image/jpeg;base64,<%= ev.getImagemBase64() %>" 
                 alt="Prévia da imagem">

        </div>
                 
        <br>
        <div class="buttons">
            <button type="submit" class="submit-btn">Salvar Alterações</button><br><br>
          <a href="#"  onclick="abrirEventos()" class="btn-link">← Voltar para Meus Eventos</a>
        </div>

    </form>
</div>
<script>
function abrirEventos() {
    const frame = document.getElementById("conteudo");
    if (frame) {
        frame.src = "/Allgig/MeusEventos"; // chama o servlet Home que devolve home.jsp
    } else {
        window.location.href = "/Allgig/MeusEventos"; // fallback caso não exista iframe
    }
}
</script>

                 
                 
                 
                 
<script>
    function previewImagem() {
        const input = document.getElementById("imagem");
        const preview = document.getElementById("preview");

        const file = input.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = e => preview.src = e.target.result;
            reader.readAsDataURL(file);
        }
    }
</script>

</body>
</html>