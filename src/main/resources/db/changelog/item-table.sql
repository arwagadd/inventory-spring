--liquibase formatted sql

--changeset Arwa:20230911_item_table
CREATE SEQUENCE public.item_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.item_id_sequence OWNER TO postgres;

CREATE TABLE public.item
(
    id                     bigint                 NOT NULL DEFAULT nextval('item_id_sequence'::regclass),
    name                   character varying(250),
    asset_id               bigint,
    price                  character varying(250),
    status                 character varying(250),
    serial_number          character varying(250),
    average_rating         character varying(250),
    quantity               bigint,
    description            character varying(250),
    CONSTRAINT asset_id_fk FOREIGN KEY (asset_id) REFERENCES public.asset,
    PRIMARY KEY (id)

) TABLESPACE pg_default;

ALTER TABLE public.item
    OWNER TO postgres;

--changeset Arwa:20230920_item_table
DELETE FROM public.item WHERE name = 'RAM';
