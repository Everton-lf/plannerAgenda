INSERT INTO schedule_entries (id, day_of_week, time_range, description, completed)
VALUES
  (nextval('hibernate_sequence'), 'SEGUNDA', '06:00-06:30', 'Acordar, higiene', false),
  (nextval('hibernate_sequence'), 'SEGUNDA', '06:30-08:00', 'Academia', false),
  (nextval('hibernate_sequence'), 'SEGUNDA', '08:20-09:00', 'Café / crianças', false),
  (nextval('hibernate_sequence'), 'SEGUNDA', '09:00-09:50', 'Inglês ativo', false),
  (nextval('hibernate_sequence'), 'SEGUNDA', '10:00-16:00', 'Trabalho', false),
  (nextval('hibernate_sequence'), 'SEGUNDA', '16:30-17:30', 'Pausa + descanso', false),
  (nextval('hibernate_sequence'), 'SEGUNDA', '17:30-18:15', 'Corrida', false),
  (nextval('hibernate_sequence'), 'SEGUNDA', '18:40-20:00', 'Cursos Caixa Verso', false),
  (nextval('hibernate_sequence'), 'SEGUNDA', '20:00-22:00', 'Faculdade ADS', false);
-- repete para os outros dias...
