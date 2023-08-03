package br.com.mvc.mudi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvc.mudi.model.Pedido;
import br.com.mvc.mudi.model.StatusPedidos;
import br.com.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("Home")
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public String home(Model model) {
		
		Sort sort = Sort.by("dataDaEntrega").ascending();
		PageRequest paginacao = PageRequest.of(0, 10, sort);
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedidos.ENTREGUE, paginacao);
		model.addAttribute("pedidos", pedidos);
		
		return "Home";
		
	}
	
}
