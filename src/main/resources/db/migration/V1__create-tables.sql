CREATE TABLE seller (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(15) NOT NULL,
	salary NUMERIC (10, 2) NOT NULL
);

CREATE TABLE client(
id SERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(15) NOT NULL,
	address TEXT NOT NULL
);
CREATE TABLE sale(
id SERIAL PRIMARY KEY,
	client_id BIGINT,
	seller_id BIGINT,
	sale_date DATE NOT NULL,
	total_amount NUMERIC,
	CONSTRAINT fk_sale_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE SET NULL,
 	CONSTRAINT fk_sale_seller FOREIGN KEY (seller_id) REFERENCES seller(id)
 	ON DELETE SET NULL
);

CREATE TABLE product(
cod_product SERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
price NUMERIC NOT NULL,
quantity INT DEFAULT NULL
);

CREATE TABLE sales_products(
	id SERIAL PRIMARY KEY,
	sale_id BIGINT,
	product_cod BIGINT,
	quantity_sold INT,
	CONSTRAINT fk_sales_products_sale FOREIGN KEY (sale_id) REFERENCES sale(id)
	ON DELETE SET NULL,
 	CONSTRAINT fk_sale_products_product FOREIGN KEY (product_cod) REFERENCES product(cod_product)
    ON DELETE SET NULL
);
