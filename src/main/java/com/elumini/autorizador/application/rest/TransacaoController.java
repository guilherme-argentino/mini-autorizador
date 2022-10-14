package com.elumini.autorizador.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elumini.autorizador.application.request.TransacaoRequest;
import com.elumini.autorizador.domain.service.TransacaoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("transacoes")
@AllArgsConstructor
public class TransacaoController {
	
	@SuppressWarnings("unused")
	private TransacaoService service;
	
	@PostMapping
	public ResponseEntity<String> cria(@RequestBody TransacaoRequest transacao) {
		return null;
	}

}
