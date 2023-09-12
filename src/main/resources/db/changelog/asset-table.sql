--liquibase formatted sql

--changeset Arwa:20230912_asset_table
CREATE SEQUENCE public.asset_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.asset_id_sequence OWNER TO postgres;

CREATE TABLE public.asset
(
    id                bigint                 NOT NULL DEFAULT nextval('asset_id_sequence'::regclass),
    name              character varying(250),
    PRIMARY KEY (id)

) TABLESPACE pg_default;

ALTER TABLE public.asset
    OWNER TO postgres;