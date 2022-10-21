package com.elumini.autorizador.infrastructure.repository.mysql;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.Transacao.TransacaoBuilder;
import com.elumini.autorizador.domain.repository.TransacaoRepository;
import com.elumini.autorizador.infrastructure.repository.entity.CartaoEntity;
import com.elumini.autorizador.infrastructure.repository.entity.TransacaoEntity;

import lombok.AllArgsConstructor;

@Primary
@Component
@AllArgsConstructor
public class MysqlDbTransacaoRepository implements TransacaoRepository {
	
	private final SpringDataJpaTransacaoRepository repository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Transacao save(Transacao transacao) {
		TransacaoEntity result = repository.save(de(transacao));
		// forçando refresh
		repository.flush();
		return de(repository.findById(result.getId())).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Transacao findById(UUID id) {
		// TODO Auto-generated method stub
		return de(repository.findById(id)).orElseThrow(EntityNotFoundException::new);
	}

	private TransacaoEntity de(Transacao transacao) {
		return TransacaoEntity.builder() //
				.cartao(CartaoEntity.builder() //
						.numeroCartao(transacao.getCartao().getNumeroCartao()) //
						.senha(transacao.getCartao().getSenha()) //
						.saldo(transacao.getCartao().getSaldo()) //
						.build()) //
				.valor(transacao.getValor()) //
				.build();
	}

	private Optional<Transacao> de(Optional<TransacaoEntity> transacaoEntity) {
		return transacaoEntity.map((tempTransacaoEntity) -> de(tempTransacaoEntity));
	}

	private Transacao de(TransacaoEntity transacao) {
		return TransacaoBuilder.builder()
				.withCartao(CartaoBuilder.builder()
						.withNumeroCartao(transacao.getCartao().getNumeroCartao()) //
						.withSenha(transacao.getCartao().getSenha()) //
						.withSaldo(transacao.getCartao().getSaldo()) //
						.build())
				.withValor(transacao.getValor())
				.build();
	}

}
