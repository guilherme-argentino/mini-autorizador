package com.elumini.autorizador.domain.repository;

import java.math.BigDecimal;
import java.util.Optional;

import com.elumini.autorizador.domain.Cartao;

public interface CartaoRepository {
	
	Optional<Cartao> findById(BigDecimal id);
	
	void save(Cartao cartao);

}
