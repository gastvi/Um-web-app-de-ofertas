package br.com.mvc.mudi.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvc.mudi.model.Pedido;
import br.com.mvc.mudi.model.StatusPedidos;
import br.com.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping("pedido")
	public String home(Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepository.findByUsuario(principal.getName());
		model.addAttribute("pedidos", pedidos);
		return "usuario/Home";
		
	}
	
	@GetMapping("pedido/{status}")
	public String status(@PathVariable("status") String status ,Model model, Principal principal) {
		List<Pedido> pedidos = pedidoRepository.findByStatusEUsuario(StatusPedidos.valueOf(status.toUpperCase()), principal.getName());
		model.addAttribute("pedidos", pedidos);
			
		return "usuario/Home";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onErro() {
		return "redirect:/usuario/Home";
	}
	
}
