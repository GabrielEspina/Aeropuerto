import ar.edu.ub.p3.tpi.api.Avion;

public class Aplicacion {

	@SuppressWarnings("null")
	public static void main(String[] args) {
		
		Thread recepcion = new Thread(new Recepcion());
		Thread aterrizaje = new Thread(new Aterrizaje(new Avion("LA-A-350","Airbus 350")));
		
		
		//en un while recibe mas de un aeropuerto
		recepcion.start();
		aterrizaje.start();

	}

}
