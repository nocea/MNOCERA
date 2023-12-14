package DAOS;

import jakarta.persistence.*;
/***
 * Tabla intermedia
 */
@Entity
@Table(name="rel_vajilla_prestamo",schema = "esqexados")
public class rel_vajilla_prestamo {
	//Columnas
	@Id
	@Column
	private int idRelacion;
	@Column(name = "cantidadReserva")
	private int cantidadReserva;
	//Relaciones
	@ManyToOne
	Vajilla vajilla;
	@ManyToOne
	Prestamo prestamo;
}
