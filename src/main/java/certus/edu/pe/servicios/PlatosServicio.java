package certus.edu.pe.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import certus.edu.pe.modelo.Platos;
import certus.edu.pe.repositoriy.PlatosRepository;

@Service
@Transactional
public class PlatosServicio {


    @Autowired
    private PlatosRepository PlatosRepository;

    public List<Platos> buscarTodo() {
        return PlatosRepository.findAll();
    }
    
    

    public Platos crear(Platos Platos) {
        return PlatosRepository.save(Platos);
    }

    public Platos actualizar(Platos platoActualizar) {
        Platos platoActual = PlatosRepository.findById(platoActualizar.getIdplato()).get();

        platoActual.setIdplato(platoActualizar.getIdplato());
		platoActual.setNombre(platoActualizar.getNombre());
		platoActual.setDescripcion(platoActualizar.getDescripcion());
		platoActual.setPrecio(platoActualizar.getPrecio());
		platoActual.setTipoplato(platoActualizar.getTipoplato());
		platoActual.setImagen(platoActualizar.getImagen());
		
		Platos platoActualizado = PlatosRepository.save(platoActual);
		return platoActualizado;
	}
    
    public Platos buscarPorId(Integer idPlatos) {
        return PlatosRepository.findById(idPlatos).orElse(null);
    }

    public void borrarPorId(Integer idPlatos) {
        PlatosRepository.deleteById(idPlatos);
    }
	
}
