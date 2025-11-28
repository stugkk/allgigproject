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
        <title>Evento Cadastrado com Sucesso</title>
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
    <h2>Evento Cadastrado com Sucesso!</h2>

    <%
        // pega o evento enviado pelo servlet
    Evento evento = (Evento) request.getAttribute("evento");

    if (evento == null) {
        out.println("<h3>Erro: Evento não encontrado no request!</h3>");
        return;
    }

        out.println("<h2>Dados do Evento</h2>");

        out.println("<br><b>Título:</b> " + evento.getTitulo());
        out.println("<br><b>Categoria:</b> " + evento.getCategoria());
        out.println("<br><b>Descrição:</b> " + evento.getDescricao());

        out.println("<br><b>CEP:</b> " + evento.getCep());
        out.println("<br><b>Logradouro:</b> " + evento.getLogradouro());
        out.println("<br><b>Bairro:</b> " + evento.getBairro());
        out.println("<br><b>Cidade:</b> " + evento.getLocalidade());
        out.println("<br><b>Estado:</b> " + evento.getEstado());
        out.println("<br><b>Número:</b> " + evento.getNumero());
    // formata data
        out.println("<br><b>Data e Hora:</b> " + evento.getDataHora().format(
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        ));

        out.println("<br><b>Preço:</b> R$ " + String.format("%.2f", evento.getPreco()));

        out.println("<br><b>Status:</b> " + (evento.isStatus() ? "Ativo" : "Inativo"));

        out.println("<br><b>Imagem:</b><br>");
        out.println("<img src='data:image/png;base64," + evento.getImagemBase64() + "' width='200'/>");
    %>

</div>
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