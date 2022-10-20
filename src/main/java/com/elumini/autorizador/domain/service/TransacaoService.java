package com.elumini.autorizador.domain.service;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.UUID;

import com.elumini.autorizador.domain.SaldoInsuficienteException;
import com.elumini.autorizador.domain.SenhaInvalidaException;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.TransacaoStatus;
import com.elumini.autorizador.domain.repository.TransacaoRepository;

public class TransacaoService {
	
	private final TransacaoRepository repository;
	
	private final CartaoService service;

	public TransacaoService(TransacaoRepository repository, CartaoService service) {
		super();
		this.repository = repository;
		this.service = service;
	}

	public Entry<Transacao, TransacaoStatus> cria(Transacao transacao) {
		if(!service.senhaCorreta(transacao.getCartao())) {
			throw new SenhaInvalidaException(transacao);
		}
		if(greaterThan(transacao.getValor(), service.saldoDe(transacao.getCartao().getNumeroCartao()).getKey())) {
			throw new SaldoInsuficienteException(transacao);
		}
		return new AbstractMap.SimpleEntry<Transacao, TransacaoStatus>(repository.save(transacao), TransacaoStatus.OK);
	}

	public Transacao findById(UUID id) {
		return repository.findById(id);
	}
	
    private boolean greaterThan(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) > 0;
    }
	
}
