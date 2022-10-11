package com.elumini.autorizador.domain;

import java.math.BigDecimal;

public class Cartao {

	private BigDecimal numeroCartao;
	private Integer senha;
	private BigDecimal saldo;
	
	public Cartao() {
		super();
	}
	
	public Cartao(BigDecimal numeroCartao, Integer senha, BigDecimal saldo) {
		super();
		this.numeroCartao = numeroCartao;
		this.senha = senha;
		this.saldo = saldo;
	}
	public BigDecimal getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(BigDecimal numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Integer getSenha() {
		return senha;
	}
	public void setSenha(Integer senha) {
		this.senha = senha;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public static class CartaoBuilder {
		
		private BigDecimal numeroCartao;
		private Integer senha;
		private BigDecimal saldo;
		
		public CartaoBuilder withNumeroCartao(BigDecimal numeroCartao) {
			this.numeroCartao = numeroCartao;
			return this;
		}

		public CartaoBuilder withSenha(Integer senha) {
			this.senha = senha;
			return this;
		}

		public CartaoBuilder withSaldo(BigDecimal saldo) {
			this.saldo = saldo;
			return this;
		}

		public static CartaoBuilder builder() {
			return new CartaoBuilder();
		}
		
		public Cartao build() {
			return new Cartao(numeroCartao, senha, saldo);
		}
		
	}

}
