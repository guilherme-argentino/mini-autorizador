package com.elumini.autorizador.domain.service;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.Optional;

import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.CartaoInexistenteException;
import com.elumini.autorizador.domain.repository.CartaoRepository;

/**
 * 
 * <h2>Serviço do domínio para a entidade {@link Cartao}</h2>
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

	public Boolean senhaCorreta(Cartao cartao) {
		Cartao result = repository.findById(cartao.getNumeroCartao()).orElseThrow(CartaoInexistenteException::new);

		return result.getSenha().equals(cartao.getSenha());
	}

	public Entry<Cartao, Boolean> atualiza(Cartao cartao) {
		return new AbstractMap.SimpleEntry<Cartao, Boolean>(repository.save(cartao), true);
	}

	public Entry<BigDecimal, Boolean> saldoDe(String numeroCartao) {
		Optional<Cartao> result = repository.findById(numeroCartao);
		return result.isPresent() //
				? new AbstractMap.SimpleEntry<BigDecimal, Boolean>(result.get().getSaldo(), true)
				: new AbstractMap.SimpleEntry<BigDecimal, Boolean>(BigDecimal.ZERO, false);
	}

	public Cartao findById(String numeroCartao) {
		// TODO Auto-generated method stub
		return repository.findById(numeroCartao).orElseThrow(CartaoInexistenteException::new);
	}

}
