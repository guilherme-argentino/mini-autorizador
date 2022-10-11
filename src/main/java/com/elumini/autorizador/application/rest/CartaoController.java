package com.elumini.autorizador.application.rest;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elumini.autorizador.application.request.CartaoRequest;
import com.elumini.autorizador.application.response.CartaoResponse;
import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.service.CartaoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("cartoes")
@AllArgsConstructor
public class CartaoController {

	private final CartaoService service;

	@PostMapping
	public ResponseEntity<CartaoResponse> cria(CartaoRequest cartao) {
		Entry<Cartao, Boolean> result = service.cria(de(cartao));
		return result.getValue() ? ResponseEntity.ok(de(result.getKey()))
				: ResponseEntity.unprocessableEntity().body(de(result.getKey()));
	}

	@GetMapping("{numeroCartao}")
	public BigDecimal saldo(@PathVariable BigDecimal numeroCartao) {
		return null;
	}

	private Cartao de(CartaoRequest cartao) {
		return null;
	}

	private CartaoResponse de(Cartao cartao) {
		return null;
	}

}
