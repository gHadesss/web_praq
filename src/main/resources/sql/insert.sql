\c web_praq
-- SET search_path TO web_praq;

INSERT INTO companies(title, address) VALUES
    ('ОАО "Курсы"', '{"city":"г. Москва", "street":"ул. Пушкина", "house":"д. 9"}'),
    ('ОАО "Яндекс"', '{"city":"г. Санкт-Петербург", "street":"ул. Колотушкина", "house":"д. 1А"}');

INSERT INTO tutors(company_id, surname, name, patronymic, description, email, phone_number) VALUES
    (1, 'Иванов', 'Иван', 'Иванович', 'Характер мягкий, не женат.', 'ivanov@mail.ru', '+74951234567'),
    (2, 'Баширов', 'Артур', 'Вадимович', 'Любитель кроканта.', 'bashirov@google.com', '+74957654321');

INSERT INTO courses(title, total_hours, company_id, description) VALUES
    ('Курс 2', 10, 1, 'Парни, вы издеваетесь?'),
    ('Курс 1', 3, 2, 'Тренировки 5.0');

INSERT INTO students(surname, name, patronymic, email, phone_number) VALUES
    ('Иванов', 'Петр', 'Иванович', 'ivanov@mail.ru', '+74951234567'),
    ('Сидоров', 'Константин', 'Сидорович', 'sidorov@yandex.ru', '+74954567123');


INSERT INTO groups(course_id, tutor_id) VALUES
    (1, 2),
    (2, 1);

INSERT INTO group_of_students(group_id, student_id) VALUES
    (1, 1),
    (1, 2),
    (2, 2);

INSERT INTO classes(group_id, class_datetime, room_number, class_duration) VALUES
    (1, '20240401 15:00:00', 1, 1),
    (1, '20240402 15:00:00', 1, 1),
    (1, '20240403 15:00:00', 1, 1);
