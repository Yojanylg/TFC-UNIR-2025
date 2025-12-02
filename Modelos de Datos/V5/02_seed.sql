INSERT INTO weddings (id, date_wedding, place, state_wedding) VALUES
  (3001, '2026-05-17 13:00:00', 'Madrid - Finca El Encinar',  'PREPARING'),
  (3002, '2026-09-12 12:30:00', 'Sevilla - Hacienda La Luz',  'PREPARING'),
  (3003, '2027-06-20 17:00:00', 'Valencia - Jardines del Turia', 'PREPARING');

INSERT INTO roles (name) VALUES
('ROLE_ADMIN'),
('ROLE_USER');


INSERT INTO products (id, name, link, description, product_type, start_date, update_date, price) VALUES
  (1, 'Robot aspirador Zeta', 'https://shop.example.com/p/robot-zeta', 'Aspirador inteligente con mapeo', 'Hogar',    NOW(), NOW(), 299.99),
  (2, 'Juego de sartenes Pro', 'https://shop.example.com/p/sartenes-pro', 'Set 5 piezas antiadherentes',   'Cocina',   NOW(), NOW(), 129.95),
  (3, 'Cafetera Espresso X',   'https://shop.example.com/p/espresso-x',   'Bomba 15 bar, vaporizador',     'Cocina',   NOW(), NOW(), 179.00),
  (4, 'Tocadiscos Vintage',    'https://shop.example.com/p/vintage',      'Bluetooth y USB',               'Ocio',     NOW(), NOW(), 219.50),
  (5, 'Juego de toallas Lujo', 'https://shop.example.com/p/toallas',      'Algodón egipcio 8 piezas',      'Baño',     NOW(), NOW(), 89.90);

INSERT INTO images_products (id, link, image_type, product_id) VALUES
  (1, 'https://cdn.example.com/prod/robot-zeta-1.jpg', 'main', 1),
  (2, 'https://cdn.example.com/prod/sartenes-1.jpg',   'main', 2),
  (3, 'https://cdn.example.com/prod/espresso-x.jpg',   'main', 3),
  (4, 'https://cdn.example.com/prod/vintage.jpg',      'main', 4),
  (5, 'https://cdn.example.com/prod/toallas.jpg',      'main', 5);

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

  -- USUARIOS SIN BODA Y NO INVITADOS
  (3114, 'Sofía',   'Rey',      'Marín',  'sofia.rm@example.com',   '+34 600000014', '{bcrypt}$2a$10$seedB', 1),
  (3115, 'Hugo',    'Cano',     'Arce',   'hugo.ca@example.com',    '+34 600000015', '{bcrypt}$2a$10$seedC', 1),
  (3116, 'Celia',   'Luna',     'Prat',   'celia.lp@example.com',   '+34 600000016', '{bcrypt}$2a$10$seedD', 1),
  (3117, 'ADMIN',   'Site',     'Root',   'admin@example.com',      '+34 600000017', '{bcrypt}$2a$10$seedE', 2);



INSERT INTO events (id, event_type, description, time, wedding_id) VALUES
  -- Boda 3001
  (4101, 'CEREMONY', 'Ceremonia civil en jardines', '2026-05-17 13:00:00', 3001),
  (4102, 'COCKTAIL',    'Recepción de bienvenida',     '2026-05-17 14:00:00', 3001),
  (4103, 'BANQUET',  'Salón principal',             '2026-05-17 15:00:00', 3001),
  (4104, 'PARTY',     'Apertura de los novios',      '2026-05-17 18:00:00', 3001),

  -- Boda 3002
  (4110, 'CEREMONY', 'Ceremonia religiosa',         '2026-09-12 12:30:00', 3002),
  (4111, 'COCKTAIL',    'Patio andaluz',               '2026-09-12 13:30:00', 3002),
  (4112, 'BANQUET',  'Hacienda La Luz - Salón A',   '2026-09-12 15:00:00', 3002),

  -- Boda 3003
  (4120, 'CEREMONY', 'Ceremonia civil ribera Turia', '2027-06-20 17:00:00', 3003),
  (4121, 'COCKTAIL',  'Carpa principal',              '2027-06-20 19:00:00', 3003),
  (4122, 'BANQUET',     'DJ set',                        '2027-06-20 21:00:00', 3003);



INSERT INTO users_weddings (id, email_groom, wedding_id, user_id) VALUES
  -- Boda 3001 (Laura & Diego)
  (6101, 'laura.gs@example.com',  3001, 3101),
  (6102, 'diego.pl@example.com',  3001, 3102),

  -- Boda 3002 (Marta & Javier)
  (6103, 'marta.rn@example.com',  3002, 3103),
  (6104, 'javier.ig@example.com', 3002, 3104),

  -- Boda 3003 (Claudia & Álvaro)
  (6105, 'claudia.sv@example.com', 3003, 3105),
  (6106, 'alvaro.mr@example.com',  3003, 3106);


INSERT INTO users_invitations (id, user_id, wedding_id, confirm, email_invitation) VALUES
  -- Boda 3001
  (7101, 3110, 3001, TRUE,  'nuria.cl@example.com'),
  (7102, 3111, 3001, FALSE, 'pedro.sm@example.com'),

  -- Boda 3002
  (7103, 3112, 3002, TRUE,  'irene.hs@example.com'),
  (7104, 3113, 3002, FALSE, 'tomas.vb@example.com'),

  -- Boda 3003
  (7105, 3111, 3003, TRUE,  'pedro.sm@example.com'),
  (7106, 3110, 3003, FALSE, 'nuria.cl@example.com');


INSERT INTO presents (id, wedding_id, product_id, user_id, confirm, price) VALUES
  -- Boda 3001
  (8101, 3001, 1, 3110, TRUE,  299.99),  -- Nuria compra Robot aspirador
  (8102, 3001, 2, 3111, FALSE,  89.90),  -- Pedro reserva toallas

  -- Boda 3002
  (8103, 3002, 2, 3112, TRUE,  129.95),  -- Irene compra sartenes
  (8104, 3002, 3, 3113, FALSE, 179.00),  -- Tomás reserva cafetera

  -- Boda 3003
  (8105, 3003, 4, 3111, TRUE,  219.50);  -- Pedro compra tocadiscos


