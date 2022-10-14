package com.elumini.autorizador.application.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoRequest {
	
	private BigDecimal numeroCartao;
	private Integer senha;

}
