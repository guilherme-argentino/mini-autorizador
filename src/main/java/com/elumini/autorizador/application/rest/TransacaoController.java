package com.elumini.autorizador.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elumini.autorizador.application.request.TransacaoRequest;
import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.Transacao.TransacaoBuilder;
import com.elumini.autorizador.domain.TransacaoStatus;
import com.elumini.autorizador.domain.service.TransacaoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("transacoes")
@AllArgsConstructor
public class TransacaoController {
	
	private TransacaoService service;
	
	@PostMapping
	public ResponseEntity<String> cria(@RequestBody TransacaoRequest transacao) {
		return ResponseEntity.ok(service.cria(de(transacao)).getValue().name());
	}

	private Transacao de(TransacaoRequest transacao) {
		// TODO Auto-generated method stub
		return TransacaoBuilder.builder() //
				.withCartao(CartaoBuilder.builder() //
						.withNumeroCartao(transacao.getNumeroCartao())
						.withSenha(transacao.getSenha())
						.build())
				.withValor(transacao.getValor()) //
				.build();
	}

}
