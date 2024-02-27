INSERT INTO roles (name) VALUES ('user'), ('admin');

INSERT INTO users (first_name, last_name, user_name, email, password, born_date, image_url)
VALUES 
('John', 'Doe', 'john_doe', 'john.doe@example.com', 'hashed_password_1', '1990-01-15', 'default-avatar'),
('Jane', 'Smith', 'jane_smith', 'jane.smith@example.com', 'hashed_password_2', '1985-05-22', 'default-avatar'),
('Michael', 'Johnson', 'michael_j', 'michael.j@example.com', 'hashed_password_3', '1992-11-07', 'default-avatar'),
('Emily', 'Anderson', 'emily_a', 'emily.a@example.com', 'hashed_password_4', '1988-03-19', 'default-avatar'),
('Christopher', 'Williams', 'chris_w', 'chris.w@example.com', 'hashed_password_5', '1995-08-12', 'default-avatar'),
('Amanda', 'Miller', 'amanda_m', 'amanda.m@example.com', 'hashed_password_6', '1993-04-25', 'default-avatar'),
('Matthew', 'Brown', 'matt_b', 'matt.b@example.com', 'hashed_password_7', '1987-09-30', 'default-avatar'),
('Olivia', 'Taylor', 'olivia_t', 'olivia.t@example.com', 'hashed_password_8', '1991-02-14', 'image8.jpg'),
('Daniel', 'Martinez', 'daniel_m', 'daniel.m@example.com', 'hashed_password_9', '1986-06-08', 'image9.jpg'),
('Sophia', 'Johnson', 'sophia_j', 'sophia.j@example.com', 'hashed_password_10', '1994-12-03', 'image10.jpg'),
('Ethan', 'Davis', 'ethan_d', 'ethan.d@example.com', 'hashed_password_11', '1989-07-18', 'image11.jpg'),
('Isabella', 'Hernandez', 'isabella_h', 'isabella.h@example.com', 'hashed_password_12', '1996-01-21', 'image12.jpg'),
('Mason', 'Garcia', 'mason_g', 'mason.g@example.com', 'hashed_password_13', '1984-04-14', 'image13.jpg'),
('Amelia', 'Lopez', 'amelia_l', 'amelia.l@example.com', 'hashed_password_14', '1997-09-27', 'image14.jpg'),
('Logan', 'Perez', 'logan_p', 'logan.p@example.com', 'hashed_password_15', '1983-02-10', 'image15.jpg'),
('Ava', 'Moore', 'ava_m', 'ava.m@example.com', 'hashed_password_16', '1998-06-15', 'image16.jpg'),
('Liam', 'Robinson', 'liam_r', 'liam.r@example.com', 'hashed_password_17', '1982-11-28', 'image17.jpg'),
('Harper', 'Turner', 'harper_t', 'harper.t@example.com', 'hashed_password_18', '1999-04-03', 'image18.jpg'),
('Noah', 'Baker', 'noah_b', 'noah.b@example.com', 'hashed_password_19', '1981-08-26', 'image19.jpg'),
('Chloe', 'Fisher', 'chloe_f', 'chloe.f@example.com', 'hashed_password_20', '2000-01-09', 'image20.jpg'),
('Juan Daniel', 'Forner Garriga', 'juvndv', 'jdanielforga@gmail.com', '12345678', '1999-11-23', 'default-avatar'),
('Natalia', 'Risueño', 'vvnat', 'natalia@gmail.com', '12345678', '1999-11-23', 'default-avatar'),
('Alex', 'Iglesias', 'Alexys2414', 'alexis@gmail.com', '12345678', '1999-11-23', 'default-avatar'),
('Julio', 'Cuevas', 'julius', 'julio@gmail.com', '12345678', '1999-11-23', 'default-avatar');

INSERT INTO users (first_name, last_name, user_name, email, password, born_date, image_url, role_id) VALUES
('Admin', 'Admin', 'admin', 'admin@admin.admin', 'admin', '2000-01-01', 'admin.jpg', 2);

update users set image_url = 'default-avatar';

INSERT INTO categories (name, description, image_url)
VALUES 
('Electrónicos', 'Productos electrónicos y gadgets modernos', 'electronica'),
('Ropa', 'Última moda en ropa y accesorios', 'ropa'),
('Hogar y Jardín', 'Artículos para el hogar y decoración', 'hogar-jardin'),
('Deportes y Aire Libre', 'Equipos y artículos deportivos', 'deportes-aire-libre'),
('Libros y Películas', 'Libros, películas y entretenimiento', 'libros-peliculas'),
('Salud y Belleza', 'Productos para el cuidado personal', 'salud-belleza'),
('Juguetes y Juegos', 'Juguetes y juegos para todas las edades', 'juguetes-juegos'),
('Automóviles', 'Accesorios y productos para automóviles', 'automoviles'),
('Alimentos y Bebidas', 'Productos alimenticios y bebidas', 'alimentos-bebidas'),
('Electrodomésticos', 'Electrodomésticos para el hogar', 'electrodomesticos');

-- Subastas para la categoría 'Electrónicos' (category_id = 1)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES 
('iPhone 13', 'Subasta del último modelo de iPhone', 1, 1, '2022-01-20 12:00:00', '2023-01-20 12:00:00', 'iphone13.jpg', 800.00),
('Smart TV 4K', 'Televisor inteligente con resolución 4K', 1, 2, '2022-03-01 10:00:00', '2024-03-01 10:00:00', 'smart_tv.jpg', 600.00),
('Cámara DSLR', 'Cámara profesional de lentes intercambiables', 1, 3, '2025-02-10 15:30:00', '2025-03-10 15:30:00', 'camara_dslr.jpg', 400.00);

-- Subastas para la categoría 'Ropa' (category_id = 2)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES 
('Abrigo de Invierno', 'Abrigo cálido para el invierno', 2, 11, '2021-12-15 08:00:00', '2022-12-15 08:00:00', 'abrigo_invierno.jpg', 80.00),
('Vestido de Fiesta', 'Elegante vestido para ocasiones especiales', 2, 12, '2021-11-05 14:45:00', '2023-11-05 14:45:00', 'vestido_fiesta.jpg', 120.00),
('Zapatos Deportivos', 'Calzado cómodo y deportivo', 2, 13, '2024-01-30 18:20:00', '2024-02-28 18:20:00', 'zapatos_deportivos.jpg', 50.00);

-- Subastas para la categoría 'Hogar y Jardín' (category_id = 3)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES 
('Set de Muebles de Patio', 'Conjunto de muebles para el jardín', 3, 1, '2022-04-10 11:30:00', '2023-04-10 11:30:00', 'muebles_patio.jpg', 300.00),
('Cortinas Modernas', 'Cortinas elegantes para el hogar', 3, 2, '2023-06-20 09:15:00', '2023-07-20 09:15:00', 'cortinas_modernas.jpg', 80.00),
('Juego de Sábanas', 'Sábanas suaves y de alta calidad', 3, 3, '2022-11-30 14:00:00', '2022-12-30 14:00:00', 'sabanas.jpg', 50.00);

-- Subastas para la categoría 'Deportes y Aire Libre' (category_id = 4)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES 
('Bicicleta de Montaña', 'Bicicleta robusta para aventuras en la montaña', 4, 11, '2022-09-01 17:45:00', '2023-09-01 17:45:00', 'bicicleta_montana.jpg', 250.00),
('Tienda de Campaña', 'Tienda espaciosa para acampar al aire libre', 4, 12, '2021-10-25 20:30:00', '2022-10-25 20:30:00', 'tienda_campamento.jpg', 120.00),
('Kayak Inflable', 'Kayak fácil de transportar e inflable', 4, 13, '2023-12-05 22:00:00', '2024-01-05 22:00:00', 'kayak_inflable.jpg', 180.00);

-- Subastas para la categoría 'Libros y Películas' (category_id = 5)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES 
('Colección de Libros Clásicos', 'Pack de libros clásicos de la literatura', 5, 1, '2021-12-01 14:00:00', '2022-12-01 14:00:00', 'libros_clasicos.jpg', 40.00),
('Películas en Blu-ray', 'Colección de películas en alta definición', 5, 2, '2021-11-15 11:30:00', '2023-11-15 11:30:00', 'peliculas_blu_ray.jpg', 30.00),
('Libro de Cocina Gourmet', 'Libro con recetas gourmet y consejos culinarios', 5, 3, '2024-02-05 16:45:00', '2024-03-05 16:45:00', 'libro_cocina_gourmet.jpg', 20.00);

-- Pone la foto de todas las subastas en default-auction

-- Inserts para pujas
INSERT INTO bids (user_id, auction_id, amount)
VALUES
    (1, 15, 1000);

-- Inserts para notificaciones en el año siguiente (2025)
INSERT INTO notifications (subject, message, send_on, category_id)
VALUES 
('Oferta Especial', '¡No te pierdas nuestra oferta exclusiva!', '2025-01-01 08:00:00', 1),
('Recordatorio de Subasta', 'La subasta de electrónicos está por finalizar.', '2025-02-15 12:30:00', 1),
('Feliz Cumpleaños', '¡Feliz cumpleaños! Esperamos que tengas un día increíble.', '2025-04-05 09:45:00', 3),
('Actualización de Cuenta', 'Hemos realizado mejoras en tu cuenta. ¡Échales un vistazo!', '2025-06-20 15:00:00', 5),
('Evento Especial', 'Te invitamos a nuestro evento exclusivo el próximo mes.', '2025-08-10 18:30:00', 2),
('Promoción Especial', 'Aprovecha nuestra promoción especial por tiempo limitado.', '2025-09-25 11:15:00',4),
('Noticias de Producto', 'Nuevos productos disponibles en nuestra tienda.', '2025-11-03 14:20:00', 2),
('Encuesta de Satisfacción', 'Queremos conocer tu opinión. Participa en nuestra encuesta.', '2025-12-15 10:00:00', 8),
('Descuento Exclusivo', 'Recibe un descuento exclusivo en tu próxima compra.', '2025-12-28 16:45:00',7),
('Agradecimiento', 'Gracias por ser parte de nuestra comunidad. ¡Te apreciamos!', '2025-12-31 22:00:00',9);

-- Inserts para incidencias con la mitad resueltas (is_solved = true)
INSERT INTO issues (user_id, subject, message, is_solved)
VALUES 
(1, 'Problema con la cuenta', 'No puedo acceder a mi cuenta.', true),
(5, 'Error en la aplicación móvil', 'La aplicación se cierra inesperadamente.', false),
(9, 'Problema de facturación', 'Cargos incorrectos en mi factura.', true),
(12, 'Fallo en el servicio', 'No puedo acceder a ciertas funciones del servicio.', false),
(15, 'Solicitud de reembolso', 'Quiero solicitar un reembolso para mi compra.', true),
(18, 'Inconveniente con el envío', 'No he recibido mi pedido a tiempo.', false),
(3, 'Problema técnico', 'Problemas técnicos al cargar imágenes.', true),
(7, 'Consulta sobre productos', 'Necesito información adicional sobre ciertos productos.', false),
(11, 'Queja sobre atención al cliente', 'Experiencia insatisfactoria con el servicio al cliente.', true),
(19, 'Error en la página de pago', 'No puedo completar la compra debido a un error en la página de pago.', false);

-- Inserts para promociones, la mitad con fecha ends_at pasada y la otra el año que viene
INSERT INTO promotions (code, ends_at, amount)
VALUES 
('DESC10', '2023-12-31 23:59:59', 10.00),  -- Termina el año pasado
('FEB20', '2024-02-28 23:59:59', 20.00),  -- Termina en febrero del próximo año
('HALFOFF', '2024-06-30 23:59:59', 50.00), -- Termina en junio del próximo año
('SPECIAL25', '2025-01-31 23:59:59', 25.00), -- Termina en enero del próximo año
('SUMMER15', '2025-07-31 23:59:59', 15.00), -- Termina en julio del próximo año
('EARLYBIRD', '2025-11-30 23:59:59', 30.00), -- Termina en noviembre del próximo año
('WINTER5', '2025-12-15 23:59:59', 5.00),  -- Termina en diciembre del próximo año
('FLASHSALE', '2026-01-15 23:59:59', 30.00), -- Termina el próximo año
('SPRING10', '2026-04-30 23:59:59', 10.00), -- Termina en abril del próximo año
('LASTCHANCE', '2026-12-31 23:59:59', 15.00); -- Termina el próximo año

-- Usuarios que gastan algunos códigos promocionales
INSERT INTO users_promotions (promotion_id, user_id)
VALUES 
(1, 3),  -- Usuario 3 utiliza el código DESC10
(3, 8),  -- Usuario 8 utiliza el código HALFOFF
(2, 12), -- Usuario 12 utiliza el código FEB20
(5, 17), -- Usuario 17 utiliza el código SUMMER15
(4, 19), -- Usuario 19 utiliza el código SPECIAL25
(6, 5),  -- Usuario 5 utiliza el código EARLYBIRD
(7, 10), -- Usuario 10 utiliza el código WINTER5
(9, 15), -- Usuario 15 utiliza el código SPRING10
(8, 7),  -- Usuario 7 utiliza el código FLASHSALE
(10, 11);-- Usuario 11 utiliza el código LASTCHANCE

-- Inserts para preferencias de categorías
INSERT INTO categories_preferences (user_id, category_id)
VALUES
  (1, 1), (1, 3), (1, 5),
  (2, 2), (2, 4),
  (3, 1),
  (4, 2), (4, 3), (4, 5),
  (5, 4), (5, 5);

-- Subastas adicionales para la categoría 'Electrónicos' (category_id = 1)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES
('MacBook Pro', 'Potente portátil para profesionales', 1, 2, '2024-06-15 09:00:00', '2025-06-15 09:00:00', 'macbook_pro.jpg', 1200.00),
('Altavoces Bluetooth', 'Altavoces portátiles con conectividad Bluetooth', 1, 3, '2024-08-20 14:30:00', '2024-09-20 14:30:00', 'altavoces_bluetooth.jpg', 100.00),
('Drone DJI Mavic Air', 'Drone compacto con cámara de alta calidad', 1, 1, '2025-01-10 11:00:00', '2025-02-10 11:00:00', 'drone_dji_mavic_air.jpg', 500.00);

-- Subastas adicionales para la categoría 'Ropa' (category_id = 2)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES
('Traje Formal', 'Traje elegante para ocasiones especiales', 2, 12, '2023-05-05 10:00:00', '2024-05-05 10:00:00', 'traje_formal.jpg', 150.00),
('Camisetas de Diseñador', 'Camisetas exclusivas de diseñadores famosos', 2, 13, '2024-09-15 12:00:00', '2024-10-15 12:00:00', 'camisetas_disenador.jpg', 70.00),
('Sudadera con Capucha', 'Sudadera cómoda y moderna', 2, 11, '2023-03-20 08:30:00', '2023-04-20 08:30:00', 'sudadera_capucha.jpg', 40.00);

-- Subastas adicionales para la categoría 'Hogar y Jardín' (category_id = 3)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES
('Robot Aspirador', 'Aspiradora inteligente para limpieza automática', 3, 1, '2024-10-10 10:30:00', '2025-10-10 10:30:00', 'robot_aspirador.jpg', 200.00),
('Set de Ollas y Sartenes', 'Juego completo de cocina de alta calidad', 3, 2, '2023-09-01 09:00:00', '2024-09-01 09:00:00', 'ollas_sartenes.jpg', 100.00),
('Lámpara de Pie Moderna', 'Lámpara elegante para iluminación ambiental', 3, 3, '2024-03-15 14:00:00', '2024-04-15 14:00:00', 'lampara_pie.jpg', 80.00);

-- Subastas adicionales para la categoría 'Deportes y Aire Libre' (category_id = 4)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES
('Patineta Eléctrica', 'Patineta con motor eléctrico para movilidad urbana', 4, 13, '2023-07-05 16:00:00', '2024-07-05 16:00:00', 'patineta_electrica.jpg', 150.00),
('Piscina Inflable', 'Piscina portátil para diversión en el jardín', 4, 11, '2024-05-20 11:00:00', '2024-06-20 11:00:00', 'piscina_inflable.jpg', 80.00),
('Raquetas de Tenis', 'Set de raquetas de tenis profesionales', 4, 12, '2023-08-15 09:30:00', '2024-08-15 09:30:00', 'raquetas_tenis.jpg', 60.00);

-- Subastas adicionales para la categoría 'Libros y Películas' (category_id = 5)
INSERT INTO auctions (auction_name, auction_description, category_id, user_id, starts_at, ends_at, image_url, initial_price)
VALUES
('Colección de Novelas', 'Pack de novelas contemporáneas', 5, 2, '2023-04-10 13:00:00', '2024-04-10 13:00:00', 'coleccion_novelas.jpg', 50.00),
('Documentales en DVD', 'Selección de documentales fascinantes', 5, 3, '2024-06-01 10:00:00', '2025-06-01 10:00:00', 'documentales_dvd.jpg', 25.00),
('Guía de Viajes', 'Libro con consejos y destinos para viajeros', 5, 1, '2024-01-15 09:00:00', '2025-01-15 09:00:00', 'guia_viajes.jpg', 30.00);

UPDATE auctions set image_url = 'default-auction';

INSERT INTO bids(auction_id, user_id, amount)
VALUES
(2, 21, 1),
(2, 22, 2),
(2, 23, 3),
(2, 24, 4);
