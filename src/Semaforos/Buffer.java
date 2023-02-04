package Semaforos;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Buffer {

	private static ConcurrentLinkedQueue<Integer> store = new ConcurrentLinkedQueue<Integer>();
	public static final int bSize = 5;
	
	//El semaforo no vacio lo inicializo a 0 mientras que el no lleno lo inicializo a 1 ya que,
	//al ser un semaforo binario solo puede tomar esos dos valores
	private static Semaphore sNoVacio = new Semaphore(0, true);
	private static Semaphore nLleno = new Semaphore(1, true);
	
	public static ConcurrentLinkedQueue<Integer> getStore() {
		return store;
	}
	
	public static Semaphore getsNoVacio() {
		return sNoVacio;
	}
	
	public static Semaphore getnLleno() {
		return nLleno;
	}
	
}
