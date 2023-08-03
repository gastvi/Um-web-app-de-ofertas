package br.com.mvc.mudi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvc.mudi.Dto.RequisicaoNovoPedido;
import br.com.mvc.mudi.model.Pedido;
import br.com.mvc.mudi.model.User;
import br.com.mvc.mudi.repository.PedidoRepository;
import br.com.mvc.mudi.repository.UserRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoController {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	UserRepository userRepository ;
	
	@GetMapping("formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "pedido/formulario"; 
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username);
		
		Pedido pedido = requisicao.toPedido();
		pedido.setUser(user);
		pedidoRepository.save(pedido);
		
		return "redirect:/Home"; 
	}

}
