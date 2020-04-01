package com.sandy.controladores;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sandy.modelos.ModeloProductos;


@WebServlet("/ControladorProductos")
public class ControladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ModeloProductos modeloProductos;
	
	@Resource(name="jdbc/Productos")
	private DataSource miPool;
	
		
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		try {
			modeloProductos = new ModeloProductos(miPool);
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// leer el parametro del formulario
		
		String parametro = request.getParameter("instruccion");
		
		// si no se envia el parametro, entonces listamos los productos
		
		if(parametro == null) parametro = "listar";
		
		// dirigir el flujo de ejecuccion al metodo adecuado
		
		switch(parametro) {
		
			case "listar":
				listarProductos(request, response);
				break;
				
			case "insertarBD":
				insertarProducto(request, response);
				break;
				
			case "cargar":
			try {
				cargaProducto(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			case "actualizarBD":
			try {
				actualizaProductos(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			case "eliminar":
			try {
				eliminarProductos(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			default:
				listarProductos(request, response);
			
		}
		

	}




	private void listarProductos(HttpServletRequest request, HttpServletResponse response) {
		
		// ------ LISTAR PRODUCTOS
				List<Productos> productos;
					
				try {
					//obtener la lista de productos desde el modelo
					
					productos = modeloProductos.getProductos();
					
					// agregar la lista de productos al request
					
					request.setAttribute("LISTAPRODUCTOS",productos);
					
					// enviar request a la pagina JSP
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/VistaListaProductos.jsp");
					dispatcher.forward(request, response);
		
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				


	}
	
	private void insertarProducto(HttpServletRequest request, HttpServletResponse response) {
		
		// ----- INSERTAR PRODUCTOS
		
		// leer informacion del producto que viene del formulario
		
			String 	codArticulo 			= request.getParameter("codigo");
			String  seccion 				= request.getParameter("seccion");
			String  nomArticulo 			= request.getParameter("nombre");
			double  precio					= Double.parseDouble(request.getParameter("precio"));
			SimpleDateFormat formatoFecha 	= new SimpleDateFormat("dd-MM-yyy");
			Date fecha 						= null;
			try {
				fecha 							= formatoFecha.parse(request.getParameter("fecha"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String  impor 					= request.getParameter("impor");
			String  pais 					= request.getParameter("pais");
		
		// con esa informacion que viene del formulario, crear un obj de tipo producto
		
			Productos nuevoProducto = new Productos(codArticulo, seccion, nomArticulo, precio, fecha, impor, pais);
		
		// enviar el obj al modelo y despues insertar el obj producto en la bd
		
			try {
				modeloProductos.agergarNuevoProducto(nuevoProducto);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		// volver a listar la tabla de productos
		
			listarProductos(request, response);
		
	}
	
	private void cargaProducto(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		// Leer el codigo articulo  que viene del listado
		
		String codigo = request.getParameter("cArticulo");
		
		// enviar codigo articulo a modelo 
		
		Productos producto = modeloProductos.getProducto(codigo);
		
		// colocar el atributo correspondiente al codigo articulo
		
		request.setAttribute("productoActualizar", producto);
		
		// enviar producto al formulario de actualizar jsp
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/actualizarProducto.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void actualizaProductos(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		
		// Leer los datos que vienen del formulario actualizar
		
		String 	codArticulo 			= request.getParameter("productoActualizar");
		String  seccion 				= request.getParameter("seccion");
		String  nomArticulo 			= request.getParameter("nombre");
		double  precio					= Double.parseDouble(request.getParameter("precio"));
		
		SimpleDateFormat formatoFecha 	= new SimpleDateFormat("dd-MM-yyy");
		Date fecha 						= null;
		try {
			fecha 							= formatoFecha.parse(request.getParameter("fecha"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String  impor 					= request.getParameter("impor");
		String  pais 					= request.getParameter("pais");
		
		//crear un objeto de tipo producto con la informacion del formulario 
		
		Productos productoActualizado = new Productos(codArticulo, seccion, nomArticulo, precio, fecha, impor, pais);
		
		// actualziar la BD con la informacion del obj producto
		
		modeloProductos.actualizarProducto(productoActualizado);
		
		// volver al listado con la informacion actualizada
		
		listarProductos(request, response);
		
	}

	private void eliminarProductos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Capturar el codigo articulo
		
		String 	codArticulo = request.getParameter("cArticulo");
		
		// borrar producto de la base de datos
		
		modeloProductos.eliminarProducto(codArticulo);
		
		// volver al listado de productos
		
		listarProductos(request, response);
		
	}
}
