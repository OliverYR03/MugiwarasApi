package certus.edu.pe.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certus.edu.pe.repositoriy.OpinionesRepository;
import javax.transaction.Transactional;
import certus.edu.pe.modelo.*;

@Service
@Transactional
public class OpinionesServicio {

	@Autowired
	private OpinionesRepository opinionesRepositorio;
	
	public List<Opiniones> buscarTodo(){
		return opinionesRepositorio.findAll();
	}
	
	public Opiniones crear(Opiniones opiniones) {
		return opinionesRepositorio.save(opiniones);
	}
	
	public Opiniones actualizar(Opiniones opinionActualizar) {
		
		Opiniones opinionActual = opinionesRepositorio.findById(opinionActualizar.getIdopinion()).get();
		
		opinionActual.setIdopinion(opinionActualizar.getIdopinion());
		opinionActual.setPlatos(opinionActualizar.getPlatos());
		opinionActual.setClientes(opinionActualizar.getClientes());
		opinionActual.setCalificacion(opinionActualizar.getCalificacion());
		opinionActual.setComentario(opinionActualizar.getComentario());
		opinionActual.setFecha(opinionActualizar.getFecha());
		
		Opiniones opinionActualizada = opinionesRepositorio.save(opinionActual);
		return opinionActualizada;
	}
	
	public Opiniones buscarPorId(Integer id) {
		return opinionesRepositorio.findById(id).get();
	}
	
	public void borrarPorId(Integer id) {
		opinionesRepositorio.deleteById(id);
	}
}
