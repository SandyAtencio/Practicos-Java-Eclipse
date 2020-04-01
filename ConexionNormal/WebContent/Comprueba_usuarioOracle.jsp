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


String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
String JDBC_USER = "system";
String JDBC_PASS = "2406";

try{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conexion = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
	PreparedStatement statement = conexion.prepareStatement("SELECT * FROM USUARIOS WHERE USUARIO = ? AND PASSWORD = ?");
	statement.setString(1, usuario);
	statement.setString(2, contra);
	ResultSet result = statement.executeQuery();
	
	if(result.next()){
		out.println("Usuario autorizado ORACLE");
	}else{
		out.println("Usuario incorrecto ORACLE");
	}
	
}catch(Exception e){
	out.println("Ocurrio un error ORACLE " + e);
}

%>
</body>
</html>