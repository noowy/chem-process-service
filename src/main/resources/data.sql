DELETE FROM chem_tech.chem_material_parameter WHERE material_id BETWEEN 0 AND 6;
DELETE FROM chem_tech.chem_material WHERE id BETWEEN 0 AND 6;
DELETE FROM chem_tech.chem_parameter WHERE id BETWEEN 0 AND 7;

---------------
-- Parameters
---------------

INSERT INTO chem_tech.chem_parameter (id, name, type, units) VALUES
	(0, 'Density', 0, 'Kg/m^3'),
	(1, 'Specific Heat', 0, 'J/(kg*C)'),
	(2, 'Melting Temperature', 0, 'C'),
	(3, 'Consistency Ratio', 1, 'Pa*s^n'),
	(4, 'Temperature Viscosity Ratio', 1, '1/C'),
	(5, 'Reduction Temperature', 1, 'C'),
	(6, 'Flow Index', 1, NULL),
	(7, 'Heat Transfer Coefficient', 1, 'W/(m^2 * C)')
;

-------------
-- Materials
-------------

INSERT INTO chem_tech.chem_material (id, name, description) VALUES
	(0, 'Low Density Polyethylene', ''),
	(1, 'High Density Polyethylene', ''),
	(2, 'Polypropylene', ''),
	(3, 'Polyvinylchloride', ''),
	(4, 'Polystyrene', ''),
	(5, 'Polymethyl Methacrylate', ''),
	(6, 'Polyamide', '')
;

------------------------
-- Materials parameters
------------------------

-- Low Density Polyethylene
INSERT INTO chem_tech.chem_material_parameter (material_id, param_id, value) VALUES
	(0, 0, 920),
	(0, 1, 2300),
	(0, 2, 120),
	(0, 3, 50000),
	(0, 4, 0.03),
	(0, 5, 120),
	(0, 6, 0.35),
	(0, 7, 250)
;

-- High Density Polyethylene
INSERT INTO chem_tech.chem_material_parameter (material_id, param_id, value) VALUES
	(1, 0, 950),
	(1, 1, 2250),
	(1, 2, 120),
	(1, 3, 29940),
	(1, 4, 0.007),
	(1, 5, 190),
	(1, 6, 0.35),
	(1, 7, 400)
;

-- Polypropylene
INSERT INTO chem_tech.chem_material_parameter (material_id, param_id, value) VALUES
	(2, 0, 890),
	(2, 1, 2300),
	(2, 2, 175),
	(2, 3, 1550),
	(2, 4, 0.015),
	(2, 5, 180),
	(2, 6, 0.4),
	(2, 7, 2000)
;

-- Polyvinylchloride
INSERT INTO chem_tech.chem_material_parameter (material_id, param_id, value) VALUES
	(3, 0, 1200),
	(3, 1, 2100),
	(3, 2, 140),
	(3, 3, 10000),
	(3, 4, 0.04),
	(3, 5, 170),
	(3, 6, 0.3),
	(3, 7, 450)
;

-- Polystyrene
INSERT INTO chem_tech.chem_material_parameter (material_id, param_id, value) VALUES
	(4, 0, 1060),
	(4, 1, 1200),
	(4, 2, 170),
	(4, 3, 11000),
	(4, 4, 0.018),
	(4, 5, 200),
	(4, 6, 0.35),
	(4, 7, 500)
;

-- Polymethyl Methacrylate
INSERT INTO chem_tech.chem_material_parameter (material_id, param_id, value) VALUES
	(5, 0, 1180),
	(5, 1, 1450),
	(5, 2, 160),
	(5, 3, 26200),
	(5, 4, 0.04),
	(5, 5, 180),
	(5, 6, 0.4),
	(5, 7, 700)
;

-- Polyamide
INSERT INTO chem_tech.chem_material_parameter (material_id, param_id, value) VALUES
	(6, 0, 1130),
	(6, 1, 2150),
	(6, 2, 225),
	(6, 3, 2500),
	(6, 4, 0.017),
	(6, 5, 225),
	(6, 6, 0.7),
	(6, 7, 500)
;
