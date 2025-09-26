drop database Happy_Feet;
create database Happy_Feet;
USE Happy_Feet;

-- ========================
-- TABLAS DE CATÁLOGO
-- ========================

-- Tipo de producto
CREATE TABLE producto_tipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre ENUM('Medicamento','Vacuna','Insumo Médico','Alimento','Otro') UNIQUE NOT NULL
);

-- Tipo de evento médico
CREATE TABLE evento_tipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre ENUM('Vacunación','Consulta','Cirugía','Desparasitación','Control','Emergencia','Hospitalización') UNIQUE NOT NULL
);

-- Estado de cita
CREATE TABLE cita_estados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre ENUM('Programada','En Proceso','Finalizada','Cancelada','No Asistió') UNIQUE NOT NULL
);

-- ========================
-- TABLAS PRINCIPALES
-- ========================

-- Dueños de mascotas
CREATE TABLE duenos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    documento_identidad VARCHAR(20) UNIQUE,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE
);

-- Veterinarios
CREATE TABLE veterinarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE
);

-- Especies
CREATE TABLE especies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL -- Ej: Perro, Gato, Ave
);

-- Razas
CREATE TABLE razas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    especie_id INT,
    nombre VARCHAR(100) NOT NULL,
    FOREIGN KEY (especie_id) REFERENCES especies(id)
);

-- Mascotas
CREATE TABLE mascotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dueno_id INT,
    raza_id INT,
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    especie_id INT,
    sexo ENUM('Macho','Hembra'),
    url_foto VARCHAR(255),
    microchip VARCHAR(20) UNIQUE,
    FOREIGN KEY (especie_id) REFERENCES especies(id),
    FOREIGN KEY (dueno_id) REFERENCES duenos(id) ON DELETE CASCADE,
    FOREIGN KEY (raza_id) REFERENCES razas(id)
);

-- Adopciones
CREATE TABLE adopciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mascota_id INT NOT NULL,
    estado ENUM('Disponible','Reservada','En Proceso','Adoptada','No Disponible') DEFAULT 'Disponible',
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    FOREIGN KEY (mascota_id) REFERENCES mascotas(id) ON DELETE CASCADE
);

-- Historial médico
CREATE TABLE historial_medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mascota_id INT,
    veterinario_id INT,
    fecha_evento DATE NOT NULL,
    evento_tipo_id INT,
    descripcion TEXT,
    diagnostico TEXT,
    tratamiento_recomendado TEXT,
    FOREIGN KEY (mascota_id) REFERENCES mascotas(id) ON DELETE CASCADE,
    FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id),
    FOREIGN KEY (evento_tipo_id) REFERENCES evento_tipos(id)
);

-- ========================
-- INVENTARIO / PRODUCTOS
-- ========================

CREATE TABLE inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(255) NOT NULL,
    producto_tipo_id INT,
    descripcion TEXT,
    fabricante VARCHAR(100),
    lote VARCHAR(50),
    cantidad_stock INT NOT NULL,
    stock_minimo INT NOT NULL,
    fecha_vencimiento DATE,
    precio_venta DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (producto_tipo_id) REFERENCES producto_tipos(id)
);

-- ========================
-- CITAS / SERVICIOS
-- ========================

CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mascota_id INT,
    fecha_hora DATETIME NOT NULL,
    motivo VARCHAR(255),
    estado_id INT,
    veterinario_id INT,
    FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id),
    FOREIGN KEY (mascota_id) REFERENCES mascotas(id),
    FOREIGN KEY (estado_id) REFERENCES cita_estados(id)
);

-- ========================
-- FACTURACIÓN / VENTAS
-- ========================

CREATE TABLE facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dueno_id INT,
    fecha_emision DATETIME NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    metodo_pago ENUM('Efectivo','Tarjeta Débito','Tarjeta Crédito','Transferencia','Cheque') NOT NULL,
    FOREIGN KEY (dueno_id) REFERENCES duenos(id)
);

CREATE TABLE items_factura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    factura_id INT,
    producto_id INT, -- puede ser producto o servicio
    servicio_descripcion VARCHAR(255),
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (factura_id) REFERENCES facturas(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES inventario(id)
);

-- ========================
-- PROVEEDORES
-- ========================
CREATE TABLE proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100) NOT NULL,
    contacto VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(150)
);

ALTER TABLE inventario
ADD COLUMN proveedor_id INT,
ADD FOREIGN KEY (proveedor_id) REFERENCES proveedores(id);


ALTER TABLE mascotas ADD estado ENUM('ACTIVA', 'INACTIVA') DEFAULT 'ACTIVA';