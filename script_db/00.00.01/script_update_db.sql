--------- ROLE_USERS TABLE ---------
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERA@test.com', '', false, NOW(), NOW(), '*Test', 'ROLE_ADMIN', 'ROLE_USERA');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERB@test.com', '', false, NOW(), NOW(), '*Test', 'ROLE_ADMIN', 'ROLE_USERB');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERC@test.com', '', false, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERC');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERD@test.com', '', false, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERD');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERE@test.com', '', false, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERE');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERF@test.com', '', false, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERF');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERG@test.com', '', true, NOW(), NOW(), '*Test', 'ROLE_ADMIN', 'ROLE_USERG');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERH@test.com', '', true, NOW(), NOW(), '*Test', 'ROLE_ADMIN', 'ROLE_USERH');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERI@test.com', '', true, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERI');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERJ@test.com', '', true, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERJ');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERK@test.com', '', true, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERK');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERL@test.com', '', true, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERL');
INSERT INTO public.ROLE_USERs(
	email, image, is_blocked, last_logged_in, last_updated, password, role, ROLE_USERname)
	VALUES ('ROLE_USERM@test.com', '', true, NOW(), NOW(), '*Test', 'ROLE_USER', 'ROLE_USERM');
	
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