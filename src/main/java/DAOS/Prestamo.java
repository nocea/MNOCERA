package DAOS;

import java.util.Calendar;
import java.util.List;

import jakarta.persistence.*;
/***
 * Clase que representa los prestamos/reservas de vajillas
 * 
 */
@Entity
@Table(name="prestamo",schema="esqexados")
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idReserva")
	private int idReserva;

	public Calendar getFchReserva() {
		return fchReserva;
	}
	public void setFchReserva(Calendar fchReserva) {
		this.fchReserva = fchReserva;
	}
	@Column(name="fchReserva")
	private Calendar fchReserva;
	@OneToMany(mappedBy = "prestamo")
	List<rel_prestamo_vajilla> listaReservas;
	public Prestamo(Calendar fchReserva) {
		super();
		this.fchReserva = fchReserva;
	}
	public Prestamo() {
		super();
	}
	
	
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public int getIdReserva() {
		// TODO Auto-generated method stub
		return idReserva;
	}
	
}
