package certus.edu.pe.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
import javax.persistence.Table;
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