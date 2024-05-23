CREATE SCHEMA EstudosMicronaut;

CREATE TABLE loja (
      id BIGINT PRIMARY KEY,
      name VARCHAR(255) NOT NULL
);

CREATE TABLE endereco (
      id BIGINT PRIMARY KEY,
      logradouro VARCHAR(255) NOT NULL,
      cidade VARCHAR(255) NOT NULL
);

CREATE TABLE produto (
     id BIGINT PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     preco DECIMAL(10, 2) NOT NULL,
     loja_id INTEGER NOT NULL,
     FOREIGN KEY (loja_id) REFERENCES loja(id)
);

CREATE TABLE client (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    age INTEGER NOT NULL,
    endereco_id INTEGER NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE pedido (
    id BIGINT PRIMARY KEY,
    endereco_id INTEGER NOT NULL,
    client_id INTEGER NOT NULL,
    loja_id INTEGER NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id),
    FOREIGN KEY (client_id) REFERENCES client(id),
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