CREATE DATABASE feedme_db;
USE feedme_db;

CREATE USER 'devuser1'@'localhost' IDENTIFIED BY 'khz@BxDZ';
GRANT ALL PRIVILEGES ON feedme_db.* TO 'devuser1'@'localhost';
FLUSH PRIVILEGES;
