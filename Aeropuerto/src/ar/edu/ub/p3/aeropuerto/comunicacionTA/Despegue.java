package ar.edu.ub.p3.aeropuerto.comunicacionTA;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import ar.edu.ub.p3.aeropuerto.aviones.ListaAviones;
import ar.edu.ub.p3.tpi.api.Avion;

public class Despegue implements Runnable{
	
//	private ListaAviones listaAviones;	
	private Aeropuerteable aeropuerto;
	
	private String ipTraficoAereo;
	private int puertoEscritura;
	/*
	public Despegue(ListaAviones listaAviones) {
		
		setListaAviones(listaAviones);
		
	}
	*/
	
	public Despegue(String ipTraficoAereo, int puertoEscritura) 
	{
			this.setIpTraficoAereo(ipTraficoAereo);
			this.setPuertoEscritura(puertoEscritura);
	}
  
	@Override
	public void run() {
		
		try (Socket so = new Socket( this.getIpTraficoAereo(), this.getPuertoEscritura() ) ) 
		{
			
			ObjectOutputStream oos  = new ObjectOutputStream( so.getOutputStream());
			
//			oos.writeObject( getListaAviones().getAviones().getFirst() ); 	//envio el avion			
//			getListaAviones().getAviones().removeFirst();					//si se envia lo saco de mi lista
			
			//Pido los aviones que estan listos para despegar en este momento [2018/04/25 wduartes]
			Avion[] aviones = this.getAeropuerto().obtenerAvionesParaDespegar();
			
			for( Avion avion : aviones)			
			{				
				//Sleep para separar la entrega de los aviones [2018/04/25 wduartes]
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
				
				oos.writeObject( avion );
				
				// A medida que los puedo ir mandando, los marco como despegados
				// OJO, esto no puede modificar a los mismos aviones del array de aviones [2018/04/25 wduartes]
				this.getAeropuerto().pudeDespegarAvion(avion);
				
			}							
						
			//Si quiero modificar el contenido de los aviones, tengo que hacerlo aca [2018/04/25 wduartes]
//			for( Avion avion : aviones)
//				this.getAeropuerto().pudeDespegarAvion(avion);
				
		}catch( IOException e ){
			
			System.out.println(">Despegue fallido");
			e.printStackTrace();
			
		}
		
	}

	/*
	public ListaAviones getListaAviones() {
		
		return listaAviones;
		
	}

	public void setListaAviones(ListaAviones listaAviones) {
		
		this.listaAviones = listaAviones;
		
	}
	
	*/

	public static Thread crearHilo(Aeropuerteable aeropuerto, String ipTraficoAereo, int puertoEscritura) 
	{		
		Despegue managerDespegue = new Despegue( ipTraficoAereo, puertoEscritura );
		Thread   hilo = new Thread( managerDespegue );
		
		managerDespegue.setAeropuerto( aeropuerto );		
		
		hilo.start();
		
		return hilo;
	}

	public Aeropuerteable getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(Aeropuerteable aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	public String getIpTraficoAereo() {
		return ipTraficoAereo;
	}

	public void setIpTraficoAereo(String ipTraficoAereo) {
		this.ipTraficoAereo = ipTraficoAereo;
	}

	public int getPuertoEscritura() {
		return puertoEscritura;
	}

	public void setPuertoEscritura(int puertoEscritura) {
		this.puertoEscritura = puertoEscritura;
	}
	
	

}
