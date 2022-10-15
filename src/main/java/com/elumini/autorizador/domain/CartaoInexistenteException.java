package com.elumini.autorizador.domain;

public class CartaoInexistenteException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5403218458694311913L;

	public CartaoInexistenteException() {
		super();
	}

	public CartaoInexistenteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CartaoInexistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public CartaoInexistenteException(String message) {
		super(message);
	}

	public CartaoInexistenteException(Throwable cause) {
		super(cause);
	}

	
}
