package com.elumini.autorizador.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartaoResponse {

	private String numeroCartao;
	private String senha;

}
