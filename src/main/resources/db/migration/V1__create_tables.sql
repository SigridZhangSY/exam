CREATE TABLE users (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role VARCHAR(255),
  password varchar(255)
);

insert into users (id, name, email, role, password) values ("001", "admin", "admin@example.com", "BACKGROUND_JOB", "$2a$04$DbgJbGA4dkQSzAvXvJcGBOv5kHuMBzrWfne3x3Cx4JQv4IJcxtBIW");

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