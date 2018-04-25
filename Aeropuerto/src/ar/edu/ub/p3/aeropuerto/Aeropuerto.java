package ar.edu.ub.p3.aeropuerto;

import ar.edu.ub.p3.tpi.api.Avion;
import ar.edu.ub.p3.aeropuerto.aviones.*;
import ar.edu.ub.p3.aeropuerto.comunicacionTA.Aeropuerteable;
import ar.edu.ub.p3.aeropuerto.comunicacionTA.ConexionTraficoAereo;
import ar.edu.ub.p3.aeropuerto.comunicacionTA.Despegue;
import ar.edu.ub.p3.aeropuerto.comunicacionTA.Recepcion;

/**
 * La clase aeropuerto modela el comportamiento de un aeropuerto
 * En un aeropuerto estan los Avion que deben despegar
 * 
 * 
 * @author wduartes
 *
 */

public class Aeropuerto  implements Aeropuerteable {
	
	private ListaAviones listaAviones;
	
//	private Thread recepcion;	
//	private Thread despegue;
	
	private ConexionTraficoAereo traficoAereo;
	
	private Aeropuerto()
	{
		setListaAviones(new ListaAviones());
	}

	public void addAvion( Avion avion ) {
		
		getListaAviones().agreagarAvion( avion );
		
	}
	 
	public  void connectTraficoAereo( String ipTraficoAereo, int puertoEscritura, int puertoEscucha) {
/*		
		//CREO LOS HILOS DE RECEPCION Y ENVIO DE AVIONES
		
		this.setDespegue(new Thread(new Despegue(listaAviones)));
		
		this.setRecepcion(new Thread(new Recepcion(listaAviones)));
		
		
		//INICIO LOS HILOS(debe estar corriendo el trafico aereo)
		
		this.getDespegue().start();
		
		this.getRecepcion().start();
*/
		
		// TODO probablemente esto se tenga que hacer en otro lugar
		// TODO y los parametros venir de otro lugar
		
		this.setTraficoAereo( new ConexionTraficoAereo( this, ipTraficoAereo, puertoEscritura, puertoEscucha ) );
	}

	public static Aeropuerto crearAeropuertoCfg1()
	{
		Aeropuerto aeropuerto = new Aeropuerto();
		
		//
		
		aeropuerto.cargarAvionesCfg1();
		
		
		//
		
		return aeropuerto;
	}
	
	public static Aeropuerto crearAeropuertoCfg2()
	{
		Aeropuerto aeropuerto = new Aeropuerto();
		
		//
		
		aeropuerto.cargarAvionesCfg2();
		
		//
		
		return aeropuerto;
	}
	
	
	public void cargarAvionesCfg1() {
		
		//AVIONES DE AEROLINEAS ARGENTINAS
		
			//SERIE AIRBUS
		
		addAvion( new Avion("AA-A-340","Airbus A340-300") );
		addAvion( new Avion("AA-A-330","Airbus A330-200") );
		
			//SERIE BOEING
		
		addAvion( new Avion("AA-B-737","Boeing 737-700") );
		addAvion( new Avion("AA-B-747","Boeing-747") );
		
			//SERIE EMBRAER - AUSTRAL
		
		addAvion( new Avion("AA-E-190","Embraer 190") );

	}
	public void cargarAvionesCfg2() {
		
		//AVIONES DE LATAM AIRLINES
		
			//SERIE AIRBUS
		
		addAvion( new Avion("LA-A-350","Airbus 350") );
		addAvion( new Avion("LA-A-321","Airbus 321") );
		addAvion( new Avion("LA-A-319","Airbus 319") );
		
			//SERIE BOEING
		
		addAvion( new Avion("LA-B-747","Boeing-747") );
		addAvion( new Avion("LA-B-787","Boeing 787-9") );
		addAvion( new Avion("LA-B-767","Boeing 767-300") );
		
	}
	
	
	

	public ListaAviones getListaAviones() {
		
		return listaAviones;
		
	}

	private void setListaAviones(ListaAviones listaAviones) {
		
		this.listaAviones = listaAviones;
		
	}
/*
	private Thread getRecepcion() {
		
		return recepcion;
		
	}

	private void setRecepcion(Thread recepcion) {
		
		this.recepcion = recepcion;
		
	}

	private Thread getDespegue() {
		
		return despegue;
		
	}

	private void setDespegue(Thread despegue) {
		
		this.despegue = despegue;
		
	}
*/
	@Override
	public Avion[] obtenerAvionesParaDespegar() {		
		// TODO Pido la lista de aviones listos para despegar
		return this.getListaAviones().getAviones().toArray( new Avion[ this.getListaAviones().getAviones().size() ] );
	}

	@Override
	public void pudeDespegarAvion(Avion avion) {
		
		//TODO hacer los cambios de estado pertinentes en el avion cuando se marca como en vuelo				
		System.out.println(  "[***DESPEGO***]" + avion.getModelo());
	}

	@Override
	public void aterrizar(Avion avion) {
		
		//TODO hacer los cambios de estado pertinentes en el avion cuando se marca como aterrizado
		System.out.println(  "[***ATERRIZO***]" + avion.getModelo());
	}

	private ConexionTraficoAereo getTraficoAereo() {
		return traficoAereo;
	}

	private void setTraficoAereo(ConexionTraficoAereo traficoAereo) {
		this.traficoAereo = traficoAereo;
	}
	
}
