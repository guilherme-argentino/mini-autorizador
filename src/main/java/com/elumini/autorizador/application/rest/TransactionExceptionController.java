package com.elumini.autorizador.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.elumini.autorizador.domain.CartaoInexistenteException;
import com.elumini.autorizador.domain.SaldoInsuficienteException;
import com.elumini.autorizador.domain.SenhaInvalidaException;
import com.elumini.autorizador.domain.TransacaoStatus;

@ControllerAdvice
public class TransactionExceptionController {
	
	@ExceptionHandler(value = SaldoInsuficienteException.class)
	public ResponseEntity<TransacaoStatus> exception(SaldoInsuficienteException exception) {
		return ResponseEntity.unprocessableEntity().body(TransacaoStatus.SALDO_INSUFICIENTE);
	}
	
	@ExceptionHandler(value = CartaoInexistenteException.class)
	public ResponseEntity<TransacaoStatus> exception(CartaoInexistenteException exception) {
		return ResponseEntity.unprocessableEntity().body(TransacaoStatus.CARTAO_INEXISTENTE);
	}
	
	@ExceptionHandler(value = SenhaInvalidaException.class)
	public ResponseEntity<TransacaoStatus> exception(SenhaInvalidaException exception) {
		return ResponseEntity.unprocessableEntity().body(TransacaoStatus.SENHA_INVALIDA);
	}	

}
