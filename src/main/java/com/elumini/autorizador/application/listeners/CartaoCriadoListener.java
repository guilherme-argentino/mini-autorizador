package com.elumini.autorizador.application.listeners;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.elumini.autorizador.application.events.CartaoCriadoEvent;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.Transacao.TransacaoBuilder;
import com.elumini.autorizador.domain.service.TransacaoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CartaoCriadoListener {

	@Value("${app.transacao.carga-inicial:500}")
	private BigDecimal cargaInicial;

	private final TransacaoService service;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleCartaoCriadoEvent(CartaoCriadoEvent event) {
		log.debug("evento CartaoCriadoEvent iniciado");

		Transacao primeiraCarga = TransacaoBuilder.builder() //
				.withCartao(event.getCartao()) //
				.withValor(cargaInicial.multiply(BigDecimal.valueOf(-1))) //
				.build();
		service.cria(primeiraCarga);
		
		log.debug("evento CartaoCriadoEvent finalizado");
	}

}
