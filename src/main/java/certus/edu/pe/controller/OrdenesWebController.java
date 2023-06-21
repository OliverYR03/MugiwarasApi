package certus.edu.pe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import certus.edu.pe.modelo.Clientes;
import certus.edu.pe.modelo.Ordenes;
import certus.edu.pe.modelo.Platos;
import certus.edu.pe.servicios.ClientesServicio;
import certus.edu.pe.servicios.OrdenesServicio;
import certus.edu.pe.servicios.PlatosServicio;

@Controller
@RequestMapping("/ordenes")
public class OrdenesWebController {

	@Autowired
    private OrdenesServicio ordenesServicio;
	
	@Autowired
	private ClientesServicio clientesServicio;
	
	@Autowired
	private PlatosServicio platosServicio;

    @GetMapping("/listarOrdenes")
    public String listarOrdenes(Model model) {
        List<Ordenes> listaOrdenes = ordenesServicio.buscarTodo();
        model.addAttribute("listaOrdenes", listaOrdenes);
        
        List<Clientes> listaClientes = clientesServicio.buscarTodo();
        model.addAttribute("listaClientes", listaClientes);
        
        List<Platos> listaPlatos = platosServicio.buscarTodo();
        model.addAttribute("listaPlatos", listaPlatos);
        
        return "/moduloOrdenes/listarOrdenes";	
    }
    
    

    @GetMapping("/nuevo")
    public String nuevoOrdenes(Model model) {
        Ordenes ordenes = new Ordenes();
        model.addAttribute("ordenes", ordenes);
        return "/moduloOrdenes/nuevoOrdenes";
    }

    @PostMapping("/guardar")
    public String crearOrdenes(@ModelAttribute("ordenes") Ordenes ordenes) {
        ordenesServicio.crear(ordenes);
        return "redirect:/ordenes/listarOrdenes";
    }

    @RequestMapping(value = "/actualizar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarOrdenes(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloOrdenes/editarOrdenes");
        Ordenes ordenes = ordenesServicio.buscarPorId(id);
        mav.addObject("ordenes", ordenes);
        
        List <Clientes> clientes = clientesServicio.buscarTodo();
        mav.addObject("listaClientes", clientes);
        
        List <Platos> platos = platosServicio.buscarTodo();
        mav.addObject("listaPlatos", platos);
        
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarOrdenes(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            ordenesServicio.borrarPorId(id);
            flash.addFlashAttribute("success", "Área Médica eliminada correctamente.");
            return "redirect:/ordenes/listarOrdenes";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Esta Área Médica no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/ordenes/listarOrdenes";
        }
    }
	
}
