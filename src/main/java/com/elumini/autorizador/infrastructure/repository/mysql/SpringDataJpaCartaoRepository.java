package com.elumini.autorizador.infrastructure.repository.mysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elumini.autorizador.infrastructure.repository.entity.CartaoEntity;

@Repository
public interface SpringDataJpaCartaoRepository extends CrudRepository<CartaoEntity, String> {

}
