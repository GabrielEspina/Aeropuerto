import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Aplication {
	//TRAFICO AEREO
	//------------------------------------------------
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
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
		
		
		
	}

}
