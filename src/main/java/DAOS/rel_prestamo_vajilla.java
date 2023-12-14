package DAOS;

import jakarta.persistence.*;

@Entity
@Table(name="rel_prestamo_vajilla",schema="esqexados")
public class rel_prestamo_vajilla {
 @Id
 @GeneratedValue(strategy =GenerationType.IDENTITY)
 @Column(name="id_rel_prestamo_vajilla")
 private int id_rel_prestamo_vajilla;
 @Column(name="cantidad_prestada")
 private int cantidad_prestada;
 @JoinColumn(name="idVajilla")
 private Vajilla vajilla;
 @ManyToOne
 @JoinColumn(name="idPrestamo")
 private Prestamo prestamo;

public rel_prestamo_vajilla(int cantidadReservar, Vajilla elemento, Prestamo prestamoElegido) {
	this.cantidad_prestada=cantidadReservar;
	this.vajilla=elemento;
	this.prestamo=prestamoElegido;
	
}
public rel_prestamo_vajilla() {
	super();
}
 
}
