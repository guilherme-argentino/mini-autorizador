package com.elumini.autorizador.domain.service;

import java.util.AbstractMap;
import java.util.Map.Entry;

import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.repository.CartaoRepository;

/**
 * 
 * <h2>Serviço do domínio para {@link Cartao}</h2>
 * 
 * IMPORTANTE: para tornar este serviço livre de código de infraestrutura, os
 * frameworks que poderiam ser aplicados como o Spring Boot ou Lombok foram
 * descartados para respeitar os princípios da arquitetura hexagonal e DDD.
 * 
 * @author Guilherme Luiz Argentino Silva
 *
 */
public class CartaoService {

	private final CartaoRepository repository;

	public CartaoService(CartaoRepository repository) {
		super();
		this.repository = repository;
	}

	public Entry<Cartao, Boolean> cria(Cartao cartao) {
		if (existe(cartao)) {
			return new AbstractMap.SimpleEntry<Cartao, Boolean>(cartao, false);
		}
		repository.save(cartao);
		return new AbstractMap.SimpleEntry<Cartao, Boolean>(cartao, true);
	}

	public Boolean existe(Cartao cartao) {
		return repository.findById(cartao.getNumeroCartao()).isPresent();
	}

}
