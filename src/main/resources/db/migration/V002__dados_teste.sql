--
-- Data for table public.categoria (LIMIT 0,7)
--
INSERT INTO categoria (id_categoria, descricao, transferencia, tipo_operacao)
VALUES (1, 'Salário', false, 'E');

INSERT INTO categoria (id_categoria, descricao, transferencia, tipo_operacao)
VALUES (2, 'Mercado', false, 'S');

INSERT INTO categoria (id_categoria, descricao, transferencia, tipo_operacao)
VALUES (3, 'Internet', false, 'S');

INSERT INTO categoria (id_categoria, descricao, transferencia, tipo_operacao)
VALUES (4, 'Telefone', false, 'S');

INSERT INTO categoria (id_categoria, descricao, transferencia, tipo_operacao)
VALUES (5, 'Plano de saúde', false, 'S');

INSERT INTO categoria (id_categoria, descricao, transferencia, tipo_operacao)
VALUES (6, 'Transferência - Entrada', true, 'E');

INSERT INTO categoria (id_categoria, descricao, transferencia, tipo_operacao)
VALUES (7, 'Transferência - Saída', true, 'S');

--
-- Data for table public.conta (LIMIT 0,5)
--
INSERT INTO conta (id_conta, descricao, ativa)
VALUES (1, 'Banco do Brasil - CC', true);

INSERT INTO conta (id_conta, descricao, ativa)
VALUES (2, 'Caixa - CC', true);

INSERT INTO conta (id_conta, descricao, ativa)
VALUES (3, 'Caixa - PP', true);

INSERT INTO conta (id_conta, descricao, ativa)
VALUES (4, 'Nubank', true);

INSERT INTO conta (id_conta, descricao, ativa)
VALUES (5, 'Carteira', true);

--
-- Data for table public.projecao (LIMIT 0,5)
--
INSERT INTO projecao (id_projecao, item, dia_vencimento, valor, periodo_inicial, periodo_final, id_categoria)
VALUES (1, 'Salário mensal', 10, 5000.00, '2020-01-01', NULL, 1);

INSERT INTO projecao (id_projecao, item, dia_vencimento, valor, periodo_inicial, periodo_final, id_categoria)
VALUES (2, 'Mercado', NULL, 600.00, '2020-01-01', NULL, 2);

INSERT INTO projecao (id_projecao, item, dia_vencimento, valor, periodo_inicial, periodo_final, id_categoria)
VALUES (4, 'Plano telefone', 20, 60.00, '2020-01-01', NULL, 4);

INSERT INTO projecao (id_projecao, item, dia_vencimento, valor, periodo_inicial, periodo_final, id_categoria)
VALUES (3, 'Internet Fibra', 15, 80.00, '2020-01-01', NULL, 3);

INSERT INTO projecao (id_projecao, item, dia_vencimento, valor, periodo_inicial, periodo_final, id_categoria)
VALUES (5, 'Unimed', 10, 420.00, '2020-01-01', NULL, 5);

--
-- Data for table public.lancamento (LIMIT 0,3)
--
INSERT INTO lancamento (id_lancamento, id_categoria, id_conta, item, data_vencimento, data_lancado, valor, valor_lancado, status, id_projecao)
VALUES (1, 1, 1, 'Salário mensal', '2020-05-10', NULL, 5000.00, NULL, 'N', 1);

INSERT INTO lancamento (id_lancamento, id_categoria, id_conta, item, data_vencimento, data_lancado, valor, valor_lancado, status, id_projecao)
VALUES (2, 1, 5, 'Resto de dinheiro', '2020-05-14', '2020-05-14', 500.00, 500.00, 'R', NULL);

INSERT INTO lancamento (id_lancamento, id_categoria, id_conta, item, data_vencimento, data_lancado, valor, valor_lancado, status, id_projecao)
VALUES (3, 2, 5, 'Compras de lanches', '2020-05-14', '2020-05-14', 100.00, 100.00, 'R', 2);

--
-- Data for sequence public.categoria_id_categoria_seq
--
SELECT pg_catalog.setval('categoria_id_categoria_seq', 7, true);
--
-- Data for sequence public.conta_id_conta_seq
--
SELECT pg_catalog.setval('conta_id_conta_seq', 5, true);
--
-- Data for sequence public.lancamento_id_lancamento_seq
--
SELECT pg_catalog.setval('lancamento_id_lancamento_seq', 4, true);
--
-- Data for sequence public.projecao_id_projecao_seq
--
SELECT pg_catalog.setval('projecao_id_projecao_seq', 5, true);
