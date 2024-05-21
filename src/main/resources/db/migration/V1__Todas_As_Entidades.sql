create schema EstudosMicronaut;

CREATE TABLE cliente (
     id BIGINT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     cpf VARCHAR(11) NOT NULL,
     age INTEGER NOT NULL,
     city VARCHAR(255) NOT NULL
);

CREATE TABLE loja (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
);

CREATE TABLE produto (
     id BIGINT PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     preco DECIMAL(10, 2) NOT NULL
);

CREATE TABLE pedido (
    id BIGINT PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    loja_id INTEGER NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (loja_id) REFERENCES loja(id)
);

CREATE TABLE item_pedido (
     id BIGINT PRIMARY KEY,
     pedido_id INTEGER NOT NULL,
     produto_id INTEGER NOT NULL,
     quantidade INTEGER NOT NULL,
     FOREIGN KEY (pedido_id) REFERENCES pedido(id),
     FOREIGN KEY (produto_id) REFERENCES produto(id)
);