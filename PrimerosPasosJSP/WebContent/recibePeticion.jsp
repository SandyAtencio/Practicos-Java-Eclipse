<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	//Leyendo datos del form
	String ciudadFavorita = request.getParameter("ciudadFavorita");
	
	//crear cookie
	
	Cookie cookie = new Cookie("agenciaViajes.ciudadFavorita", ciudadFavorita);
	
	//vida de la cookie
	cookie.setMaxAge(365*24*60*60); // 1 año segun calculo del parametro
	
	//enviar a usuario
	response.addCookie(cookie);

%>
Gracias por enviar tus preferencias!
<a href="agenciaViajes.jsp">Ir a la agencia de viajes</a>
</body>
</html>