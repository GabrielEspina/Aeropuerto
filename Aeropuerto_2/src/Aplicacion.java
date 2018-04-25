import ar.edu.ub.p3.aeropuerto.Aeropuerto;

public class Aplicacion {

	public static void main(String[] args) {
		
		System.out.println("Soy el aeropuerto 2\n---------------------------\n");
		
		Aeropuerto aeropuerto = Aeropuerto.crearAeropuertoCfg2();
		
		aeropuerto.connectTraficoAereo();
		
	}

}
