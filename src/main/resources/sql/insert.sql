\c web_praq
SET search_path TO web_praq;

INSERT INTO companies(title, address) VALUES
    ('ООО "Курсы"', '{"Город":"г. Москва", "Улица":"ул. Пушкина", "Дом":"д. 9"}');

INSERT INTO tutors(company_id, surname, name, patronymic, description, email, phone_number) VALUES
    (1, 'Иванов', 'Иван', 'Иванович', 'Характер мягкий, не женат.', 'ivanov@mail.ru', '+74951234567');

INSERT INTO courses(title, total_hours, company_id, description) VALUES
    ('Курс', 2, 1, 'Парни, вы издеваетесь?');

INSERT INTO students(surname, name, patronymic, email, phone_number) VALUES
    ('Иванов', 'Иван', 'Иванович', 'ivanov@mail.ru', '+74951234567');

INSERT INTO groups(course_id, tutor_id) VALUES
    (1, 1);

INSERT INTO group_of_students(group_id, student_id) VALUES
    (1, 1);

INSERT INTO classes(group_id, class_datetime, room_number, class_duration) VALUES
    (1, '20240401 15:00:00', 1, 1);
