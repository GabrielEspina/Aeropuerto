package ar.edu.ub.p3.aeropuerto.comunicacionTA;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import ar.edu.ub.p3.aeropuerto.aviones.ListaAviones;
import ar.edu.ub.p3.tpi.api.Avion;

public class Recepcion implements Runnable{

	private ListaAviones listaAviones;
	
	public Recepcion(ListaAviones listaAviones) {

		setListaAviones(listaAviones);
		
	}
	
	@Override
	public void run() {
		
		try (Socket so = new Socket("localhost",8889)){ //puerto 8889 para aterrizar aviones
	
			try(ObjectInputStream ois = new ObjectInputStream(so.getInputStream())){
				
				Avion avion = (Avion) ois.readObject();
				
				getListaAviones().agreagarAvion(avion);
				
				System.out.println(">Aterrizaje Exitoso> " + avion.getId() + "\t " + avion.getModelo());
				
			} catch (ClassNotFoundException e) {
				
				System.out.println(">Aterrizaje fallido");
				
			}
	
		}catch( IOException e ){
			
			System.out.println(">Conexion con Canal de Aterrizaje fallido");
			
		}
		
	}

	public ListaAviones getListaAviones() {
		return listaAviones;
	}

	public void setListaAviones(ListaAviones listaAviones) {
		this.listaAviones = listaAviones;
	}

}
