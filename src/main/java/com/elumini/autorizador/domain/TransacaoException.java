package com.elumini.autorizador.domain;

public class TransacaoException extends BusinessException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7049842802255345461L;
	
	private Transacao transacao;
	
	public TransacaoException(Transacao transacao) {
		super();
		this.transacao = transacao;
	}
	public TransacaoException(String message, Transacao transacao, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.transacao = transacao;
	}
	public TransacaoException(String message, Transacao transacao, Throwable cause) {
		super(message, cause);
		this.transacao = transacao;
	}
	public TransacaoException(String message, Transacao transacao) {
		super(message);
		this.transacao = transacao;
	}
	public TransacaoException(Transacao transacao, Throwable cause) {
		super(cause);
		this.transacao = transacao;
	}
	public Transacao getTransacao() {
		return transacao;
	}

}
