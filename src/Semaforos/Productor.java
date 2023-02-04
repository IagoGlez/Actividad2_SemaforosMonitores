package Semaforos;
import java.util.Random;

public class Productor extends Thread{

	private static int productoresCount = 0;
	private int productorId;
	
	public Productor() {
		productorId = ++productoresCount;
		start();
	}
	
	private void producir() {
		Random rdmNum = new Random();
		
		
		int sleepTime = rdmNum.nextInt(250-25+1) + 25; //Rango (max - min + 1) + min va en milisegundos entre 250 y 25
		int elementosProducidos = rdmNum.nextInt(5) +1;
		try {
			sleep(sleepTime);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("----------------------");
		System.out.println("Elementos producidos de golpe: " +elementosProducidos + " por el mismo productor" + productorId);
		for (int i= 0; i<elementosProducidos; i++) {
			int numP = rdmNum.nextInt(999) + 1;
			if (Buffer.getStore().size() == Buffer.bSize) {
				System.out.println("Productor " + productorId +": El Buffer está lleno, esperando a que algún consumidor trabaje");
				return;
			}else {
				Buffer.getStore().add(numP);
				System.out.println("Productor " + productorId +": Número " + numP + " producido");
				System.out.println("BufferSize: " + Buffer.getStore().size());
			}
		}
		System.out.println("----------------------");
		
		
	}
	
	@Override
	public void run(){
		
		while(true) {
			
			try {
				//Comprueba si hay permisos
				Buffer.getnLleno().acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			producir();
			
			Buffer.getsNoVacio().release();
		}
	}
}
