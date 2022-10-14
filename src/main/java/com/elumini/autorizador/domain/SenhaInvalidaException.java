package com.elumini.autorizador.domain;

public class SenhaInvalidaException extends TransacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5403218458694311913L;

	public SenhaInvalidaException(String message, Transacao transacao, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, transacao, cause, enableSuppression, writableStackTrace);
	}

	public SenhaInvalidaException(String message, Transacao transacao, Throwable cause) {
		super(message, transacao, cause);
	}

	public SenhaInvalidaException(String message, Transacao transacao) {
		super(message, transacao);
	}

	public SenhaInvalidaException(Transacao transacao, Throwable cause) {
		super(transacao, cause);
	}

	public SenhaInvalidaException(Transacao transacao) {
		super(transacao);
	}

}
