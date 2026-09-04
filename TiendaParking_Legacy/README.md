# TiendaParking

Proyecto desarrollado en Java como ejercicio de programación orientada a objetos aplicando el patrón **MVC** (Modelo - Vista - Controlador).

## ¿De qué trata?

Sistema de consola para el registro básico de los elementos de un servicio de transporte: chofer, carro, motor y pasajero.

No tiene base de datos. El objetivo es practicar la estructura MVC y la separación de responsabilidades entre capas.

## Estructura del proyecto

```
src/
├── model/       → Clases de datos y reglas de negocio (validaciones)
├── controller/  → Intermediarios entre la vista y el modelo
├── views/       → Captura de datos por consola (Scanner)
└── main/        → Punto de entrada del programa
```

## Cómo ejecutar

Compilar desde la raíz del proyecto:
```bash
javac -d bin src/model/*.java src/controller/*.java src/views/*.java src/main/*.java
```

Ejecutar:
```bash
java -cp bin main.Tiendaparking
```

## Validaciones incluidas

Cada entidad valida sus datos antes de registrarse:

- **Chofer** → nombre, apellido, cédula (solo números) y licencia no pueden estar vacíos.
- **Carro** → placa (mínimo 3 caracteres), color, marca y modelo obligatorios.
- **Motor** → tipo, número de serie requeridos; caballos de fuerza debe ser un número mayor a 0.
- **Pasajero** → nombre, apellido y cédula (solo números) obligatorios.

Si algún dato no cumple la regla, el sistema muestra el error correspondiente sin detener el programa.

## Tecnologías

- Java 24
- Sin dependencias externas
