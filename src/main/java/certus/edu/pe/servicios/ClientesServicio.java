package certus.edu.pe.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certus.edu.pe.modelo.Clientes;
import certus.edu.pe.repositoriy.ClientesRepository;
import javax.transaction.Transactional;

@Service
@Transactional
public class ClientesServicio {
	
	@Autowired 
	private ClientesRepository repositorio;
	
	
	public List<Clientes> buscarTodo(){
		return repositorio.findAll();
	}
	
	
	//Crear
	public Clientes crear(Clientes cliente) {
		return repositorio.save(cliente);
	}
	
	
	//Actualizar
	public Clientes actualizar(Clientes clienteActualizar) {
		Clientes clienteActual = repositorio.findById(clienteActualizar.getIdclientes()).get();
		
		clienteActual.setIdclientes(clienteActualizar.getIdclientes());
		clienteActual.setNombre(clienteActualizar.getNombre());
		clienteActual.setDireccion(clienteActualizar.getDireccion());
		clienteActual.setNumero(clienteActualizar.getNumero());
		clienteActual.setCorreo(clienteActualizar.getCorreo());
		clienteActual.setTipopago(clienteActualizar.getTipopago());
		
		Clientes clienteActualizado = repositorio.save(clienteActual);
		return clienteActualizado;
	}
	
	
	public Clientes buscarPorId(Integer id) {
		return repositorio.findById(id).get();
	}
	
	public void borrarPorId(Integer id) {
		repositorio.deleteById(id);
	}
	

}
