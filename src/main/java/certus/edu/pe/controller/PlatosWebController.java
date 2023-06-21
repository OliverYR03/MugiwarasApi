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

import certus.edu.pe.modelo.Platos;
import certus.edu.pe.servicios.PlatosServicio;

@Controller
@RequestMapping("/platos")
public class PlatosWebController {
	@Autowired
    private PlatosServicio platosServicio;

    @GetMapping("/listarPlato")
    public String listarPlatos(Model model) {
        List<Platos> listaPlatos = platosServicio.buscarTodo();
        model.addAttribute("listaPlatos", listaPlatos);
        return "/moduloPlatos/listarPlato";		
    }
    
    

    @GetMapping("/nuevo")
    public String nuevoPlato(Model model) {
        Platos platos = new Platos();
        model.addAttribute("platos", platos);
        return "moduloPlatos/nuevoPlato";
    }

    @PostMapping("/guardar")
    public String crearPlato(@ModelAttribute("plato") Platos platos) {
        platosServicio.crear(platos);
        return "redirect:/platos/listarPlato";
    }

    @RequestMapping(value = "/actualizar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarPlatos(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloPlatos/editarPlato");
        Platos platos = platosServicio.buscarPorId(id);
        mav.addObject("platos", platos);
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPlato(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            platosServicio.borrarPorId(id);
            flash.addFlashAttribute("success", "Área Médica eliminada correctamente.");
            return "redirect:/platos/listarPlato";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Esta Área Médica no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/platos/listarPlato";
        }
    }
}
