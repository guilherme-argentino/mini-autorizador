package com.elumini.autorizador.domain;

public class CartaoInexistenteException extends TransacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5403218458694311913L;

	public CartaoInexistenteException(String message, Transacao transacao, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, transacao, cause, enableSuppression, writableStackTrace);
	}

	public CartaoInexistenteException(String message, Transacao transacao, Throwable cause) {
		super(message, transacao, cause);
	}

	public CartaoInexistenteException(String message, Transacao transacao) {
		super(message, transacao);
	}

	public CartaoInexistenteException(Transacao transacao, Throwable cause) {
		super(transacao, cause);
	}

	public CartaoInexistenteException(Transacao transacao) {
		super(transacao);
	}

}
