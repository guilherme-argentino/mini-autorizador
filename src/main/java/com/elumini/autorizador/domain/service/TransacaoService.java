package com.elumini.autorizador.domain.service;

import java.util.AbstractMap;
import java.util.Map.Entry;

import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.TransacaoStatus;
import com.elumini.autorizador.domain.repository.TransacaoRepository;

public class TransacaoService {
	
	private final TransacaoRepository repository;

	public TransacaoService(TransacaoRepository repository) {
		super();
		this.repository = repository;
	}

	public Entry<Transacao, TransacaoStatus> cria(Transacao transacao) {
		return new AbstractMap.SimpleEntry<Transacao, TransacaoStatus>(repository.save(transacao), TransacaoStatus.OK);
	}
	
}
