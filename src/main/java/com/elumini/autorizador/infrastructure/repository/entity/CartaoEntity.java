package com.elumini.autorizador.infrastructure.repository.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.MODULE)
public class CartaoEntity {
	
	@Id
	private BigDecimal numeroCartao;
	private Integer senha;
	private BigDecimal saldo;

}
