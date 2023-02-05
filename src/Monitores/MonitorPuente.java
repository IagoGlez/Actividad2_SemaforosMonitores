package Monitores;

public class MonitorPuente {

	private boolean puenteDisponible = true;
	private boolean[] sentido = new boolean[4];
	private boolean sentidoCocheAnterior = true;
	
	
	public MonitorPuente() {
		for(int i = 0; i <4; i++) {
			puenteDisponible = true;
			if (i <= 1) {
				sentido[i] = true;
			}else {
				sentido[i] = false;
			}
		}		
	}
	
	public synchronized void obtenerTurnoCruzar(int i, Boolean sentidoCocheActual) {
		//El coche i espera hasta que no haya coches cruzando el puente en dirección opuesta
		//System.out.println(sentidoCocheAnterior);
		//System.out.println(sentidoCocheActual);
		while(!puenteDisponible && sentidoCocheAnterior != sentidoCocheActual) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//El coche i se dispone a cruzar el puente.
		puenteDisponible = false;
		sentidoCocheAnterior = sentidoCocheActual;
		if (sentido[i] == true) {
			sentido[i] = false;
		}else {
			sentido[i] = true;
		}
		
	}
	
	public synchronized void liberarTurnoCruzar(int i) {
		//El coche i ha terminado de cruzar y libera el puente
		puenteDisponible = true;
		if (sentido[i] == true) {
			sentido[i] = false;
		}else {
			sentido[i] = true;
		}
		
		//Notificamos a los coches en espera que se ha liberado el puente, por lo que pueden comprobar otra vez si pueden 
		//empezar a cruzar o no
		notify();
		
	}
	
}
