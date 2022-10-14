package com.elumini.autorizador.infrastructure.repository.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.MODULE)
public class CartaoEntity {
	
	@Id
	private String numeroCartao;
	private String senha;
	private BigDecimal saldo;

}
