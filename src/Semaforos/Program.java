package Semaforos;
public class Program {

	public static final int Productores_Count = 10;
	public static final int Consumidores_Count = 10;
	
	public static void main(String[] args) {
		for (int i = 0; i< Productores_Count; i++) {
			new Productor();
		}
		for (int i = 0; i< Consumidores_Count; i++) {
			new Consumidor();
		}
		

	}

}
