\c web_praq
SET search_path TO web_praq;

DROP TABLE IF EXISTS companies CASCADE;
CREATE TABLE companies (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    address JSONB NOT NULL
);

DROP TABLE IF EXISTS tutors CASCADE;
CREATE TABLE tutors (
    id SERIAL PRIMARY KEY,
    company_id INTEGER REFERENCES companies(id) ON DELETE CASCADE,
    surname VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    patronymic VARCHAR(30),
    description TEXT NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS courses CASCADE;
CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    company_id INTEGER REFERENCES companies(id) ON DELETE CASCADE,
    total_hours DOUBLE PRECISION NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT pos_hours CHECK (total_hours > 0)
);

DROP TABLE IF EXISTS groups CASCADE;
CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    course_id INTEGER REFERENCES courses(id) ON DELETE CASCADE,
    tutor_id INTEGER REFERENCES tutors(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    surname VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    patronymic VARCHAR(30),
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS group_of_students CASCADE;
CREATE TABLE group_of_students (
    student_id INTEGER REFERENCES students(id) ON DELETE CASCADE,
    group_id INTEGER REFERENCES groups(id) ON DELETE CASCADE,
    PRIMARY KEY (student_id, group_id)
);

DROP TABLE IF EXISTS classes CASCADE;
CREATE TABLE classes (
    group_id INTEGER REFERENCES groups(id) ON DELETE CASCADE,
    class_datetime TIMESTAMP NOT NULL, 
    room_number INTEGER NOT NULL CHECK (room_number > 0),
    class_duration DOUBLE PRECISION NOT NULL CHECK (class_duration > 0),
    PRIMARY KEY (group_id, class_datetime, room_number)
);