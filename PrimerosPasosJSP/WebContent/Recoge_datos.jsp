<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

String nombre = request.getParameter("nombre");
String apellido = request.getParameter("apellido");
String usuario = request.getParameter("usuario");
String contra = request.getParameter("contra");
String pais = request.getParameter("pais");
String tecno = request.getParameter("tecnologias");

Class.forName("com.mysql.jdbc.Driver");
try{
	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyectojsp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
	Statement statement = conexion.createStatement();
	String query = "INSERT INTO usuarios (nombre, apellido, usuario, contra, pais, tecnologia) VALUES ('"+ nombre + "','"+ apellido + "','"+ usuario + "','"+ contra + "','"+ pais + "','"+ tecno + "')";
	statement.executeUpdate(query);
	out.println("Registrado con exito");
}catch(Exception e){
	out.println("Ocurrio un error " + e);
}

%>
</body>
</html>