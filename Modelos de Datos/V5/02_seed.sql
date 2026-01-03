USE mywedding;

INSERT INTO roles (name) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

INSERT INTO users_app (id, name, first_surname, second_surname, email, phone, password, rol_id) VALUES
(1,  'Laura', 'Lopez', 'García', 'laura@example.com',  '+34 600 111 222', 'hash_demo_1', 2),
(2,  'Jorge', 'Lobo', 'Puig', 'jorge.lobo@example.com', '+34 600 333 444', 'hash_demo_2', 2),
(3,  'Marco',  'Ruiz', NULL, 'marco.ruiz@example.com', '+34 600 555 666', 'hash_demo_3', 2),

(10, 'Lucía',  'Santos', 'Pérez', 'lucia.santos@example.com', '+34 611 000 010', 'hash_demo_10', 1),
(11, 'Mario',  'Navarro', 'Ruiz', 'mario.navarro@example.com', '+34 611 000 011', 'hash_demo_11', 1),
(12, 'Elena',  'Vega',   'Díaz',  'elena.vega@example.com',   '+34 611 000 012', 'hash_demo_12', 1),
(13, 'Hugo',   'Campos', 'López', 'hugo.campos@example.com',  '+34 611 000 013', 'hash_demo_13', 1),
(14, 'Sara',   'Moreno', 'Iglesias', 'sara.moreno@example.com','+34 611 000 014', 'hash_demo_14', 1);

INSERT INTO allergies (
  id, user_id,
  gluten, crustaceos, huevos, pescado, cacahuete, soja, leche,
  frutos_cascara, apio, mostaza, sesamo, sulfitos, altramuces, moluscos
) VALUES
(1, 10, true,  false, false, false, false, false, true,  false, false, false, false, false, false, false),
(2, 11, false, true,  false, false, false, false, false, false, false, false, false, false, false, true ),
(3, 12, false, false, true,  false, false, false, false, false, false, false, false, false, false, false),
(4, 13, false, false, false, true,  false, false, false, false, false, false, false, false, false, false);

INSERT INTO weddings (id, date_wedding, place, state_wedding) VALUES
(100, '2026-06-20', 'Madrid - Jardines del Retiro', 'PREPARING'),
(101, '2026-09-12', 'Valencia - Albufera',         'PREPARING');

INSERT INTO users_weddings (id, email_groom, wedding_id, user_id) VALUES
(1000, 'lucia.santos@example.com', 100, 10), -- novia registrada
(1001, 'mario.navarro@example.com',100, 11), -- novio registrado
(1002, 'elena.vega@example.com',   101, 12), -- novia registrada
(1003, 'no_registrado.groom@example.com', 101, NULL); -- novio no registrado (solo email)

INSERT INTO events (id, event_type, description, time, wedding_id) VALUES
(2000, 'CEREMONY',  'Ceremonia civil en el jardín principal', '12:30', 100),
(2001, 'COCKTAIL',  'Cóctel de bienvenida',                   '14:00', 100),
(2002, 'BANQUET',   'Banquete y brindis',                     '15:30', 100),
(2003, 'PARTY',     'Fiesta con DJ',                          '18:00', 100),

(2010, 'CEREMONY',  'Ceremonia junto al lago',                '13:00', 101),
(2011, 'BANQUET',   'Paella y menú mediterráneo',             '15:00', 101),
(2012, 'PARTY',     'Fiesta y barra libre',                   '19:00', 101);

INSERT INTO users_invitations (id, user_id, wedding_id, confirm, notified, email_invitation) VALUES
(3000, 12,   100, true,  true,  'elena.vega@example.com'),
(3001, 13,   100, false, true,  'hugo.campos@example.com'),
(3002, 14,   100, true,  false, 'sara.moreno@example.com'),
(3003, NULL, 100, false, true,  'invitado.no.registrado1@example.com'),

(3010, 10,   101, true,  true,  'lucia.santos@example.com'),
(3011, 11,   101, true,  true,  'mario.navarro@example.com'),
(3012, NULL, 101, false, false, 'invitado.no.registrado2@example.com');

INSERT INTO companions (id, user_invitation_id, name, first_surname, second_surname, email, adult, allergies) VALUES
(4000, 3002, 'Daniel', 'Ramos', NULL, 'daniel.ramos@example.com', true,  'Sin alergias'),
(4001, 3000, 'Paula',  'Serrano', 'Gil', 'paula.serrano@example.com', true, 'Gluten'),
(4002, 3003, 'Nora',   'Martín', NULL, 'nora.martin@example.com', false, 'Leche');

INSERT INTO products (id, name, link, description, product_type, start_date, update_date, price) VALUES
  (7000, 'Robot aspirador Zeta', 'https://shop.example.com/p/robot-zeta', 'Aspirador inteligente con mapeo', 'CATALOG', '2025-11-19', '2026-01-02', 299.99),
  (7001, 'Juego de sartenes Pro', 'https://shop.example.com/p/sartenes-pro', 'Set 5 piezas antiadherentes',   'CATALOG',   '2025-11-19', '2026-01-02', 129.95),
  (7003, 'Cafetera Espresso X',   'https://shop.example.com/p/espresso-x',   'Bomba 15 bar, vaporizador',     'CATALOG',   '2025-11-19', '2026-01-02', 179.00),
  (7004, 'Tocadiscos Vintage',    'https://shop.example.com/p/vintage',      'Bluetooth y USB',               'CATALOG',     '2025-11-19', '2026-01-02', 219.50),
  (7005, 'Juego de toallas Lujo', 'https://shop.example.com/p/toallas',      'Algodón egipcio 8 piezas',      'CATALOG',     '2025-11-19', '2026-01-02', 89.90);

