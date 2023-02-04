package Semaforos;
import java.util.Random;

public class Consumidor extends Thread{

	private static int consumidorCount = 0;
	private int consumidorId;
	
	public Consumidor() {
		consumidorId = ++consumidorCount;
		start();
	}
	
	private void consumir() {
		Random rdmNum = new Random();
		int sleepTime = rdmNum.nextInt(250-25 + 1) +25;
		int elementosConsumidos = rdmNum.nextInt(5) +1;
		
		try {
			sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("----------------------");
		System.out.println("Elementos consumidos de golpe: " +elementosConsumidos + " por el mismo consumidor: " + consumidorId);
		for (int i= 0; i<elementosConsumidos; i++) {
			if (Buffer.getStore().size() == 0) {
				System.out.println("Consumidor " + consumidorId +": El Buffer está vacío, esperando a que algún productor trabaje");
				return;
			}
			else {
				//Consumir
				int consumirNumero = Buffer.getStore().poll();
				System.out.println("Consumidor " + consumidorId +": Número " +  consumirNumero + " consumido.");
				System.out.println("BufferSize: " + Buffer.getStore().size());
			}
			
		}
		
		
	}
	
	@Override
	public void run() {
		while(true) {
			if (Buffer.getStore().size() == 0) {
				System.out.println("Consumidor " + consumidorId +": El buffer está vacío, esperando a que el productor trabaje");
			}
			
			try {
				Buffer.getsNoVacio().acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			consumir();
			
			Buffer.getnLleno().release();
		}
		
	}
	
}
