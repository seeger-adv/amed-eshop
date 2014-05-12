INSERT INTO customer (mail, password, created, lastlogin) VALUES ('admin@local.com', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec', 0, 0);
INSERT INTO Address (name, street, zip, city, customer) VALUES ('Jan Seeger', 'Orionweg 9b', 70565, 'Stuttgart', 'admin@local.com');
INSERT INTO PaymentInfo (cvv, number, owner, customer) VALUES ('123', '123456789', 'Jan Seeger', 'admin@local.com');

INSERT INTO category (categoryid, slug) VALUES (1, 'Coffee');
INSERT INTO category (categoryid, slug) VALUES (2, 'Coffee Makers');
INSERT INTO category (categoryid, slug) VALUES (3, 'Accessories');

INSERT INTO category (categoryid, slug, parent) VALUES (4, 'Trials', 1);
INSERT INTO category (categoryid, slug, parent) VALUES (5, 'Normale Portionen', 1);

INSERT INTO category (categoryid, slug, parent) VALUES (7, 'Pourover Coffee Makers', 2);
INSERT INTO category (categoryid, slug, parent) VALUES (8, 'French Press', 2);

INSERT INTO category (categoryid, slug, parent) VALUES (9, 'Grinders', 3);
INSERT INTO category (categoryid, slug, parent) VALUES (10, 'Cups', 3);
INSERT INTO category (categoryid, slug, parent) VALUES (11, 'Pots', 3);
INSERT INTO category (categoryid, slug, parent) VALUES (12, 'Literature', 3);

-- Coffee
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 5, 'Ethiopia Yirgacheffe Coffee', 5, 13.95, 'https://dl-web.dropbox.com/get/eshop/1073_1_lb_whole_bean_l_thumb.jpg?_subject_uid=236039489&w=AAC_9A3PiLwAJFHR6U-QS3DykCbr5naGkh5pQrzJUTGK6w', 'We offer Ethiopian Yirgacheffe, picked by hand on a farm in the mountains high above the town of Sidamo. There, the coffee beans are washed, and then soaked up to 72 hours in fermentation tanks. This wet process method produces intensely flavorful beans, with an intensely floral aroma, and mellow, smooth taste.'); 
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 8, 'Maui Yellow Caturra Coffee', 5, 13.95, 'https://dl-web.dropbox.com/get/eshop/1326_1_lb_whole_bean_l_thumb.jpg?_subject_uid=236039489&w=AAA-T1Xl0mdnuaJCdOXrBCPbkx2sN7Gy_jzEM_08ga-WyA', 'Spicy aroma and smooth creamy body with apricot notes and a pleasant tart finish.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 3, 'Kenya AA Coffee', 5, 13.95, 'https://dl-web.dropbox.com/get/eshop/1085_1_lb_whole_bean_l_thumb.jpg?_subject_uid=236039489&w=AABM8x3EVpzkcvmDPPlNOo9LNYAcpRLBjv4ABQU7IEG3ZA', 'Kenya has become the giant of African coffee production, ever since coffee made its way over the mountains from Ethiopia. Kenya AA is the largest bean grown in Kenya, and brews up a complex, fruity, light, and very bright cup. This is an exquisite coffee with an assertive, lively personality. Trust us, in coffee that’s a good thing.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 16, 'Italian Ground Coffee', 5, 8.95, 'https://dl-web.dropbox.com/get/eshop/1083_12_oz_ground_l_thumb.jpg?_subject_uid=236039489&w=AAAN7ScOProE0i1yc8HckOAFSAMyiRDMt8Mu0HpmpMmkug', 'Deep roasted flavor with a smoky aroma and a chocolaty finish.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 0, 'Breakfast Blend Coffee', 5, 8.95, 'https://dl-web.dropbox.com/get/eshop/1066_12_oz_ground_l_thumb.jpg?_subject_uid=236039489&w=AACOYYZPjdIPsnRKhlwP69S1K_AW_31OsHxpU0dAnqgzxA', 'Fruity aroma with a dark chocolate flavor and nutty finish.');

-- Trials
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 0, 'Viannese Trial', 4, 1.95, 'https://dl-web.dropbox.com/get/eshop/1318_16_count_l_thumb.jpg?_subject_uid=236039489&w=AAC2Ac4Hp1hYftd_NaZ4wFTmNa9aTYcy_TDj7Vtl7qB4Bg', 'Vienna-style roasting creates a deep brown, medium dark roast. Mellow, smooth and delicately nuanced with hints of chocolate, our Viennese Blend draws on a unique Vienna-style of roasting that dates back to 1683 when the country’s first coffee house opened.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 25, 'Costa Rica Trial', 4, '1.85', 'https://dl-web.dropbox.com/get/eshop/1317_16_count_l_thumb.jpg?_subject_uid=236039489&w=AACaJv9f7GR67lxuWrVAb2ITURjrC85Ke1KaVPGL4LeGuA','Our Costa Rica coffee comes from the finest high-altitude farms, where the unique climate and rich soils produce coffees that exhibit a bright, clean taste. With its balanced, crisp flavor and fragrant aroma, this medium-bodied coffee is a customer favorite in our cafés.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 8, 'Cafe 63 Trial', 4, 1.63, 'https://dl-web.dropbox.com/get/eshop/1316_16_count_l_thumb.jpg?_subject_uid=236039489&w=AAALqN9izHWD1GiGOvdCZjh2TL13FnBIFzo1XgzYp3C_sw', 'Our Café &apos;63 Roast presents the smooth, light-bodied taste and fruity aroma we’ve been known for since we opened our first café in 1963. Hand-selected for the quality of their brightness and flavor, the beans are roasted to perfection to bring you a delicious and satisfying cup.');

-- Grinders
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 2, 'Bodum Blade Grinder', 9, 35.00, 'https://dl-web.dropbox.com/get/eshop/1240_black_l_thumb.jpg?_subject_uid=236039489&w=AAAFF0EfScbOSC2XnSJATxWv4HyEZrI9CcD3PtSc736aRg', 'There&apos;s more to great coffee than choosing exquisite beans and the right brewing method. Freshly grinding the beans right before they come in contact with hot water makes the difference in taste that real coffee connoisseurs appreciate. The bistro blade grinder is the perfect tool for this. It&apos;s small enough to stay put on the counter for easy access and its cable can be stored inside the grinder at the bottom. It&apos;s got a transparent lid so the fineness grade can be observed. The longer the grind the finer the powder.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 1, 'Bodum Burr Grinder', 9, 120.00, 'https://dl-web.dropbox.com/get/eshop/1239_black_l_thumb.jpg?_subject_uid=236039489&w=AADptqE5DxkSquCa3I_UkoxRCLcPCRDGAve4XU5_X5MxJw', 'Ask any coffee connoisseur and they&apos;ll tell you that in order for coffee beans to develop their full flavor profile, they must be ground right before coming in contact with hot water. This is where the bistro electric burr coffee grinder comes in and becomes part of the coffee making ritual. The Bistro is continuously adjustable - twisting the upper bean container determines how finely ground the beans will be. With over 14 settings from coarse to fine, you can deliver the ideal grind for any brewing method.');

-- French Presses
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 2, 'Chambord French Press', 8, 39.95, 'https://dl-web.dropbox.com/get/eshop/1209_bodum_french_press_l_thumb.jpg?_subject_uid=236039489&w=AADHyRI2tpgzSfIYnS5j3GzgDWmsM9mBqQ2U6pD2hsLLFQ', 'Some purists insist that French Press is the best way to yield bold, robust coffee. It’s definitely one of the easiest with this set.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 2, 'Bodum Young French Press', 8, 32.95, 'https://dl-web.dropbox.com/get/eshop/1096_french_press_l_thumb.jpg?_subject_uid=236039489&w=AACG6C7Rd8mVjvD6tgDcHgy8zTU7FgiA0JTgwwT1Lfr7Ww', 'Some purists insist that French Press is the best way to yield bold, robust coffee. It’s definitely one of the easiest with this set. Its santoprene frame keeps coffee hot longer, while keeping its body cool to the touch for safety. Use it anywhere; home, the office, even outdoors, for the quintessential California experience.');

-- Pots
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 2, 'Bamboo Ceramic Tea Set', 11, 18.95, 'https://dl-web.dropbox.com/get/eshop/1267_green_c_thumb.jpg?_subject_uid=236039489&w=AADnKVeiioiu9y7GU7Jr0mYWKr4RHj9gg6xk7oskKf_bqA', 'It bends but never breaks, and is henceforth a Japanese symbol of strength and prosperity. May every sip of our fine teas - and every step of your unique journey - be bamboo.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 2, 'Uzu Ceramic Tea Set', 11, 15.95, 'https://dl-web.dropbox.com/get/eshop/1268_white_c_thumb.jpg?_subject_uid=236039489&w=AABlVWstZzLNnOw2PaJoo81WduxTi-Xh55QMSx3HKg-kVw', 'Uzu, Japanese for swirl, describes this textured finish. There&apos;s no symbolism, says the designer. But we say stirring our teas with honey, lemon, or milk, makes for a beautiful uzu.');
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 2, 'Cherry Blossom Tea Set', 11, 21.95, 'https://dl-web.dropbox.com/get/eshop/1265_black_c_thumb.jpg?_subject_uid=236039489&w=AAC-6lmP_3RcAomfJJcQgC9AzkIjb7gLDq-FKGqne7C6Hg', 'This design, centered around the traditional Japanese values of simplicity and purity, will make our exotic tea - and you its drinker - feel right at home.');

-- Cups
INSERT INTO product (productid, itemsLeft, slug, category, price, image, description) VALUES (null, 2, 'The Jaydin Tumbler', 10, 14.95, 'https://dl-web.dropbox.com/get/eshop/1105_red_l_thumb.jpg?_subject_uid=236039489&w=AACLPSfdymsl0iwmKVXtvOfshTj8KqG1M5O-yNuBilmqqQ', 'This sleek marvel is double-wall insulated with premium stainless steel, so it works with hot and cold beverages.');