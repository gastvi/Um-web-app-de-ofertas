package br.com.mvc.mudi.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mvc.mudi.Dto.RequisicaoNovaOferta;
import br.com.mvc.mudi.model.Oferta;
import br.com.mvc.mudi.model.Pedido;
import br.com.mvc.mudi.repository.PedidoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaRest {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping
	public ResponseEntity<Oferta> criarOferta (@Valid @RequestBody RequisicaoNovaOferta requisicao) {
		
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(requisicao.getPedidoId());
		
		if(!pedidoBuscado.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoBuscado.get();
		
		Oferta nova = requisicao.ToOferta();
		nova.setPedido(pedido);
		pedido.getOferta().add(nova);
		
		pedidoRepository.save(pedido);
		
		return ResponseEntity.ok().body(nova);
		
	}
	
}
