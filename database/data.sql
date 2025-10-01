USE Happy_Feet;

-- ========================
-- TABLAS DE CATALOGO
-- ========================

-- Tipo de producto
INSERT INTO producto_tipo (nombre) VALUES 
('Medicamento'),
('Vacuna'),
('Insumo Medico'),
('Alimento'),
('Otro');

-- Tipo de evento medico
INSERT INTO evento_tipo (nombre) VALUES 
('Vacunacion'),
('Consulta'),
('Cirugia'),
('Desparasitacion'),
('Control'),
('Emergencia'),
('Hospitalizacion');

-- Estado de cita
INSERT INTO cita_estado (nombre) VALUES 
('Programada'),
('En Proceso'),
('Finalizada'),
('Cancelada'),
('No Asistio');

-- ========================
-- PROVEEDORES
-- ========================

INSERT INTO proveedor (nombre_empresa, contacto, telefono, email, direccion) VALUES
('VetSupply Colombia', 'Carlos Martinez', '3101234567', 'contacto@vetsupply.com', 'Calle 45 #23-15 Bogota'),
('PetPharma SAS', 'Laura Gomez', '3209876543', 'ventas@petpharma.com', 'Carrera 10 #30-20 Medellin'),
('AnimalCare Ltda', 'Roberto Diaz', '3156789012', 'info@animalcare.com', 'Avenida 15 #50-30 Cali'),
('VetMed Colombia', 'Sofia Ramirez', '3187654321', 'pedidos@vetmed.com', 'Calle 80 #12-45 Barranquilla');

-- ========================
-- DUENOS
-- ========================

INSERT INTO dueno (nombre_completo, documento_identidad, direccion, telefono, email) VALUES
('Maria Lopez Garcia', '1098765432', 'Calle 12 #34-56 Floridablanca', '3201234567', 'maria.lopez@email.com'),
('Juan Carlos Ruiz', '1087654321', 'Carrera 27 #15-30 Bucaramanga', '3109876543', 'juan.ruiz@email.com'),
('Ana Sofia Moreno', '1076543210', 'Avenida Quebrada Seca #45-20', '3156789012', 'ana.moreno@email.com'),
('Pedro Sanchez Torres', '1065432109', 'Calle 36 #10-15 Giron', '3187654321', 'pedro.sanchez@email.com'),
('Lucia Fernandez Castro', '1054321098', 'Carrera 33 #25-40 Piedecuesta', '3145678901', 'lucia.fernandez@email.com'),
('Carlos Alberto Diaz', '1043210987', 'Calle 52 #18-25 Floridablanca', '3134567890', 'carlos.diaz@email.com'),
('Diana Patricia Gomez', '1032109876', 'Avenida Gonzalez Valencia #30-10', '3123456789', 'diana.gomez@email.com'),
('Roberto Martinez Silva', '1021098765', 'Calle 45 #22-35 Bucaramanga', '3112345678', 'roberto.martinez@email.com');

-- ========================
-- VETERINARIOS
-- ========================

INSERT INTO veterinario (nombre_completo, especialidad, telefono, email) VALUES
('Dr. Andres Felipe Paez', 'Cirugia General', '3201112233', 'dr.paez@happyfeet.com'),
('Dra. Carolina Mendoza', 'Medicina Interna', '3202223344', 'dra.mendoza@happyfeet.com'),
('Dr. Miguel Angel Rojas', 'Dermatologia', '3203334455', 'dr.rojas@happyfeet.com'),
('Dra. Paula Andrea Vargas', 'Cardiologia', '3204445566', 'dra.vargas@happyfeet.com'),
('Dr. Luis Eduardo Santos', 'Medicina General', '3205556677', 'dr.santos@happyfeet.com');

-- ========================
-- ESPECIES Y RAZAS
-- ========================

INSERT INTO especie (nombre) VALUES 
('Perro'),
('Gato'),
('Ave'),
('Conejo'),
('Hamster');

-- Razas de Perro (especie_id = 1)
INSERT INTO raza (especie_id, nombre) VALUES
(1, 'Labrador Retriever'),
(1, 'Golden Retriever'),
(1, 'Pastor Aleman'),
(1, 'Bulldog Frances'),
(1, 'Chihuahua'),
(1, 'Beagle'),
(1, 'Poodle'),
(1, 'Mestizo'),
(1, 'Schnauzer'),
(1, 'Boxer');

-- Razas de Gato (especie_id = 2)
INSERT INTO raza (especie_id, nombre) VALUES
(2, 'Persa'),
(2, 'Siames'),
(2, 'Angora'),
(2, 'British Shorthair'),
(2, 'Maine Coon'),
(2, 'Domestico Pelo Corto'),
(2, 'Bengala'),
(2, 'Ragdoll');

-- Razas de Ave (especie_id = 3)
INSERT INTO raza (especie_id, nombre) VALUES
(3, 'Periquito Australiano'),
(3, 'Canario'),
(3, 'Loro Amazonas'),
(3, 'Cacatua'),
(3, 'Agapornis');

-- Razas de Conejo (especie_id = 4)
INSERT INTO raza (especie_id, nombre) VALUES
(4, 'Holandes'),
(4, 'Cabeza de Leon'),
(4, 'Mini Lop'),
(4, 'Rex');

-- Razas de Hamster (especie_id = 5)
INSERT INTO raza (especie_id, nombre) VALUES
(5, 'Sirio'),
(5, 'Ruso'),
(5, 'Roborovski');

-- ========================
-- MASCOTAS
-- ========================

-- Perros
INSERT INTO mascota (dueno_id, raza_id, nombre, fecha_nacimiento, especie_id, sexo, microchip, estado) VALUES
(1, 1, 'Max', '2020-03-15', 1, 'Macho', 'MC001234567890', 'ACTIVA'),
(1, 2, 'Luna', '2021-07-20', 1, 'Hembra', 'MC001234567891', 'ACTIVA'),
(2, 3, 'Rocky', '2019-05-10', 1, 'Macho', 'MC001234567892', 'ACTIVA'),
(2, 4, 'Coco', '2022-01-08', 1, 'Macho', 'MC001234567893', 'ACTIVA'),
(3, 5, 'Toby', '2021-11-25', 1, 'Macho', 'MC001234567894', 'ACTIVA'),
(4, 6, 'Bella', '2020-09-14', 1, 'Hembra', 'MC001234567895', 'ACTIVA'),
(5, 7, 'Nala', '2022-04-30', 1, 'Hembra', 'MC001234567896', 'ACTIVA'),
(6, 8, 'Bruno', '2019-12-05', 1, 'Macho', 'MC001234567897', 'ACTIVA'),
(7, 9, 'Chispa', '2021-06-18', 1, 'Hembra', 'MC001234567898', 'ACTIVA'),
(8, 10, 'Zeus', '2020-08-22', 1, 'Macho', 'MC001234567899', 'ACTIVA');

-- Gatos
INSERT INTO mascota (dueno_id, raza_id, nombre, fecha_nacimiento, especie_id, sexo, microchip, estado) VALUES
(1, 11, 'Michi', '2021-02-14', 2, 'Macho', 'MC001234567900', 'ACTIVA'),
(2, 12, 'Mia', '2020-10-07', 2, 'Hembra', 'MC001234567901', 'ACTIVA'),
(3, 13, 'Garfield', '2022-03-20', 2, 'Macho', 'MC001234567902', 'ACTIVA'),
(4, 14, 'Luna', '2021-05-12', 2, 'Hembra', 'MC001234567903', 'ACTIVA'),
(5, 15, 'Simba', '2020-07-25', 2, 'Macho', 'MC001234567904', 'ACTIVA'),
(6, 16, 'Nieve', '2022-01-30', 2, 'Hembra', 'MC001234567905', 'ACTIVA'),
(7, 17, 'Tigre', '2021-09-08', 2, 'Macho', 'MC001234567906', 'ACTIVA'),
(8, 18, 'Pelusa', '2020-11-15', 2, 'Hembra', 'MC001234567907', 'ACTIVA');

-- Aves
INSERT INTO mascota (dueno_id, raza_id, nombre, fecha_nacimiento, especie_id, sexo, estado) VALUES
(1, 19, 'Piolín', '2022-06-10', 3, 'Macho', 'ACTIVA'),
(3, 20, 'Canela', '2021-08-20', 3, 'Hembra', 'ACTIVA'),
(5, 21, 'Loro', '2020-04-15', 3, 'Macho', 'ACTIVA'),
(7, 22, 'Blanca', '2021-12-01', 3, 'Hembra', 'ACTIVA');

-- Conejos
INSERT INTO mascota (dueno_id, raza_id, nombre, fecha_nacimiento, especie_id, sexo, estado) VALUES
(2, 23, 'Tambor', '2022-02-14', 4, 'Macho', 'ACTIVA'),
(4, 24, 'Copito', '2021-10-20', 4, 'Macho', 'ACTIVA'),
(6, 25, 'Pelusa', '2022-05-08', 4, 'Hembra', 'ACTIVA');

-- Hamsters
INSERT INTO mascota (dueno_id, raza_id, nombre, fecha_nacimiento, especie_id, sexo, estado) VALUES
(3, 26, 'Hammy', '2023-01-15', 5, 'Macho', 'ACTIVA'),
(8, 27, 'Bola', '2022-11-20', 5, 'Hembra', 'ACTIVA');

-- ========================
-- INVENTARIO
-- ========================

-- Medicamentos
INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, proveedor_id) VALUES
('Amoxicilina 500mg', 1, 'Antibiotico de amplio espectro', 'VetLab', 'LOT2024-001', 150, 30, '2025-12-31', 15000.00, 1),
('Carprofeno 75mg', 1, 'Antiinflamatorio no esteroideo', 'PharmaVet', 'LOT2024-002', 100, 20, '2025-10-15', 25000.00, 2),
('Metronidazol 250mg', 1, 'Antiparasitario y antibacteriano', 'VetMed', 'LOT2024-003', 80, 15, '2025-11-30', 18000.00, 1),
('Prednisolona 5mg', 1, 'Corticoide antiinflamatorio', 'AnimalPharma', 'LOT2024-004', 120, 25, '2026-01-20', 20000.00, 3),
('Omeprazol 20mg', 1, 'Protector gastrico', 'VetCare', 'LOT2024-005', 90, 20, '2025-09-30', 22000.00, 2);

-- Vacunas
INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, proveedor_id) VALUES
('Vacuna Quintuple Canina', 2, 'Proteccion contra 5 enfermedades', 'BioVet', 'VAC2024-001', 60, 15, '2025-08-31', 45000.00, 1),
('Vacuna Antirrabica', 2, 'Prevencion de rabia', 'GlobalVet', 'VAC2024-002', 80, 20, '2025-12-15', 35000.00, 2),
('Vacuna Triple Felina', 2, 'Proteccion para gatos', 'FelineVet', 'VAC2024-003', 50, 12, '2025-10-20', 40000.00, 3),
('Vacuna Leucemia Felina', 2, 'Prevencion de leucemia en gatos', 'CatCare', 'VAC2024-004', 40, 10, '2025-11-10', 50000.00, 4);

-- Insumos Medicos
INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, proveedor_id) VALUES
('Jeringas 3ml', 3, 'Jeringas descartables esteriles', 'MedSupply', 'INS2024-001', 500, 100, '2026-12-31', 500.00, 1),
('Agujas 21G', 3, 'Agujas hipodermica calibre 21', 'MedSupply', 'INS2024-002', 400, 80, '2026-12-31', 300.00, 1),
('Vendas Elasticas 10cm', 3, 'Vendaje elastico', 'VetSupply', 'INS2024-003', 200, 40, '2027-06-30', 3500.00, 2),
('Suero Fisiologico 500ml', 3, 'Solucion salina esteril', 'MedCare', 'INS2024-004', 100, 25, '2025-12-31', 8000.00, 3),
('Guantes de Latex Caja x100', 3, 'Guantes desechables', 'SafetyVet', 'INS2024-005', 50, 15, '2026-08-31', 25000.00, 1);

-- Alimentos
INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, proveedor_id) VALUES
('Concentrado Perro Adulto 15kg', 4, 'Alimento balanceado premium', 'DogChow', 'ALI2024-001', 80, 20, '2025-06-30', 95000.00, 2),
('Concentrado Cachorro 10kg', 4, 'Nutricion para cachorros', 'PuppyPro', 'ALI2024-002', 60, 15, '2025-07-15', 105000.00, 2),
('Concentrado Gato Adulto 8kg', 4, 'Alimento para gatos', 'CatFood', 'ALI2024-003', 70, 18, '2025-08-20', 85000.00, 3),
('Snacks Dentales Perro', 4, 'Premios para higiene dental', 'DentaDog', 'ALI2024-004', 150, 30, '2025-10-30', 15000.00, 2),
('Alimento Aves 1kg', 4, 'Semillas balanceadas para aves', 'BirdFood', 'ALI2024-005', 100, 25, '2025-09-15', 12000.00, 4);

-- Otros
INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, proveedor_id) VALUES
('Shampoo Antipulgas', 5, 'Shampoo medicado', 'PetClean', 'OTR2024-001', 80, 20, '2026-03-31', 28000.00, 2),
('Collar Antipulgas Perro', 5, 'Proteccion 6 meses', 'FleaFree', 'OTR2024-002', 100, 25, '2026-12-31', 45000.00, 3),
('Pipeta Antipulgas Gato', 5, 'Tratamiento topico mensual', 'CatProtect', 'OTR2024-003', 120, 30, '2025-11-30', 35000.00, 3);

-- ========================
-- HISTORIAL MEDICO
-- ========================

-- Registros de vacunacion
INSERT INTO historial_medico (mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES
(1, 1, '2024-01-15', 1, 'Aplicacion de vacuna quintuple', 'Perro sano, apto para vacunacion', 'Refuerzo en 1 año'),
(1, 2, '2024-02-20', 1, 'Aplicacion de vacuna antirrabica', 'Sin reacciones adversas', 'Refuerzo anual'),
(2, 1, '2024-01-20', 1, 'Aplicacion de vacuna quintuple', 'Cachorra sana', 'Refuerzo en 21 dias'),
(3, 3, '2024-03-10', 1, 'Vacuna antirrabica', 'Perro adulto en buen estado', 'Control anual');

-- Consultas
INSERT INTO historial_medico (mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES
(4, 2, '2024-04-05', 2, 'Consulta por vomito y diarrea', 'Gastroenteritis leve', 'Dieta blanda, Metronidazol por 5 dias'),
(5, 4, '2024-05-12', 2, 'Revision por cojera en pata trasera', 'Esguince leve', 'Reposo y Carprofeno por 7 dias'),
(11, 3, '2024-06-15', 2, 'Consulta dermatologica por prurito', 'Dermatitis alergica', 'Shampoo medicado y Prednisolona'),
(12, 2, '2024-07-20', 2, 'Control de peso y nutricion', 'Sobrepeso leve', 'Dieta hipocalorica');

-- Desparasitacion
INSERT INTO historial_medico (mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES
(6, 5, '2024-03-25', 4, 'Desparasitacion interna', 'Prevencion parasitaria', 'Repetir en 3 meses'),
(7, 5, '2024-04-30', 4, 'Desparasitacion interna y externa', 'Prevencion', 'Control trimestral'),
(13, 5, '2024-05-15', 4, 'Desparasitacion felina', 'Control preventivo', 'Proxima dosis en 90 dias');

-- Cirugias
INSERT INTO historial_medico (mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES
(8, 1, '2024-06-10', 3, 'Castracion', 'Procedimiento exitoso', 'Reposo 7 dias, antibiotico preventivo'),
(14, 1, '2024-07-05', 3, 'Ovariohisterectomia', 'Cirugia sin complicaciones', 'Control postoperatorio en 10 dias');

-- Controles
INSERT INTO historial_medico (mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES
(9, 2, '2024-08-15', 5, 'Control postoperatorio', 'Recuperacion satisfactoria', 'Alta medica'),
(10, 4, '2024-09-01', 5, 'Control cardiologico', 'Funcion cardiaca normal', 'Control anual');

-- Emergencias
INSERT INTO historial_medico (mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES
(15, 1, '2024-08-20', 6, 'Ingreso por intoxicacion alimentaria', 'Intoxicacion leve', 'Fluidoterapia y observacion 24h'),
(16, 2, '2024-09-10', 6, 'Traumatismo por caida', 'Contusion sin fracturas', 'Antiinflamatorios y reposo');

-- ========================
-- CITAS
-- ========================

-- Citas finalizadas
INSERT INTO cita (mascota_id, fecha_hora, motivo, estado_id, veterinario_id) VALUES
(1, '2024-09-15 09:00:00', 'Control anual', 3, 2),
(2, '2024-09-16 10:30:00', 'Vacunacion', 3, 1),
(3, '2024-09-17 14:00:00', 'Consulta dermatologica', 3, 3),
(4, '2024-09-18 11:00:00', 'Desparasitacion', 3, 5),
(11, '2024-09-19 15:30:00', 'Control peso', 3, 2);

-- Citas programadas
INSERT INTO cita (mascota_id, fecha_hora, motivo, estado_id, veterinario_id) VALUES
(5, '2024-10-05 09:00:00', 'Control postoperatorio', 1, 1),
(6, '2024-10-06 10:00:00', 'Vacuna antirrabica', 1, 1),
(7, '2024-10-07 11:30:00', 'Consulta general', 1, 2),
(12, '2024-10-08 14:00:00', 'Limpieza dental', 1, 3),
(13, '2024-10-09 15:00:00', 'Control veterinario', 1, 4),
(8, '2024-10-10 09:30:00', 'Vacunacion', 1, 5);

-- Citas en proceso
INSERT INTO cita (mascota_id, fecha_hora, motivo, estado_id, veterinario_id) VALUES
(14, '2024-10-01 10:00:00', 'Cirugia programada', 2, 1);

-- Citas canceladas
INSERT INTO cita (mascota_id, fecha_hora, motivo, estado_id, veterinario_id) VALUES
(9, '2024-09-25 16:00:00', 'Control general', 4, 2),
(15, '2024-09-26 09:00:00', 'Consulta', 4, 3);

-- Cita no asistida
INSERT INTO cita (mascota_id, fecha_hora, motivo, estado_id, veterinario_id) VALUES
(10, '2024-09-20 11:00:00', 'Vacunacion', 5, 5);

-- ========================
-- FACTURAS E ITEMS
-- ========================

-- Factura 1
INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES
(1, '2024-09-15 10:30:00', 95000.00, 'Tarjeta Debito');

INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(1, NULL, 'Consulta veterinaria general', 1, 50000.00, 50000.00),
(1, 1, NULL, 1, 45000.00, 45000.00);

-- Factura 2
INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES
(2, '2024-09-16 11:45:00', 130000.00, 'Efectivo');

INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(2, 6, NULL, 2, 45000.00, 90000.00),
(2, NULL, 'Aplicacion de vacuna', 1, 40000.00, 40000.00);

-- Factura 3
INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES
(3, '2024-09-17 15:30:00', 148000.00, 'Tarjeta Credito');

INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(3, NULL, 'Consulta especializada dermatologia', 1, 80000.00, 80000.00),
(3, 21, NULL, 1, 28000.00, 28000.00),
(3, 4, NULL, 2, 20000.00, 40000.00);

-- Factura 4
INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES
(4, '2024-09-18 12:15:00', 73000.00, 'Transferencia');

INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(4, NULL, 'Desparasitacion', 1, 35000.00, 35000.00),
(4, 3, NULL, 1, 18000.00, 18000.00),
(4, 20, NULL, 1, 20000.00, 20000.00);

-- Factura 5
INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES
(6, '2024-09-20 14:00:00', 450000.00, 'Tarjeta Credito');

INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(5, NULL, 'Cirugia castracion', 1, 350000.00, 350000.00),
(5, 1, NULL, 2, 15000.00, 30000.00),
(5, 11, NULL, 1, 8000.00, 8000.00),
(5, 18, NULL, 1, 95000.00, 95000.00);

-- Actualizar total de factura 5
UPDATE factura SET total = 483000.00 WHERE id = 5;

-- Factura 6
INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES
(5, '2024-09-22 10:00:00', 220000.00, 'Efectivo');

INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(6, 17, NULL, 2, 105000.00, 210000.00),
(6, NULL, 'Asesoria nutricional', 1, 10000.00, 10000.00);

-- Factura 7
INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES
(7, '2024-09-25 16:30:00', 92000.00, 'Tarjeta Debito');

INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(7, 22, NULL, 2, 45000.00, 90000.00),
(7, 13, NULL, 1, 500.00, 500.00),
(7, 14, NULL, 3, 300.00, 900.00),
(7, 15, NULL, 1, 3500.00, 3500.00);

-- Actualizar total de factura 7
UPDATE factura SET total = 94900.00 WHERE id = 7;