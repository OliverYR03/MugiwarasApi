package certus.edu.pe.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certus.edu.pe.modelo.Ordenes;
import certus.edu.pe.repositoriy.OrdenesRepository;

import javax.transaction.Transactional;


@Service
@Transactional
public class OrdenesServicio {

	@Autowired
	private OrdenesRepository ordenesRepository;
	
	public List<Ordenes> buscarTodo(){
		return ordenesRepository.findAll();
	}
	

	public Ordenes crear (Ordenes ordenes) {
		return ordenesRepository.save(ordenes);
	}
	

	public Ordenes actualizar(Ordenes ordenesActualizar) {
		Ordenes ordenesActual = ordenesRepository.findById(ordenesActualizar.getIdorden()).get();
		
		ordenesActual.setIdorden(ordenesActualizar.getIdorden());
		ordenesActual.setClientes(ordenesActualizar.getClientes());
		ordenesActual.setFecha(ordenesActualizar.getFecha());
		ordenesActual.setPlatos(ordenesActualizar.getPlatos());
		ordenesActual.setTotal(ordenesActualizar.getTotal());
		
		Ordenes ordenesActualizado = ordenesRepository.save(ordenesActual);
		return ordenesActualizado;
	}
	
	public Ordenes buscarPorId(Integer id) {
		return ordenesRepository.findById(id).get();
	}
	
	public void borrarPorId(Integer id) {
		ordenesRepository.deleteById(id);
	}
}
