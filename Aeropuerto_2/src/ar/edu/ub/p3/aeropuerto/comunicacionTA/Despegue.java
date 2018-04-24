package ar.edu.ub.p3.aeropuerto.comunicacionTA;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import ar.edu.ub.p3.aeropuerto.aviones.ListaAviones;

public class Despegue implements Runnable{
	
	private ListaAviones listaAviones;
	
	public Despegue(ListaAviones listaAviones) {
		
		setListaAviones(listaAviones);
		
	}
	
	@Override
	public void run() {
		
		try (Socket so = new Socket("localhost",8888)){ //puerto 8888 para despegar aviones
			
			ObjectOutputStream oos  = new ObjectOutputStream( so.getOutputStream());
			
			oos.writeObject( getListaAviones().getAviones().getFirst() ); 	//envio el avion
			
			getListaAviones().getAviones().removeFirst();					//si se envia lo saco de mi lista
			
		}catch( IOException e ){
			
			System.out.println(">Despegue fallido");
			
		}
		
	}

	public ListaAviones getListaAviones() {
		
		return listaAviones;
		
	}

	public void setListaAviones(ListaAviones listaAviones) {
		
		this.listaAviones = listaAviones;
		
	}
	
	

}
