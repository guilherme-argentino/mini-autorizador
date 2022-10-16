package com.elumini.autorizador.application.events;

import com.elumini.autorizador.domain.Transacao;

import lombok.Value;

@Value
public class TransacaoCriadaEvent {
	
	private Transacao transacao;

}
