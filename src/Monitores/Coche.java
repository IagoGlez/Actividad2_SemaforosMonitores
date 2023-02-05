package Monitores;

import java.util.Random;

public class Coche extends Thread{

	private enum State{ESPERANDO, CRUZANDO};
	private State state;
	private String nombre;
	private String modelo;
	private int id;
	private static int totalCoches;
	private MonitorPuente monitor;
	private int vecesCruzado = 0;
	private Boolean direccion;
	
	public Coche(String n, String model, MonitorPuente m, Boolean d) {
		id = totalCoches++;
		nombre = n;
		modelo = model;
		state = state.ESPERANDO;
		monitor = m;
		direccion = d;
		start();
		
	}
	public Boolean getDireccion() {
		return direccion;
	}
	
	public void cruzar() {
		String direccionString= "";
		if (direccion == true) {
			direccionString = "Norte a Sur";
		}else {
			direccionString = "Sur a Norte";
		}
		
		System.out.println("El coche " + nombre + " - " + modelo + " con ID: " + id + " está cruzando de " + direccionString);
		
		Random rnd = new Random();
		int tiempoCruzar = rnd.nextInt(250 - 50 + 1) + 50;
		
		try {
			sleep(tiempoCruzar);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (direccion == true) {
			direccion = false;
		}else
			direccion = true;
		
		vecesCruzado++;
		System.out.println("El coche " + nombre + " - " + modelo + " con ID: " + id + " ha cruzado. Veces cruzadas: " + vecesCruzado);
		monitor.liberarTurnoCruzar(id);
		state = state.ESPERANDO;
		
	}
	
	

	public void esperar() {
		String direccionString= "";
		if (direccion == true) {
			direccionString = "Norte";
		}else {
			direccionString = "Sur";
		}
		
		Random rnd = new Random();
		int tiempoCruzar = rnd.nextInt(999 - 200 + 1) + 200;
		
		try {
			sleep(tiempoCruzar);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("El coche " + nombre + " - " + modelo + " con ID: " + id + " está esperando para poder cruzar en el " + direccionString);
		monitor.obtenerTurnoCruzar(id, direccion);
		//El coche tiene la oporturnidad de empezar a cruzar
		state =  state.CRUZANDO;
	}
	
	
	@Override
	public void run() {
		
		while(true) {
			
			switch(state) {
			case ESPERANDO:
				esperar();
				break;
			case CRUZANDO:
				cruzar();
				break;	
			}	
		}
	}
}
