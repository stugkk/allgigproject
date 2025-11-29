<%-- 
    Document   : perfil
    Created on : 25 de nov. de 2025, 01:39:31
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>

<%@page import="model.Usu"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<link href="${pageContext.request.contextPath}/style/geral.css" rel="stylesheet" />
<link href="../../style/custom.css" rel="stylesheet" type="text/css"/>
<meta charset="UTF-8">
<title>Meu Perfil</title>

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
        width: 350px;
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

    .info {
        text-align: left;
        margin-top: 10px;
        font-size: 16px;
    }

    .info b {
        color: #ffcbf2;
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

    .submit-btn {
    padding: 2px 1px;
    background: #6c5ce7; 
    color: white;
    }

    .submit-btn:hover {
    background: #5947d1;
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(108, 92, 231, 0.4);
    }

</style>
</head>
<body>
    <form>
    <div class="perfil-box">

<%
    Usu usuario = (Usu) request.getAttribute("usuario");
%>
<img src="data:image/jpeg;base64,<%= usuario.getFotoperfilbase64() %>" class="foto">
<p><b>Nome:</b> <%= usuario.getNome() %></p>
<p><b>Email:</b> <%= usuario.getEmail() %></p>
<p><b>CPF:</b> <%= usuario.getCpf() %></p>
<p><b>Telefone:</b> <%= usuario.getTelefone() %></p>
<p><b>Nascimento:</b> <%= usuario.getNasc() %></p>
<p><b>Organizador:</b><%= usuario.isOrganizador() ? "Sim" : "Não" %></p>


    <a href="menuprincipal/meuperfil/perfilupdate.jsp" class="btn-link" > Editar Usuário </a>
    <a href="menuprincipal/meuperfil/perfildelete.jsp" target="_top" onclick="return confirm('Deseja excluir sua conta?')">
    <button type="button" class="submit-btn">Deletar</button>
</a>
 

    

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