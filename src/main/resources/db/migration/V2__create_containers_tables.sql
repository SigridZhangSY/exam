CREATE TABLE providers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE jobs (
  id INT AUTO_INCREMENT PRIMARY KEY,
  provider_id INT,
  FOREIGN KEY (provider_id) REFERENCES providers(id)
);

CREATE TABLE containers (
  id INT PRIMARY KEY
);

CREATE TABLE jobcontainer (
  job_id INT,
  container_id INT,
  FOREIGN KEY (job_id) REFERENCES jobs(id),
  FOREIGN KEY (container_id) REFERENCES containers(id)
);