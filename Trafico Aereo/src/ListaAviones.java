
import java.util.LinkedList;

import ar.edu.ub.p3.tpi.api.Avion;


public class ListaAviones {
	private LinkedList <Avion> aviones;
	
	public ListaAviones() {
		this.setAviones(new LinkedList <Avion> ());
	}

	public void agreagarAvion(Avion avion){
		this.getAviones().add(avion);
		
	}
	
	public String toString(){
		String representacion = "";
		for(Avion avion: this.getAviones())
			representacion += avion.getId() + "\t "+ avion.getModelo()+"\n";
		return representacion;
	}
	
	
	
	public LinkedList <Avion> getAviones() {
		return aviones;
	}

	public void setAviones(LinkedList <Avion> aviones) {
		this.aviones = aviones;
	}


	
}
