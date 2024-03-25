CREATE TABLE worker(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(1000) NOT NULL,
                       birthday DATE,
                       level ENUM('Trainee', 'Junior', 'Middle', 'Senior') NOT NULL,
                       salary INT,
                       CONSTRAINT worker_name_length CHECK (LENGTH(name)>=2),
                       CONSTRAINT year_values CHECK (birthday >= '1901-01-01'),
                       CONSTRAINT salary_values CHECK (salary <= 100000 AND salary >= 100)
);

CREATE TABLE client(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(1000) NOT NULL,
                       CONSTRAINT client_name_length CHECK (LENGTH(name)>=2)
);

CREATE TABLE project(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        client_id INT NOT NULL,
                        start_date DATE,
                        finish_date DATE,
                        CONSTRAINT client_id_fk FOREIGN KEY(client_id) REFERENCES client(id)
);

CREATE TABLE project_worker(
                               project_id INT NOT NULL,
                               worker_id INT NOT NULL,
                               PRIMARY KEY (project_id, worker_id),
                               FOREIGN KEY (project_id) REFERENCES project(id),
                               FOREIGN KEY (worker_id) REFERENCES worker(id)
);