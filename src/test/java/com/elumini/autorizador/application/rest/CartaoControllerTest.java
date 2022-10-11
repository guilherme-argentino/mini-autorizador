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
import org.springframework.test.web.servlet.MockMvc;

import com.elumini.autorizador.application.request.CartaoRequest;
import com.elumini.autorizador.application.response.CartaoResponse;
import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.service.CartaoService;
import com.elumini.autorizador.util.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class CartaoControllerTest {

	@Autowired
	private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

	@MockBean
	CartaoService service;

	@Test
	void givenValidCardAndPass_whenCreateCard_thenReturnSuccessWithJsonData() throws Exception {

		CartaoRequest inputCard = new CartaoRequest(BigDecimal.valueOf(1234567812345678L), 1234);
		CartaoResponse resultCard = new CartaoResponse(BigDecimal.valueOf(1234567812345678L), 1234);
		String resultCardString = objectMapper.writeValueAsString(resultCard);

		given(service.cria(any())).willReturn(new AbstractMap.SimpleEntry<Cartao, Boolean>(
				new Cartao(BigDecimal.valueOf(1234567812345678L), 1234, BigDecimal.ZERO), true));

		mvc.perform(post("/cartoes").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(inputCard)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(resultCardString));
	}

	@Test
	void givenValidCardAndPass_whenCreateCard_thenReturnUnprocessableEntityWithJsonData() throws Exception {

		CartaoRequest inputCard = new CartaoRequest(BigDecimal.valueOf(1234567812345678L), 1234);
		CartaoResponse resultCard = new CartaoResponse(BigDecimal.valueOf(1234567812345678L), 1234);
		String resultCardString = objectMapper.writeValueAsString(resultCard);

		given(service.cria(any())).willReturn(new AbstractMap.SimpleEntry<Cartao, Boolean>(
				new Cartao(BigDecimal.valueOf(1234567812345678L), 1234, BigDecimal.ZERO), false));

		mvc.perform(post("/cartoes").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(inputCard)))
				.andDo(print()).andExpect(status().isUnprocessableEntity())
				.andExpect(content().json(resultCardString));
	}

}
