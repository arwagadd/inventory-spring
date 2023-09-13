--liquibase formatted sql

--changeset Arwa:20230911_user_table
CREATE SEQUENCE public.user_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.user_id_sequence OWNER TO postgres;

CREATE TABLE public.user
(
    id                bigint                 NOT NULL DEFAULT nextval('user_id_sequence'::regclass),
    name              character varying(250),
    phone_number      character varying(250),
    job_status        character varying(250),
    PRIMARY KEY (id)

) TABLESPACE pg_default;

ALTER TABLE public.user
    OWNER TO postgres;