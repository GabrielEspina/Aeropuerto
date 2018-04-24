import ar.edu.ub.p3.aeropuerto.Aeropuerto;

public class Aplicacion {

	public static void main(String[] args) {
		
		Aeropuerto aeropuerto = Aeropuerto.crearAeropuertoCfg2();
		
		aeropuerto.connectTraficoAereo();
		
	}

}
