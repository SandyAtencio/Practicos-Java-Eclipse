package com.sandy.modelos;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.sql.DataSource;

import com.sandy.controladores.Productos;

public class ModeloProductos {
	
	private DataSource origenDatos;
	
	public ModeloProductos(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}
	
	public List<Productos> getProductos() throws Exception{
		
		List<Productos> productos = new ArrayList<Productos>();
		
		Connection conexion = null;
		Statement statement = null;
		ResultSet rs 		= null;
		try {
		// ----- establecer la conexion -----
		
		conexion = origenDatos.getConnection();
		
		// ------ crear la sentencia sql y statement
		
		String query = "SELECT * FROM PRODUCTOS";
		statement = conexion.createStatement();
		
		// --- ejecutar sql
		
		rs = statement.executeQuery(query);
		
		// ----- recorre el resultset obtenido
		
		while(rs.next()) {
			
			String idArticulo 	= rs.getString(1);
			String seccion 		= rs.getString(2);
			String nomArticulo	= rs.getString(3);
			double precio 		= rs.getDouble(4);
			Date   fecha 		= rs.getDate(5);
			String importado 	= rs.getString(6);
			String pais 		= rs.getString(7);
			
			Productos tempProducto = new Productos(idArticulo, seccion, nomArticulo, precio, fecha, importado, pais);
			productos.add(tempProducto);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			statement.close();
			conexion.close();			
		}
		
		return productos;
	}

	public void agergarNuevoProducto(Productos nuevoProducto) throws Exception{
		
		Connection conexion = null;
		PreparedStatement prStatement = null;
			
		try {
			// obtener la conexion
			conexion = origenDatos.getConnection();
			
			// crear sql y consulta preparada
			
			String sql = "INSERT INTO PRODUCTOS (CODIGOARTICULO, SECCION, NOMBREARTICULO, PRECIO, FECHA, IMPORTADO, PAISDEORIGEN) VALUES (?,?,?,?,?,?,?)";
			prStatement = conexion.prepareStatement(sql);
			
			// establecer parametros del producto
			
			prStatement.setString(1, nuevoProducto.getIdArticulo());
			prStatement.setString(2, nuevoProducto.getSeccion());
			prStatement.setString(3, nuevoProducto.getNomArticulo());
			prStatement.setDouble(4, nuevoProducto.getPrecio());
			
			java.util.Date utilDate = nuevoProducto.getFecha();
			java.sql.Date  fechaConvertida = new java.sql.Date(utilDate.getTime());
			
			prStatement.setDate(5, fechaConvertida);
			prStatement.setString(6, nuevoProducto.getImportado());
			prStatement.setString(7, nuevoProducto.getPaisOrigen());
			
			// ejecutar la sql
			prStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			prStatement.close();
			conexion.close();			
		}
		
	}

	public Productos getProducto(String codigo) throws Exception {
		
		Productos producto 		= null;
		String codigoArt 		= codigo;
		Connection conexion 	= null;
		PreparedStatement st 	= null;
		ResultSet rs 			= null;
		
		try {
			
			// establecer la conexion bd
			conexion = origenDatos.getConnection();
		
			// crear sql que busque el producto
			String sql = "SELECT * FROM PRODUCTOS WHERE CODIGOARTICULO = ?";
			
			// crear la consulta preparada
			st = conexion.prepareStatement(sql);
			
			// establecer los parametros
			st.setString(1, codigoArt);
			
			// ejecutar la consulta
			 rs = st.executeQuery();
			// obtener los datos de respuesta
			 
			 if(rs.next()) {
				 
				 	String idArticulo 	= rs.getString("CODIGOARTICULO");
					String seccion 		= rs.getString("SECCION");
					String nomArticulo	= rs.getString("NOMBREARTICULO");
					double precio 		= rs.getDouble("PRECIO");
					Date fecha 			= rs.getDate("FECHA");
					String importado 	= rs.getString("IMPORTADO");
					String pais 		= rs.getString("PAISDEORIGEN");
					
					producto = new Productos(idArticulo, seccion, nomArticulo, precio, fecha, importado, pais);
					
			 }else {
				 throw new Exception("No hemos encontrado el producto con codigo = " + codigoArt);
			 }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			st.close();
			conexion.close();			
		}
		return producto;
	}

	
	public void actualizarProducto(Productos productoActualizado) throws Exception{
		
		Connection conexion = null;
		PreparedStatement prStatement = null;
			
		try {
			// obtener la conexion
			conexion = origenDatos.getConnection();
			
			// crear sql y consulta preparada
			
			String sql = "UPDATE PRODUCTOS SET SECCION = ?, NOMBREARTICULO = ?, PRECIO = ?, FECHA = ?,"+
			" IMPORTADO = ?, PAISDEORIGEN = ? WHERE CODIGOARTICULO = ?";
			
			prStatement = conexion.prepareStatement(sql);
			
			// establecer parametros del producto
			
			
			prStatement.setString(1, productoActualizado.getSeccion());
			prStatement.setString(2, productoActualizado.getNomArticulo());
			prStatement.setDouble(3, productoActualizado.getPrecio());
			
			java.util.Date utilDate = productoActualizado.getFecha();
			java.sql.Date  fechaConvertida = new java.sql.Date(utilDate.getTime());
			
			prStatement.setDate(4, fechaConvertida);
			prStatement.setString(5, productoActualizado.getImportado());
			prStatement.setString(6, productoActualizado.getPaisOrigen());
			prStatement.setString(7, productoActualizado.getIdArticulo());
			
			// ejecutar la sql
			prStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			prStatement.close();
			conexion.close();			
		}
		
	}

	public void eliminarProducto(String codArticulo) throws Exception{

		Connection conexion = null;
		PreparedStatement prStatement = null;
			
		try {
			// obtener la conexion
			conexion = origenDatos.getConnection();
			
			// crear sql y consulta preparada
			
			String sql = "DELETE FROM PRODUCTOS WHERE CODIGOARTICULO = ?";
			
			prStatement = conexion.prepareStatement(sql);
			
			// establecer parametros del producto

			prStatement.setString(1, codArticulo);
			
			// ejecutar la sql
			prStatement.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			prStatement.close();
			conexion.close();			
		}
		
	}

}
