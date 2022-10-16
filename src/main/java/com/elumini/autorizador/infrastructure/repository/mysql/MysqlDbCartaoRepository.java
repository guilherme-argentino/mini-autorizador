package com.elumini.autorizador.infrastructure.repository.mysql;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.domain.repository.CartaoRepository;
import com.elumini.autorizador.infrastructure.repository.entity.CartaoEntity;

import lombok.AllArgsConstructor;

@Primary
@Component
@AllArgsConstructor
public class MysqlDbCartaoRepository implements CartaoRepository {
	
	private final SpringDataJpaCartaoRepository repo;

	@Override
	public Optional<Cartao> findById(String id) {
		return from(repo.findById(id));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Cartao save(Cartao cartao) {
		return from(repo.save(from(cartao)));

	}

	private CartaoEntity from(Cartao cartao) {
		return CartaoEntity.builder()
				.numeroCartao(cartao.getNumeroCartao())
				.saldo(cartao.getSaldo())
				.senha(cartao.getSenha())
				.build();
	}

	private Optional<Cartao> from(Optional<CartaoEntity> cartaoEntity) {
		return cartaoEntity.map((tempCartaoEntity) -> from(tempCartaoEntity));
	}

	private Cartao from(CartaoEntity cartaoEntity) {
		return CartaoBuilder.builder()
				.withNumeroCartao(cartaoEntity.getNumeroCartao())
				.withSaldo(cartaoEntity.getSaldo())
				.withSenha(cartaoEntity.getSenha())
				.build();
	}

}
