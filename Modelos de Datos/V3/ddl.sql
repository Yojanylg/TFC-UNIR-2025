CREATE DATABASE IF NOT EXISTS myweddingplanner;

USE myweddingplanner;

-- CREANDO TABLAS

-- Adecuado a Modelo 2 

CREATE TABLE itinerarios (
    id BIGINT not null auto_increment PRIMARY KEY,
    descripcion VARCHAR(255)
);

-- Adecuado a Modelo 2

CREATE TABLE bodas (
    id BIGINT not null auto_increment PRIMARY KEY,
    lugar VARCHAR(255) NOT NULL,
    fecha VARCHAR(255) NOT NULL,
    id_itinerario BIGINT,
    FOREIGN KEY (id_itinerario) REFERENCES itinerarios(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Adecuado a Modelo 2
-- Modelo 3
-- Add USUARIO.password
-- Add USUARIOS.id_rol
-- Add TABLA roles

CREATE TABLE roles (
    id BIGINT not null auto_increment PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios (
    id BIGINT not null auto_increment PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido_1 VARCHAR(255) NOT NULL,
    apellido_2 VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    telefono VARCHAR(255) NOT NULL,
    id_rol BIGINT DEFAULT 1,
    FOREIGN KEY (id_rol) REFERENCES roles(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Adecuado a Modelo 2

CREATE TABLE alergenos (
    id BIGINT not null auto_increment PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Adecuado a Modelo 2

CREATE TABLE imagen_alergenos (
    id BIGINT not null auto_increment PRIMARY KEY,
    enlace VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    id_alergeno BIGINT,
    FOREIGN KEY (id_alergeno) REFERENCES alergenos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Adecuado a Modelo 2

CREATE TABLE productos (
    id BIGINT not null auto_increment PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    enlace_compra VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    valor DECIMAL(10,2)
);

-- Adecuado a Modelo 2

CREATE TABLE imagen_productos (
    id BIGINT not null auto_increment PRIMARY KEY,
    enlace VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    id_producto BIGINT,
    FOREIGN KEY (id_producto) REFERENCES productos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Adecuado a Modelo 2

CREATE TABLE invitados (
    id BIGINT not null auto_increment PRIMARY KEY,
    id_usuario BIGINT,
    id_boda BIGINT,
    nombre VARCHAR(255),
    apellido_1 VARCHAR(255),
    apellido_2 VARCHAR(255),
    email VARCHAR(255),
    telefono VARCHAR(255),
    confirmado BOOLEAN DEFAULT false,
    acompanantes_mayores INT,
    acompanantes_menores INT,
    FOREIGN KEY (id_boda) REFERENCES bodas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Adecuado a Modelo 2 

CREATE TABLE regalos (
    id BIGINT not null auto_increment PRIMARY KEY,
    id_producto BIGINT,
    descripcion VARCHAR(255),
    enlace_compra VARCHAR(255),
    comprador BIGINT,
    valor DECIMAL(10,2),
    confirmado BOOLEAN DEFAULT false,
    id_boda BIGINT,
    FOREIGN KEY (id_boda) REFERENCES bodas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Adecuado a Modelo 2 

CREATE TABLE usuarios_alergenos (
	id BIGINT auto_increment NOT NULL PRIMARY KEY,
	id_usuario BIGINT NULL,
	id_alergeno BIGINT NULL,
    nombre VARCHAR(255),
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE bodas_usuario (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    id_usuario BIGINT NULL,
    id_boda BIGINT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_boda) REFERENCES bodas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- utilizando modelo 2 en mysql

CREATE TABLE novios (
    id BIGINT auto_increment NOT NULL PRIMARY KEY,
    id_usuario BIGINT,
    id_boda BIGINT,
    nombre VARCHAR(255),
    apellido_1 VARCHAR(255),
    apellido_2 VARCHAR(255),
    email VARCHAR(255),
    telefono VARCHAR(255),
    CONSTRAINT fk_bu_boda
        FOREIGN KEY (id_boda) REFERENCES bodas(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO roles (nombre)
VALUES
("ROLE_USER"),
("ROLE_ADMIN");

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

INSERT INTO usuarios (nombre, apellido_1, apellido_2, email, telefono, id_rol)
VALUES
('Laura', 'Martínez', 'Gómez', 'laura.martinez@example.com', '+34611122334',0),
('Carlos', 'Pérez', 'Ruiz', 'carlos.perez@example.com', '+34622233445', 0),
('Ana', 'Santos', NULL, 'ana.santos@example.com', '+34633344556', 0),
('Miguel', 'Rodríguez', 'Lopez', 'miguel.rodriguez@example.com', '+34644455667', 0);

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

INSERT INTO novios (id_usuario, id_boda, nombre, apellido_1, apellido_2, email, telefono) VALUES
(1, 1, 'Laura','Martínez','Gómez','laura.martinez@example.com','+34611122334'),
(2, 2, 'Carlos','Pérez','Ruiz','carlos.perez@example.com','+34622233445'),
(3, 3, 'Ana','Santos',NULL,'ana.santos@example.com','+34633344556'),
(4, 3, 'Miguel','Rodríguez','López','miguel.rodriguez@example.com','+34644455667');

INSERT INTO invitados (id_usuario, id_boda, nombre, apellido_1, apellido_2,
                        email, telefono, confirmado, acompanantes_mayores, acompanantes_menores
)
VALUES
-- BODA 1
(NULL, 1, 'Laura', 'Sánchez', 'Gómez', 'laura.sanchez@example.com', '600123456', TRUE, 1, 0),
(NULL, 1, 'Carlos', 'Martínez', 'Pérez', 'carlos.martinez@example.com', '600654321', FALSE, 0, 1),
(NULL, 1, 'Paula', 'Fernández', 'Torres', 'paula.fernandez@example.com', '622334455', TRUE, 1, 2),
(NULL, 1, 'Javier', 'Romero', 'Gil', 'javier.romero@example.com', '622556677', TRUE, 0, 0),

-- BODA 2
(NULL, 2, 'Ana', 'Ruiz', 'López', 'ana.ruiz@example.com', '611223344', TRUE, 2, 0),
(NULL, 2, 'Miguel', 'Díaz', 'Santos', 'miguel.diaz@example.com', '611998877', FALSE, 0, 0),

-- BODA 3
(NULL, 3, 'Paula', 'Fernández', 'Torres', 'paula.fernandez@example.com', '622334455', TRUE, 1, 2),
(NULL, 3, 'Javier', 'Romero', 'Gil', 'javier.romero@example.com', '622556677', TRUE, 0, 0),
(NULL, 3, 'Paula', 'Fernández', 'Torres', 'paula.fernandez@example.com', '622334455', TRUE, 1, 2),
(NULL, 3, 'Javier', 'Romero', 'Gil', 'javier.romero@example.com', '622556677', TRUE, 0, 0),

-- BODA 4
(NULL, 4, 'Lucía', 'Navarro', 'Ortiz', 'lucia.navarro@example.com', '633778899', FALSE, 1, 0),
(NULL, 4, 'David', 'Castro', 'Méndez', 'david.castro@example.com', '633112233', TRUE, 0, 1);


INSERT INTO regalos (
    id_producto, descripcion, enlace_compra, comprador, valor, confirmado, id_boda
)
VALUES
-- 🎁 BODA 1
(101, 'Juego de copas de cristal', 'https://www.amazon.es/dp/B08CGLASS', NULL, 45.99, FALSE, 1),
(102, 'Tostadora vintage', 'https://www.amazon.es/dp/B09TOAST', 1, 65.50, TRUE, 1),

-- 🎁 BODA 2
(201, 'Robot de cocina multifunción', 'https://www.amazon.es/dp/B07COOKER', NULL, 299.00, FALSE, 2),
(202, 'Set de sábanas premium', 'https://www.amazon.es/dp/B08SHEETS', 2, 89.90, TRUE, 2),

-- 🎁 BODA 3
(301, 'Cuadro decorativo personalizado', 'https://www.etsy.com/es/listing/123456', 3, 59.95, TRUE, 3),
(302, 'Cafetera italiana de acero', 'https://www.amazon.es/dp/B07COFFEE', NULL, 34.75, FALSE, 3),
(401, 'Vale de viaje para luna de miel', 'https://www.viajesluna.com/vale', 4, 500.00, TRUE, 3),
(402, 'Set de cuchillos de cocina profesional', 'https://www.amazon.es/dp/B09KNIFE', NULL, 120.00, FALSE, 3);
