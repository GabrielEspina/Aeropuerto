import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ar.edu.ub.p3.tpi.api.Avion;

public class Recepcion implements Runnable {

	private Avion avion;
	
	@Override
	
	public void run() {
		
		try(ServerSocket ss = new ServerSocket(8888)){
			
			Socket so = ss.accept();
			
			System.out.println("DESPEGUE> puerto " + so.getPort());
			
			try(ObjectInputStream oin = new ObjectInputStream (so.getInputStream())){
				
				setAvion((Avion) oin.readObject()); 
				
				System.out.println("Aeropuerto "+ so.getPort()+" despega >"+ getAvion().getId() + " "+ getAvion().getModelo());
				
			} catch (IOException e) {
				System.out.println("Fallo en vuelo de avion");
				
			} catch (ClassNotFoundException e) {
				System.out.println("Error en recepcion de avion");
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
