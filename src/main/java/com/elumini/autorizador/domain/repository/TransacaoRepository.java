package com.elumini.autorizador.domain.repository;

import com.elumini.autorizador.domain.Transacao;

public interface TransacaoRepository {
	
	Transacao save(Transacao transacao);
}
