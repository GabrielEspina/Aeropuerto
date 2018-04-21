import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import ar.edu.ub.p3.tpi.api.Avion;;


public class Aplication {
	//TRAFICO AEREO
	//------------------------------------------------
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		try(ServerSocket ss = new ServerSocket(8888)){
			
			Socket so = ss.accept();
			try(ObjectInputStream oin = new ObjectInputStream (so.getInputStream())){
				
				Avion avion = (Avion) oin.readObject();
				System.out.println(avion.getId() + " "+ avion.getModelo());
				
			}
			
		}catch(Exception e){
			
		}
	}

}
