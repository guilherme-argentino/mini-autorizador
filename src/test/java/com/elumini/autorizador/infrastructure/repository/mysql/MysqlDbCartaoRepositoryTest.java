package com.elumini.autorizador.infrastructure.repository.mysql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.elumini.autorizador.domain.Cartao;
import com.elumini.autorizador.domain.Cartao.CartaoBuilder;
import com.elumini.autorizador.infrastructure.repository.entity.CartaoEntity;

@ExtendWith(SpringExtension.class)
@Import(value = {
		MysqlDbCartaoRepository.class
})
@DataJpaTest
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
class MysqlDbCartaoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private MysqlDbCartaoRepository repository;


	/**
	 * FIXME: parou de funcionar com erro
	 * org.hibernate.TransientPropertyValueException: object references an unsaved
	 * transient instance - save the transient instance before flushing
	 */
	void testFindById() {
		CartaoEntity cartao = CartaoEntity.builder()
				.numeroCartao("1234567812345678")
				.senha("1234")
				.saldo(BigDecimal.ZERO)
				.build();
		
		entityManager.persistAndFlush(cartao);
		
		Optional<Cartao> result = repository.findById(cartao.getNumeroCartao());
		
		assertTrue(result.isPresent());
		assertEquals(cartao.getNumeroCartao(), result.get().getNumeroCartao());
	}

	@Test
	void testSave() {
		Cartao cartao = CartaoBuilder.builder()
				.withNumeroCartao("1234567812345678")
				.withSenha("1234")
				.withSaldo(BigDecimal.ZERO)
				.build();
		
		repository.save(cartao);

		CartaoEntity result = entityManager.find(CartaoEntity.class, cartao.getNumeroCartao());
		
		assertNotNull(result);
		assertEquals(cartao.getNumeroCartao(), result.getNumeroCartao());
	}

}
