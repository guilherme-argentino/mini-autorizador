package com.elumini.autorizador.application.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoRequest {
	
	private String numeroCartao;
	private String senha;

}
