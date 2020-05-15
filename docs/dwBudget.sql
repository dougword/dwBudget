--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 12.2

-- Started on 2020-04-11 18:32:13

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

--
-- TOC entry 197 (class 1259 OID 26713)
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id_categoria bigint NOT NULL,
    descricao character varying(100) NOT NULL,
    transferencia boolean NOT NULL,
    id_categoria_pai bigint,
    tipo_operacao character(1) NOT NULL,
    CONSTRAINT categoria_tipo_operacao CHECK ((tipo_operacao = ANY (ARRAY['E'::bpchar, 'S'::bpchar])))
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 26711)
-- Name: categoria_id_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.categoria_id_categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.categoria_id_categoria_seq OWNER TO postgres;

--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 196
-- Name: categoria_id_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.categoria_id_categoria_seq OWNED BY public.categoria.id_categoria;


--
-- TOC entry 201 (class 1259 OID 26743)
-- Name: conta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conta (
    id_conta bigint NOT NULL,
    descricao character varying(100) NOT NULL,
    ativa boolean NOT NULL
);


ALTER TABLE public.conta OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 26741)
-- Name: conta_id_conta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.conta_id_conta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.conta_id_conta_seq OWNER TO postgres;

--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 200
-- Name: conta_id_conta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.conta_id_conta_seq OWNED BY public.conta.id_conta;


--
-- TOC entry 203 (class 1259 OID 26751)
-- Name: lancamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.lancamento (
    id_lancamento bigint NOT NULL,
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


ALTER TABLE public.lancamento OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 26749)
-- Name: lancamento_id_lancamento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.lancamento_id_lancamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.lancamento_id_lancamento_seq OWNER TO postgres;

--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 202
-- Name: lancamento_id_lancamento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.lancamento_id_lancamento_seq OWNED BY public.lancamento.id_lancamento;


--
-- TOC entry 199 (class 1259 OID 26727)
-- Name: projecao; Type: TABLE; Schema: public; Owner: postgres
--

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


ALTER TABLE public.projecao OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 26725)
-- Name: projecao_id_projecao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.projecao_id_projecao_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.projecao_id_projecao_seq OWNER TO postgres;

--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 198
-- Name: projecao_id_projecao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.projecao_id_projecao_seq OWNED BY public.projecao.id_projecao;


--
-- TOC entry 2703 (class 2604 OID 26716)
-- Name: categoria id_categoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria ALTER COLUMN id_categoria SET DEFAULT nextval('public.categoria_id_categoria_seq'::regclass);


--
-- TOC entry 2709 (class 2604 OID 26746)
-- Name: conta id_conta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta ALTER COLUMN id_conta SET DEFAULT nextval('public.conta_id_conta_seq'::regclass);


--
-- TOC entry 2710 (class 2604 OID 26754)
-- Name: lancamento id_lancamento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lancamento ALTER COLUMN id_lancamento SET DEFAULT nextval('public.lancamento_id_lancamento_seq'::regclass);


--
-- TOC entry 2705 (class 2604 OID 26730)
-- Name: projecao id_projecao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projecao ALTER COLUMN id_projecao SET DEFAULT nextval('public.projecao_id_projecao_seq'::regclass);


--
-- TOC entry 2713 (class 2606 OID 26718)
-- Name: categoria categoria_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pk PRIMARY KEY (id_categoria);


--
-- TOC entry 2717 (class 2606 OID 26748)
-- Name: conta conta_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta
    ADD CONSTRAINT conta_pk PRIMARY KEY (id_conta);


--
-- TOC entry 2719 (class 2606 OID 26757)
-- Name: lancamento lancamento_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lancamento
    ADD CONSTRAINT lancamento_pk PRIMARY KEY (id_lancamento);


--
-- TOC entry 2715 (class 2606 OID 26735)
-- Name: projecao projecao_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projecao
    ADD CONSTRAINT projecao_pk PRIMARY KEY (id_projecao);


--
-- TOC entry 2720 (class 2606 OID 26719)
-- Name: categoria categoria_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_fk FOREIGN KEY (id_categoria_pai) REFERENCES public.categoria(id_categoria);


--
-- TOC entry 2722 (class 2606 OID 26758)
-- Name: lancamento lancamento_fk_1; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lancamento
    ADD CONSTRAINT lancamento_fk_1 FOREIGN KEY (id_categoria) REFERENCES public.categoria(id_categoria);


--
-- TOC entry 2723 (class 2606 OID 26763)
-- Name: lancamento lancamento_fk_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lancamento
    ADD CONSTRAINT lancamento_fk_2 FOREIGN KEY (id_conta) REFERENCES public.conta(id_conta);


--
-- TOC entry 2724 (class 2606 OID 26768)
-- Name: lancamento lancamento_fk_3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.lancamento
    ADD CONSTRAINT lancamento_fk_3 FOREIGN KEY (id_projecao) REFERENCES public.projecao(id_projecao);


--
-- TOC entry 2721 (class 2606 OID 26736)
-- Name: projecao projecao_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.projecao
    ADD CONSTRAINT projecao_fk FOREIGN KEY (id_categoria) REFERENCES public.categoria(id_categoria);


-- Completed on 2020-04-11 18:32:13

--
-- PostgreSQL database dump complete
--

