package br.com.mvc.mudi.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.mvc.mudi.model.Oferta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RequisicaoNovaOferta {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	public Long pedidoId;
	
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	@NotNull
	public String valor; 

	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotNull
	public String dataDaEntrega;
	
	public String comentario;
	
	

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long peidoid) {
		this.pedidoId = peidoid;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataDaEntrega() {
		return dataDaEntrega;
	}

	public void setDataDaEntrega(String dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Oferta ToOferta() {
		
		Oferta oferta = new Oferta();
		
		oferta.setComentario(this.comentario);
		oferta.setDataDaEntrega(LocalDate.parse(this.dataDaEntrega, formatter));
		oferta.setValor(new BigDecimal(this.valor));
		
		return oferta;
	}



}
