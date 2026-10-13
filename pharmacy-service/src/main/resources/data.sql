INSERT INTO medicines (id, name, price) VALUES (1, 'Panadol', 35000) ON CONFLICT (id) DO NOTHING;
SELECT setval(pg_get_serial_sequence('medicines', 'id'), GREATEST((SELECT MAX(id) FROM medicines), 1));
