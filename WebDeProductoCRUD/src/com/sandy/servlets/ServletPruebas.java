package com.sandy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.*;


@WebServlet("/servletPruebas")
public class ServletPruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//establecer el DataSource
	
	@Resource(name ="jdbc/Productos")
	private DataSource pool;
	
	
	
    public ServletPruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//crear el obj PrintWriter
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		//crear conexion con BD
		
	    String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	    String JDBC_USER = "system";
	    String JDBC_PASS = "2406";
		
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = pool.getConnection();
			String query = "SELECT * FROM PRODUCTOS";
			st = conexion.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				String nomArticulo = rs.getString(3);
				out.println(nomArticulo);
			}
			
		}catch(Exception e) {
			out.println("Error de conexion " + e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
