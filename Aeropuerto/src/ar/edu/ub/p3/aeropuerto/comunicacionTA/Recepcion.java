package ar.edu.ub.p3.aeropuerto.comunicacionTA;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import ar.edu.ub.p3.tpi.api.Avion;

public class Recepcion implements Runnable{

//	private ListaAviones listaAviones;
	private Aeropuerteable aeropuerto;	
	private int            puertoEscucha;
	
	public Recepcion(int puertoEscucha) {		
		this.setPuertoEscucha(puertoEscucha);
	}
/*
	public Recepcion(ListaAviones listaAviones) {

		setListaAviones(listaAviones);
		
	}
*/
	
	@Override
	public void run() {
		
		try( ServerSocket serverSocket = new ServerSocket( this.getPuertoEscucha() ) )
		{		
			do
			{
				Socket 			  clientSocket = serverSocket.accept();
				
				ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
				
				Avion avion = (Avion) ois.readObject();
				
				this.getAeropuerto().aterrizar(avion);
				
//				System.out.println(">Aterrizaje Exitoso> " + avion.getId() + "\t " + avion.getModelo());
			}
			while( true );
			
		}
		catch (IOException | ClassNotFoundException e) 
		{
		}
		
/*		
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
*/
	}
/*
	public ListaAviones getListaAviones() {
		return listaAviones;
	}

	public void setListaAviones(ListaAviones listaAviones) {
		this.listaAviones = listaAviones;
	}
*/
	public static Thread crearHilo(Aeropuerteable aeropuerto, int puertoEscucha) {		
		Recepcion managerDespegue = new Recepcion( puertoEscucha );
		Thread   hilo = new Thread( managerDespegue );
		
		managerDespegue.setAeropuerto( aeropuerto );		
		
		hilo.start();
		
		return hilo;
	}

	private Aeropuerteable getAeropuerto() {
		return aeropuerto;
	}

	private void setAeropuerto(Aeropuerteable aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	private int getPuertoEscucha() {
		return puertoEscucha;
	}

	private void setPuertoEscucha(int puertoEscucha) {
		this.puertoEscucha = puertoEscucha;
	}

}
