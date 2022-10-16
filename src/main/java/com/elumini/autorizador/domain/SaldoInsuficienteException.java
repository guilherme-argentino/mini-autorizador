package com.elumini.autorizador.domain;

public class SaldoInsuficienteException extends TransacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5375452489047512513L;

	public SaldoInsuficienteException(String message, Transacao transacao, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, transacao, cause, enableSuppression, writableStackTrace);
	}

	public SaldoInsuficienteException(String message, Transacao transacao, Throwable cause) {
		super(message, transacao, cause);
	}

	public SaldoInsuficienteException(String message, Transacao transacao) {
		super(message, transacao);
	}

	public SaldoInsuficienteException(Transacao transacao, Throwable cause) {
		super(transacao, cause);
	}

	public SaldoInsuficienteException(Transacao transacao) {
		super(transacao);
	}

}
