package certus.edu.pe.modelo;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="platos")
//@NamedQueries({@NamedQuery(name = "Platos.findAll", query = "SELECT p FROM Platos p") 	//, @NamedQuery(name  = "Platos.findByIdPlato", query = "SELECT p FROM Platos p WHERE p.idplato = :idplato") 	//, @NamedQuery(name  = "Platos.findByNombre", query = "SELECT p FROM Platos p WHERE p.nombre = :nombre") 	//, @NamedQuery(name  = "Platos.findByDescripcion", query = "SELECT p FROM Platos p WHERE p.descripcion = :descripcion") 	//, @NamedQuery(name  = "Platos.findByPrecio", query = "SELECT p FROM Platos p WHERE p.precio = :precio") 	//, @NamedQuery(name  = "Platos.findByTipoPlato", query = "SELECT p FROM Platos p WHERE p.tipoplato = :tipoplato") 	//, @NamedQuery(name  = "Platos.findByImagen", query = "SELECT p FROM Platos p WHERE p.imagen = :imagen")})

public class Platos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = true)
	private Integer idplato;
	
	@Basic(optional = false)
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "precio")
	private Integer precio;
	
	@Column(name = "tipoplato")
	private String tipoplato;
	
	@Column(name = "imagen")
	private String imagen;
	
}
