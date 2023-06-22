package certus.edu.pe.modelo;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="opiniones")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@NamedQueries({
	//@NamedQuery(name = "Opiniones.findAll", query = "SELECT p FROM Opiniones p")
	//, @NamedQuery(name  = "Opiniones.findByIdOpinion", query = "SELECT p FROM Opiniones p WHERE p.idopinion = :idopinion")
	//, @NamedQuery(name  = "Opiniones.findByIdPlato", query = "SELECT p FROM Opiniones p WHERE p.idplato = :idplato")
	//, @NamedQuery(name  = "Opiniones.findByNombre", query = "SELECT p FROM Opiniones p WHERE p.idcliente = :idcliente")
	//, @NamedQuery(name  = "Opiniones.findByDescripcion", query = "SELECT p FROM Opiniones p WHERE p.calificacion = :calificacion")
	//, @NamedQuery(name  = "Opiniones.findByPrecio", query = "SELECT p FROM Opiniones p WHERE p.comentario = :comentario")
	//, @NamedQuery(name  = "Opiniones.findByTipoPlato", query = "SELECT p FROM Opiniones p WHERE p.fecha = :fecha")})

public class Opiniones implements Serializable{

	private static final long serialVersionUID = 1L;

		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = true)
		private Integer idopinion;
		
		@ManyToOne(optional = false)
		@JoinColumn(name ="idplato", referencedColumnName = "idplato")
		private Platos platos;
		
		@ManyToOne(optional = false)
	    @JoinColumn(name ="idcliente", referencedColumnName = "idclientes")
		private Clientes clientes;
	    
	    @Column(name ="calificacion")
		private Integer calificacion;
	    
	    @Column(name ="comentario")
		private String  comentario;
	    
	    @Column(name ="fecha")
		private String  fecha;
	    
}