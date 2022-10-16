-- liquibase formatted sql

-- changeset mini-autorizador:create-cartao-table
create table transacao_entity (id binary(255) not null, valor numeric(19,2), cartao_numero_cartao varchar(255), primary key (id))
