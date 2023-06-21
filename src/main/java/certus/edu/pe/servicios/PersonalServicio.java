package certus.edu.pe.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certus.edu.pe.modelo.Personal;
import certus.edu.pe.repositoriy.PersonalRepository;
import certus.edu.pe.repositoriy.PlatosRepository;

@Service
@Transactional
public class PersonalServicio {
	
	@Autowired
	private PersonalRepository personalRepositorio;
	
	public PersonalServicio() {
		
	}
	
	   public List<Personal> buscarTodo() {
	        return personalRepositorio.findAll();
	    }
	
	public Personal crear(Personal personal) {
		return personalRepositorio.save(personal);
	}
	
	
public Personal actualizar(Personal personalActualizar) {
		
		Personal personalActual = personalRepositorio.findById(personalActualizar.getIdpersonal()).get();
		
		personalActual.setIdpersonal(personalActualizar.getIdpersonal());
		personalActual.setNombre(personalActualizar.getNombre());
		personalActual.setDni(personalActualizar.getDni());
		personalActual.setCargo(personalActualizar.getCargo());
		personalActual.setImagen(personalActualizar.getImagen());
		
		Personal personalActualizado = personalRepositorio.save(personalActual);
		return personalActualizado;
	}
	
	public Personal buscarPorId(Integer id) {
		return personalRepositorio.findById(id).get();
	}
	
	public void borrarPorId(Integer id) {
	
		personalRepositorio.deleteById(id);
	}
	

}
