package com.elumini.autorizador.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Map.Entry;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.Transacao.TransacaoBuilder;
import com.elumini.autorizador.domain.TransacaoStatus;
import com.elumini.autorizador.domain.repository.TransacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
class TransacaoServiceTest {
	
	@MockBean
	TransacaoRepository repository;
	
	@Autowired
	TransacaoService service;
	
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void givenValidTransactionData_whenCreatesTransaction_ReturnsSuccess() throws Exception {
		Transacao transacao = TransacaoBuilder.builder()
				.withCartao(CartaoBuilder.builder()
						.withNumeroCartao("6549873025634501")
						.withSenha("1234")
						.build())
				.withValor(BigDecimal.valueOf(10.00))
				.build();
		
		Transacao transacaoClonada = objectMapper
			      .readValue(objectMapper.writeValueAsString(transacao), Transacao.class);
		transacaoClonada.setId(UUID.randomUUID());
		
		given(repository.save(transacao)).willReturn(transacaoClonada);
		
		Entry<Transacao, TransacaoStatus> result = service.cria(transacao);
		
		assertEquals(TransacaoStatus.OK, result.getValue());
	}

}
