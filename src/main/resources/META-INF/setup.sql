INSERT INTO customer (mail, password, created, lastlogin) VALUES ('admin@local.com', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 0, 0);
INSERT INTO Address (name, street, zip, city, customer) VALUES ('Jan Seeger', 'Orionweg 9b', 70565, 'Stuttgart', 'admin@local.com');
INSERT INTO PaymentInfo (cvv, number, owner, customer) VALUES ('123', '123456789', 'Jan Seeger', 'admin@local.com');

INSERT INTO category (categoryid, slug) VALUES (1, 'Coffee');
INSERT INTO category (categoryid, slug) VALUES (2, 'Coffee Makers');
INSERT INTO category (categoryid, slug) VALUES (3, 'Accessories');

INSERT INTO category (categoryid, slug, parent) VALUES (4, 'Trials', 1);
INSERT INTO category (categoryid, slug, parent) VALUES (5, 'Normale Portionen', 1);
INSERT INTO category (categoryid, slug, parent) VALUES (6, 'Abonnements', 1);

INSERT INTO category (categoryid, slug, parent) VALUES (7, 'Pourover Coffee Makers', 2);
INSERT INTO category (categoryid, slug, parent) VALUES (8, 'French Press', 2);

INSERT INTO category (categoryid, slug, parent) VALUES (9, 'Grinders', 3);
INSERT INTO category (categoryid, slug, parent) VALUES (10, 'Cups', 4);
INSERT INTO category (categoryid, slug, parent) VALUES (11, 'Pots', 4);
INSERT INTO category (categoryid, slug, parent) VALUES (12, 'Literature', 4);

INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 0, 'Product 1', 5, 2.5, 'https://farm4.staticflickr.com/3116/2295096211_1c641c008e_t.jpg', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 4, 'Product 2', 8, 10, 'https://farm4.staticflickr.com/3240/3890035295_08dc6685d6_t.jpg', 'Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 5, 'Product 3', 8, 13.37, 'https://farm3.staticflickr.com/2645/4108170113_5208ee6dd7_t.jpg', 'Bavaria ipsum dolor sit amet singd und glei wirds no fui lustiga gfreit mi geh a ganze Hoiwe hawadere midananda heid is ma Wuascht! Vui huift vui a ganze griaß God beinand, Weibaleid singan oans, zwoa, gsuffa hogg ma uns zamm! Da luja von schoo jo mei Jodler! A geh schoo des is schee Ramasuri Xaver Landla Kuaschwanz dahoam. Charivari Haferl gscheid a liabs Deandl sodala oamoi. De Sonn wui Weiznglasl Zwedschgndadschi oa Servas wea ko, dea ko glei woaß jo leck mi do: Ozapfa großherzig Ramasuri oa hod Ohrwaschl heid gfoids ma sagrisch guad wiavui ghupft wia gsprunga. Oans vasteh Gaudi dahoam, hoam a liabs Deandl Biazelt Stubn moand! Da umananda Gstanzl amoi. Baamwach gwiss eam, a bravs! Oba nackata auf’d Schellnsau mehra Gamsbart, Schaung kost nix wann griagd ma nacha wos z’dringa.');