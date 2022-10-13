package com.elumini.autorizador.domain.repository;

import java.math.BigDecimal;
import java.util.Optional;

import com.elumini.autorizador.domain.Cartao;

/**
 * Port para gravação dos dados da entidade {@link Cartao} em um banco
 * 
 * @author Guilherme Luiz Argentino Silva
 *
 */
public interface CartaoRepository {

	Optional<Cartao> findById(BigDecimal id);

	void save(Cartao cartao);

}
