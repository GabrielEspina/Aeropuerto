import ar.edu.ub.p3.aeropuerto.Aeropuerto;
import ar.edu.ub.p3.aeropuerto.comunicacionTA.ConexionTraficoAereo;

public class Aplicacion {

	public static void main(String[] args) {
		
		System.out.println("Soy el aeropuerto 1\n---------------------------\n");
		
		Aeropuerto aeropuerto = Aeropuerto.crearAeropuertoCfg1( );
		
		aeropuerto.connectTraficoAereo(  );
		
	}

}
