create schema EstudosMicronaut;

-- Tabela Cliente
CREATE TABLE cliente (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     cpf VARCHAR(11) NOT NULL,
     age INTEGER NOT NULL,
     city VARCHAR(255) NOT NULL
);

-- Tabela Loja
CREATE TABLE loja (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    foreign key ()
);

-- Tabela Produto
CREATE TABLE produto (
     id SERIAL PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     preco DECIMAL(10, 2) NOT NULL
);

-- Tabela Pedido
CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    loja_id INTEGER NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    FOREIGN KEY (loja_id) REFERENCES loja(id)
);

-- Tabela ItemPedido
CREATE TABLE item_pedido (
     id SERIAL PRIMARY KEY,
     pedido_id INTEGER NOT NULL,
     produto_id INTEGER NOT NULL,
     quantidade INTEGER NOT NULL,
     FOREIGN KEY (pedido_id) REFERENCES pedido(id),
     FOREIGN KEY (produto_id) REFERENCES produto(id)
);


