-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL DEFAULT 'nextval('users_id_seq'::regclass)',
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    image character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_blocked boolean NOT NULL,
    last_logged_in timestamp(6) without time zone,
    last_updated timestamp(6) without time zone NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;
    

-- Table: public.jwt_token

-- DROP TABLE IF EXISTS public.jwt_token;

CREATE TABLE IF NOT EXISTS public.jwt_token
(
    id bigint NOT NULL DEFAULT 'nextval('jwt_token_id_seq'::regclass)',
    access_token character varying(255) COLLATE pg_catalog."default",
    last_update timestamp(6) without time zone NOT NULL,
    refresh_token character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_id bigint,
    CONSTRAINT jwt_token_pkey PRIMARY KEY (id),
    CONSTRAINT uk_164jcv4uy5y5ev8w6r38fxd79 UNIQUE (refresh_token),
    CONSTRAINT uk_t5d21b115q0121fu96kf8eqf1 UNIQUE (access_token),
    CONSTRAINT fkc8byj5reo1231wqon0fxk476w FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.jwt_token
    OWNER to postgres;


-- Table: public.products

-- DROP TABLE IF EXISTS public.products;

CREATE TABLE IF NOT EXISTS public.products
(
    id bigint NOT NULL DEFAULT 'nextval('products_id_seq'::regclass)',
    brand character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_update timestamp(6) without time zone NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    price bigint NOT NULL,
    product_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    quantity bigint NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT uk_922x4t23nx64422orei4meb2y UNIQUE (product_code)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.products
    OWNER to postgres;    
    
    
-- Table: public.customer_order

-- DROP TABLE IF EXISTS public.customer_order;

CREATE TABLE IF NOT EXISTS public.customer_order
(
    id bigint NOT NULL DEFAULT 'nextval('customer_order_id_seq'::regclass)',
    address character varying(255) COLLATE pg_catalog."default",
    company_address character varying(255) COLLATE pg_catalog."default",
    company_name character varying(255) COLLATE pg_catalog."default",
    last_update timestamp(6) without time zone NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    total_price bigint NOT NULL,
    CONSTRAINT customer_order_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.customer_order
    OWNER to postgres;
    
    
-- Table: public.ordered_product

-- DROP TABLE IF EXISTS public.ordered_product;

CREATE TABLE IF NOT EXISTS public.ordered_product
(
    id bigint NOT NULL DEFAULT 'nextval('ordered_product_id_seq'::regclass)',
    brand character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    price bigint NOT NULL,
    product_code character varying(255) COLLATE pg_catalog."default" NOT NULL,
    product_id bigint NOT NULL,
    quantity bigint NOT NULL,
    total_price bigint NOT NULL,
    CONSTRAINT ordered_product_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.ordered_product
    OWNER to postgres;
    
    
-- Table: public.customer_ordered_product

-- DROP TABLE IF EXISTS public.customer_ordered_product;

CREATE TABLE IF NOT EXISTS public.customer_ordered_product
(
    customer_order_id bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT fk1qju4f2aeayvvggwdonl3ghb FOREIGN KEY (product_id)
        REFERENCES public.ordered_product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkg7ci8co0k68yy9vsss8l2oiuo FOREIGN KEY (customer_order_id)
        REFERENCES public.customer_order (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.customer_ordered_product
    OWNER to postgres;
    
    
--------- ROLE_USERS TABLE ---------
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (12, 'userJ@test.com', '', false, '2023-03-06 15:02:05.322874', '2023-03-07 17:34:37.562168', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'userJJ');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (3, 'userA@test.com', 'IMAGE', false, '2023-03-08 01:04:28.471118', '2023-03-08 01:04:28.471118', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'UserA');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (6, 'userD@test.com', '', false, '2023-03-06 15:02:05.322874', '2023-03-06 15:02:05.322874', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'userD');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (8, 'userF@test.com', '', false, '2023-03-06 15:02:05.322874', '2023-03-06 15:02:05.322874', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'userF');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (13, 'UserK@test.com', '', true, '2023-03-06 15:02:05.322874', '2023-03-06 15:02:05.322874', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'UserK');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (4, 'UserB@test.com', '', false, '2023-03-01 15:02:05.322874', '2023-03-01 15:02:05.322874', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_ADMIN', 'UserB');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (5, 'UserC@test.com', '', false, '2023-03-02 15:02:05.322874', '2023-03-02 15:02:05.322874', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'UserC');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (7, 'UserE@test.com', '', false, '2023-01-11 15:02:05.322874', '2023-03-06 15:02:05.322874', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'UserE');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (10, 'UserH@test.com', '', false, '2023-01-06 15:02:05.322874', '2023-01-06 15:02:05.322874', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_ADMIN', 'UserH');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (11, 'UserI@test.com', '', true, '2023-03-06 15:02:05.322874', '2023-03-07 13:03:49.945416', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'UserI');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (9, 'UserG@test.com', '', false, '2023-03-06 15:02:05.322874', '2023-03-07 13:10:49.448119', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_ADMIN', 'UserG');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (15, 'UserM@test.com', '', false, '2023-03-06 15:02:05.322874', '2023-03-07 13:29:50.314164', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'UserM');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (14, 'userL@test.com', '', false, '2023-03-06 15:02:05.322874', '2023-03-07 13:30:07.005061', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'userL');
INSERT INTO public.users (id, email, image, is_blocked, last_logged_in, last_updated, password, role, username) VALUES (17, 'userZ@test.com', '', true, '2023-03-07 22:19:41.307832', '2023-03-07 13:31:52.557819', '$2a$10$/oCDdgJwfQgHeEAV9dvcW.lb5JaGQQh3D70T7K04bUdllRGOYuaze', 'ROLE_USER', 'UserZ');

	
--------- PRODUCT TABLE ---------
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('EYE MO', NOW(), 'Moist Eye Lubricant Eyedrop 7.5ml', 535, 'EM1', 10 );
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('EYE MO', NOW(), 'Regular Eye Drops 15ml', 1000, 'EM2', 5);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('EYE GLO', NOW(), 'Relieves Sore, Swollen & Watery Eyes 10ml', 700, 'EG2', 15);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('ROHTO', NOW(), 'Cool Eye Wash 150ml', 3000, 'RT1', 34);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('EYE GLO', NOW(), 'Plus 10ml', 550, 'EG1', 0);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('ROHTO', NOW(), 'Eye Drops Aqua 13ml', 2050, 'RT3', 5);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('OPTREX', NOW(), 'Eye Lotion 110ml', 3600, 'OT1', 34);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('OPTREX', NOW(), 'C Cube Eye Drops 8ml', 880, 'OT3', 40 );
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('SYSTANE', NOW(), 'Hydration Eye Drops 10ml', 350, 'ST1', 0);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('OPTREX', NOW(), 'Rehydrating Eye Drops 10ml', 1570, 'OT2', 0);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('ROHTO', NOW(), 'Extra Cool Eye Drops 13ml', 840, 'RT2', 0);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('TEARS NATURALE', NOW(), 'Tears Naturale II 15Ml', 660, 'TN1', 100);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('BIO-LIFE', NOW(), 'Glucosamine 100s + 2 x 30 s', 8590, 'BL1', 100);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('BLACKMORES', NOW(), 'Pregnancy and Breast Feeding Gold 60s', 4575, 'BM2', 74);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('BIO-LIFE', NOW(), 'MaxX Fish Oil 1000mg Omega-3 3x100s', 2690, 'BL2', 15);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('BLACKMORES', NOW(), 'Calcium+D3 120s', 1755, 'BM1', 0);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('BIO-LIFE', NOW(), 'Multivitamins and Minerals 30S', 8750, 'BL3', 0);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('NESTLE', NOW(), 'Hot Cereal', 1459, 'NS01', 50);
INSERT INTO public.products(
	brand, last_update, name, price, product_code, quantity)
	VALUES ('NESTUM', NOW(), '3in1 Original (15 packs) 28g', 1847, 'NS02', 15);
	
