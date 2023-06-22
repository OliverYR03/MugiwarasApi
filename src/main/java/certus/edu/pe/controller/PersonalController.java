package certus.edu.pe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import certus.edu.pe.model.exceptions.ResourceNotFoundException;
import certus.edu.pe.modelo.Personal;
import certus.edu.pe.repository.PersonalRepository;

//@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/personal")
public class PersonalController {

	@Autowired
	private PersonalRepository personalRepository;
	
	@GetMapping("/listar")
	public List<Personal> getAllPersonal() 
	{
        return personalRepository.findAll();
    }
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Personal> getPersonalId(@PathVariable (value = "id") Integer personalId)
	throws ResourceNotFoundException {
		
		Personal personal = personalRepository.findById(personalId).orElseThrow(() ->
		new ResourceNotFoundException("Pesronal not found for this id : :" + personalId));
		return ResponseEntity.ok(personal);
	}
	
	@PostMapping("nuevo")
	public Personal createPersonal(@Validated @RequestBody Personal personal) {
		return personalRepository.save(personal);
	}
	
	@PutMapping("/listar/{id}")
	public ResponseEntity <Personal> updatePersonal(@PathVariable(value = "id") Integer personalId,
			@Validated @RequestBody Personal personalDetails) throws ResourceNotFoundException{
			Personal personal = personalRepository.findById(personalId)
			.orElseThrow(() -> new ResourceNotFoundException("Personal not found for this id : : " + personalId));
			
			personal.setNombre(personalDetails.getNombre());
			personal.setDni(personalDetails.getDni());
			personal.setCargo(personalDetails.getCargo());
			personal.setImagen(personalDetails.getImagen());
			
			final Personal updatePersonal = personalRepository.save(personal);
			return ResponseEntity.ok(updatePersonal);
	}
	
	@DeleteMapping("/listar/{id}")
	public Map<String, Boolean> deletePersonal (@PathVariable (value = "id") Integer personalId)
			throws ResourceNotFoundException{
		Personal personal = personalRepository.findById(personalId)
					.orElseThrow(() -> new ResourceNotFoundException("´Personal not found for this id : :" + personalId));
		personalRepository.delete(personal);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
	}
}
