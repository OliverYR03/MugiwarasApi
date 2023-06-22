package certus.edu.pe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import certus.edu.pe.model.exceptions.ResourceNotFoundException;
import certus.edu.pe.modelo.Ordenes;
import certus.edu.pe.repository.OrdenesRepository;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenesController {

	@Autowired
	private OrdenesRepository ordenesRepository;
	
	@GetMapping("/listar")
	public List<Ordenes> getAllOrdenes() 
	{
        return ordenesRepository.findAll();
    }
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Ordenes> getOrdenId(@PathVariable (value = "id") Integer ordenesId)
	throws ResourceNotFoundException {
		
		Ordenes ordenes = ordenesRepository.findById(ordenesId).orElseThrow(() ->
		new ResourceNotFoundException("Orden not found for this id : :" + ordenesId));
		return ResponseEntity.ok(ordenes);
	}
	
	@PostMapping("nuevo")
	public Ordenes createOrden(@Validated @RequestBody Ordenes ordenes) {
		return ordenesRepository.save(ordenes);
	}
	
	@PutMapping("/listar/{id}")
	public ResponseEntity <Ordenes> updateOrden(@PathVariable(value = "id") Integer ordenesId,
	@Validated @RequestBody Ordenes ordenesDetails) throws ResourceNotFoundException{
	Ordenes ordenes = ordenesRepository.findById(ordenesId)
	.orElseThrow(() -> new ResourceNotFoundException("Ordenes not found for this id : : " + ordenesId));
	
	ordenes.setClientes(ordenesDetails.getClientes());
	ordenes.setFecha(ordenesDetails.getFecha());
	ordenes.setPlatos(ordenesDetails.getPlatos());
	ordenes.setTotal(ordenesDetails.getTotal());
	
	final Ordenes updatePlato = ordenesRepository.save(ordenes);
	return ResponseEntity.ok(updatePlato);
	}
	
	@DeleteMapping("/listar/{id}")
	public Map<String, Boolean> deleteOrden (@PathVariable (value = "id") Integer ordenesId)
			throws ResourceNotFoundException{
			Ordenes ordenes = ordenesRepository.findById(ordenesId)
					.orElseThrow(() -> new ResourceNotFoundException("Ordenes not found for this id : :" + ordenesId));
			ordenesRepository.delete(ordenes);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	}
	
}
