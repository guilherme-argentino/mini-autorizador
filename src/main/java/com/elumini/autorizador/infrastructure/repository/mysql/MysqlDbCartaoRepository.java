package com.elumini.autorizador.infrastructure.repository.mysql;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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
		// TODO Auto-generated method stub
		return from(repo.findById(id));
	}

	@Override
	public void save(Cartao cartao) {
		// TODO Auto-generated method stub
		repo.save(from(cartao));

	}

	private CartaoEntity from(Cartao cartao) {
		return CartaoEntity.builder()
				.numeroCartao(cartao.getNumeroCartao())
				.saldo(cartao.getSaldo())
				.senha(cartao.getSenha())
				.build();
	}

	private Optional<Cartao> from(Optional<CartaoEntity> cartaoEntity) {
		// TODO Auto-generated method stub
		return cartaoEntity.map((tempCartaoEntity) -> CartaoBuilder.builder()
				.withNumeroCartao(tempCartaoEntity.getNumeroCartao())
				.withSaldo(tempCartaoEntity.getSaldo())
				.withSenha(tempCartaoEntity.getSenha())
				.build());
	}

}
