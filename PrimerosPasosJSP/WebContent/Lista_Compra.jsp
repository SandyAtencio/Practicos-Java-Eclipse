<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@ page import="java.util.*" %>
<html>

<body>
<form name="Formulario_Compra" action="Lista_Compra.jsp">
 
  <p>Articulos a comprar:</p>
  <p>
    <label>
      <input type="checkbox" name="articulos" value="agua " >
      Agua </label>
    <br>
    <label>
      <input type="checkbox" name="articulos" value="leche" >
      Leche </label>
    <br>
     <label>
      <input type="checkbox" name="articulos" value="pan" >
      Pan </label>
    <br>
     <label>
      <input type="checkbox" name="articulos" value="mazanas" >
      Manzanas </label>
    <br>
     <label>
      <input type="checkbox" name="articulos" value="carne" >
      Carne </label>
      <br>
       <label>
      <input type="checkbox" name="articulos" value="pescado" >
      Pescado </label>
  </p>
  <p>
    <input type="submit" name="button" id="button" value="Enviar">
    <br>
  </p>
</form>

<h2>Carro de la compra:</h2>
<ul>
	<% 
	List<String> listaElementos = (List<String>)session.getAttribute("misElementos");
	
	if(listaElementos == null){
		listaElementos = new ArrayList<String>();
		session.setAttribute("misElementos", listaElementos);
	}
		
	String[] elementos = request.getParameterValues("articulos");
	
	if(elementos !=null){
		for(String eleTemp : elementos){
			listaElementos.add(eleTemp);
		}
	}
	
	for(String eleTemp: listaElementos){
		out.println("<li>" + eleTemp + "</li>");
	}
	
	%>
</ul>
</body>

</html>