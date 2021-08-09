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
    CONSTRAINT fk_shopping_cart_id
        FOREIGN KEY(shopping_cart_id)
            REFERENCES shopping_cart(id),
    CONSTRAINT fk_product_id
        FOREIGN KEY(product_id)
            REFERENCES product(id)
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
    CONSTRAINT fk_order_id
        FOREIGN KEY(order_id)
            REFERENCES webshop_order(id),
    CONSTRAINT fk_product_id
        FOREIGN KEY(product_id)
            REFERENCES product(id)
);
