package com.elumini.autorizador.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Map.Entry;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.domain.repository.CartaoRepository;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
class CartaoServiceTest {
	
	@MockBean
	private CartaoRepository repository;
	
	@Autowired
	private CartaoService service;

	@Test
	void givenValidaCardData_whenCreatesCard_ReturnsInsertedCardAndTrue() {
		
		Cartao cartao = CartaoBuilder.builder()
				.withNumeroCartao("1234567812345678")
				.withSenha("1234")
				.withSaldo(BigDecimal.ZERO)
				.build();
		
		given(repository.findById(any())).willReturn(Optional.ofNullable(null));
		
		Entry<Cartao, Boolean> result = service.cria(cartao);
		
		assertEquals(Boolean.TRUE, result.getValue());
		assertEquals(cartao.getNumeroCartao(), result.getKey().getNumeroCartao());
		assertEquals(cartao.getSenha(), result.getKey().getSenha());
		
	}
	
	@Test
	void givenValidaCardData_whenCheckIfExists_ReturnsTrue() {
		
		Cartao cartao = CartaoBuilder.builder()
				.withNumeroCartao("1234567812345678")
				.withSenha("1234")
				.withSaldo(BigDecimal.ZERO)
				.build();
		
		given(repository.findById(any())).willReturn(Optional.of(cartao));
		
		Boolean result = service.existe(cartao);
		
		assertEquals(Boolean.TRUE, result);
		
	}
	
	@Test
	void givenValidCardNumber_whenQueriesBalance_ReturnsBalanceAndTrue() {
		
		String inputCardNumber = "1234123412341234";
		BigDecimal balance = BigDecimal.valueOf(495.15);
		
		Cartao cartao = CartaoBuilder.builder()
				.withNumeroCartao(inputCardNumber)
				.withSenha("1234")
				.withSaldo(balance)
				.build();
		
		given(repository.findById(any())).willReturn(Optional.of(cartao));
		
		Entry<BigDecimal, Boolean> result = service.saldoDe(inputCardNumber);
		
		assertEquals(Boolean.TRUE, result.getValue());
		assertEquals(balance, result.getKey());
		
	}

}
