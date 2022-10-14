package com.elumini.autorizador.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.elumini.autorizador.MiniAutorizadorApplication;
import com.elumini.autorizador.domain.repository.CartaoRepository;
import com.elumini.autorizador.domain.service.CartaoService;
import com.elumini.autorizador.domain.service.TransacaoService;

@Configuration
@ComponentScan(basePackageClasses = MiniAutorizadorApplication.class)
public class BeanConfiguration {
	
	@Bean
	public CartaoService cartaoService(CartaoRepository cartaoRepository) {
		return new CartaoService(cartaoRepository);
	}
	
	@Bean
	public TransacaoService transacaoService() {
		return new TransacaoService();
	}

}
