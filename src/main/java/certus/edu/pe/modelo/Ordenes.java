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
@Table(name="ordenes")

@Data
@AllArgsConstructor
@NoArgsConstructor

//@NamedQueries({
//	@NamedQuery(name = "Ordenes.findAll", query = "SELECT p FROM Ordenes p")
//	, @NamedQuery(name  = "Ordenes.findByIdorden", query = "SELECT p FROM Ordenes p WHERE p.idorden = :idorden")
//	, @NamedQuery(name  = "Ordenes.findByIdcliente", query = "SELECT p FROM Ordenes p WHERE p.idcliente = :idcliente")
//	, @NamedQuery(name  = "Ordenes.findByFecha", query = "SELECT p FROM Ordenes p WHERE p.fecha = :fecha")
//	, @NamedQuery(name  = "Ordenes.findByPlato", query = "SELECT p FROM Ordenes p WHERE p.platos = :platos")
//	, @NamedQuery(name  = "Ordenes.findByTotal", query = "SELECT p FROM Ordenes p WHERE p.total = :total")
//})

public class Ordenes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional=true)
	private Integer idorden;
	
	@Basic(optional = false)
	
	@ManyToOne(optional = false)
    @JoinColumn(name ="idcliente", referencedColumnName = "idclientes")
	private Clientes clientes;
	
	@Column(name = "fecha")
	private String fecha;
	
	@ManyToOne(optional = false)
	@JoinColumn(name ="IdPlatos", referencedColumnName = "idplato")
	private Platos platos;
	
	@Column(name = "total")
	private Integer total;

	
	
}