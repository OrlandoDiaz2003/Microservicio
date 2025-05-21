INSERT INTO categoria VALUES(1, 'Alimento');
INSERT INTO categoria VALUES(2, 'Habitacion');
INSERT INTO categoria VALUES(3, 'Decoracion');
INSERT INTO categoria VALUES(4, 'Aseo');
INSERT INTO categoria VALUES(5, 'Electronico');

-- Productos Ecológicos (1)
INSERT INTO producto VALUES(1, 1200.0, 1, 50, 'Leche orgánica de vaca');
INSERT INTO producto VALUES(1, 1300.0, 2, 40, 'Leche de almendra orgánica');
INSERT INTO producto VALUES(1, 1100.0, 3, 60, 'Yogurt natural orgánico');
INSERT INTO producto VALUES(1, 1000.0, 4, 70, 'Yogurt de fresa orgánico');

-- Productos de Madera Sostenible (2)
INSERT INTO producto VALUES(2, 55000.0, 5, 10, 'Cama individual de madera reciclada');
INSERT INTO producto VALUES(2, 75000.0, 6, 8, 'Cama matrimonial de madera sostenible');
INSERT INTO producto VALUES(2, 35000.0, 7, 15, 'Colchón de espuma natural');
INSERT INTO producto VALUES(2, 30000.0, 8, 20, 'Colchón inflable ecológico');

-- Decoración Ecológica (3)
INSERT INTO producto VALUES(3, 5000.0, 9, 25, 'Cuadro de naturaleza en madera reciclada');
INSERT INTO producto VALUES(3, 6000.0, 10, 30, 'Cuadro abstracto ecológico');
INSERT INTO producto VALUES(3, 2000.0, 11, 50, 'Florero de vidrio reciclado');
INSERT INTO producto VALUES(3, 1500.0, 12, 40, 'Florero cerámico hecho a mano');

-- Productos de Higiene Ecológicos (4)
INSERT INTO producto VALUES(4, 800.0, 13, 100, 'Jabón líquido orgánico');
INSERT INTO producto VALUES(4, 750.0, 14, 120, 'Jabón en barra ecológico');
INSERT INTO producto VALUES(4, 2000.0, 15, 90, 'Shampoo para cabello seco ecológico');
INSERT INTO producto VALUES(4, 2100.0, 16, 85, 'Shampoo anticaspa orgánico');

-- Electrónica Ecológica (5)
INSERT INTO producto VALUES(5, 250000.0, 17, 5, 'Televisor LED de bajo consumo');
INSERT INTO producto VALUES(5, 150000.0, 18, 10, 'Televisor 32 pulgadas ecológico');
INSERT INTO producto VALUES(5, 45000.0, 19, 12, 'Radio portátil con energía solar');
INSERT INTO producto VALUES(5, 55000.0, 20, 8, 'Radio despertador ecológico');

COMMIT;