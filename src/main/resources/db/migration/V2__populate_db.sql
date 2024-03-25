-- Добавление работников
INSERT INTO worker (ID, NAME, BIRTHDAY, LEVEL, SALARY) VALUES
                                                           (1, 'John Doe', '1990-01-15', 'Trainee', 800),
                                                           (2, 'Jane Smith', '1985-05-20', 'Junior', 1200);

-- Добавление клиентов
INSERT INTO client (ID, NAME) VALUES
                                  (1, 'Acme Corporation'),
                                  (2, 'XYZ Corp');

-- Добавление проектов
INSERT INTO project (ID, CLIENT_ID, START_DATE, FINISH_DATE) VALUES
                                                                 (1, 1, '2022-01-01', '2022-06-30'),
                                                                 (2, 2, '2022-02-15', '2022-09-15');

-- Назначение работников на проекты
INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 2);