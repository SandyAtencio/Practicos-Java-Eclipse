<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.sql.*" %>
<%

String usuario = request.getParameter("usuario");
String contra = request.getParameter("contra");

Class.forName("com.mysql.jdbc.Driver");
try{
	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectojsp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
	PreparedStatement statement = conexion.prepareStatement("SELECT * FROM USUARIOS WHERE USUARIO = ? AND CONTRA = ?");
	statement.setString(1, usuario);
	statement.setString(2, contra);
	ResultSet result = statement.executeQuery();
	
	if(result.absolute(1)){
		out.println("Usuario autorizado MYSQL");
	}else{
		out.println("Usuario incorrecto MYSQL");
	}
	
}catch(Exception e){
	out.println("Ocurrio un error MYSQL" + e);
}

%>
</body>
</html>