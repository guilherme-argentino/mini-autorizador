package com.elumini.autorizador.infrastructure.repository.mysql;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
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
	@Transactional
	public Transacao save(Transacao transacao) {
		return de(repository.save(de(transacao)));
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
