/*
------------------------------------------------------------------------------------
Proyecto:   My Wedding
Archivo:    01_ddl.sql
Autor:      Yojany Lobo Gil, 
            Paloma de los Milagros, 
            Bryan Ludena
Fecha:      2025-10-23
Version:    5.0
Descripción: Script DDL para crear el esquema de la aplicación My Wedding (tablas, 
            relaciones y constraints) en MySQL. Este proyecto corresponte al proyecto
            de Fin de Ciclo de Grado Superior DAM - UNIR.
------------------------------------------------------------------------------------
Dependencias: 
                - Requiere tener creada la base de datos "mywedding"

                - Requiere permisos de ejecución CREATE / ALTER / FK.
------------------------------------------------------------------------------------
Historial de cambios:

usuarios nueva col estado 
Agregamos tabla ROLES y relación: 1 roles * usuarios

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
CREATE TABLE allergens (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
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

CREATE TABLE allergens_images (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    link VARCHAR(255),
    image_type VARCHAR(255),
    id_allergen BIGINT,
    CONSTRAINT fk_allergens_images_allergens 
        FOREIGN KEY (id_allergen) REFERENCES allergens(id) 
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

CREATE TABLE products (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    link VARCHAR(255) ,
    description VARCHAR(255),
    product_type VARCHAR(255),
    start_date DATETIME,
    update_date DATETIME,
    price NUMERIC(10,2)
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

CREATE TABLE images_products (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    link VARCHAR(255),
    image_type VARCHAR(255),
    product_id BIGINT,
    CONSTRAINT fk_images_products_products
        FOREIGN KEY (product_id) REFERENCES products(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

/* -------------------------------------------------------------
Tabla: roles
Descripción: 
            Contiene la información referente a los roles 
            de usuarios en la app
Relaciones:
            - Un ususario puede ser ROL_USER o ROLE_ADMIN
            - Se relaciona con usuarios mediante FK
Notas:
---------------------------------------------------------------- */

--CREATE TABLE roles (
  --  id BIGINT not null auto_increment PRIMARY KEY,
  --  nombre VARCHAR(255) NOT NULL
--);

/* -------------------------------------------------------------
Tabla: usuarios
Descripción: 
            Contiene la información referente a los usuarios registrados
            en la app
Relaciones:
            - Un ususario puede tener varias alergias
            - Se relaciona con alergias mediante FK
            - Un usuario tiene solo un rol
            - se relaciona con roles mediante FK
Notas:
            - email debe ser único
---------------------------------------------------------------- */

CREATE TABLE users_app (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    first_surname VARCHAR(255),
    second_surname VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(255),
    password VARCHAR(255),
    rol_id BIGINT DEFAULT 1,
    FOREIGN KEY (rol_id) REFERENCES roles(id) ON DELETE CASCADE ON UPDATE CASCADE
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

CREATE TABLE allergies (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	allergen_id BIGINT NULL,
    user_id BIGINT(255),
	CONSTRAINT fk_allergies_users_app
        FOREIGN KEY (user_id) REFERENCES users_app(id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_allergies_allergens
        FOREIGN KEY (allergen_id) REFERENCES allergens(id) 
        ON DELETE CASCADE ON UPDATE CASCADE
);


/* ==============================================================
RELACIONES ENTRE TABLAS
=================================================================

- imagen_alergenos.id_alergeno -> alergeno.id   (N:1)
- imagen_productos.id_productoo -> producto.id  (N:1)
- alergias.id_usuario -> usuarios.id            (N:1)
- ususarios.id_roles -> roles.id                (N:1)

============================================================== */






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

CREATE TABLE weddings (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dateWedding DATETIME,
    place VARCHAR(255),
    state_wedding VARCHAR(255)  DEFAULT "PREPARING"
);

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

CREATE TABLE events (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    event_type VARCHAR(255),
    description VARCHAR(255),
    time DATETIME,
    wedding_id BIGINT,
    CONSTRAINT fk_events_weddings
        FOREIGN KEY (wedding_id) REFERENCES weddings(id) 
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

CREATE TABLE users_invitations (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    wedding_id BIGINT,
    confirm BOOLEAN DEFAULT false,  
    email_invitation VARCHAR(255),
    child_acompanion INT,
    adult_acompanion INT,
    CONSTRAINT fk_users_invitations_weddings
        FOREIGN KEY (wedding_id) REFERENCES weddings(id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_users_invitations_users_app
        FOREIGN KEY (user_id) REFERENCES users_app(id) 
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

CREATE TABLE presents (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    wedding_id BIGINT,
    product_id BIGINT,
    user_id BIGINT,
    confirm BOOLEAN DEFAULT false,
    price NUMERIC(10,2),
    CONSTRAINT fk_presents_weddings
        FOREIGN KEY (wedding_id) REFERENCES weddings(id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_presents_products
        FOREIGN KEY (product_id) REFERENCES products(id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_presents_users_app
        FOREIGN KEY (user_id) REFERENCES users_app(id) 
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

CREATE TABLE user_weddings (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email_groom VARCHAR(255),
    wedding_id BIGINT,
    user_id BIGINT,
    CONSTRAINT fk_user_weddings_weddings
        FOREIGN KEY (wedding_id) REFERENCES weddings(id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_user_weddings_users_app
        FOREIGN KEY (user_id) REFERENCES users_app(id) 
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
