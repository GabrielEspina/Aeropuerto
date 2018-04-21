import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ar.edu.ub.p3.tpi.api.Avion;

public class Conexion implements Runnable{
	private Socket so;
	private Avion avion;
	
	public Conexion(Socket so) {
		this.setSo(so);
	}
	
	@Override
	public void run() {
		esperoAvion();
		enviarAvionADestino();
	}
	
	public void esperoAvion() {
		try(ObjectInputStream oin = new ObjectInputStream (getSo().getInputStream())){
			
			setAvion((Avion) oin.readObject()); 
			System.out.println("Aeropuerto "+ so.getPort()+" despega >"+ getAvion().getId() + " "+ getAvion().getModelo());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarAvionADestino() {
		try{
			
			ObjectOutputStream oos  = new ObjectOutputStream( so.getOutputStream());
			oos.writeObject(new Avion("AA-B-747","Boeing-747"));
			
		}catch(Exception e){
			
		}
	}

	public Socket getSo() {
		return so;
	}

	public void setSo(Socket so) {
		this.so = so;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

}