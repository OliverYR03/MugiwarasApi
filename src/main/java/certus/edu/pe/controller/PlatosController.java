package certus.edu.pe.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import certus.edu.pe.model.exceptions.ResourceNotFoundException;
import certus.edu.pe.modelo.Platos;
import certus.edu.pe.repository.PlatosRepository;

// @CrossOrigin(origins = "http://localhost:4200") // conectar a angular peruano  || 3000 for react
@RestController
@RequestMapping("/api/platos")
public class PlatosController {

	@Autowired
	private PlatosRepository platosRepository;
	
	@GetMapping("/listar")
	public List<Platos> getAllPlatos() 
	{
        return platosRepository.findAll();
    }
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Platos> getPlatosId(@PathVariable (value = "id") Integer platosId)
	throws ResourceNotFoundException {
		
		Platos platos = platosRepository.findById(platosId).orElseThrow(() ->
		new ResourceNotFoundException("Plato not found for this id : :" + platosId));
		return ResponseEntity.ok(platos);
	}
	
	@PostMapping("nuevo")
	public Platos createPlato(@Validated @RequestBody Platos platos) {
		return platosRepository.save(platos);
	}
	
	@PutMapping("/listar/{id}")
	public ResponseEntity <Platos> updatePlato(@PathVariable(value = "id") Integer platosId,
	@Validated @RequestBody Platos platosDetails) throws ResourceNotFoundException{
	Platos platos = platosRepository.findById(platosId)
	.orElseThrow(() -> new ResourceNotFoundException("Platos not found for this id : : " + platosId));
	
	platos.setNombre(platosDetails.getNombre());
	
	final Platos updatePlato = platosRepository.save(platos);
	return ResponseEntity.ok(updatePlato);
	}
	
	@DeleteMapping("/listar/{id}")
	public Map<String, Boolean> deletePlato (@PathVariable (value = "id") Integer platosId)
			throws ResourceNotFoundException{
			Platos platos = platosRepository.findById(platosId)
					.orElseThrow(() -> new ResourceNotFoundException("Platos not found for this id : :" + platosId));
			platosRepository.delete(platos);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	}
	}
