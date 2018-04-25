import java.net.ServerSocket;
import java.net.Socket;

import ar.edu.ub.p3.tpi.api.Avion;

public class Aplicacion {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		Thread recepcion = new Thread(new Recepcion());
		Thread aterrizaje = new Thread(new Aterrizaje(new Avion("LA-A-350","Airbus 350")));
		
		//IUYILI
		//en un while recibe mas de un aeropuerto
		recepcion.start();
		aterrizaje.start();
/*
Codigo que estaba en el otro main
		int puerto = 8888;				//CREO UN PUERTO CUALQUIERA
		Thread [] aeropuerto = new Thread[2];	//CREO DOS HILOS (DOS AEROPUERTOS PODRAN ENTRAR);
		
		Socket so;
		for(int i=0;i<2;i++) {
			try(ServerSocket ss = new ServerSocket(puerto)){
				so = ss.accept();
				System.out.println("Se conecto el aeropuerto " + so.getPort());
				aeropuerto[i] = new Thread(new Conexion(so));
			}catch(Exception e){
				
			}
		}
		
		for(int i = 0 ; i<2; i++){
			aeropuerto[i].start();
		}
		
		for(int i = 0 ; i<2; i++){ 
			aeropuerto[i].join();;
		}
		  
 
 */
	}

}
