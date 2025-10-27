/*
------------------------------------------------------------------------------------
Proyecto:   My Wedding
Archivo:    01_ddl.sql
Autor:      Yojany Lobo Gil, 
            Paloma de los Milagros, 
            Bryan Ludena
Fecha:      2025-10-25
Version:       1.0
Descripción: Script DDL para crear el esquema de la aplicación My Wedding (tablas, 
            relaciones y constraints) en MySQL. Este proyecto corresponte al proyecto
            de Fin de Ciclo de Grado Superior DAM - UNIR.
------------------------------------------------------------------------------------
Dependencias: 
                - Requiere tener creada la base de datos "mywedding"

                - Requiere permisos de ejecución CREATE / ALTER / FK.
------------------------------------------------------------------------------------
Historial de cambios:

TODO 
    alergenos.nombre UNIQUE
    usuarios -> agregar password
    imagen_(producto || alergeno) -> cuando definamos los tipos de imagenes, se pueden
                                    unificar en una tabla y agregar restricción check
    usuarios.email -> UNIQUE
    itinerarios -> UPGRADE esta tabla debe evolucionar y convertirce en la instanciación
                    de los distintos eventos dentro de una boda: banquete, ceremonia, etc. 


------------------------------------------------------------------------------------
*/



/* ------------------------------------------------------------------------------------
1. CREACIÓN DE LA DASE DE DATOS Y USO DEL SCHEMA
------------------------------------------------------------------------------------ */

CREATE DATABASE IF NOT EXISTS mywedding;

USE mywedding;

/* ------------------------------------------------------------------------------------
1. CREACIÓN DE TABLAS
------------------------------------------------------------------------------------ */

/* -------------------------------------------------------------
Tabla: alergenos
Descripción: 
            Contiene los distintos alergenos que pueden estar
            presentes en los alimentos ofrecidos en la boda
Relaciones: 
            - Puede tener multiples imágenes
            - Se relaciona con imagenes_alergenos mediante FK
Notas:
            - Cada registro representa un único tipo de alérgeno
            - Campo "nombre" debe ser único para evitar duplicados
---------------------------------------------------------------- */
CREATE TABLE alergenos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255)
);

/* -------------------------------------------------------------
Tabla: imagen_alergenos
Descripción:
            Contiene los enlaces a las imágenes que ilustran alérgenos
            que pueden ser utilizadas en la IU.
Relaciones:
            - Cada imagen pertenece a un único alérgeno
            - Se relaciona FK id_alergeno con alergenos.id
Notas:      
            - el campo tipo se refiere a una de las distintas posiciones
            que una imágen puede tomar dentro de la IU
---------------------------------------------------------------- */
CREATE TABLE imagen_alergenos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    enlace VARCHAR(255),
    tipo VARCHAR(255),
    id_alergeno BIGINT,
    CONSTRAINT fk_imagen_alergeno 
        FOREIGN KEY (id_alergeno) REFERENCES alergenos(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

/* -------------------------------------------------------------
Tabla: productos
Descripción:
            - Contiene los productos que a modo de ideas de regalo 
            ofrece la APP a los Novios para que puedan agregar 
            a su lista de regalos
Relaciones:
            - Cada producto puede relacionarse con muchas imagen_productos
            - Se relaciona con imagen_producto mediante FK
---------------------------------------------------------------- */
CREATE TABLE productos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    enlace_compra VARCHAR(255) ,
    descripcion VARCHAR(255),
    valor NUMERIC(10,2)
);


/* -------------------------------------------------------------
Tabla: imagen_producto
Descripción:
            Contiene los enlaces a las imágenes que ilustran productos
            que pueden ser utilizadas en la IU.
Relaciones:
            - Cada imagen pertenece a un único producto
            - Se relaciona mediante FK (id_producto) con producto.id
Notas:      
            - el campo tipo se refiere a una de las distintas posiciones
            que una imágen puede tomar dentro de la IU
---------------------------------------------------------------- */
CREATE TABLE imagen_productos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    enlace VARCHAR(255),
    tipo VARCHAR(255),
    id_producto BIGINT,
    CONSTRAINT fk_imagen_producto
        FOREIGN KEY (id_producto) REFERENCES productos(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

/* -------------------------------------------------------------
Tabla: usuarios
Descripción: 
            Contiene la información referente a los usuarios registrados
            en la app
Relaciones:
            - Un ususario puede tener varias alergias
            - Se relaciona con alergias mediante FK
Notas:
            - email debe ser único
---------------------------------------------------------------- */
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    apellido_1 VARCHAR(255),
    apellido_2 VARCHAR(255),
    email VARCHAR(255),
    telefono VARCHAR(255)
);

/* -------------------------------------------------------------
Tabla: alergias
Descripción:
            - Contiene el conjunto de las alergias de los usuarios de la APP
            - Esta tabla debiera estar securizada y protegida dado que es información
            con una especial protección
Relaciones:
            - Se relaciona por medio de FK (id_usuario) con usuario.id
            - el campo id_alergeno guarda el dato de alergeno.id. No se establece
            relación entre las tablas. Queda pendiente para la implementacions del Modelo 1
---------------------------------------------------------------- */
CREATE TABLE alergias (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	id_usuario BIGINT NULL,
	id_alergeno BIGINT NULL,
    nombre VARCHAR(255),
	CONSTRAINT fk_alergias_usuario 
        FOREIGN KEY (id_usuario) REFERENCES usuarios(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);


/* ==============================================================
RELACIONES ENTRE TABLAS
=================================================================

- imagen_alergenos.id_alergeno -> alergeno.id   (N:1)
- imagen_productos.id_productoo -> producto.id  (N:1)
- alergias.id_usuario -> usuarios.id            (N:1)

============================================================== */





/* -------------------------------------------------------------
Tabla: itinerarios
Descripción:
            - de comento no es más que un conjunto de oraciones con los distintos 
            itinerarios de cada boda.
            - necesita UPGRADE para que guarde datos de diferentes TIPO de eventos
            que pueden ocurrir en una BODA.
Relaciones:
            - se relaciona 1:1 con bodas.id a falta de UPGRADE de la tabla
---------------------------------------------------------------- */
CREATE TABLE itinerarios (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255)
);

/* -------------------------------------------------------------
Tabla: bodas
Descripción:
            - contiene la información básica de una boda
Relaciones:
            - se relaciona 1:N con invitados FK (invitados.id_boda)
            - se relaciona 1:N con novios FK (novios.id_boda)
            - se relaciona 1:N con inviregalos FK (regalos.id_boda)
            - se relaciona 1:1 con itinerarios FK (boda.id_itinerario)
Notas:
        - esta tabla ha de sufrir UPGRADE para establecer todas las relaciones
        que tiene con otras tablas y que de momento se dejan pendientes

---------------------------------------------------------------- */
CREATE TABLE bodas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    lugar VARCHAR(255),
    fecha VARCHAR(255),
    id_itinerario BIGINT,
    CONSTRAINT fk_bodas_itinerario 
        FOREIGN KEY (id_itinerario) REFERENCES itinerarios(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

/* -------------------------------------------------------------
Tabla: invitados
Descripción:
            - contiene los datos de lo invitados de cada boda
            - esta tabla permite realizar invitaciones a usuarios no regisrados
            en la App
            - una vez registrados los usuarios podrán modificar las
            condiciones de su invitación
            - en un futuro UPGRADE podría permitir a los invitados elegir a 
            qué eventos de la boda asistirán o no
Relaciones: 
            - se relaciona con usuarios.id fk (invitados.id_boda)
---------------------------------------------------------------- */
CREATE TABLE invitados (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
    CONSTRAINT fk_usuario_boda
        FOREIGN KEY (id_boda) REFERENCES bodas(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

/* -------------------------------------------------------------
Tabla: regalos
Descripción:
            - contiene los datos de los regalos de cada boda
            - esta tabla permite a los novios agregar regalos a su
            su lista de boda para que los invitados los puedan consultar 
            en la App
Relaciones:
            - se relaciona con boda.id por FK (regalos.id_boda)
---------------------------------------------------------------- */
CREATE TABLE regalos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_boda BIGINT,
    id_producto BIGINT,
    descripcion VARCHAR(255),
    enlace_compra VARCHAR(255),
    id_usuario BIGINT,
    valor NUMERIC(10,2),
    confirmado BOOLEAN DEFAULT false,
    CONSTRAINT fk_reg_boda
        FOREIGN KEY (id_boda) REFERENCES bodas(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);


/* -------------------------------------------------------------
Tabla: novios
Descripción:
            - contiene los datos de los novios de la boda, permitiendo
            que solo un novio esté registrado o lo hagan los dos
Relaciones:
            - se relacina con boda.id mediante FK (novios.id_boda)
---------------------------------------------------------------- */
CREATE TABLE novios (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
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

/* ==============================================================
RELACIONES ENTRE TABLAS
=================================================================

- boda.id_itinerario -> itinerario.id   (1:1)
- invitados.id_boda -> boda.id          (N:1)
- regalos.id_boda -> boda.id            (N:1)
- novios.id_boda -> boda.id             (N:1)
============================================================== */
