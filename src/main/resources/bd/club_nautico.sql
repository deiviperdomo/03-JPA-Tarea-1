create database club_nautico;
use club_nautico;

CREATE TABLE Socios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    direccion TEXT
);

CREATE TABLE Barcos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    numero_amarre INT NOT NULL,
    cuota DECIMAL(10,2) NOT NULL,
    id_socio INT NOT NULL,
    FOREIGN KEY (id) REFERENCES Socios(id) ON DELETE CASCADE
);

CREATE TABLE Patrones (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20)
);

CREATE TABLE Salidas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_barco INT NOT NULL,
    id_patron INT NOT NULL,
    fecha_salida DATETIME NOT NULL,
    destino VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_barco) REFERENCES Barcos(id) ON DELETE CASCADE,
    FOREIGN KEY (id_patron) REFERENCES Patrones(id) ON DELETE CASCADE
);

-- Insertar datos en la tabla Socios
INSERT INTO Socios (nombre, apellidos, dni, telefono, direccion) VALUES
('Juan Pérez', 'García', '12345678A', '600123456', 'Calle Falsa 123, Madrid'),
('Ana López', 'Martínez', '87654321B', '600654321', 'Avenida Siempre Viva 456, Madrid'),
('Carlos Sánchez', 'Fernández', '11223344C', '600789012', 'Plaza Mayor 789, Madrid');

-- Insertar datos en la tabla Barcos
INSERT INTO Barcos (matricula, nombre, numero_amarre, cuota, id_socio) VALUES
('ABC123', 'El Velero', 1, 150.00, 1),
('DEF456', 'La Lancha', 2, 200.00, 2),
('GHI789', 'El Yate', 3, 250.00, 3);

-- Insertar datos en la tabla Patrones
INSERT INTO Patrones (nombre, apellidos, dni, telefono) VALUES
('Miguel Torres', 'Rodríguez', '33445566D', '600345678'),
('Lucía Gómez', 'Hernández', '44556677E', '600456789'),
('Pedro Ruiz', 'Jiménez', '55667788F', '600567890');

-- Insertar datos en la tabla Salidas
INSERT INTO Salidas (id_barco, id_patron, fecha_salida, destino) VALUES
(1, 1, '2025-04-01 10:00:00', 'Islas Baleares'),
(2, 2, '2025-04-02 11:00:00', 'Costa del Sol'),
(3, 3, '2025-04-03 12:00:00', 'Islas Canarias');
/* 
Los socios tienen una lista de barcos que poseen.
Cada barco está vinculado a un solo socio.
Los patrones pueden ser socios o no, por lo que se almacenan en una tabla independiente.
Cada salida está vinculada a un barco y un patrón, registrando la fecha, hora y destino.
*/