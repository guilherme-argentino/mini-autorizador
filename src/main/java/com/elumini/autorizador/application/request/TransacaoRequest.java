package com.elumini.autorizador.application.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoRequest {
	
	private String numeroCartao;
	private String senha;
	private BigDecimal valor;

}
