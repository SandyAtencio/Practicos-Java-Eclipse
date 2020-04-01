package com.sandy.controladores;

import java.util.Date;

public class Productos {
	
	private String 	idArticulo;
	private String 	seccion;
	private String 	nomArticulo;
	private double 	precio;
	private Date 	fecha;
	private String	importado;
	private String 	paisOrigen;
	
	public Productos(String idArticulo, String seccion, String nomArticulo, double precio, Date fecha,
			String importado, String paisOrigen) {
	
		this.idArticulo = idArticulo;
		this.seccion = seccion;
		this.nomArticulo = nomArticulo;
		this.precio = precio;
		this.fecha = fecha;
		this.importado = importado;
		this.paisOrigen = paisOrigen;
	}
	
	
	public Productos(String seccion, String nomArticulo, double precio, Date fecha,
			String importado, String paisOrigen) {
	
		this.seccion = seccion;
		this.nomArticulo = nomArticulo;
		this.precio = precio;
		this.fecha = fecha;
		this.importado = importado;
		this.paisOrigen = paisOrigen;
	}


	public String getIdArticulo() {
		return idArticulo;
	}


	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}


	public String getSeccion() {
		return seccion;
	}


	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}


	public String getNomArticulo() {
		return nomArticulo;
	}


	public void setNomArticulo(String nomArticulo) {
		this.nomArticulo = nomArticulo;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getImportado() {
		return importado;
	}


	public void setImportado(String importado) {
		this.importado = importado;
	}


	public String getPaisOrigen() {
		return paisOrigen;
	}


	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}


	@Override
	public String toString() {
		return "Productos [idArticulo=" + idArticulo + ", seccion=" + seccion + ", nomArticulo=" + nomArticulo
				+ ", precio=" + precio + ", fecha=" + fecha + ", importado=" + importado + ", paisOrigen=" + paisOrigen
				+ "]";
	}
	
	
	
	

}
