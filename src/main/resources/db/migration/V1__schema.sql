create schema EstudosMicronaut;

CREATE TABLE client (
    id NUMERIC PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    age NUMERIC NOT NULL,
    city VARCHAR(255) NOT NULL
);
