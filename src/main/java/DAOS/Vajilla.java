package DAOS;
import java.util.List;

import jakarta.persistence.*;
/***
 * Clase que representa un elemento de una vajilla
 */
@Entity
@Table(name="vajilla",schema="esqexados")
public class Vajilla {
	//Columnas
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idElemento")
	private int idElemento;
	
	@Column(name="codigoElemento")
	private String codigoElemento;
	
	@Column(name="nombreElemento")
	private String nombreElemento;
	
	@Column(name="descripcionElemento")
	private String descripcionElemento;
	
	@Column(name="cantidadElemento")
	private int cantidadElemento;
	//Relaciones
	@OneToMany(mappedBy="vajilla")
	List<rel_vajilla_prestamo> listaVajillas;
	//Getters y Setters
	public int getCantidadElemento() {
		return cantidadElemento;
	}
	public void setCantidadElemento(int cantidadElemento) {
		this.cantidadElemento = cantidadElemento;
	}
	//Constructores
	public Vajilla( String codigoElemento,String  nombreElemento, String descripcionElemento, int cantidadElemento) {
		super();
		this.codigoElemento=codigoElemento;
		this.nombreElemento = nombreElemento;
		this.descripcionElemento = descripcionElemento;
		this.cantidadElemento = cantidadElemento;
	}
	
	public Vajilla() {
		super();
	}
	//metodos para mostrar
	@Override
	public String toString() {
		return "Vajilla [idElemento=" + idElemento + ", codigoElemento=" + codigoElemento + ", nombreElemento="
				+ nombreElemento + ", descripcionElemento=" + descripcionElemento + ", cantidadElemento="
				+ cantidadElemento + "]";
	}
	public String mostrarElemento() {
		return "[codigoElemento=" + codigoElemento + ", nombreElemento="
				+ nombreElemento +  ", cantidadElemento="
				+ cantidadElemento + "]";
	}
	
	
}
