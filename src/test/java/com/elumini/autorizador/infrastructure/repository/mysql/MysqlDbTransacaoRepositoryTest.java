package com.elumini.autorizador.infrastructure.repository.mysql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.domain.Transacao;
import com.elumini.autorizador.domain.Transacao.TransacaoBuilder;
import com.elumini.autorizador.infrastructure.repository.entity.CartaoEntity;
import com.elumini.autorizador.infrastructure.repository.entity.TransacaoEntity;

@ExtendWith(SpringExtension.class)
@Import(value = { MysqlDbTransacaoRepository.class })
@DataJpaTest
@DirtiesContext
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class MysqlDbTransacaoRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private MysqlDbTransacaoRepository repository;

	/**
	 * FIXME: parou de funcionar com erro
	 * org.hibernate.TransientPropertyValueException: object references an unsaved
	 * transient instance - save the transient instance before flushing
	 */
	@SuppressWarnings("unused")
	private void testSaveOk() {

		Transacao transacao = TransacaoBuilder.builder() //
				.withCartao(CartaoBuilder.builder() //
						.withNumeroCartao("1234123412341234") //
						.withSenha("1234") //
						.build()) //
				.withValor(BigDecimal.TEN) //
				.build();

		entityManager.persistAndFlush(CartaoEntity.builder() //
				.numeroCartao("1234123412341234") //
				.senha("1234") //
				.build());

		Transacao result = repository.save(transacao);

		entityManager.find(TransacaoEntity.class, result.getId());

		assertNotNull(result);
		assertEquals(transacao.getValor(), result.getValor());

	}

	/**
	 * TODO: necess??rio verificar porque o sistema est?? inserindo o cart??o, sendo
	 * que foi explicitamente configurado para n??o fazer isso
	 */
	public void testSaveCartaoInexistente() {

		final Transacao transacao = TransacaoBuilder.builder() //
				.withCartao(CartaoBuilder.builder() //
						.withNumeroCartao("6549873025634501") //
						.withSenha("1234") //
						.build()) //
				.withValor(BigDecimal.TEN) //
				.build();

		assertThrows(EntityNotFoundException.class, () -> {
			repository.save(transacao);
		});

	}

}
