INSERT INTO alergenos (nombre) VALUES
('Gluten'),('Lácteos'),('Frutos secos'),('Mariscos'),('Huevo'),
('Soja'),('Mostaza'),('Sésamo'),('Apio'),('Sulfitos');


INSERT INTO productos (nombre, enlace_compra, descripcion, valor) VALUES
('Vajilla elegante','https://shop.example.com/vajilla','Set de 24 piezas de porcelana blanca',120.50),
('Cafetera espresso','https://shop.example.com/cafetera','Cafetera automática de acero inoxidable',199.99),
('Juego de toallas premium','https://shop.example.com/toallas','Set de 6 toallas de algodón egipcio',85.00),
('Set de copas de vino','https://shop.example.com/copas','Copas de cristal soplado artesanalmente',60.00),
('Robot de cocina','https://shop.example.com/robot','Procesador multifunción',349.00),
('Batidora de vaso','https://shop.example.com/batidora','Alta potencia para smoothies',79.90),
('Juego de sartenes','https://shop.example.com/sartenes','Antiadherentes de 3 piezas',99.95),
('Manta de sofá','https://shop.example.com/manta','Tejido suave y cálido',35.50),
('Juego de cuchillos','https://shop.example.com/cuchillos','Acero inoxidable 6 piezas',89.00),
('Hervidor eléctrico','https://shop.example.com/hervidor','1.7L con apagado automático',45.00);


INSERT INTO imagen_alergenos (enlace, tipo, id_alergeno) VALUES
('https://cdn.example.com/img/alergenos/gluten.png','png',1),
('https://cdn.example.com/img/alergenos/lacteos.jpg','jpg',2),
('https://cdn.example.com/img/alergenos/frutossecos.png','png',3),
('https://cdn.example.com/img/alergenos/mariscos.jpg','jpg',4),
('https://cdn.example.com/img/alergenos/huevo.png','png',5),
('https://cdn.example.com/img/alergenos/soja.jpg','jpg',6),
('https://cdn.example.com/img/alergenos/mostaza.png','png',7),
('https://cdn.example.com/img/alergenos/sesamo.jpg','jpg',8),
('https://cdn.example.com/img/alergenos/apio.png','png',9),
('https://cdn.example.com/img/alergenos/sulfitos.jpg','jpg',10);


INSERT INTO imagen_productos (enlace, tipo, id_producto) VALUES
('https://cdn.example.com/img/productos/vajilla.png','png',1),
('https://cdn.example.com/img/productos/cafetera.jpg','jpg',2),
('https://cdn.example.com/img/productos/toallas.jpg','jpg',3),
('https://cdn.example.com/img/productos/copas.png','png',4),
('https://cdn.example.com/img/productos/robot.jpg','jpg',5),
('https://cdn.example.com/img/productos/batidora.jpg','jpg',6),
('https://cdn.example.com/img/productos/sartenes.png','png',7),
('https://cdn.example.com/img/productos/manta.jpg','jpg',8),
('https://cdn.example.com/img/productos/cuchillos.jpg','jpg',9),
('https://cdn.example.com/img/productos/hervidor.png','png',10);


INSERT INTO usuarios (nombre, apellido_1, apellido_2, email, telefono) VALUES
('Laura','Martínez','Gómez','laura.martinez@example.com','+34611122334'),
('Carlos','Pérez','Ruiz','carlos.perez@example.com','+34622233445'),
('Ana','Santos',NULL,'ana.santos@example.com','+34633344556'),
('Miguel','Rodríguez','López','miguel.rodriguez@example.com','+34644455667'),
('Sofía','Navarro','Iglesias','sofia.navarro@example.com','+34655566778'),
('Diego','García','Romero','diego.garcia@example.com','+34666677889'),
('Elena','Torres','Vega','elena.torres@example.com','+34677788990'),
('Javier','Flores','Mora','javier.flores@example.com','+34688899001'),
('María','Luna','Prieto','maria.luna@example.com','+34699900112'),
('Pablo','Vidal','Serrano','pablo.vidal@example.com','+34700011223');


INSERT INTO alergias (id_usuario, id_alergeno, nombre) VALUES
(1, 1, 'Gluten'),
(1, 2, 'Lácteos'),
(2, 3, 'Frutos secos'),
(3, 2, 'Lácteos'),
(4, 4, 'Mariscos'),
(5, 5, 'Huevo'),
(6, 6, 'Soja'),
(7, 7, 'Mostaza'),
(8, 8, 'Sésamo'),
(9, 9, 'Apio');


INSERT INTO itinerarios (descripcion) VALUES
('Ceremonia y recepción en el mismo lugar'),
('Ceremonia en iglesia y banquete en hotel'),
('Boda en la playa con cena al aire libre'),
('Boda rural con comida tradicional'),
('Boda urbana con cóctel'),
('Ceremonia civil en jardines'),
('Boda de destino en montaña'),
('Elopement íntimo'),
('Ceremonia al atardecer'),
('Brunch nupcial');


INSERT INTO bodas (lugar, fecha, id_itinerario) VALUES
('Hotel Mediterráneo', '2025-06-14', 1),
('Iglesia San Jorge y Hotel Palace', '2025-08-22', 2),
('Playa del Sol', '2025-09-10', 3),
('Casa rural La Encina', '2025-05-03', 4),
('Azotea Centro', '2025-07-19', 5),
('Jardines del Río', '2025-09-27', 6),
('Refugio de Montaña', '2025-10-12', 7),
('Mirador del Faro', '2025-04-26', 8),
('Hacienda Los Olivos', '2025-11-08', 9),
('Café del Parque', '2025-03-15', 10);


INSERT INTO invitados (id_usuario, id_boda, nombre, apellido_1, apellido_2, email, telefono, confirmado, acompanantes_mayores, acompanantes_menores) VALUES
(1, 1, 'Laura','Martínez','Gómez','laura.martinez@example.com','+34611122334', TRUE, 1, 0),
(2, 1, 'Carlos','Pérez','Ruiz','carlos.perez@example.com','+34622233445', FALSE, 0, 0),
(3, 2, 'Ana','Santos',NULL,'ana.santos@example.com','+34633344556', TRUE, 2, 1),
(4, 3, 'Miguel','Rodríguez','López','miguel.rodriguez@example.com','+34644455667', TRUE, 1, 0),
(5, 4, 'Sofía','Navarro','Iglesias','sofia.navarro@example.com','+34655566778', FALSE, 0, 0),
(6, 5, 'Diego','García','Romero','diego.garcia@example.com','+34666677889', TRUE, 0, 0),
(7, 6, 'Elena','Torres','Vega','elena.torres@example.com','+34677788990', FALSE, 1, 1),
(8, 7, 'Javier','Flores','Mora','javier.flores@example.com','+34688899001', TRUE, 0, 0),
(9, 8, 'María','Luna','Prieto','maria.luna@example.com','+34699900112', TRUE, 0, 0),
(10, 9, 'Pablo','Vidal','Serrano','pablo.vidal@example.com','+34700011223', FALSE, 0, 0);


INSERT INTO regalos (id_boda, id_producto, descripcion, enlace_compra, id_usuario, valor, confirmado) VALUES
(1, 2, 'Cafetera para la pareja', 'https://shop.example.com/cafetera', 1, 199.99, TRUE),
(1, 4, 'Copas para brindis', 'https://shop.example.com/copas', 2, 60.00, FALSE),
(2, 1, 'Vajilla principal', 'https://shop.example.com/vajilla', 3, 120.50, TRUE),
(3, 3, 'Toallas de baño', 'https://shop.example.com/toallas', 4, 85.00, TRUE),
(4, 5, 'Robot cocina', 'https://shop.example.com/robot', NULL, 349.00, FALSE),
(5, 6, 'Batidora smoothies', 'https://shop.example.com/batidora', 5, 79.90, TRUE),
(6, 7, 'Sartenes nuevas', 'https://shop.example.com/sartenes', NULL, 99.95, FALSE),
(7, 8, 'Manta para sofá', 'https://shop.example.com/manta', 6, 35.50, TRUE),
(8, 9, 'Cuchillos de chef', 'https://shop.example.com/cuchillos', 7, 89.00, TRUE),
(9, null, 'Hervidor práctico', 'https://shop.example.com/hervidor', NULL, 45.00, FALSE);

INSERT INTO novios (id_usuario, id_boda, nombre, apellido_1, apellido_2, email, telefono) VALUES
(1, 1, 'Laura','Martínez','Gómez','laura.martinez@example.com','+34611122334'),
(2, 2, 'Carlos','Pérez','Ruiz','carlos.perez@example.com','+34622233445'),
(3, 3, 'Ana','Santos',NULL,'ana.santos@example.com','+34633344556'),
(4, 4, 'Miguel','Rodríguez','López','miguel.rodriguez@example.com','+34644455667'),
(5, 5, 'Sofía','Navarro','Iglesias','sofia.navarro@example.com','+34655566778'),
(6, 6, 'Diego','García','Romero','diego.garcia@example.com','+34666677889'),
(7, 7, 'Elena','Torres','Vega','elena.torres@example.com','+34677788990'),
(8, 8, 'Javier','Flores','Mora','javier.flores@example.com','+34688899001'),
(9, 9, 'María','Luna','Prieto','maria.luna@example.com','+34699900112'),
(null, 10, 'Pablo','Vidal','Serrano','pablo.vidal@example.com','+34700011223');