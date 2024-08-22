CREATE TABLE
  public.produto (
    id serial NOT NULL,
    preco numeric NULL,
    nome character varying(255) NULL,
    quantidade smallint NULL
  );

INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (1, 'Caneta Azul', '19.99', 150);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (2, 'Caderno Universitário', '49.50', 75);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (3, 'Lápis HB', '5.99', 300);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (4, 'Mochila Escolar', '299.99', 20);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (5, 'Borracha', '9.99', 200);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (6, 'Estojo Simples', '15.49', 120);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (7, 'Calculadora Científica', '79.90', 40);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (8, 'Régua 30cm', '12.00', 180);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (9, 'Apontador de Metal', '7.50', 250);
INSERT INTO "produto" ("id", "nome", "preco", "quantidade") VALUES (10, 'Tablet Infantil', '189.90', 0);
