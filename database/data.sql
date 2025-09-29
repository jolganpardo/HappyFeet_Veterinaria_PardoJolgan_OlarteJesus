-- ========================
-- CATÁLOGOS
-- ========================

INSERT INTO producto_tipo (id, nombre) VALUES
(1, 'Medicamento'),
(2, 'Vacuna'),
(3, 'Insumo Médico'),
(4, 'Alimento'),
(5, 'Otro');

INSERT INTO evento_tipo (id, nombre) VALUES
(1, 'Vacunación'),
(2, 'Consulta'),
(3, 'Cirugía'),
(4, 'Desparasitación'),
(5, 'Control'),
(6, 'Emergencia'),
(7, 'Hospitalización');

INSERT INTO cita_estado (id, nombre) VALUES
(1, 'Programada'),
(2, 'En Proceso'),
(3, 'Finalizada'),
(4, 'Cancelada'),
(5, 'No Asistió');

-- ========================
-- PRINCIPALES
-- ========================

INSERT INTO dueno (id, nombre_completo, documento_identidad, direccion, telefono, email) VALUES
(1, 'Carlos Pérez', '12345678', 'Calle 10 #45-23', '3001234567', 'carlos@example.com'),
(2, 'María Gómez', '87654321', 'Carrera 15 #22-11', '3109876543', 'maria@example.com');

INSERT INTO veterinario (id, nombre_completo, especialidad, telefono, email) VALUES
(1, 'Dr. Juan Ramírez', 'Cirugía', '3015551234', 'juanramirez@vet.com'),
(2, 'Dra. Laura Torres', 'Medicina General', '3026669876', 'lauratorres@vet.com');

INSERT INTO especie (id, nombre) VALUES
(1, 'Perro'),
(2, 'Gato');

INSERT INTO raza (id, especie_id, nombre) VALUES
(1, 1, 'Labrador'),
(2, 2, 'Siames');

INSERT INTO mascota (id, dueno_id, raza_id, nombre, fecha_nacimiento, especie_id, sexo, url_foto, microchip, estado) VALUES
(1, 1, 1, 'Firulais', '2019-05-10', 1, 'Macho', NULL, 'MC12345', 'ACTIVA'),
(2, 2, 2, 'Misu', '2020-08-15', 2, 'Hembra', NULL, 'MC67890', 'ACTIVA');

INSERT INTO adopcion (id, mascota_id, estado, fecha_inicio, fecha_fin) VALUES
(1, 1, 'No Disponible', '2021-01-01', '2021-01-15'),
(2, 2, 'Adoptada', '2022-03-01', '2022-03-20');

INSERT INTO historial_medico (id, mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES
(1, 1, 1, '2023-05-10', 2, 'Consulta general', 'Gastroenteritis', 'Medicamento y dieta blanda'),
(2, 2, 2, '2023-06-01', 1, 'Vacunación anual', 'Sin novedades', 'Vacuna antirrábica');

-- ========================
-- INVENTARIO / PROVEEDORES
-- ========================

INSERT INTO proveedor (id, nombre_empresa, contacto, telefono, email, direccion) VALUES
(1, 'Vet Supplies SA', 'Pedro López', '3151112233', 'pedro@vetsupplies.com', 'Zona Industrial 123'),
(2, 'Alimentos Felices', 'Ana Ruiz', '3162223344', 'ana@alifelices.com', 'Av Siempre Viva 742');

INSERT INTO inventario (id, nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, proveedor_id) VALUES
(1, 'Antibiótico X', 1, 'Antibiótico de amplio espectro', 'LabVet', 'L123', 50, 10, '2025-12-31', 15000.00, 1),
(2, 'Vacuna Rabia', 2, 'Vacuna contra la rabia', 'VacunVet', 'V456', 30, 5, '2026-06-30', 25000.00, 1),
(3, 'Croquetas Premium', 4, 'Alimento para perro', 'DogFood Inc.', 'C789', 100, 20, '2024-11-15', 120000.00, 2);

-- ========================
-- CITAS
-- ========================

INSERT INTO cita (id, mascota_id, fecha_hora, motivo, estado_id, veterinario_id) VALUES
(1, 1, '2023-07-10 10:00:00', 'Revisión general', 1, 1),
(2, 2, '2023-07-15 11:00:00', 'Vacunación', 1, 2);

-- ========================
-- FACTURAS / VENTAS
-- ========================

INSERT INTO factura (id, dueno_id, fecha_emision, total, metodo_pago) VALUES
(1, 1, '2023-07-10 10:30:00', 15000.00, 'Efectivo'),
(2, 2, '2023-07-15 11:30:00', 25000.00, 'Tarjeta Crédito');

INSERT INTO items_factura (id, factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 1, NULL, 1, 15000.00, 15000.00),
(2, 2, 2, NULL, 1, 25000.00, 25000.00);
