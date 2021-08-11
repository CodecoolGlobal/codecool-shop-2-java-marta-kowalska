ALTER TABLE IF EXISTS ONLY product
DROP CONSTRAINT IF EXISTS fk_category_id CASCADE,
DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;

ALTER TABLE IF EXISTS ONLY cart_item
DROP CONSTRAINT IF EXISTS fk_shopping_cart_id CASCADE,
DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;

ALTER TABLE IF EXISTS ONLY order_item
DROP CONSTRAINT IF EXISTS fk_order_id CASCADE,
DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;



DROP TABLE IF EXISTS product_category;
CREATE TABLE product_category
(
    id serial NOT NULL,
    name varchar NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS product_supplier;
CREATE TABLE product_supplier
(
    id serial NOT NULL,
    name varchar NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS product;
CREATE TABLE product
(
    id serial NOT NULL,
    name varchar NOT NULL,
    description varchar NOT NULL,
    price DECIMAL(19, 4) NOT NULL,
    currency varchar NOT NULL DEFAULT 'USD',
    picture varchar NOT NULL,
    category_id INT,
    supplier_id INT,
    PRIMARY KEY(id),
    CONSTRAINT fk_category_id
        FOREIGN KEY(category_id)
            REFERENCES product_category(id),
    CONSTRAINT fk_supplier_id
        FOREIGN KEY(supplier_id)
            REFERENCES product_supplier(id)
);

DROP TABLE IF EXISTS shopping_cart;
CREATE TABLE shopping_cart
(
    id serial NOT NULL,
    checked_out boolean DEFAULT false,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS cart_item;
CREATE TABLE cart_item
(
    id serial NOT NULL,
    PRIMARY KEY(id),
    price DECIMAL(19, 4) NOT NULL,
    currency varchar NOT NULL DEFAULT 'USD',
    quantity INT NOT NULL,
    shopping_cart_id INT,
    product_id INT,
    name varchar NOT NULL,
    description varchar NOT NULL,
    picture varchar NOT NULL,
    category_id INT,
    supplier_id INT,
    CONSTRAINT fk_shopping_cart_id
        FOREIGN KEY(shopping_cart_id)
            REFERENCES shopping_cart(id),
    CONSTRAINT fk_product_id
        FOREIGN KEY(product_id)
            REFERENCES product(id),
            unique(product_id)
);

DROP TABLE IF EXISTS webshop_order;
CREATE TABLE webshop_order
(
    id serial NOT NULL,
    firstName varchar NOT NULL,
    lastName varchar NOT NULL,
    email varchar NOT NULL,
    phoneNumber varchar NOT NULL,
    country varchar NOT NULL,
    city varchar NOT NULL,
    zipcode varchar NOT NULL,
    street varchar NOT NULL,
    PRIMARY KEY(id)
);

DROP TABLE IF EXISTS order_item;
CREATE TABLE order_item
(
    id serial NOT NULL,
    PRIMARY KEY(id),
    price DECIMAL(19, 4) NOT NULL,
    currency varchar NOT NULL DEFAULT 'USD',
    quantity INTEGER NOT NULL,
    order_id INT,
    product_id INT,
    name varchar NOT NULL,
    description varchar NOT NULL,
    picture varchar NOT NULL,
    category_id INT,
    supplier_id INT,
    CONSTRAINT fk_order_id
        FOREIGN KEY(order_id)
            REFERENCES webshop_order(id),
    CONSTRAINT fk_product_id
        FOREIGN KEY(product_id)
            REFERENCES product(id)
);

INSERT INTO product_category VALUES  (0, 'Hardware');
INSERT INTO product_category VALUES  (1, 'Software');
INSERT INTO product_category VALUES  (2, 'Merchandise');
INSERT INTO product_category VALUES  (3, 'Stolen data');
INSERT INTO product_category VALUES  (4, 'Services');
SELECT pg_catalog.setval('product_category_id_seq', 4, true);

INSERT INTO product_supplier VALUES  (0, 'BKK');
INSERT INTO product_supplier VALUES  (1, 'Anonymous');
INSERT INTO product_supplier VALUES  (2, 'Neo');
INSERT INTO product_supplier VALUES  (3, 'Trinity');
INSERT INTO product_supplier VALUES  (4, 'D3F4ULT');
INSERT INTO product_supplier VALUES  (5, 'The Calculator');
INSERT INTO product_supplier VALUES  (6, 'Mr.Robot');
INSERT INTO product_supplier VALUES  (7, 'M1CK3Y');
SELECT pg_catalog.setval('product_supplier_id_seq', 7, true);

INSERT INTO product VALUES  (0, 'Hacker Laptop', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.',
                             949, DEFAULT, 'hacker_laptop.jpg', 0, 1);
INSERT INTO product VALUES  (1, 'My first hacking laptop', 'Keep your baby''s little hands (and mind) busy as can be with this interactive toy laptop that introduces password cracking, stealing data and more!',
                             491, DEFAULT, 'kid_laptop.png', 0, 4);
INSERT INTO product VALUES  (2, 'Cracked curriculum', 'Finish Codecool in one day! All tasks with solutions',
                             89, DEFAULT, 'curriculum.png', 1, 5);
INSERT INTO product VALUES  (3, 'Cracking a password', 'Cracking any password', 8.5, DEFAULT, 'crack_password.jpg', 4, 5);
INSERT INTO product VALUES  (4, 'Attend attendance', 'Attending attendance instead of you: being on time and great answer for attendance question included', 2, DEFAULT, 'attendance.jpg', 4, 3);
INSERT INTO product VALUES  (5, 'Passing a PA', 'We will take your PA for you, all green scores guaranteed!', 20, DEFAULT, 'PA.jpg', 4, 6);
INSERT INTO product VALUES  (6, 'Anonymous mask', 'No one will know that you are a hacker if you wear this', 15.5, DEFAULT, 'mask.jpg', 2, 1);
INSERT INTO product VALUES  (7, 'Credit card credentials', 'Best credit card data with a lot of money', 150000, DEFAULT, 'card.jpg', 3, 7);
INSERT INTO product VALUES  (8, 'Hoodie', 'A must have for every real hacker', 150, DEFAULT, 'hoodie.jpg', 2, 2);
INSERT INTO product VALUES  (9, '105 bus', 'Bus to get you everywhere you want with your hacker friends. Bus connecting people', 1, DEFAULT, '105bus.jpg', 0, 0);
SELECT pg_catalog.setval('product_id_seq', 9, true);

INSERT INTO shopping_cart VALUES  (1, DEFAULT);
SELECT pg_catalog.setval('shopping_cart_id_seq', 1, true);

