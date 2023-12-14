package DAOS;

import java.util.Calendar;
import java.util.List;

import jakarta.persistence.*;
/***
 * Clase que representa los prestamos/reservas de vajillas
 * @Entity
 */
@Table(name="prestamo",schema="esqexados")
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idReserva")
	private int idReserva;

	@Column(name="fchReserva")
	private Calendar fchReserva;
}
