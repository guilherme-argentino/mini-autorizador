package com.elumini.autorizador.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Transacao {
	
	private UUID id;
	private Cartao cartao;
	private BigDecimal valor;

	public Transacao() {
	}

	public Transacao(Cartao cartao, BigDecimal valor) {
		super();
		this.id = UUID.randomUUID();
		this.cartao = cartao;
		this.valor = valor;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public static class TransacaoBuilder {
		
		private Cartao cartao;
		private BigDecimal valor;

		public TransacaoBuilder withCartao(Cartao cartao) {
			this.cartao = cartao;
			return this;
		}

		public TransacaoBuilder withValor(BigDecimal valor) {
			this.valor = valor;
			return this;
		}
		
		public static TransacaoBuilder builder() {
			return new TransacaoBuilder();
		}
		
		public Transacao build() {
			return new Transacao(cartao, valor);
		}
		
	}

}
