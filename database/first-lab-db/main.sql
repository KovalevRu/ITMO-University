-- Удаление таблиц в правильном порядке
DROP TABLE IF EXISTS Sobytiye;
DROP TABLE IF EXISTS Atmosfera;
DROP TABLE IF EXISTS Sputnik;
DROP TABLE IF EXISTS Korabl;
DROP TABLE IF EXISTS Planeta;

-- Создание таблиц
CREATE TABLE Planeta (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  diameter_km INT NOT NULL
);

CREATE TABLE Sputnik (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  diametr_km INT,
  planeta_id INT REFERENCES Planeta(id)
);

CREATE TABLE Korabl (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  launch_date DATE NOT NULL,
  mission_goal TEXT NOT NULL
);

CREATE TABLE Sobytiye (
  id SERIAL PRIMARY KEY,
  type TEXT NOT NULL,
  event_time TIMESTAMP NOT NULL,
  korabl_id INT NOT NULL REFERENCES Korabl(id),
  planeta_id INT REFERENCES Planeta(id),
  sputnik_id INT REFERENCES Sputnik(id),
  CHECK (
    (planeta_id IS NOT NULL AND sputnik_id IS NULL)
    OR
    (planeta_id IS NULL AND sputnik_id IS NOT NULL)
  )
);

CREATE TABLE Atmosfera (
  id SERIAL PRIMARY KEY,
  planeta_id INT NOT NULL UNIQUE REFERENCES Planeta(id),
  pressure INT NOT NULL,
  temperature INT NOT NULL
);

-- Вставка данных
INSERT INTO Planeta (name, diameter_km) VALUES
('Earth', 12742),
('Mars', 6779),
('Jupiter', 139820);

INSERT INTO Sputnik (name, diametr_km, planeta_id) VALUES
('Moon', 3474, 1),
('Phobos', 22, 2),
('Europa', 3121, 3);

INSERT INTO Korabl (name, launch_date, mission_goal) VALUES
('Apollo 11', '1969-07-16', 'Moon landing'),
('Mars Express', '2003-06-02', 'Explore Mars'),
('Juno', '2011-08-05', 'Study Jupiter');

INSERT INTO Sobytiye (type, event_time, korabl_id, planeta_id, sputnik_id) VALUES
('Orbit Insertion', '2003-12-25 00:00:00', 2, 2, NULL),
('Landing', '1969-07-20 20:17:00', 1, NULL, 1),
('Flyby', '2016-06-27 00:00:00', 3, NULL, 3);

INSERT INTO Atmosfera (planeta_id, pressure, temperature) VALUES
(1, 1013, 15),
(2, 610, -60),
(3, 1000000, -145);
