package com.elumini.autorizador.infrastructure.repository.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

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
	@Size(min = 16, max = 16)
	private String numeroCartao;
	@Size(min = 4, max = 4)
	private String senha;
	@Min(value = 0)
	private BigDecimal saldo;

}
