CREATE TABLE public.categoria (
    id_categoria bigserial NOT NULL,
    descricao character varying(100) NOT NULL,
    transferencia boolean NOT NULL,
    tipo_operacao character(1) NOT NULL,
    CONSTRAINT categoria_tipo_operacao CHECK ((tipo_operacao = ANY (ARRAY['E'::bpchar, 'S'::bpchar])))
);

CREATE TABLE public.conta (
    id_conta bigserial NOT NULL,
    descricao character varying(100) NOT NULL,
    ativa boolean NOT NULL
);

CREATE TABLE public.lancamento (
    id_lancamento bigserial NOT NULL,
    id_categoria bigint NOT NULL,
    id_conta bigint NOT NULL,
    item character varying(100) NOT NULL,
    data_vencimento date NOT NULL,
    data_lancado date,
    valor numeric(9,2) NOT NULL,
    valor_lancado numeric(9,2),
    status character(1) NOT NULL,
    id_projecao bigint,
    CONSTRAINT lancamento_status CHECK ((status = ANY (ARRAY['N'::bpchar, 'P'::bpchar, 'R'::bpchar])))
);

CREATE TABLE public.projecao (
    id_projecao bigserial NOT NULL,
    item character varying(100) NOT NULL,
    dia_vencimento smallint,
    valor numeric(9,2) NOT NULL,
    periodo_inicial date NOT NULL,
    periodo_final date,
    id_categoria bigint NOT NULL,
    CONSTRAINT projecao_periodo_final CHECK (((periodo_final IS NULL) OR (periodo_final >= periodo_inicial))),
    CONSTRAINT projecao_periodo_final_dia_1 CHECK (((periodo_final IS NULL) OR (date_part('day'::text, periodo_final) = (1)::double precision))),
    CONSTRAINT projecao_periodo_inicial_dia_1 CHECK ((date_part('day'::text, periodo_inicial) = (1)::double precision))
);

ALTER TABLE ONLY categoria
    ADD CONSTRAINT categoria_pk
    PRIMARY KEY (id_categoria);

ALTER TABLE ONLY conta
    ADD CONSTRAINT conta_pk
    PRIMARY KEY (id_conta);

ALTER TABLE ONLY lancamento
    ADD CONSTRAINT lancamento_pk
    PRIMARY KEY (id_lancamento);

ALTER TABLE ONLY projecao
    ADD CONSTRAINT projecao_pk
    PRIMARY KEY (id_projecao);

ALTER TABLE ONLY lancamento
    ADD CONSTRAINT lancamento_fk_1
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria);

ALTER TABLE ONLY lancamento
    ADD CONSTRAINT lancamento_fk_2
    FOREIGN KEY (id_conta) REFERENCES conta(id_conta);

ALTER TABLE ONLY lancamento
    ADD CONSTRAINT lancamento_fk_3
    FOREIGN KEY (id_projecao) REFERENCES projecao(id_projecao);

ALTER TABLE ONLY projecao
    ADD CONSTRAINT projecao_fk
    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria);
