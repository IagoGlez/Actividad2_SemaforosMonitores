package Monitores;

public class Program {

	public static void main(String[] args) {
		
		MonitorPuente monitor = new MonitorPuente();
		
		//Coches
		new Coche("BMW", "Serie 1", monitor, true);
		new Coche("Mercedes", "Clase A", monitor, true);
		new Coche("Nissan", "Terano", monitor,true);
		new Coche("Porche", "930 Turbo", monitor, true);

	}

}
