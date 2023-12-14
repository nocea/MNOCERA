package Servicios;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import DAOS.Prestamo;
import DAOS.Vajilla;
import DAOS.rel_prestamo_vajilla;
import jakarta.persistence.EntityManager;

public class Implementacion_Consultas implements Interfaz_Consultas {

	@Override
	public void DarAltaElemento(EntityManager em) {
		try {
			Scanner scan=new Scanner(System.in);
			String nombre,descripcion,codigo;
			int cantidad;
			//pido los datos
			System.out.println("Introduzca el nombre del elemento: ");
			nombre=scan.next();
			System.out.println("Introduzca la descripción del elemento: ");
			descripcion=scan.next();
			System.out.println("Introduzca la cantidad del elemento: ");
			cantidad=scan.nextInt();
			codigo=nombre+"-"+descripcion;
			//creo el elemento
			Vajilla elemento=new Vajilla(codigo,nombre,descripcion,cantidad);
			em.getTransaction().begin();
			//lo guardo
			em.persist(elemento);
			em.getTransaction().commit();
			System.out.println("[INFO]-Se ha dado de alta el elemento");
		}catch(Exception e) {
			System.out.println("[ERROR]-Se ha producido un error");
		}
		
	}
	@Override
	public void EliminarElemento(EntityManager em) {
		try {
			em.getTransaction().begin();
			int idElemento=0;
			List<Vajilla>listaElementos;
			listaElementos=MostrarElementos(em);//muestro para elegir
			for (int i = 0; i < listaElementos.size(); i++) {
				System.out.println(listaElementos.get(i).toString());
			}
			Scanner scan=new Scanner(System.in);
			System.out.println("Introduce el id del que quieras eliminar: ");
			idElemento=scan.nextInt();//pido el que quiera eliminar
			Vajilla elemento=em.find(Vajilla.class, idElemento);
			em.remove(elemento);//lo elimino
			em.getTransaction().commit();
			System.out.println("[INFO]-Se ha dado de baja el elemento");
		} catch (Exception e) {
			System.out.println("[ERROR]-Se ha producido un error");
		}
		
	}
	@Override
	public List<Vajilla> MostrarElementos(EntityManager em) {
		List<Vajilla> listaElementos = null;
		try {
			listaElementos=em.createQuery("SELECT a FROM Vajilla a", Vajilla.class).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listaElementos;
	}

	@Override
	public void ModificarCantidadElemento(EntityManager em) {
		try {
		em.getTransaction().begin();
		int idElemento=0;
		int cantidad=0;
		List<Vajilla>listaElementos;
		listaElementos=MostrarElementos(em);
		for (int i = 0; i < listaElementos.size(); i++) {
			System.out.println(listaElementos.get(i).toString());
		}
			Scanner scan=new Scanner(System.in);
			System.out.println("Introduce el id del que quieras editar la cantidad: ");
			idElemento=scan.nextInt();
			Vajilla elemento=em.find(Vajilla.class,idElemento);
			if(elemento.getCantidadElemento()==0)//si ya es 0 no me deja cambiarlo
			{System.out.println("[INFO]-No se puede bajar más la cantidad,ya es 0");}
			else {
			System.out.println("Introduce la cantidad que quiere reducir: ");
			cantidad=scan.nextInt();
			//si es menor que cero cuando resto lo dejo en 0
				if(elemento.getCantidadElemento()-cantidad<0) {
					elemento.setCantidadElemento(0);
				}
				else {
				elemento.setCantidadElemento(elemento.getCantidadElemento()-cantidad);
				}
			}
			em.merge(elemento);
			em.getTransaction().commit();
			System.out.println("[INFO]-Se ha cambiado la cantidad");
		}catch (Exception e) {
			System.out.println("[ERROR]-Se ha producido un error");
		}
	}
	@Override
	public void MostrarStock(EntityManager em) {
		List<Vajilla> listaElementos = null;
		try {
			listaElementos=em.createQuery("SELECT a FROM Vajilla a", Vajilla.class).getResultList();
			for (int i = 0; i < listaElementos.size(); i++) {
				System.out.println(listaElementos.get(i).mostrarElemento());
			}
		}catch(Exception e) {
			System.out.println("[ERROR]-Se ha producido un error");
		}
	}
	//Funciona sin gestionar las fechas
	@Override
	public void CrearReserva(EntityManager em) {
		em.getTransaction().begin();
		int dia,mes,año,idElemento,cantidadReservar,idPrestamo=0;
		Boolean existePrestamo=false;
		List<Vajilla> listaElementos;
		Scanner scan=new Scanner(System.in);
		//Pido la fecha
		Calendar fchReserva=Calendar.getInstance();
		System.out.println("Introduce el dia de la reserva: ");
		dia=scan.nextInt();
		System.out.println("Introduce el mes de la reserva: ");
		mes=scan.nextInt();
		System.out.println("Introduce el año de la reserva: ");
		año=scan.nextInt();
		//la guardo
		fchReserva.set(año, mes-1, dia);
		//y guardo el prestamo
		Prestamo prestamo=new Prestamo(fchReserva);
		em.persist(prestamo);
		em.getTransaction().commit();
		em.getTransaction().begin();
		System.out.println("Se ha guardado el prestamo");
		//Muestro los elementos
		listaElementos=MostrarElementos(em);
		for (int i = 0; i < listaElementos.size(); i++) {
			
			System.out.println(listaElementos.get(i).toString());
		}
			System.out.println("Introduce el id del que quieras seleccionar la reserva: ");
			idElemento=scan.nextInt();
			//guardo el elemento a reservar
		Vajilla elemento=em.find(Vajilla.class,idElemento);
		System.out.println("Guarda la reserva");
		//obtengo las reservas/prestamos
		List<Prestamo>listaReservas=em.createQuery("SELECT a FROM Prestamo a", Prestamo.class).getResultList();
		for (int i = 0; i < listaReservas.size(); i++) {
			//si la fecha de la reserva es la fecha que he introducido antes guardo su id
			if(listaReservas.get(i).getFchReserva()==fchReserva)
				idPrestamo=listaReservas.get(i).getIdReserva();
		}
		//por el id que he guardado antes obtengo el prestamo
		Prestamo prestamoElegido=em.find(Prestamo.class,idPrestamo);
		//guardo la cantidad a reservar
		System.out.println("Introduce la cantidad a reservar: ");
		cantidadReservar=scan.nextInt();
		//si la cantidad es mas que el stock no dejo reservar
		if(cantidadReservar>elemento.getCantidadElemento()) {
			System.out.println("No se puede reservar mas del stock");
		}
		else {
			//si es menor le paso todos los campos a la reserva y la guardo
		rel_prestamo_vajilla reserva=new rel_prestamo_vajilla(cantidadReservar,elemento,prestamoElegido);
		em.persist(reserva);
		em.getTransaction().commit();
		}
	}

}




