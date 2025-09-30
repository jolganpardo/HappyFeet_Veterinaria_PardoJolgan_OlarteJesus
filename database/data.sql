-- Datos de la base de datos

USE Happy_Feet;

-- ========================
-- PRODUCTO TIPO
-- ========================
INSERT INTO producto_tipo (nombre) VALUES
('Medicamento'),
('Vacuna'),
('Insumo Medico'),
('Alimento'),
('Otro');

-- ========================
-- EVENTO TIPO
-- ========================
INSERT INTO evento_tipo (nombre) VALUES
('Vacunacion'),
('Consulta'),
('Cirugia'),
('Desparasitacion'),
('Control'),
('Emergencia'),
('Hospitalizacion');

-- ========================
-- CITA ESTADO
-- ========================
INSERT INTO cita_estado (nombre) VALUES
('Programada'),
('En Proceso'),
('Finalizada'),
('Cancelada'),
('No Asistio');

-- ========================
-- DUENOS
-- ========================
INSERT INTO dueno (nombre_completo, documento_identidad, direccion, telefono, email) VALUES
('Juan Perez','12345678','Calle 1 #123','3001234567','juan@mail.com'),
('Maria Gomez','87654321','Calle 2 #456','3007654321','maria@mail.com'),
('Carlos Lopez','11223344','Calle 3 #789','3001122334','carlos@mail.com'),
('Ana Torres','44332211','Calle 4 #321','3004433221','ana@mail.com'),
('Luis Fernandez','55667788','Calle 5 #654','3005566778','luis@mail.com');

-- ========================
-- VETERINARIOS
-- ========================
INSERT INTO veterinario (nombre_completo, especialidad, telefono, email) VALUES
('Dr. Pedro Martinez','Cirugia','3001112222','pedro@mail.com'),
('Dra. Laura Diaz','Medicina General','3003334444','laura@mail.com'),
('Dr. Ricardo Suarez','Cardiologia','3005556666','ricardo@mail.com'),
('Dra. Sofia Morales','Dermatologia','3007778888','sofia@mail.com'),
('Dr. Andres Rojas','Emergencias','3009990000','andres@mail.com');

-- ========================
-- ESPECIES
-- ========================
INSERT INTO especie (nombre) VALUES
('Perro'),
('Gato'),
('Ave'),
('Reptil'),
('Conejo');

-- ========================
-- RAZAS
-- ========================
INSERT INTO raza (especie_id, nombre) VALUES
(1,'Labrador'),
(1,'Bulldog'),
(2,'Siamés'),
(2,'Persa'),
(3,'Canario');

-- ========================
-- MASCOTAS
-- ========================
INSERT INTO mascota (dueno_id, raza_id, nombre, fecha_nacimiento, especie_id, sexo, url_foto, microchip, estado) VALUES
(1,1,'Max','2020-05-10',1,'Macho','url1.jpg','MC1001','ACTIVA'),
(2,2,'Luna','2019-08-20',1,'Hembra','url2.jpg','MC1002','ACTIVA'),
(3,3,'Simba','2021-01-15',2,'Macho','url3.jpg','MC1003','ACTIVA'),
(4,4,'Nina','2018-12-05',2,'Hembra','url4.jpg','MC1004','ACTIVA'),
(5,5,'Piolin','2022-03-30',3,'Macho','url5.jpg','MC1005','ACTIVA');

-- ========================
-- HISTORIAL MEDICO
-- ========================
INSERT INTO historial_medico (mascota_id, veterinario_id, fecha_evento, evento_tipo_id, descripcion, diagnostico, tratamiento_recomendado) VALUES
(1,1,'2023-01-10',1,'Vacuna anual','Sin problemas','Vacuna aplicada'),
(2,2,'2023-02-15',2,'Chequeo general','Leve alergia','Antihistaminico'),
(3,3,'2023-03-20',3,'Cirugia de pata','Fractura','Reposo y analgésicos'),
(4,4,'2023-04-05',4,'Desparasitacion rutinaria','Sin problemas','Desparasitante oral'),
(5,5,'2023-05-12',5,'Control de peso','Sobrepeso','Dieta especial');

-- ========================
-- PROVEEDORES
-- ========================
INSERT INTO proveedor (nombre_empresa, contacto, telefono, email, direccion) VALUES
('Proveedor A','Carlos','3001111111','provA@mail.com','Calle 10 #100'),
('Proveedor B','Ana','3002222222','provB@mail.com','Calle 11 #200'),
('Proveedor C','Luis','3003333333','provC@mail.com','Calle 12 #300'),
('Proveedor D','Marta','3004444444','provD@mail.com','Calle 13 #400'),
('Proveedor E','Pedro','3005555555','provE@mail.com','Calle 14 #500');

-- ========================
-- INVENTARIO
-- ========================
INSERT INTO inventario (nombre_producto, producto_tipo_id, descripcion, fabricante, lote, cantidad_stock, stock_minimo, fecha_vencimiento, precio_venta, proveedor_id) VALUES
('Antipulgas','Medicamento','Tratamiento contra pulgas','LabFarm','L100','50','10','2024-12-31',50.00,1),
('Vacuna Rabia','Vacuna','Vacuna anual contra rabia','VacLab','L200','100','20','2025-06-30',75.00,2),
('Guantes','Insumo Medico','Guantes de latex','MediSup','L300','200','50','2026-01-01',5.00,3),
('Alimento Premium','Alimento','Comida balanceada','PetFood','L400','150','30','2025-11-15',120.00,4),
('Shampoo Antipulgas','Otro','Shampoo especial','PetCare','L500','80','15','2024-09-10',35.00,5);

-- ========================
-- CITAS
-- ========================
INSERT INTO cita (mascota_id, fecha_hora, motivo, estado_id, veterinario_id) VALUES
(1,'2023-09-01 10:00:00','Vacuna anual',1,1),
(2,'2023-09-02 11:00:00','Chequeo general',2,2),
(3,'2023-09-03 09:30:00','Cirugia pata',3,3),
(4,'2023-09-04 14:00:00','Desparasitacion',4,4),
(5,'2023-09-05 15:30:00','Control de peso',5,5);

-- ========================
-- FACTURA
-- ========================
INSERT INTO factura (dueno_id, fecha_emision, total, metodo_pago) VALUES
(1,'2023-09-01 10:30:00',150.00,'Efectivo'),
(2,'2023-09-02 11:30:00',200.00,'Tarjeta Debito'),
(3,'2023-09-03 10:00:00',300.00,'Tarjeta Credito'),
(4,'2023-09-04 14:30:00',100.00,'Transferencia'),
(5,'2023-09-05 16:00:00',75.00,'Cheque');

-- ========================
-- ITEMS FACTURA
-- ========================
INSERT INTO items_factura (factura_id, producto_id, servicio_descripcion, cantidad, precio_unitario, subtotal) VALUES
(1,1,'',2,50.00,100.00),
(1,2,'',1,50.00,50.00),
(2,2,'',2,75.00,150.00),
(2,4,'',1,50.00,50.00),
(3,4,'',2,120.00,240.00),
(4,3,'',5,5.00,25.00),
(5,5,'',1,35.00,35.00);
