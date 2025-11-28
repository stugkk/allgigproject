<%-- 
    Document   : removerEvento
    Created on : 27 de nov. de 2025, 00:02:39
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.DAO.EventoDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Remover Evento</title>
    </head>
    <body>
<%

    String idParam = request.getParameter("id");

    if (idParam != null && !idParam.isEmpty()) {

        try {
            int idEvento = Integer.parseInt(idParam);

            EventoDAO dao = new EventoDAO();
            dao.remover(idEvento); // Remove no banco

            // Redireciona após remover
            response.sendRedirect("/Allgig/MeusEventos");
            return;

        } catch (Exception e) {
            out.println("<h3>Erro ao remover evento: " + e.getMessage() + "</h3>");
        }

    } else {
        out.println("<h3>ID do evento não informado!</h3>");
    }
    %>
    </body>
</html>
