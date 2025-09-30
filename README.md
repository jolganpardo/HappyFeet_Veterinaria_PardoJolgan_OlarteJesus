# 🐾 HappyFeet Veterinaria

**HappyFeet** es una aplicación de gestión para clínicas veterinarias, desarrollada en Java, que permite administrar información de mascotas, dueños, veterinarios, citas, historiales médicos, inventario de productos y facturación. Este proyecto fue realizado por **Jolgan Pardo** y **Jesús Olarte** como parte de un proyecto académico.

## 📌 Tabla de contenidos

* [Descripcion del proyecto](#descripcion-del-proyecto)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Instalacion](#instalacion)
* [Uso](#uso)
* [Estructura del proyecto](#estructura-del-proyecto)
* [Contribuciones](#contribuciones)

## 📝 Descripcion del proyecto

HappyFeet es una solución integral para clínicas veterinarias que facilita:

* Registro y gestión de dueños de mascotas.
* Administración de información de mascotas, incluyendo especie, raza, sexo y estado.
* Gestión de veterinarios y especialidades.
* Programación y seguimiento de citas médicas.
* Registro de historiales médicos, diagnósticos y tratamientos.
* Control de inventario de productos veterinarios.
* Generación de facturas y control de pagos.

## ⚙️ Tecnologias utilizadas

* **Lenguaje de programación**: Java
* **Base de datos**: MySQL
* **ORM**: JDBC
* **IDE**: IntelliJ IDEA

## 📥 Instalacion

1. Clona este repositorio en tu máquina local:

   ```bash
   git clone https://github.com/jolganpardo/HappyFeet_Veterinaria_PardoJolgan_OlarteJesus.git
   ```

2. Importa el proyecto en tu IDE favorito (recomendado IntelliJ IDEA).

3. Configura la conexión a la base de datos MySQL:

   * Crea una base de datos llamada `Happy_Feet`.
   * Ejecuta los scripts SQL proporcionados en la carpeta `database/` para crear las tablas necesarias.

4. Asegúrate de tener el driver JDBC de MySQL en el classpath del proyecto.

## 🚀 Uso

* Ejecuta la clase principal `Main.java` para iniciar la aplicación.
* Navega a través de los menús interactivos en la consola para gestionar los diferentes módulos del sistema.

## 📁 Estructura del proyecto

```
HappyFeet_Veterinaria_PardoJolgan_OlarteJesus/
│
├── src/
│   ├── controller/           # Controladores de la aplicación
│   ├── model/                # Entidades y modelos de datos
│   ├── repository/           # Clases de acceso a datos (DAO)
│   └── service/              # Lógica de negocio
│
├── database/                 # Scripts SQL para la base de datos
├── libs/                     # Librerías externas
├── .gitignore                # Archivos y carpetas ignorados por Git
└── HappyFeet_Veterinaria_PardoJolgan_OlarteJesus.iml  # Configuración del proyecto en IntelliJ IDEA
```

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Si deseas mejorar este proyecto, por favor sigue estos pasos:

1. Haz un fork de este repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

