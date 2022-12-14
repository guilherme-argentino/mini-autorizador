package com.elumini.autorizador.application.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.elumini.autorizador.application.request.CartaoRequest;
import com.elumini.autorizador.application.response.CartaoResponse;
import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.service.CartaoService;
import com.elumini.autorizador.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CartaoControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	CartaoService service;

	@Test
	void givenValidCardAndPass_whenCreateCard_thenReturnSuccessWithJsonData() throws Exception {

		CartaoRequest inputCard = new CartaoRequest("1234567812345678", "1234");
		CartaoResponse resultCard = new CartaoResponse("1234567812345678", "1234");
		String resultCardString = objectMapper.writeValueAsString(resultCard);

		given(service.cria(any())).willReturn(new AbstractMap.SimpleEntry<Cartao, Boolean>(
				new Cartao("1234567812345678", "1234", BigDecimal.ZERO), true));

		mvc.perform(post("/cartoes").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(inputCard)))
				.andDo(print()).andExpect(status().isOk()).andExpect(content().json(resultCardString));
	}

	@Test
	void givenValidCardAndPass_whenCreateCard_thenReturnUnprocessableEntityWithJsonData() throws Exception {

		CartaoRequest inputCard = new CartaoRequest("1234567812345678", "1234");
		CartaoResponse resultCard = new CartaoResponse("1234567812345678", "1234");
		String resultCardString = objectMapper.writeValueAsString(resultCard);

		given(service.cria(any())).willReturn(new AbstractMap.SimpleEntry<Cartao, Boolean>(
				new Cartao("1234567812345678", "1234", BigDecimal.ZERO), false));

		mvc.perform(post("/cartoes").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(inputCard)))
				.andDo(print()).andExpect(status().isUnprocessableEntity()).andExpect(content().json(resultCardString));
	}

	@Test
	void givenValidCard_whenQueryBalance_thenReturnSuccessWithBalanceInfo() throws Exception {
		String inputCardNumber = "1234123412341234";
		BigDecimal balance = BigDecimal.valueOf(495.15);

		given(service.saldoDe(any())).willReturn(new AbstractMap.SimpleEntry<BigDecimal, Boolean>(balance, true));

		mvc.perform(get("/cartoes/" + inputCardNumber)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(is(balance.toString())));
	}

	@Test
	void givenValidCard_whenQueryBalance_thenReturUnprocessableEntity() throws Exception {
		String inputCardNumber = "1234123412341234";
		BigDecimal balance = BigDecimal.valueOf(495.15);

		given(service.saldoDe(any())).willReturn(new AbstractMap.SimpleEntry<BigDecimal, Boolean>(balance, false));

		mvc.perform(get("/cartoes/" + inputCardNumber)).andDo(print()).andExpect(status().isNotFound());
	}

}
