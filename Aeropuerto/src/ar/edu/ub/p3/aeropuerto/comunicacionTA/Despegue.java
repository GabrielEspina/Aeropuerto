package ar.edu.ub.p3.aeropuerto.comunicacionTA;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import ar.edu.ub.p3.aeropuerto.aviones.ListaAviones;
import ar.edu.ub.p3.tpi.api.Avion;

public class Despegue implements Runnable{
	
	private ListaAviones listaAviones;
	
	private Aeropuerteable aeropuerto;
	
	public Despegue(ListaAviones listaAviones) {
		
		setListaAviones(listaAviones);
		
	}
	
	@Override
	public void run() {
		
		try (Socket so = new Socket("localhost",8888)){ //puerto 8888 para despegar aviones
			
			ObjectOutputStream oos  = new ObjectOutputStream( so.getOutputStream());
			
//			oos.writeObject( getListaAviones().getAviones().getFirst() ); 	//envio el avion			
//			getListaAviones().getAviones().removeFirst();					//si se envia lo saco de mi lista
			
			Avion[] aviones = this.getAeropuerto().obtenerAvionesParaDespegar().clone();
			
			for( Avion avion : aviones)		
				oos.writeObject( avion );				
						
			for( Avion avion : aviones)
				this.getAeropuerto().pudeDespegarAvion(avion);
				
		}catch( IOException e ){
			
			System.out.println(">Despegue fallido");
			e.printStackTrace();
			
		}
		
	}

	public ListaAviones getListaAviones() {
		
		return listaAviones;
		
	}

	public void setListaAviones(ListaAviones listaAviones) {
		
		this.listaAviones = listaAviones;
		
	}

	public static Thread crearHilo(Aeropuerteable aeropuerto, String ipTraficoAereo, int puertoEscritura) {
		
		//TODO dejar de ignorar los parametros de ip y puerto
		Despegue managerDespegue = new Despegue( null );
		Thread   hilo = new Thread( managerDespegue );
		
		managerDespegue.setAeropuerto( aeropuerto );		
		
		hilo.start();
		
		return hilo;
	}

	public Aeropuerteable getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(Aeropuerteable aeropuerto) {
		this.aeropuerto = aeropuerto;
	}
	
	

}
