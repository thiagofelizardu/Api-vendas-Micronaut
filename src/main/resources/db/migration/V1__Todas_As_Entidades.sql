CREATE SCHEMA EstudosMicronaut;

CREATE TABLE tb_endereco (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,
      logradouro VARCHAR(255) NOT NULL,
      cidade VARCHAR(255) NOT NULL
);

CREATE TABLE tb_produto (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     nome VARCHAR(255) NOT NULL,
     preco DECIMAL(10, 2) NOT NULL,
);

CREATE TABLE tb_client (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    age INTEGER NOT NULL,
    endereco_id INTEGER NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES tb_endereco(id)
);

CREATE TABLE tb_pedido (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id BIGINT NOT NULL,
    itens_do_pedido BIGINT,
    FOREIGN KEY (itens_do_pedido) REFERENCES tb_item_pedido(id),
    FOREIGN KEY (cliente_id) REFERENCES tb_clientnte(id),
);

CREATE TABLE tb_item_pedido (
     id BIGINT PRIMARY KEY AUTO_INCREMENT,
     pedido_id BIGINT NOT NULL,
     produto_id BIGINT NOT NULL,
     quantidade BIGINT NOT NULL,
     FOREIGN KEY (pedido_id) REFERENCES tb_pedido(id),
     FOREIGN KEY (produto_id) REFERENCES tb_produto(id)
);