--liquibase formatted sql

--changeset Arwa:20230917_item_request_table
CREATE SEQUENCE public.item_request_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.item_request_id_sequence OWNER TO postgres;

CREATE TABLE public.item_request
(
    id                    bigint                 NOT NULL DEFAULT nextval('item_request_id_sequence'::regclass),
    user_id                 int,
    item_id                 int,
    quantity                int,
    start_time               timestamp,
    end_time                timestamp,
    request_status         character varying(250),
    request_date_time       timestamp,
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.user,
    CONSTRAINT item_id_fk FOREIGN KEY (item_id) REFERENCES public.item,
    PRIMARY KEY (id)

) TABLESPACE pg_default;

ALTER TABLE public.item_request
    OWNER TO postgres;









