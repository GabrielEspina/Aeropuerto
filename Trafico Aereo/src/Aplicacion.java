import ar.edu.ub.p3.tpi.api.Avion;

public class Aplicacion {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		System.out.println("Soy el trafico aereo\n---------------------------\n");

		ListaAviones listaAviones = new ListaAviones();
/*		
 * Quito la lista de aviones precargados del TA [2018/04/25 wduartes]
		listaAviones.agreagarAvion( new Avion("LA-A-350","Airbus 350") );
		listaAviones.agreagarAvion( new Avion("LA-A-321","Airbus 321") );
		listaAviones.agreagarAvion( new Avion("LA-A-319","Airbus 319") );
		listaAviones.agreagarAvion( new Avion("LA-B-747","Boeing-747") );
		listaAviones.agreagarAvion( new Avion("LA-B-787","Boeing 787-9") );
		listaAviones.agreagarAvion( new Avion("LA-B-767","Boeing 767-300") );
*/		 
		Thread recepcion = new Thread(new Recepcion(listaAviones));
		
		// Solo hay un hilo en el trafico aereo para escuchar los aviones
		// ese mismo hilo,debe crear un hilo propio de trabajo para determinar 
		// cuando los tiene que aterrizar [2018/04/25 wduartes]
		
		//Quito el thread de aterrizaje, el TA ahora rebota a origen los aviones [2018/04/25 wduartes]
//		Thread aterrizaje = new Thread(new Aterrizaje(listaAviones));
		
		//IUYILI
		//en un while recibe mas de un aeropuerto
		recepcion.start();
		
		//Quito el thread de aterrizaje, el TA ahora rebota a origen los aviones [2018/04/25 wduartes]		
//		aterrizaje.start();
		
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
