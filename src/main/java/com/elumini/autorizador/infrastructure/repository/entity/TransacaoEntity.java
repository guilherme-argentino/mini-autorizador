package com.elumini.autorizador.infrastructure.repository.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.elumini.autorizador.infrastructure.repository.listeners.TransacaoListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@EntityListeners(TransacaoListener.class)
@NoArgsConstructor(access = AccessLevel.MODULE)
public class TransacaoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	/**
	 * FIXME: configuração ainda deixa preencher os dados do cartão e salvá-los
	 */
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.REFRESH})
	private CartaoEntity cartao;
	
	private BigDecimal valor;

}
