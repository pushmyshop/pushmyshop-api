INSERT INTO TEMPLATE (id,name) values (1,'pizzeria');
INSERT INTO TEMPLATE (id,name) values (2,'cafeteria');
INSERT INTO CUSTOMER (id,first_name,last_name,mail,username,password) values (1,'Tim','Horton','tim.horton@gmail.com','tim','$2a$10$a3v1uCjRnFzJEvBpP7804.Dx6hOf3FsiZOzGhInt39obMn2dgIRkK');
INSERT INTO CUSTOMER (id,first_name,last_name,mail,username,password) values (2,'Zev','Siegel','zev.siegel@gmail.com','zev','$2a$10$T.gTEIMxUhqRaA2ybJBlMOcv82nwCzGEVoZGejSiM7U7WbCfAFZB6');
INSERT INTO CUSTOMER (id,first_name,last_name,mail,username,password) values (3,'Fred','La Place','laplace@gmail.com','laplace','$2a$10$xvyQkKeW7aGuBN80bp4czeCmxYIdmhAYtx.cBisQMh0T7SE2biUV6');
INSERT INTO COMPAGNY (id,name,customer_id,template_id) values (1,'Tim Hortons',1,2);
INSERT INTO COMPAGNY (id,name,customer_id,template_id) values (2,'Tim Horton Donut Drive-in',1,2);
INSERT INTO COMPAGNY (id,name,customer_id,template_id) values (3,'Starbucks',2,2);
INSERT INTO COMPAGNY (id,name,customer_id,template_id) values (4,'Mod Pizza',3,1);


INSERT INTO PRODUCT (id,desc,name,price,compagny_id) values (1,'A medium roast blend, expertly roasted with care to deliver a perfect balance and the unique Tim Hortons taste that has made it Canada s Favourite Coffee.','ORIGINAL BLEND',2.00,1);
INSERT INTO PRODUCT (id,desc,name,price,compagny_id) values (2,'Carefully roasted to bring out the full rich flavour and aroma of a Dark Roast coffee, our Dark Roast has a perfectly smooth finish.','DARK ROAST COFFEE',2.50,1);
INSERT INTO PRODUCT (id,desc,name,price,compagny_id) values (3,'Savour the great tasting flavour you love, decaffeinated. Our coffee beans are naturally decaffeinated using the Swiss Water® Process, leaving only a quality Decaf coffee you can enjoy all day long.','DECAF COFFEE',1.50,1);
INSERT INTO PRODUCT (id,desc,name,price,compagny_id) values (4,'Aussi subtile soit sa saveur, l’influence de ce café n’en est pas moins ensorcelante. On peut comparer l’expérience de ce café aux branches fines et souples du gracieux saule pleureur.','Starbucks Decaf Willow Blend',3,3);

INSERT INTO PRODUCT (id,desc,name,price,compagny_id) values (5,'Fond de tomate, Mozarella, Basilic','La Margherita',8,4)
INSERT INTO PRODUCT (id,desc,name,price,compagny_id) values (6,'Fond de tomate, Anchois, Fromage, Olives, Tomates cerise, Ail','La Provencale',9,4)