-- Datos de prueba

-- ========================
-- PRODUCTO TIPO
-- ========================
INSERT INTO producto_tipo (nombre) VALUES
('Medicamento'),
('Vacuna'),
('Insumo Médico'),
('Alimento'),
('Otro');

-- ========================
-- EVENTO TIPO (Tipo de evento médico)
-- ========================
INSERT INTO evento_tipo (nombre) VALUES
('Vacunación'),
('Consulta'),
('Cirugía'),
('Desparasitación'),
('Control'),
('Emergencia'),
('Hospitalización');

-- ========================
-- ESTADO DE CITA
-- ========================
INSERT INTO cita_estado (nombre) VALUES
('Programada'),
('En Proceso'),
('Finalizada'),
('Cancelada'),
('No Asistió');

-- ========================
-- INSERTAR ESPECIES
-- ========================
INSERT INTO especie (nombre) VALUES
('Perro'),
('Gato'),
('Ave'),
('Conejo');

-- ========================
-- INSERTAR RAZAS
-- ========================
-- Razas de Perro
INSERT INTO raza (especie_id, nombre) VALUES
((SELECT id FROM especie WHERE nombre='Perro'), 'Labrador'),
((SELECT id FROM especie WHERE nombre='Perro'), 'Golden Retriever'),
((SELECT id FROM especie WHERE nombre='Perro'), 'Bulldog Francés'),
((SELECT id FROM especie WHERE nombre='Perro'), 'Pastor Alemán');

-- Razas de Gato
INSERT INTO raza (especie_id, nombre) VALUES
((SELECT id FROM especie WHERE nombre='Gato'), 'Siames'),
((SELECT id FROM especie WHERE nombre='Gato'), 'Persa'),
((SELECT id FROM especie WHERE nombre='Gato'), 'Maine Coon'),
((SELECT id FROM especie WHERE nombre='Gato'), 'Bengala');

-- Razas de Ave
INSERT INTO raza (especie_id, nombre) VALUES
((SELECT id FROM especie WHERE nombre='Ave'), 'Canario'),
((SELECT id FROM especie WHERE nombre='Ave'), 'Periquito'),
((SELECT id FROM especie WHERE nombre='Ave'), 'Loro Amazónico'),
((SELECT id FROM especie WHERE nombre='Ave'), 'Cacatúa');

-- Razas de Conejo
INSERT INTO raza (especie_id, nombre) VALUES
((SELECT id FROM especie WHERE nombre='Conejo'), 'Enano Holandés'),
((SELECT id FROM especie WHERE nombre='Conejo'), 'Rex'),
((SELECT id FROM especie WHERE nombre='Conejo'), 'Angora'),
((SELECT id FROM especie WHERE nombre='Conejo'), 'Cabeza de León');