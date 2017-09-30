INSERT INTO customer (id,first_name,last_name,mail,username,password) values (1,'Tim','Horton','tim.horton@gmail.com','tim','$2a$10$a3v1uCjRnFzJEvBpP7804.Dx6hOf3FsiZOzGhInt39obMn2dgIRkK');
INSERT INTO customer (id,first_name,last_name,mail,username,password) values (2,'Zev','Siegel','zev.siegel@gmail.com','zev','$2a$10$T.gTEIMxUhqRaA2ybJBlMOcv82nwCzGEVoZGejSiM7U7WbCfAFZB6');
INSERT INTO customer (id,first_name,last_name,mail,username,password) values (3,'Fred','La Place','laplace@gmail.com','laplace','$2a$10$xvyQkKeW7aGuBN80bp4czeCmxYIdmhAYtx.cBisQMh0T7SE2biUV6');

INSERT INTO compagny (id,name,customer_id,template) values (1,'Tim Hortons',1,'cafeteria');
INSERT INTO compagny (id,name,customer_id,template) values (2,'Tim Horton Donut Drive-in',1,'cafeteria');
INSERT INTO compagny (id,name,customer_id,template) values (3,'Starbucks',2,'cafeteria');
INSERT INTO compagny (id,name,customer_id,template, title, sub_title, address_line1, postal_code, town, telephone_number, email_address, image, logo) values (4,'Mo Pizza',3,'pizzeria', 'Mo Pizza Ennevelin', 'les pizzas tout en fraicheur', '7 rue nationale', '59710' , 'Ennevelin', '0320020302', 'mo@pizza.fr', 'https://c1.staticflickr.com/3/2829/11406903003_699a90a23c_b.jpg', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtpzKsGbYgGoXOkGGSkwy0-uK6fkkEzaps5kUaoYL-Q3d2-4Ao');

INSERT INTO product (id,description,name,price,compagny_id) values (1,'A medium roast blend, expertly roasted with care to deliver a perfect balance and the unique Tim Hortons taste that has made it Canada s Favourite Coffee.','ORIGINAL BLEND',2.00,1);
INSERT INTO product (id,description,name,price,compagny_id) values (2,'Carefully roasted to bring out the full rich flavour and aroma of a Dark Roast coffee, our Dark Roast has a perfectly smooth finish.','DARK ROAST COFFEE',2.50,1);
INSERT INTO product (id,description,name,price,compagny_id) values (3,'Savour the great tasting flavour you love, decaffeinated. Our coffee beans are naturally decaffeinated using the Swiss Water® Process, leaving only a quality Decaf coffee you can enjoy all day long.','DECAF COFFEE',1.50,1);
INSERT INTO product (id,description,name,price,compagny_id) values (4,'Aussi subtile soit sa saveur, l’influence de ce café n’en est pas moins ensorcelante. On peut comparer l’expérience de ce café aux branches fines et souples du gracieux saule pleureur.','Starbucks Decaf Willow Blend',3,3);

INSERT INTO product (id,description,name,price,compagny_id, image) values (5,'Fond de tomate, Mozarella, Basilic','La Margherita',8,4, 'https://c1.staticflickr.com/7/6005/5875385917_5dc05e590c_b.jpg')
INSERT INTO product (id,description,name,price,compagny_id, image) values (6,'Fond de tomate, Anchois, Fromage, Olives, Tomates cerise, Ail','La Provencale',9,4, 'https://c1.staticflickr.com/7/6195/6060600500_2d382f5f45_b.jpg')