package ar.edu.ub.p3.aeropuerto.comunicacionTA;

public class ConexionTraficoAereo {

	private String 	ipTraficoAereo;
	private int 	puertoEscritura;
	private int 	puertoEscucha;
	private Aeropuerteable aeropuerto;
	
	
	private Thread hiloDespegues;
	private Thread hiloAterrizajes;
	
	public ConexionTraficoAereo( Aeropuerteable aeropuerto, String ipTraficoAereo, int puertoEscritura, int puertoEscucha) {
		
		this.setAeropuerto(aeropuerto);
		this.setIpTraficoAereo(ipTraficoAereo);
		this.setPuertoEscritura(puertoEscritura);
		this.setPuertoEscucha(puertoEscucha);
		
		//TODO enviar un mensaje al trafico aereo para agregarme a la lista de aeropuertos
		//     para que sepa en que puertos escucho para poder mandarme aviones
		
		//TODO Creo el thread que vigila la lista de aviones para saber cuales tienen que despegar
		this.setHiloDespegues( Despegue.crearHilo( this.getAeropuerto(), this.getIpTraficoAereo(), this.getPuertoEscritura() ) );
		
		//TODO Creo el thread que se encarga de escuchar si llegan aviones
		this.setHiloAterrizajes( Recepcion.crearHilo( this.getAeropuerto(), this.getPuertoEscucha() ) );
	}
	
	private String getIpTraficoAereo() {
		return ipTraficoAereo;
	}
	
	private void setIpTraficoAereo(String ipTraficoAereo) {
		this.ipTraficoAereo = ipTraficoAereo;
	}
	
	private int getPuertoEscritura() {
		return puertoEscritura;
	}
	
	private void setPuertoEscritura(int puertoEscritura) {
		this.puertoEscritura = puertoEscritura;
	}
	
	private int getPuertoEscucha() {
		return puertoEscucha;
	}
	
	private void setPuertoEscucha(int puertoEscucha) {
		this.puertoEscucha = puertoEscucha;
	}

	private Aeropuerteable getAeropuerto() {
		return aeropuerto;
	}

	private void setAeropuerto(Aeropuerteable aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	private Thread getHiloDespegues() {
		return hiloDespegues;
	}

	private void setHiloDespegues(Thread hiloDespegues) {
		this.hiloDespegues = hiloDespegues;
	}

	private Thread getHiloAterrizajes() {
		return hiloAterrizajes;
	}

	private void setHiloAterrizajes(Thread hiloAterrizajes) {
		this.hiloAterrizajes = hiloAterrizajes;
	}

	///////////////////////////////////////////////////////////////////////////
	//
	
}
