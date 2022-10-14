-- liquibase formatted sql

-- changeset mini-autorizador:create-cartao-table
create table cartao_entity (numero_cartao decimal(19,2) not null, saldo decimal(19,2), senha integer, primary key (numero_cartao))