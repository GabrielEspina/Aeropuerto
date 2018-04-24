import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ar.edu.ub.p3.tpi.api.Avion;

public class Aterrizaje implements Runnable{
	private Avion avion;
	
	public Aterrizaje(Avion avion) {
		this.setAvion(avion);
	}
	
	@Override
	public void run() {
		try(ServerSocket ss = new ServerSocket(8889)){
			
			Socket so = ss.accept();
			
			System.out.println("ATERRIZAJE> puerto " + so.getPort());
			
			try(ObjectOutputStream ois = new ObjectOutputStream (so.getOutputStream())){
				
				ois.writeObject(avion);
				
				System.out.println("Aeropuerto "+ so.getPort()+" aterriza >"+ getAvion().getId() + " "+ getAvion().getModelo());
				
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

}
