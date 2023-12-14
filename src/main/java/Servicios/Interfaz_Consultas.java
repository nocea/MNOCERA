package Servicios;

import java.util.List;

import DAOS.Vajilla;
import jakarta.persistence.EntityManager;

public interface Interfaz_Consultas {
	/***
	 * Método que sirve para crear un elemento de la Vajilla
	 * @param em
	 */
	public void DarAltaElemento(EntityManager em);
	/***
	 * Método que sirve para eliminar un elemento de la Vajilla
	 * @param em
	 */
	public void EliminarElemento(EntityManager em);
	/***
	 * Método que sirve para mostrar todos los Elementos con el toString
	 * @param em
	 * @return
	 */
	public List<Vajilla> MostrarElementos(EntityManager em);
	/***
	 * Método para reducir la cantidad de stock de un elemento
	 * @param em
	 */
	public void ModificarCantidadElemento(EntityManager em);
	/***
	 * Método que muestra todo el stock disponible
	 * @param em
	 */
	public void MostrarStock(EntityManager em);
	/***
	 * Método para crear una reserva(NO TERMINADO)
	 * @param em
	 */
	public void CrearReserva(EntityManager em);
}
