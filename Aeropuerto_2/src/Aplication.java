import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ar.edu.ub.p3.tpi.api.Avion;;

public class Aplication {
	//AEROPUERTO
	//------------------------------------------------
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		try (Socket so = new Socket("localhost",8888)){
			
			ObjectOutputStream oos  = new ObjectOutputStream( so.getOutputStream());
			oos.writeObject(new Avion("LA-A201","AVION-747"));
			
		}catch(Exception e){
			
		}

	}

}
