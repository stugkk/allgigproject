<%-- 
    Document   : perfildelete
    Created on : 27 de nov. de 2025, 00:54:57
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>
<%@page import="model.DAO.UsuDAO"%>
<%@page import="model.Usu"%>

<%
    // Pegar usuário logado da sessão
    Usu usuarioLogado = (Usu) session.getAttribute("usuarioLogado");

    // Se não houver usuário, manda para login
    if (usuarioLogado == null) {
        response.sendRedirect("../../index.html");
        return;
    }

    try {
        // Chama o metodo perfildelete usando o ID da sessão
        UsuDAO dao = new UsuDAO();
        dao.delete(usuarioLogado.getId());

        // Mata sessão
        session.invalidate();

        // Redireciona após exclusão
        response.sendRedirect("../../index.html");

    } catch (Exception e) {
        out.println("<h3>Erro ao excluir conta: " + e.getMessage() + "</h3>");
    }
%>