package com.elumini.autorizador.infrastructure.repository.mysql;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.repository.CartaoRepository;

import lombok.AllArgsConstructor;

@Primary
@Component
@AllArgsConstructor
public class MysqlDbCartaoRepository implements CartaoRepository {
	
	@SuppressWarnings("unused")
	private final SpringDataJpaCartaoRepository repo;

	@Override
	public Optional<Cartao> findById(BigDecimal id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void save(Cartao cartao) {
		// TODO Auto-generated method stub

	}

}
