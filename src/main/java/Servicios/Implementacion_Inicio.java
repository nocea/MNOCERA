package Servicios;

import java.util.Scanner;

public class Implementacion_Inicio implements Interfaz_Inicio {

	@Override
	public int Menu() {
		int opcion=0;
		Scanner scan=new Scanner(System.in);
		do {
			System.out.println("Introduzca una opción");
			System.out.println("1.Alta Elemento Vajilla");
			System.out.println("2.Baja Elemento Vajilla");
			System.out.println("3.Modificar Stock Elemento Vajilla");
			System.out.println("4.Mostrar Stock");
			System.out.println("5.Crear Reserva");
			System.out.println("0.Salir de la app");
			opcion=scan.nextInt();
			if(opcion<0||opcion>5)
				System.out.println("[ERROR]-Esa opción no está en el menú");
		}while(opcion<0||opcion>4);
		return opcion;
	}

}
