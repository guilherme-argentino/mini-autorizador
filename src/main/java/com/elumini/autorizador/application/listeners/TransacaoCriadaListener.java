package com.elumini.autorizador.application.listeners;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.elumini.autorizador.application.events.TransacaoCriadaEvent;
import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.service.CartaoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class TransacaoCriadaListener {

	private final CartaoService service;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
	public void handleTransacaoCriadaEvent(TransacaoCriadaEvent event) {
		log.debug("evento TransacaoCriadaEvent iniciado");

		Transacao transacao = event.getTransacao();
		Cartao cartao = transacao.getCartao();

		BigDecimal saldo = cartao.getSaldo().subtract(transacao.getValor());
		cartao.setSaldo(saldo);

		service.atualiza(cartao);
	}

}
