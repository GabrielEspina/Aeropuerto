import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import ar.edu.ub.p3.tpi.api.Avion;

public class Recepcion implements Runnable {

//	private Avion avion;
	private ListaAviones listaAviones;
	
	public Recepcion(ListaAviones listaAviones) {
	// TODO Auto-generated constructor stub
		
		setListaAviones(listaAviones);
		
	}
	  
	@Override
	public void run() {
		
		try(ServerSocket ss = new ServerSocket(8888)){
			
			while(true) {
				
				Socket so = ss.accept();
			
				System.out.println("Conectado con Aeropuerto " + so.getPort()+ "\t para despegar aviones");				
				
				//Creo una lista de aviones recibidos para poder rebotarlos al terminar [2018/04/25 wduartes]
				List<Avion>	avionesRecibidosEnTransaccion = new LinkedList<Avion>();
				
				try(ObjectInputStream oin = new ObjectInputStream (so.getInputStream())){
					
					do 
					{
						Avion avion = (Avion) oin.readObject();					
				
						if( avion != null ) 
						{
					
							listaAviones.agreagarAvion(avion);
				
							System.out.println("Aeropuerto "+ so.getPort()+" despega >"+  avion.getModelo());
												
							///////////////////////////////////////////////////
							// DEBUG: Agrego el avion a la lista de aviones recibidos en la transaccion [2018/04/25 wduartes]
							
							avionesRecibidosEnTransaccion.add( avion );
						
						}
					
					}
					while( true );
				
				} catch (EOFException e) {
				
					//Ya no hay mas aviones para recibir, dejo el loop
					
					
				} catch (IOException e) {
					System.out.println("Fallo en vuelo de avion");
					e.printStackTrace();
					
				} catch (ClassNotFoundException e) {
					System.out.println("Error en recepcion de avion");
					e.printStackTrace();
				}
				
				///////////////////////////////////////////////////
				// DEBUG: reboto todos los aviones que recibi al aeropuerto [2018/04/25 wduartes]
				
				for( Avion avion : avionesRecibidosEnTransaccion )			
					this.enviarAvionDebug( so, avion );			
				
			}
		}catch(Exception e){	
			
		}
		
	}
	
	private void enviarAvionDebug(Socket so, Avion avion) {		
		
		try( Socket s = new Socket( "127.0.0.1", 8889) ){
			
			try( ObjectOutputStream oos  = new ObjectOutputStream( s.getOutputStream()) )
			{
				oos.writeObject( avion );
			}
			catch (IOException e) {
				throw e;
			}			
			
		}
		catch ( IOException e) {
			e.printStackTrace();
		}	
	}

/*
	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}
*/
	public ListaAviones getListaAviones() {
		return listaAviones;
	}

	public void setListaAviones(ListaAviones listaAviones) {
		this.listaAviones = listaAviones;
	}

}
