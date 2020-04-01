<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1 style="text-align:center">Agencia de viajes</h1>
<%
	//valor por defecto
	String ciudadFavorita = "Madrid";

	//Lee la cookie de la peticion del navegador
	 Cookie[] cookies = request.getCookies();
	
	//buscar las preferencias. la ciudad favorita
	if(cookies !=null){
		for(Cookie cookieTemp: cookies){
			if("agenciaViajes.ciudadFavorita".equals(cookieTemp.getName())){
				ciudadFavorita = cookieTemp.getValue();
			}
		}
	}
%>
<h3>Vuelos a <%= ciudadFavorita %></h3>
<p>Textto de ejemplo</p>
</body>
</html>