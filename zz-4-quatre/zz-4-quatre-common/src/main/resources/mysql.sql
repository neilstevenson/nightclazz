DROP TABLE IF EXISTS personne_t;

CREATE TABLE personne_t (
 id                     INT NOT NULL,
 pre_nom                VARCHAR(60) NOT NULL,
 nom_de_familie         VARCHAR(60) NOT NULL,
 birth_dd               INT NOT NULL,
 birth_mm               INT NOT NULL,
 birth_ccyy             INT NOT NULL,
 creation               TIMESTAMP NOT NULL,
 PRIMARY KEY (id)
) ENGINE InnoDB;
 
-- https://en.wikipedia.org/wiki/March_26#Births

INSERT INTO personne_t (id, pre_nom, nom_de_familie, birth_dd, birth_mm, birth_ccyy, creation)
VALUES
 (1, 'Theodore', 'Aubanel', 26, 03, 1829, DATE_SUB(CURDATE(), INTERVAL 13 DAY))
,(2, 'Theodore', 'Tuffier', 26, 03, 1857, DATE_SUB(CURDATE(), INTERVAL 12 DAY))
,(3, 'Guccio', 'Gucci', 26, 03, 1881, DATE_SUB(CURDATE(), INTERVAL 11 DAY))
,(4, 'Tennessee', 'Williams', 26, 03, 1911, DATE_SUB(CURDATE(), INTERVAL 10 DAY))
,(5, 'Pierre', 'Boulez', 26, 03, 1925, DATE_SUB(CURDATE(), INTERVAL 9 DAY))
,(6, 'Leonard', 'Nimoy', 26, 03, 1931, DATE_SUB(CURDATE(), INTERVAL 8 DAY))
,(7, 'Alan', 'Arkin', 26, 03, 1934, DATE_SUB(CURDATE(), INTERVAL 7 DAY))
,(8, 'James', 'Caan', 26, 03, 1940, DATE_SUB(CURDATE(), INTERVAL 6 DAY))
,(9, 'Diana', 'Ross', 26, 03, 1944, DATE_SUB(CURDATE(), INTERVAL 5 DAY))
,(10, 'Alain', 'Madelin', 26, 03, 1946, DATE_SUB(CURDATE(), INTERVAL 4 DAY))
,(11, 'William', 'Hague', 26, 03, 1961, DATE_SUB(CURDATE(), INTERVAL 3 DAY))
,(12, 'Larry', 'Page', 26, 03, 1973, DATE_SUB(CURDATE(), INTERVAL 2 DAY))
,(13, 'Keira', 'Knightly', 26, 03, 1985, DATE_SUB(CURDATE(), INTERVAL 1 DAY))
;
