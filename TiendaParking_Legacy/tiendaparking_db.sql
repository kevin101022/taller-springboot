CREATE DATABASE IF NOT EXISTS tiendaparking;
USE tiendaparking;

CREATE TABLE carros (
    id_carro INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL
);

CREATE TABLE motores (
    id_motor INT AUTO_INCREMENT PRIMARY KEY,
    numero_serie VARCHAR(50) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    caballos_fuerza VARCHAR(20) NOT NULL
);

CREATE TABLE choferes (
    id_chofer INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(20) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    licencia VARCHAR(50) NOT NULL
);

CREATE TABLE pasajeros (
    id_pasajero INT AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(20) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    tipo_documento VARCHAR(20) NOT NULL,
    genero VARCHAR(20) NOT NULL
);

CREATE TABLE viajes (
    id_viaje INT AUTO_INCREMENT PRIMARY KEY,
    id_carro INT NOT NULL,
    id_motor INT NOT NULL,
    id_chofer INT NOT NULL,
    id_pasajero INT NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_carro) REFERENCES carros(id_carro),
    FOREIGN KEY (id_motor) REFERENCES motores(id_motor),
    FOREIGN KEY (id_chofer) REFERENCES choferes(id_chofer),
    FOREIGN KEY (id_pasajero) REFERENCES pasajeros(id_pasajero)
);
