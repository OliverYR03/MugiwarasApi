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
import certus.edu.pe.modelo.Opiniones;
import certus.edu.pe.modelo.Platos;
import certus.edu.pe.servicios.ClientesServicio;
import certus.edu.pe.servicios.OpinionesServicio;
import certus.edu.pe.servicios.PlatosServicio;

@Controller
@RequestMapping("/opiniones")
public class OpinionesWebController {
	
	@Autowired
    private OpinionesServicio opinionesServicio;
	
	@Autowired
	private ClientesServicio clientesServicio;
	
	@Autowired
	private PlatosServicio platosServicio;

    @GetMapping("/listarOpinion")
    public String listarPlatos(Model model) {
        List<Opiniones> listaOpiniones = opinionesServicio.buscarTodo();
        model.addAttribute("listaOpiniones", listaOpiniones);
        
        List<Clientes> listaClientes = clientesServicio.buscarTodo();
        model.addAttribute("listaClientes", listaClientes);
        
        List<Platos> listaPlatos = platosServicio.buscarTodo();
        model.addAttribute("listaPlatos", listaPlatos);
        
        return "/moduloOpiniones/listarOpinion";	
    }
    
    

    @GetMapping("/nuevo")
    public String nuevoPlato(Model model) {
        Opiniones opiniones = new Opiniones();
        model.addAttribute("opinion", opiniones);
        return "/moduloOpiniones/nuevoOpinion";
    }

    @PostMapping("/guardar")
    public String crearPlato(@ModelAttribute("opinion") Opiniones opiniones) {
        opinionesServicio.crear(opiniones);
        return "redirect:/opiniones/listarOpinion";
    }

    @RequestMapping(value = "/actualizar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarPlatos(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloOpiniones/editarOpinion");
        Opiniones opiniones = opinionesServicio.buscarPorId(id);
        mav.addObject("opiniones", opiniones);
        
        List <Clientes> clientes = clientesServicio.buscarTodo();
        mav.addObject("listaClientes", clientes);
        
        List <Platos> platos = platosServicio.buscarTodo();
        mav.addObject("listaPlatos", platos);
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String borrarPorId(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            opinionesServicio.borrarPorId(id);
            flash.addFlashAttribute("success", "Área Médica eliminada correctamente.");
            return "redirect:/opiniones/listarOpinion";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Esta Área Médica no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/opiniones/listarOpinion";
        }
    }
}
