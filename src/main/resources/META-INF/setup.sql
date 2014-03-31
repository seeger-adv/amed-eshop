INSERT INTO customer (mail, password, created, lastlogin) VALUES ('admin@local.com', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 0, 0);

INSERT INTO category (categoryid, slug) VALUES (1, 'Kaffee');
INSERT INTO category (categoryid, slug) VALUES (2, 'Maschinen');
INSERT INTO category (categoryid, slug) VALUES (3, 'Zubehör');

INSERT INTO category (categoryid, slug, parent) VALUES (4, 'Probeportionen', 1);
INSERT INTO category (categoryid, slug, parent) VALUES (5, 'Normale Portionen', 1);
INSERT INTO category (categoryid, slug, parent) VALUES (6, 'Abonements', 1);

INSERT INTO category (categoryid, slug, parent) VALUES (7, 'Vollautomaten', 2);
INSERT INTO category (categoryid, slug, parent) VALUES (8, 'Sonstige', 2);

INSERT INTO category (categoryid, slug, parent) VALUES (9, 'Mühlen', 3);
INSERT INTO category (categoryid, slug, parent) VALUES (10, 'Tassen', 4);
INSERT INTO category (categoryid, slug, parent) VALUES (11, 'Kannen', 4);
INSERT INTO category (categoryid, slug, parent) VALUES (12, 'Literatur', 4);

INSERT INTO product (productid, slug, price, description) VALUES (null, 'Product 1', 2.5, 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.');
INSERT INTO product (productid, slug, price, description) VALUES (null, 'Product 2', 10, 'Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.');
INSERT INTO product (productid, slug, price, description) VALUES (null, 'Product 3', 13.37, 'At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.');