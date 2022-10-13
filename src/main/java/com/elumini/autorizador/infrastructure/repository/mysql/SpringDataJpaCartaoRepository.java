package com.elumini.autorizador.infrastructure.repository.mysql;

import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elumini.autorizador.infrastructure.repository.entity.CartaoEntity;

@Repository
public interface SpringDataJpaCartaoRepository extends CrudRepository<CartaoEntity, BigDecimal> {

}
