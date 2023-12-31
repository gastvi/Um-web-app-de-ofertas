package br.com.mvc.mudi.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mvc.mudi.interceptor.InterceptadorDeAcessos;
import br.com.mvc.mudi.interceptor.InterceptadorDeAcessos.Acesso;

@RequestMapping("acessos")
@RestController
public class AcessoRest {

	
	@GetMapping
	public List<Acesso> getAcesso(){
		return InterceptadorDeAcessos.acessos;
	}
	
}
