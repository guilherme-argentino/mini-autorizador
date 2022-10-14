package com.elumini.autorizador.domain;

import java.math.BigDecimal;

public class Cartao {

	private String numeroCartao;
	private String senha;
	private BigDecimal saldo;
	
	public Cartao() {
		super();
	}
	
	public Cartao(String numeroCartao, String senha, BigDecimal saldo) {
		super();
		this.numeroCartao = numeroCartao;
		this.senha = senha;
		this.saldo = saldo;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public static class CartaoBuilder {
		
		private String numeroCartao;
		private String senha;
		private BigDecimal saldo;
		
		public CartaoBuilder withNumeroCartao(String numeroCartao) {
			this.numeroCartao = numeroCartao;
			return this;
		}

		public CartaoBuilder withSenha(String senha) {
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
