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

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void handleTransacaoCriadaEvent(TransacaoCriadaEvent event) {
		log.debug("evento TransacaoCriadaEvent iniciado");

		try {
			Transacao transacao = event.getTransacao();
			Cartao cartao = service.findById(transacao.getCartao().getNumeroCartao());

			BigDecimal saldo = cartao.getSaldo().subtract(transacao.getValor());
			cartao.setSaldo(saldo);
			transacao.setCartao(cartao);

			service.atualiza(cartao);
		} catch (Exception e) {
			log.debug("evento TransacaoCriadaEvent finalizado com erro!!", e);
			throw e;
		}
		
		log.debug("evento TransacaoCriadaEvent finalizado");
	}

}
