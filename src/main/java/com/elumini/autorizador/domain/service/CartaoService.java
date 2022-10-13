package com.elumini.autorizador.domain.service;

import java.util.Map.Entry;

import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.repository.CartaoRepository;

/**
 * 
 * <h2> Serviço do domínio para {@link Cartao} </h2>
 * 
 * IMPORTANTE: para tornar este serviço livre de código de infraestrutura,
 * nenhum framework que poderia ser aplicado como o Spring Boot ou Lombok foram
 * descartados para respeitar os princípios da arquitetura hexagonal e DDD.
 * 
 * @author Guilherme Luiz Argentino Silva
 *
 */
public class CartaoService {

	@SuppressWarnings("unused")
	private final CartaoRepository repository;

	public CartaoService(CartaoRepository repository) {
		super();
		this.repository = repository;
	}

	public Entry<Cartao, Boolean> cria(Cartao cartao) {
		return null;
	}

	public Boolean existe(Cartao cartao) {
		return null;
	}

}
