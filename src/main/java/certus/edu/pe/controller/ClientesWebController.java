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
import certus.edu.pe.servicios.ClientesServicio;

@Controller
@RequestMapping("/clientes")
public class ClientesWebController {
	@Autowired
    private ClientesServicio clientesServicio;

    @GetMapping("/listarCliente")
    public String listarClientes(Model model) {
        List<Clientes> listaClientes = clientesServicio.buscarTodo();
        model.addAttribute("listaClientes", listaClientes);
        return "/moduloClientes/listarCliente";	
    }
    
    

    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        Clientes clientes = new Clientes();
        model.addAttribute("cliente", clientes);
        return "/moduloClientes/nuevoCliente";
    }

    @PostMapping("/guardar")
    public String crearPlato(@ModelAttribute("cliente") Clientes clientes) {
        clientesServicio.crear(clientes);
        return "redirect:/clientes/listarCliente";
    }

    @RequestMapping(value = "/actualizar/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ModelAndView editarPlatos(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("/moduloClientes/editarCliente");
        Clientes clientes = clientesServicio.buscarPorId(id);
        mav.addObject("clientes", clientes);
        return mav;
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPlato(@PathVariable("id") int id, RedirectAttributes flash) {
        try {
            clientesServicio.borrarPorId(id);
            flash.addFlashAttribute("success", "Área Médica eliminada correctamente.");
            return "redirect:/clientes/listarCliente";
        } catch (DataIntegrityViolationException error) {
            flash.addFlashAttribute("error", "Esta Área Médica no se puede eliminar debido a restricciones de clave foránea.");
            System.out.println(error);
            return "redirect:/clientes/listarCliente";
        }
    }
}
