INSERT INTO allergens (name) 
VALUES
('Gluten'),
('Crustáceos'),
('Huevos '),
('Pescado '),
('Cacahuete'),
('Soja'),
('Leche'),
('Frutos de cáscara'),
('Apio'),
('Mostaza'),
('Sésamo'),
('Sulfitos'),
('Altramuces'),
('Moluscos');


INSERT INTO allergens_images (link, image_type, id_allergen) 
VALUES
('https://mywedding.altoyo.es/altramuces.png','png',1),
('https://mywedding.altoyo.es/apio.png','png',2),
('https://mywedding.altoyo.es/cacahuete.png','png',3),
('https://mywedding.altoyo.es/crustaceo.png','png',4),
('https://mywedding.altoyo.es/frutos_cascara.png','png',5),
('https://mywedding.altoyo.es/gluten.png','png',6),
('https://mywedding.altoyo.es/huevos.png','png',7),
('https://mywedding.altoyo.es/leche.png','png',8),
('https://mywedding.altoyo.es/molusco.png','png',9),
('https://mywedding.altoyo.es/mostaza.png','png',10),
('https://mywedding.altoyo.es/pescado.png','png',11),
('https://mywedding.altoyo.es/sesamo.png','png',12),
('https://mywedding.altoyo.es/soja.png','png',13),
('https://mywedding.altoyo.es/sulfitos.png','png',14);

INSERT INTO products (id, name, link, description, product_type, start_date, update_date, price) VALUES
  (2101, 'Robot aspirador Zeta', 'https://shop.example.com/p/robot-zeta', 'Aspirador inteligente con mapeo', 'Hogar',    NOW(), NOW(), 299.99),
  (2102, 'Juego de sartenes Pro', 'https://shop.example.com/p/sartenes-pro', 'Set 5 piezas antiadherentes',   'Cocina',   NOW(), NOW(), 129.95),
  (2103, 'Cafetera Espresso X',   'https://shop.example.com/p/espresso-x',   'Bomba 15 bar, vaporizador',     'Cocina',   NOW(), NOW(), 179.00),
  (2104, 'Tocadiscos Vintage',    'https://shop.example.com/p/vintage',      'Bluetooth y USB',               'Ocio',     NOW(), NOW(), 219.50),
  (2105, 'Juego de toallas Lujo', 'https://shop.example.com/p/toallas',      'Algodón egipcio 8 piezas',      'Baño',     NOW(), NOW(), 89.90);

INSERT INTO images_products (id, link, image_type, product_id) VALUES
  (2201, 'https://cdn.example.com/prod/robot-zeta-1.jpg', 'main', 2101),
  (2202, 'https://cdn.example.com/prod/sartenes-1.jpg',   'main', 2102),
  (2203, 'https://cdn.example.com/prod/espresso-x.jpg',   'main', 2103),
  (2204, 'https://cdn.example.com/prod/vintage.jpg',      'main', 2104),
  (2205, 'https://cdn.example.com/prod/toallas.jpg',      'main', 2105);

INSERT INTO users_app (id, name, first_surname, second_surname, email, phone, password, rol_id) VALUES
  -- Novios boda #3001
  (3101, 'Laura',   'García',   'Santos', 'laura.gs@example.com',   '+34 600000001', '{bcrypt}$2a$10$seed1', 1),
  (3102, 'Diego',   'Pérez',    'López',  'diego.pl@example.com',   '+34 600000002', '{bcrypt}$2a$10$seed2', 1),

  -- Novios boda #3002
  (3103, 'Marta',   'Ruiz',     'Nadal',  'marta.rn@example.com',   '+34 600000003', '{bcrypt}$2a$10$seed3', 1),
  (3104, 'Javier',  'Iglesias', 'Gil',    'javier.ig@example.com',  '+34 600000004', '{bcrypt}$2a$10$seed4', 1),

  -- Novios boda #3003
  (3105, 'Claudia', 'Serrano',  'Vega',   'claudia.sv@example.com', '+34 600000005', '{bcrypt}$2a$10$seed5', 1),
  (3106, 'Álvaro',  'Muñoz',    'Román',  'alvaro.mr@example.com',  '+34 600000006', '{bcrypt}$2a$10$seed6', 1),

  -- Invitados potenciales (algunos invitan/otros no)
  (3110, 'Nuria',   'Castro',   'León',   'nuria.cl@example.com',   '+34 600000010', '{bcrypt}$2a$10$seed7', 1),
  (3111, 'Pedro',   'Suárez',   'Mena',   'pedro.sm@example.com',   '+34 600000011', '{bcrypt}$2a$10$seed8', 1),
  (3112, 'Irene',   'Hidalgo',  'Solís',  'irene.hs@example.com',   '+34 600000012', '{bcrypt}$2a$10$seed9', 1),
  (3113, 'Tomás',   'Vidal',    'Barra',  'tomas.vb@example.com',   '+34 600000013', '{bcrypt}$2a$10$seedA', 1),

  -- USUARIOS SIN BODA Y NO INVITADOS (requisito)
  (3114, 'Sofía',   'Rey',      'Marín',  'sofia.rm@example.com',   '+34 600000014', '{bcrypt}$2a$10$seedB', 1),
  (3115, 'Hugo',    'Cano',     'Arce',   'hugo.ca@example.com',    '+34 600000015', '{bcrypt}$2a$10$seedC', 1),
  (3116, 'Celia',   'Luna',     'Prat',   'celia.lp@example.com',   '+34 600000016', '{bcrypt}$2a$10$seedD', 1),
  (3117, 'ADMIN',   'Site',     'Root',   'admin@example.com',      '+34 600000017', '{bcrypt}$2a$10$seedE', 2);

INSERT INTO weddings (id, dateWedding, place, state_wedding) VALUES
  (3001, '2026-05-17 13:00:00', 'Madrid - Finca El Encinar',  'PREPARING'),
  (3002, '2026-09-12 12:30:00', 'Sevilla - Hacienda La Luz',  'PREPARING'),
  (3003, '2027-06-20 17:00:00', 'Valencia - Jardines del Turia', 'PREPARING');

INSERT INTO events (id, event_type, description, time, wedding_id) VALUES
  -- Boda 3001
  (4101, 'Ceremonia', 'Ceremonia civil en jardines', '2026-05-17 13:00:00', 3001),
  (4102, 'Cóctel',    'Recepción de bienvenida',     '2026-05-17 14:00:00', 3001),
  (4103, 'Banquete',  'Salón principal',             '2026-05-17 15:00:00', 3001),
  (4104, 'Baile',     'Apertura de los novios',      '2026-05-17 18:00:00', 3001),

  -- Boda 3002
  (4110, 'Ceremonia', 'Ceremonia religiosa',         '2026-09-12 12:30:00', 3002),
  (4111, 'Cóctel',    'Patio andaluz',               '2026-09-12 13:30:00', 3002),
  (4112, 'Banquete',  'Hacienda La Luz - Salón A',   '2026-09-12 15:00:00', 3002),

  -- Boda 3003
  (4120, 'Ceremonia', 'Ceremonia civil ribera Turia', '2027-06-20 17:00:00', 3003),
  (4121, 'Banquete',  'Carpa principal',              '2027-06-20 19:00:00', 3003),
  (4122, 'Baile',     'DJ set',                        '2027-06-20 21:00:00', 3003);

INSERT INTO allergies (allergen_id, user_id) VALUES
  (1, 3110), -- Nuria: Lactosa
  (2, 3112), -- Irene: Frutos secos
  (3, 3102); -- Diego (novio): Gluten


INSERT INTO user_weddings (id, email_groom, wedding_id, user_id) VALUES
  -- Boda 3001 (Laura & Diego)
  (6101, 'laura.gs@example.com',  3001, 3101),
  (6102, 'diego.pl@example.com',  3001, 3102),

  -- Boda 3002 (Marta & Javier)
  (6103, 'marta.rn@example.com',  3002, 3103),
  (6104, 'javier.ig@example.com', 3002, 3104),

  -- Boda 3003 (Claudia & Álvaro)
  (6105, 'claudia.sv@example.com', 3003, 3105),
  (6106, 'alvaro.mr@example.com',  3003, 3106);


INSERT INTO users_invitations (id, user_id, wedding_id, confirm, email_invitation, child_acompanion, adult_acompanion) VALUES
  -- Boda 3001
  (7101, 3110, 3001, TRUE,  'nuria.cl@example.com',  0, 1),
  (7102, 3111, 3001, FALSE, 'pedro.sm@example.com',  0, 0),

  -- Boda 3002
  (7103, 3112, 3002, TRUE,  'irene.hs@example.com',  1, 0),
  (7104, 3113, 3002, FALSE, 'tomas.vb@example.com',  0, 1),

  -- Boda 3003
  (7105, 3111, 3003, TRUE,  'pedro.sm@example.com',  0, 0),
  (7106, 3110, 3003, FALSE, 'nuria.cl@example.com',  0, 0);


INSERT INTO presents (id, wedding_id, product_id, user_id, confirm, price) VALUES
  -- Boda 3001
  (8101, 3001, 2101, 3110, TRUE,  299.99),  -- Nuria compra Robot aspirador
  (8102, 3001, 2105, 3111, FALSE,  89.90),  -- Pedro reserva toallas

  -- Boda 3002
  (8103, 3002, 2102, 3112, TRUE,  129.95),  -- Irene compra sartenes
  (8104, 3002, 2103, 3113, FALSE, 179.00),  -- Tomás reserva cafetera

  -- Boda 3003
  (8105, 3003, 2104, 3111, TRUE,  219.50);  -- Pedro compra tocadiscos


