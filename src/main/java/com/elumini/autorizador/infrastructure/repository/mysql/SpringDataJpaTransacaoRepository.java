package com.elumini.autorizador.infrastructure.repository.mysql;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.elumini.autorizador.infrastructure.repository.entity.TransacaoEntity;

@Repository
public interface SpringDataJpaTransacaoRepository extends CrudRepository<TransacaoEntity, UUID> {

}
