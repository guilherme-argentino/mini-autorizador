-- liquibase formatted sql

-- changeset mini-autorizador:add-constraint-transacao-table
alter table transacao_entity add constraint fk_numero_cartao foreign key (cartao_numero_cartao) references cartao_entity(numero_cartao)
