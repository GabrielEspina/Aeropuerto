package ar.edu.ub.p3.aeropuerto;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ar.edu.ub.p3.tpi.api.Avion;

/**
 * La clase aeropuerto modela el comportamiento de un aeropuerto
 * En un aeropuerto estan los Avion que deben despegar
 * 
 * 
 * @author wduartes
 *
 */

public class Aeropuerto {
	
	private Avion[] aviones = new Avion[0];
	
	private Aeropuerto()
	{
		
	}

	public void addAvion( Avion avion ) {
		this.setAviones( agregar( this.getAviones(), avion ) );
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private Avion[] agregar( Avion[] aviones, Avion avion ){
		//TODO esto se debe mover a una clase utils de la API
		
		Avion[] nuevosAviones= new Avion[ aviones.length + 1 ];
		
		//Copio los hijos
		copiar( aviones, nuevosAviones );
		
		//Agrego la persona al final
		nuevosAviones[ nuevosAviones.length - 1 ] = avion;
				
		return nuevosAviones;		
	}
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	private void copiar(Avion[] origen, Avion[] destino)
	{
		for( int posicion = 0; posicion < origen.length; posicion ++ )
			destino[posicion] = origen[posicion];		
	}	
	
	///////////////////////////////////////////////////////////////////////////
	//
	
	public void despegarAviones() {
		for( Avion avion : this.getAviones() )
			this.despegar( avion );		
	}

	private void despegar( Avion avion ) {
		// TODO esto debe invocar al trafico aereo para despegar al avion
		try (Socket so = new Socket("localhost",8888)){
			
			ObjectOutputStream oos  = new ObjectOutputStream( so.getOutputStream());
			oos.writeObject( avion );
			
		}catch( IOException e ){
			
		}
	}	
	
	public Avion[] getAviones() {
		return aviones;
	}

	private void setAviones(Avion[] aviones) {
		this.aviones = aviones;
	}
	
	public static Aeropuerto crearAeropuertoCfg1()
	{
		Aeropuerto aeropuerto = new Aeropuerto();
		
		//
		
		aeropuerto.addAvion( new Avion("AA-B-747","Boeing-747") );
		
		//
		
		return aeropuerto;
	}
	
	public static Aeropuerto crearAeropuertoCfg2()
	{
		Aeropuerto aeropuerto = new Aeropuerto();
		
		//
		
		aeropuerto.addAvion( new Avion("AA-B-747","Boeing-747") );
		
		//
		
		return aeropuerto;
	}
	
}
