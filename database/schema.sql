CREATE DATABASE IF NOT EXISTS Happy_Feet;
USE Happy_Feet;

CREATE TABLE especies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE razas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    especie_id INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    FOREIGN KEY (especie_id) REFERENCES especies(id)
);

CREATE TABLE producto_tipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL -- 'Medicamento', 'Vacuna', 'Insumo Médico', 'Alimento'
);

CREATE TABLE evento_tipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL -- 'Vacunación', 'Consulta', 'Cirugía', 'Desparasitación'
);

CREATE TABLE cita_estados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) UNIQUE NOT NULL -- 'Programada', 'En Proceso', 'Finalizada', 'Cancelada'
);

-- =========== TABLAS OPERACIONALES DEL NEGOCIO ===========

CREATE TABLE duenos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    documento_identidad VARCHAR(20) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE mascotas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dueno_id INT,
    nombre VARCHAR(100) NOT NULL,
    raza_id INT,
    fecha_nacimiento DATE,
    sexo ENUM('Macho', 'Hembra'),
    url_foto VARCHAR(255),
    FOREIGN KEY (dueno_id) REFERENCES duenos(id),
    FOREIGN KEY (raza_id) REFERENCES razas(id)
);

CREATE TABLE historial_medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mascota_id INT,
    fecha_evento DATE NOT NULL,
    evento_tipo_id INT,
    descripcion TEXT,
    diagnostico TEXT,
    tratamiento_recomendado TEXT,
    FOREIGN KEY (mascota_id) REFERENCES mascotas(id),
    FOREIGN KEY (evento_tipo_id) REFERENCES evento_tipos(id)
);

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
    precio_venta DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (producto_tipo_id) REFERENCES producto_tipos(id)
);

CREATE TABLE citas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mascota_id INT,
    fecha_hora DATETIME NOT NULL,
    motivo VARCHAR(255),
    estado_id INT,
    FOREIGN KEY (mascota_id) REFERENCES mascotas(id),
    FOREIGN KEY (estado_id) REFERENCES cita_estados(id)
);

CREATE TABLE facturas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dueno_id INT,
    fecha_emision DATETIME NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (dueno_id) REFERENCES duenos(id)
);

CREATE TABLE items_factura (
    id INT AUTO_INCREMENT PRIMARY KEY,
    factura_id INT,
    producto_id INT, -- Puede ser un producto del inventario
    servicio_descripcion VARCHAR(255), -- O un servicio
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (factura_id) REFERENCES facturas(id),
    FOREIGN KEY (producto_id) REFERENCES inventario(id)
);

CREATE TABLE proveedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    contacto VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(255)
);

ALTER TABLE inventario 
ADD proveedor_id INT,
ADD FOREIGN KEY (proveedor_id) REFERENCES proveedores(id);

CREATE TABLE veterinarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(255) NOT NULL,
    especialidad VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100)
);

ALTER TABLE citas 
ADD veterinario_id INT,
ADD FOREIGN KEY (veterinario_id) REFERENCES veterinarios(id);

CREATE TABLE servicios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);

ALTER TABLE items_factura
ADD servicio_id INT,
ADD FOREIGN KEY (servicio_id) REFERENCES servicios(id);

ALTER TABLE inventario
ADD fecha_ultima_compra DATE;
