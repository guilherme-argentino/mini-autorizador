package com.elumini.autorizador.application.rest;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elumini.autorizador.application.request.CartaoRequest;
import com.elumini.autorizador.application.response.CartaoResponse;

@RestController
@RequestMapping("cartoes")
public class CartaoController {
	
	@PostMapping
	public CartaoResponse cria(CartaoRequest cartao) {
		return null;
	}
	
	@GetMapping("{numeroCartao}")
	public BigDecimal saldo(@PathVariable BigDecimal numeroCartao) {
		return null;
	}

}
