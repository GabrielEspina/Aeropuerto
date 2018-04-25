import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ar.edu.ub.p3.tpi.api.Avion;

public class Aterrizaje implements Runnable{
	
	private Avion avion;
	private ListaAviones listaAviones;
	
	public Aterrizaje(ListaAviones listaAviones) {
		setListaAviones(getListaAviones());
	}
	
	@Override
	public void run() {
		try(ServerSocket ss = new ServerSocket(8889)){
			
			Socket so = ss.accept();
			
			System.out.println("Conectado con Aeropuerto " + so.getPort()+ "\t para aterrizar aviones");
			
			try(ObjectOutputStream ois = new ObjectOutputStream (so.getOutputStream())){
				System.out.println("llegue");
				while(!listaAviones.getAviones().isEmpty()) {
					
					setAvion(listaAviones.getAviones().getFirst());
					
					
					ois.writeObject(avion);
					
					System.out.println("Aeropuerto "+ so.getPort()+" aterriza >"+ getAvion().getModelo());
					
					listaAviones.getAviones().removeFirst();
				}
				System.out.println("Lista de aviones vacia");
				
			} catch (IOException e) {
				
				System.out.println("Fallo el aterrizaje del avion");
				
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
