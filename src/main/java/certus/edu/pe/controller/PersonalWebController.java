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

import certus.edu.pe.modelo.Personal;
import certus.edu.pe.servicios.PersonalServicio;

@Controller
@RequestMapping("/personal")
public class PersonalWebController {
	@Autowired
    private PersonalServicio personalServicio;

    @GetMapping("/listarPersonal")
    public String listarPersonal(Model model) {
        List<Personal> listaPersonal = personalServicio.buscarTodo();
        model.addAttribute("listaPersonal", listaPersonal);
        return "/moduloPersonal/listarPersonal";
    }
    
    

    @GetMapping("/nuevo")
    public String nuevoPersonal(Model model) {
        Personal personal = new Personal();
        model.addAttribute("personal", personal);
        return "/moduloPersonal/nuevoPersonal";
    }

    @PostMapping("/guardar")
    public String crearPersonal(@ModelAttribute("personal") Personal personal) {
        personalServicio.crear(personal);
        return "redirect:/personal/listarPersonal";
    }

    @RequestMapping(value = "/actualizar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarPlatos(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloPersonal/editarPersonal");
        Personal personal = personalServicio.buscarPorId(id);
        mav.addObject("personal", personal);
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String borrarPorId(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            personalServicio.borrarPorId(id);
            flash.addFlashAttribute("success", "Área Médica eliminada correctamente.");
            return "redirect:/personal/listarPersonal";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Esta Área Médica no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/personal/listarPersonal";
        }
    }
}
