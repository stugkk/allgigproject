<%-- 
    Document   : index
    Created on : 23 de nov. de 2025, 22:32:30
    Author     : guilherme campos 11241102449 enzo romao 11241105170
--%>
<%@page import="model.Usu"%>
<%@page import="java.net.URI"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%
    Usu usuario = (Usu) session.getAttribute("usuarioLogado");

    // se não estiver logado ou não for organizador
    if (usuario == null || !usuario.isOrganizador()) {
%>
        <script>
            alert("Acesso negado! Apenas organizadores podem criar eventos.");
            window.parent.location.href = "../index.jsp"; 
        </script>
<%
        return; // impede o resto da página de carregar
    }
%>
<!DOCTYPE html>
<html data-bs-theme="auto">
    <head>
        <title>Criar Evento</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="../../style/geral.css" rel="stylesheet" type="text/css"/>
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
textarea{
    width: 350px;  
}
        </style>
    </head>

<body>
    <div class="container">
        <h2>Criar Evento</h2>
        <form method="post" action="/Allgig/Criareventoservlet" enctype="multipart/form-data">
        <!-- formulário enviando para o servlet -->

            <b>Título do Evento:</b>
            <input type="text" name="titulo" required>
            <p>
            <b>Categoria:</b>
                <select name="categoria" required>
    <option value="">Selecione...</option>
    <option value="show">Show</option>
    <option value="teatro">Teatro</option>
    <option value="evento">Evento Social</option>
    <option value="curso">Curso / Workshop</option>
    <option value="curso">Games</option>
    <option value="curso">Outro</option>
                </select> 
            <P>
            <b>Descrição:</b><p>
            <textarea name="descricao" required></textarea>
            <p>
             
             <%
            String cep = request.getParameter("cep");
            
            if(cep != null && !cep.trim().isEmpty()){
                String urlCEP = "https://viacep.com.br/ws/" + cep + "/json/";
            
                try{
                  //conexao
                    URL url = URI.create(urlCEP).toURL();
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // abre a conexão
                    conn.setRequestMethod("GET");                    
                    int responseCode = conn.getResponseCode();                     
                    if (responseCode == HttpURLConnection.HTTP_OK){ 
        //leitura
                        BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
                        String inputLine;
                        StringBuilder resp = new StringBuilder();

                        while ((inputLine = in.readLine()) != null){
                            resp.append(inputLine);
                        }
                        in.close();

         //processamento json
                        JSONObject json = new JSONObject(resp.toString());

                        if (json.has("erro")){
                            out.println("<B>CEP NÃO ENCONTRADO!<p><B>"); 
                        }else{                  
     
                         %>
                         <br><b>CEP: </b> <input type="text" name="cep" value="<%=json.getString("cep")%>"> 
                         <br><b>Logradouro: </b> <input type="text" name="logradouro" value="<%=json.getString("logradouro")%>"> 
                         <br><b>Bairro: </b> <input type="text" name="bairro" value="<%=json.getString("bairro")%>"> 
                         <br><b>Localidade: </b> <input type="text" name="localidade" value="<%=json.getString("localidade")%>"> 
                         <br><b>Estado: </b> <input type="text" name="estado" value="<%=json.getString("estado")%>"> 
                         <br><b>Número: </b> <input type="text" name="numero"> 
                         <%                            
                        }                    
                    }                    
                } catch (Exception ex){
                    out.println("Erro: " + ex);
                }            
            }
        %>
            <b>Data e Hora:</b>
            <input type="datetime-local" name="dataHora" required>
            <p> 
           <b> Preço:</b>
            <input type="number" name="preco" step="0.01" required>
            <p>
                <%--inserir a foto com previzualização --%>
            <input type="file" id="foto" name="imagem" accept="image/jpeg, .jpg, .jpeg"  onchange="previewImagem()">
<img id="preview" src="../../imagens/defaultimage.jpeg" alt="Pré-visualização" style="width: 150px; height: 150px; border-radius: 5%; margin-top: 10px;">
            <div class="buttons">
            <button type="submit" class="submit-btn">Cadastrar Evento</button>
            <button type="reset" class="reset-btn">Limpar</button>                    
            </div>
        </form>

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

    /*codigo para receber o comando do botao no index principal */
            window.addEventListener("message", event => {
                if (event.data.theme) {
                    document.documentElement.setAttribute("data-bs-theme", event.data.theme);
                }
            });
</script>
    </body>
</html>