<%-- 
    Document   : removerPresenca
    Created on : 26 de nov. de 2025, 21:34:10
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.DAO.PresencaDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remover Presença</title>
    </head>
    <body>

<%
    // Pega o ID enviado via GET: removerPresenca.jsp
    String idParam = request.getParameter("id");

    if (idParam != null && !idParam.isEmpty()) {
        try {
            int idPresenca = Integer.parseInt(idParam);

            PresencaDAO dao = new PresencaDAO();
            dao.remover(idPresenca); // remove pelo id no banco

            // Redireciona de volta à lista após excluir
            response.sendRedirect("presenca.jsp");
            return;

        } catch (Exception e) {
            out.println("<h3>Erro ao remover presença: " + e.getMessage() + "</h3>");
        }
    } else {
        out.println("<h3>ID da presença não informado!</h3>");
    }
%>
    </body>
</html>
