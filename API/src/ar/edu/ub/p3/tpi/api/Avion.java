package ar.edu.ub.p3.tpi.api;


import java.io.Serializable;

public class Avion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String modelo;
	
	public Avion(String id, String modelo) {
		// TODO Auto-generated constructor stub	
		this.setId(id);
		this.setModelo(modelo);
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}

