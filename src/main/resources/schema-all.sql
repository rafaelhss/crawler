DROP TABLE noticia IF EXISTS;

CREATE TABLE noticia  (
    noticia_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    titulo VARCHAR(255),
    texto VARCHAR(255)
);