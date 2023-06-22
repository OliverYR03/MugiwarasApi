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
import certus.edu.pe.modelo.Opiniones;
import certus.edu.pe.repository.OpinionesRepository;

//@CrossOrigin(origins = "http://localhost:4200") // conectar a angular peruano  || 3000 for react
@RestController
@RequestMapping("/api/opiniones")
public class OpinionesController {
	@Autowired
	private OpinionesRepository opinionesRepository;
	
	@GetMapping("/listar")
	public List<Opiniones> getAllOpiniones() 
	{
        return opinionesRepository.findAll();
    }
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Opiniones> getOpinionId(@PathVariable (value = "id") Integer opinionId)
	throws ResourceNotFoundException {
		
		Opiniones opiniones = opinionesRepository.findById(opinionId).orElseThrow(() ->
		new ResourceNotFoundException("Orden not found for this id : :" + opinionId));
		return ResponseEntity.ok(opiniones);
	}
	
	@PostMapping("nuevo")
	public Opiniones createOpinion(@Validated @RequestBody Opiniones opiniones) {
		return opinionesRepository.save(opiniones);
	}
	

	
	@PutMapping("/listar/{id}")
	public ResponseEntity <Opiniones> updateOpinion(@PathVariable(value = "id") Integer opinionId,
	@Validated @RequestBody Opiniones ordenesDetails) throws ResourceNotFoundException{
	Opiniones opiniones = opinionesRepository.findById(opinionId)
	.orElseThrow(() -> new ResourceNotFoundException("Opiniones not found for this id : : " + opinionId));
	
	opiniones.setClientes(ordenesDetails.getClientes());
	opiniones.setPlatos(ordenesDetails.getPlatos());
	opiniones.setClientes(ordenesDetails.getClientes());
	opiniones.setCalificacion(ordenesDetails.getCalificacion());
	opiniones.setComentario(ordenesDetails.getComentario());
	opiniones.setFecha(ordenesDetails.getFecha());
	
	final Opiniones updatePlato = opinionesRepository.save(opiniones);
	return ResponseEntity.ok(updatePlato);
	}
	
	@DeleteMapping("/listar/{id}")
	public Map<String, Boolean> deleteOpinion (@PathVariable (value = "id") Integer opinionId)
			throws ResourceNotFoundException{
			Opiniones opiniones = opinionesRepository.findById(opinionId)
					.orElseThrow(() -> new ResourceNotFoundException("Opiniones not found for this id : :" + opinionId));
			opinionesRepository.delete(opiniones);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	}
	
}
