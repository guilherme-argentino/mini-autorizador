package com.elumini.autorizador.application.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.AbstractMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.elumini.autorizador.application.request.TransacaoRequest;
import com.elumini.autorizador.domain.CartaoInexistenteException;
import com.elumini.autorizador.domain.SaldoInsuficienteException;
import com.elumini.autorizador.domain.SenhaInvalidaException;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.Transacao.TransacaoBuilder;
import com.elumini.autorizador.domain.TransacaoStatus;
import com.elumini.autorizador.domain.service.TransacaoService;
import com.elumini.autorizador.util.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class TransacaoControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TransacaoService service;

	@Test
	void givenValidaTransactionData_whenCreatesTransaction_ReturnsSuccess() throws Exception {
		TransacaoRequest transacaoRequest = new TransacaoRequest("6549873025634501", "1234", BigDecimal.valueOf(10.00));
		Transacao transacao = TransacaoBuilder.builder().build();
		
		given(service.cria(any())).willReturn(new AbstractMap.SimpleEntry<Transacao, TransacaoStatus>(transacao, TransacaoStatus.OK));
		
		mvc.perform(post("/transacoes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(JsonUtil.toJson(transacaoRequest))) //
		.andDo(print()) //
		.andExpect(status().isOk()) //
		.andExpect(content().string("OK"));
	}

	@Test
	void givenValidaTransactionData_whenCreatesTransaction_ReturnsLowBalance() throws Exception {
		TransacaoRequest transacaoRequest = new TransacaoRequest("6549873025634501", "1234", BigDecimal.TEN);
		Transacao transacao = TransacaoBuilder.builder().build();
		
		given(service.cria(any())).willThrow(new SaldoInsuficienteException(transacao));
		
		mvc.perform(post("/transacoes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(JsonUtil.toJson(transacaoRequest))) //
		.andDo(print()) //
		.andExpect(status().isUnprocessableEntity()) //
		.andExpect(content().string("SALDO_INSUFICIENTE"));
	}

	@Test
	void givenTransactionDataWithIncorrectPassword_whenCreatesTransaction_ReturnsInvalidPassword() throws Exception {
		TransacaoRequest transacaoRequest = new TransacaoRequest("6549873025634501", "1234", BigDecimal.TEN);
		Transacao transacao = TransacaoBuilder.builder().build();
		
		given(service.cria(any())).willThrow(new SenhaInvalidaException(transacao));
		
		mvc.perform(post("/transacoes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(JsonUtil.toJson(transacaoRequest))) //
		.andDo(print()) //
		.andExpect(status().isUnprocessableEntity()) //
		.andExpect(content().string("SENHA_INVALIDA"));
	}

	@Test
	void givenTransactionDataWithInexistentCard_whenCreatesTransaction_ReturnsInexistentCard() throws Exception {
		TransacaoRequest transacaoRequest = new TransacaoRequest("6549873025634501", "1234", BigDecimal.TEN);
		
		given(service.cria(any())).willThrow(new CartaoInexistenteException());
		
		mvc.perform(post("/transacoes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(JsonUtil.toJson(transacaoRequest))) //
		.andDo(print()) //
		.andExpect(status().isUnprocessableEntity()) //
		.andExpect(content().string("CARTAO_INEXISTENTE"));
	}

}
