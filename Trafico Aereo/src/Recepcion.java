import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ar.edu.ub.p3.tpi.api.Avion;

public class Recepcion implements Runnable {

	private Avion avion;
	private ListaAviones listaAviones;
	
	public Recepcion(ListaAviones listaAviones) {
	// TODO Auto-generated constructor stub
		
		setListaAviones(listaAviones);
		
	}
	
	@Override
	public void run() {
		
		try(ServerSocket ss = new ServerSocket(8888)){
			while(true) {
				
				Socket so = ss.accept();
			
				System.out.println("Conectado con Aeropuerto " + so.getPort()+ "\t para despegar aviones");
			
				try(ObjectInputStream oin = new ObjectInputStream (so.getInputStream())){
					
					do {
					
						setAvion((Avion) oin.readObject()); 
				
						if(!(avion == null)) {
					
							listaAviones.agreagarAvion(avion);
				
							System.out.println("Aeropuerto "+ so.getPort()+" despega >"+  getAvion().getModelo());
						
						}
					
					}while(!(avion == null));
				
			} catch (IOException e) {
				System.out.println("Fallo en vuelo de avion");
				
			} catch (ClassNotFoundException e) {
				System.out.println("Error en recepcion de avion");
			}
			}
		}catch(Exception e){	
			
		}
		
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public ListaAviones getListaAviones() {
		return listaAviones;
	}

	public void setListaAviones(ListaAviones listaAviones) {
		this.listaAviones = listaAviones;
	}

}
