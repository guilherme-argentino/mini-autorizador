package com.elumini.autorizador.domain.repository;

import java.util.UUID;

import com.elumini.autorizador.domain.Transacao;

public interface TransacaoRepository {
	
	Transacao save(Transacao transacao);

	Transacao findById(UUID id);
}
