package com.elumini.autorizador.infrastructure.repository.listeners;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.elumini.autorizador.application.events.TransacaoCriadaEvent;
import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.Transacao.TransacaoBuilder;
import com.elumini.autorizador.infrastructure.repository.entity.TransacaoEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TransacaoListener {

	private final ApplicationEventPublisher publisher;

	@Autowired
	public TransacaoListener(ApplicationEventPublisher publisher) {
		super();
		this.publisher = publisher;
	}

	@PostPersist
	private void anyPersist(TransacaoEntity entity) {
		log.debug("[TRANSACAO AUDIT] add complete for transacao: {}", entity.getId());
		publisher.publishEvent(new TransacaoCriadaEvent(from(entity)));
	}

	private Transacao from(TransacaoEntity entity) {
		return TransacaoBuilder.builder() //
				.withCartao( //
						CartaoBuilder.builder() //
								.withNumeroCartao(entity.getCartao().getNumeroCartao()) //
								.withSaldo(entity.getCartao().getSaldo()) //
								.withSenha(entity.getCartao().getSenha()) //
								.build()) //
				.withValor(entity.getValor()) //
				.build();
	}

}
