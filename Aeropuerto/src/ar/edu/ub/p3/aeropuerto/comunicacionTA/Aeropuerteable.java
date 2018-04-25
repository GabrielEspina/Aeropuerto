package ar.edu.ub.p3.aeropuerto.comunicacionTA;
import ar.edu.ub.p3.tpi.api.Avion;

public interface Aeropuerteable {
	
	public Avion[] obtenerAvionesParaDespegar();
	public void    pudeDespegarAvion( Avion avion );
	public void    aterrizar( Avion avion );
	
}
