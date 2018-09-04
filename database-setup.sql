CREATE DATABASE job_management;
\c job_management;
CREATE TABLE job_info (id int NOT NULL, name varchar(20) UNIQUE NOT NULL, description varchar(100), priority varchar(20), execute_now varchar(20), status varchar(20) );
CREATE SEQUENCE job_sequence_generator START 1;
CREATE unique INDEX job_index ON job_info(name);