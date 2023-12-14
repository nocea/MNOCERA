package Controladores;

import java.util.Calendar;
import java.util.Scanner;

import Servicios.Implementacion_Consultas;
import Servicios.Implementacion_Inicio;
import Servicios.Interfaz_Consultas;
import Servicios.Interfaz_Inicio;
import jakarta.persistence.*;


		
public class Inicio {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
		EntityManager em = emf.createEntityManager();
		Interfaz_Inicio interfazInicio=new Implementacion_Inicio();
		Interfaz_Consultas interfazConsultas=new Implementacion_Consultas();
		int opcion;
		do {
			opcion=interfazInicio.Menu();//llamo al menu y obtengo la opcion
			switch (opcion) {
			case 1:
				interfazConsultas.DarAltaElemento(em);
				break;
			case 2:
				interfazConsultas.EliminarElemento(em);
				break;
			case 3:
				interfazConsultas.ModificarCantidadElemento(em);
				break;
			case 4:
				interfazConsultas.MostrarStock(em);
				break;
			case 5:
				System.out.println("NO ME HA DADO TIEMPO A TERMINAR LA RESERVA");
				break;
			}
			
		}while(opcion!=0);
		
	}
}


