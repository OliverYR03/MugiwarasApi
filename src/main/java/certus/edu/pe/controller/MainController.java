package certus.edu.pe.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {
	
	@RequestMapping("/logout")
		public String welcome(Model model, HttpServletRequest request) {
			return "/index";
		}
	}

