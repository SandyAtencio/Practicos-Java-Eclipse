<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.*, java.sql.*, com.sandy.jsptags.*" %>
    
    <%
    
    List<Empleado> datos = new ArrayList<Empleado>();
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
    String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    String JDBC_USER = "system";
    String JDBC_PASS = "2406";
    
    try{
    	
    	Connection conexion = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
    	Statement statement = conexion.createStatement();
    	String query = "SELECT * FROM EMPLEADOS";
    	ResultSet result = statement.executeQuery(query);
    	while(result.next()){
    		datos.add(new Empleado(result.getString(1), 
				    				result.getString(2), 
				    				result.getString(3), 
				    				result.getDouble(4))
    				);
    	}
    	result.close();
    	conexion.close();
    	
    }catch(Exception e){
    	out.println("Ha ocurrido un error" + e);
    }
    pageContext.setAttribute("empleados", datos);
    
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
	<tr>
		<td>Nombre</td> <td>Apellido</td> <td>Puesto</td> <td>Salario</td>
	</tr>
	<c:forEach var="empTemp" items="${ empleados }">
		<tr>
		<td>${empTemp.nombre }</td><td>${empTemp.apellido }</td><td>${empTemp.puesto }</td>
		<td>
			<c:if test="${empTemp.salario<800000 }">
				${empTemp.salario + 50000}
			</c:if>
			
			<c:if test="${empTemp.salario>=900000 }">
				${empTemp.salario}
			</c:if>
		</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>