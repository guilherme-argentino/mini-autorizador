package com.elumini.autorizador.infrastructure.repository.listeners;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.elumini.autorizador.application.events.CartaoCriadoEvent;
import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.infrastructure.repository.entity.CartaoEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CartaoListener {
	
    @Autowired
    private ApplicationEventPublisher publisher;
	
	@PostPersist
	private void anyPersist(CartaoEntity entity) {
		log.debug("[CARTAO AUDIT] add complete for cartao: {}", entity.getNumeroCartao());
		publisher.publishEvent(new CartaoCriadoEvent(from(entity)));
	}

	private Cartao from(CartaoEntity entity) {
		return CartaoBuilder.builder()
				.withNumeroCartao(entity.getNumeroCartao())
				.withSaldo(entity.getSaldo())
				.withSenha(entity.getSenha())
				.build();
	}

}
