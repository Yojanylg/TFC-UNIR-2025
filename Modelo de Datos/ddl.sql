CREATE DATABASE myweddingplanner;

USE myweddingplanner;

-- CREANDO TABLAS

CREATE TABLE itinerarios (
    id BIGINT not null auto_increment PRIMARY KEY,
    descripcion VARCHAR(255)
);

CREATE TABLE bodas (
    id BIGINT not null auto_increment PRIMARY KEY,
    lugar VARCHAR(255) NOT NULL,
    fecha VARCHAR(255) NOT NULL,
    id_itinerario BIGINT,
    FOREIGN KEY (id_itinerario) REFERENCES itinerarios(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE usuarios (
    id BIGINT not null auto_increment PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido_1 VARCHAR(255) NOT NULL,
    apellido_2 VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL
);

CREATE TABLE alergenos (
    id BIGINT not null auto_increment PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE imagen_alergenos (
    id BIGINT not null auto_increment PRIMARY KEY,
    enlace VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    id_alergeno BIGINT,
    FOREIGN KEY (id_alergeno) REFERENCES alergenos(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE productos (
    id BIGINT not null auto_increment PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    enlace_compra VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2)
);

CREATE TABLE imagen_productos (
    id BIGINT not null auto_increment PRIMARY KEY,
    enlace VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    id_producto BIGINT,
    FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE invitacion_usuarios (
    id BIGINT not null auto_increment PRIMARY KEY,
    id_usuario BIGINT,
    id_boda BIGINT,
    acompanantes_mayores INT,
    acompanantes_menores INT,
    confirmado BOOLEAN DEFAULT false,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_boda) REFERENCES bodas(id) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE regalos_boda (
    id BIGINT not null auto_increment PRIMARY KEY,
    id_usuario BIGINT,
    id_boda BIGINT,
    id_producto BIGINT,
    valor DECIMAL(10,2),
    confirmado BOOLEAN DEFAULT false,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_boda) REFERENCES bodas(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE usuarios_alergenos (
	id BIGINT auto_increment NOT NULL PRIMARY KEY,
	id_usuario BIGINT NULL,
	id_alergeno BIGINT NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id_alergeno) REFERENCES alergenos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE bodas_usuario (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    id_usuario BIGINT NULL,
    id_boda BIGINT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_boda) REFERENCES bodas(id) ON DELETE CASCADE ON UPDATE CASCADE,
);


INSERT INTO itinerarios (descripcion)
VALUES 
('Ceremonia y recepción en el mismo lugar'),
('Ceremonia en iglesia y banquete en hotel'),
('Boda en la playa con cena al aire libre');


INSERT INTO bodas (lugar, fecha, id_itinerario)
VALUES 
('Hotel Mediterráneo', '2025-06-14', 1),
('Iglesia San Jorge y Hotel Palace', '2025-08-22', 2),
('Playa del Sol', '2025-09-10', 3);

INSERT INTO usuarios (nombre, apellido_1, apellido_2, email, telefono)
VALUES
('Laura', 'Martínez', 'Gómez', 'laura.martinez@example.com', '+34611122334'),
('Carlos', 'Pérez', 'Ruiz', 'carlos.perez@example.com', '+34622233445'),
('Ana', 'Santos', NULL, 'ana.santos@example.com', '+34633344556'),
('Miguel', 'Rodríguez', 'Lopez', 'miguel.rodriguez@example.com', '+34644455667');

INSERT INTO alergenos (nombre)
VALUES 
('Gluten'),
('Lácteos'),
('Frutos secos'),
('Mariscos');


INSERT INTO imagen_alergenos (enlace, tipo, id_alergeno)
VALUES 
('https://cdn.example.com/img/alergenos/gluten.png', 'png', 1),
('https://cdn.example.com/img/alergenos/lacteos.jpg', 'jpg', 2),
('https://cdn.example.com/img/alergenos/frutossecos.png', 'png', 3),
('https://cdn.example.com/img/alergenos/mariscos.jpg', 'jpg', 4);

INSERT INTO productos (nombre, enlace_compra, descripcion, valor)
VALUES 
('Vajilla elegante', 'https://shop.example.com/vajilla', 'Set de 24 piezas de porcelana blanca', 120.50),
('Cafetera espresso', 'https://shop.example.com/cafetera', 'Cafetera automática de acero inoxidable', 199.99),
('Juego de toallas premium', 'https://shop.example.com/toallas', 'Set de 6 toallas de algodón egipcio', 85.00),
('Set de copas de vino', 'https://shop.example.com/copas', 'Copas de cristal soplado artesanalmente', 60.00);

INSERT INTO imagen_productos (enlace, tipo, id_producto)
VALUES
('https://cdn.example.com/img/productos/vajilla.png', 'png', 1),
('https://cdn.example.com/img/productos/cafetera.jpg', 'jpg', 2),
('https://cdn.example.com/img/productos/toallas.jpg', 'jpg', 3),
('https://cdn.example.com/img/productos/copas.png', 'png', 4);

INSERT INTO invitacion_usuarios (id_usuario, id_boda, acompanantes_mayores, acompanantes_menores, confirmado)
VALUES 
(1, 1, 1, 0, TRUE),
(2, 1, 0, 0, FALSE),
(3, 2, 2, 1, TRUE),
(4, 3, 1, 0, TRUE);

INSERT INTO regalos_boda (id_usuario, id_boda, id_producto, valor, confirmado)
VALUES 
(1, 1, 2, 199.99, TRUE),
(2, 1, 4, 60.00, FALSE),
(3, 2, 1, 120.50, TRUE),
(4, 3, 3, 85.00, TRUE);

INSERT INTO usuarios_alergenos (id_usuario, id_alergeno)
VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(3, 2),
(4, 3);

INSERT INTO bodas_usuario (id_usuario, id_boda)
VALUES 
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(3, 2),
(4, 3);