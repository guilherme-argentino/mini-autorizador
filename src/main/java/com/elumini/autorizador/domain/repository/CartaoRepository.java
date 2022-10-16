package com.elumini.autorizador.domain.repository;

import java.util.Optional;

import com.elumini.autorizador.domain.Cartao;

/**
 * Port para gravação dos dados da entidade {@link Cartao} em um banco
 * 
 * @author Guilherme Luiz Argentino Silva
 *
 */
public interface CartaoRepository {

	Optional<Cartao> findById(String id);

	Cartao save(Cartao cartao);

}
