<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Vista Lista Productos</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>

<div class="jumbotron">

  <h1 class="display-4">CRUD EN JAVA - ORACLE</h1>
  <hr class="my-4">
	
	<div class="card">
		  <div class="card-body">
		   <button type="button" class="btn btn-success" onclick="window.location.href='insertaProducto.jsp'">Insertar registro</button>
		  </div>
	</div>

	<table class="table">
		<thead>
			<tr>
			
			<th>CODIGO ARTICULO</th>
			<th>SECCION</th>
			<th>NOMBRE ARTICULO</th>
			<th>PRECIO</th>
			<th>FECHA</th>
			<th>IMPORTADO</th>
			<th>PAIS DE ORIGEN</th>
			<th>ACCION</th>
			
			</tr>
		</thead>
	
	
	<c:forEach var="temPro" items="${ LISTAPRODUCTOS }">
	
	<!-- - link para  cada producto con su codigo -->
	<c:url var="linkTemProd" value="ControladorProductos">
	
		<c:param name="instruccion" value="cargar"></c:param>
		<c:param name="cArticulo" value="${ temPro.idArticulo }"></c:param>
	</c:url>
	
		<!-- - link para eliminar cada producto con su codigo -->
	<c:url var="linkTemProdEliminar" value="ControladorProductos">
	
		<c:param name="instruccion" value="eliminar"></c:param>
		<c:param name="cArticulo" value="${ temPro.idArticulo }"></c:param>
	</c:url>
	
	<tbody>
		<tr>
		
		<th scope="row">${ temPro.idArticulo }</th>
		<td>${ temPro.seccion }</td>
		<td>${ temPro.nomArticulo }</td>
		<td>${ temPro.precio }</td>
		<td>${ temPro.fecha }</td>
		<td>${ temPro.importado }</td>
		<td>${ temPro.paisOrigen }</td>
		<td><a href="${ linkTemProd }">Actualizar</a>&nbsp;&nbsp;<a href="${ linkTemProdEliminar }">Eliminar</a></td>
	
		</tr>
		</tbody>
	</c:forEach>
	
	
	
	</table>
</div>


</body>
</html>