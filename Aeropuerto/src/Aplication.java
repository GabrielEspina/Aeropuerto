/*
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
*/

import ar.edu.ub.p3.aeropuerto.Aeropuerto;
//import ar.edu.ub.p3.tpi.api.Avion;;

public class Aplication {
/*	
	//AEROPUERTO
	//------------------------------------------------
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		try (Socket so = new Socket("localhost",8888)){
			
			ObjectOutputStream oos  = new ObjectOutputStream( so.getOutputStream());
			oos.writeObject(new Avion("AA-B-747","Boeing-747"));
			
		}catch(Exception e){
			
		}

	}
*/
	public static void main(String[] args) {
		
		Aeropuerto aeropuerto = Aeropuerto.crearAeropuertoCfg1();
		
		aeropuerto.despegarAviones();
	}
}
