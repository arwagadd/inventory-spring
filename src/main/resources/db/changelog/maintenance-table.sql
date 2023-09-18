--liquibase formatted sql

--changeset Arwa:20230917_maintenance_table
CREATE SEQUENCE public.maintenance_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.maintenance_id_sequence OWNER TO postgres;

CREATE TABLE public.maintenance
(
    id                     bigint                 NOT NULL DEFAULT nextval('maintenance_id_sequence'::regclass),
    item_id              int,
    action               character varying(250) ,
    price                int,
    start_date            timestamp,
    end_date              timestamp,
    CONSTRAINT item_id_fk FOREIGN KEY (item_id) REFERENCES public.item,
    PRIMARY KEY (id)

) TABLESPACE pg_default;

ALTER TABLE public.maintenance
    OWNER TO postgres;