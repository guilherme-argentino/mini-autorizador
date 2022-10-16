package com.elumini.autorizador.application.events;

import com.elumini.autorizador.domain.Cartao;

import lombok.Value;

@Value
public class CartaoCriadoEvent {
	
	private Cartao cartao;

}
