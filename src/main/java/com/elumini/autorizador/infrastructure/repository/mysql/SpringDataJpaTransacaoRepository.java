package com.elumini.autorizador.infrastructure.repository.mysql;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elumini.autorizador.infrastructure.repository.entity.TransacaoEntity;

@Repository
public interface SpringDataJpaTransacaoRepository extends JpaRepository<TransacaoEntity, UUID> {

}
