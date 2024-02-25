# Составление расписаний и ведение данных в тренинговом центре

## Возможные пользователи
Администрация тренингового центра, которая составляет расписание занятий и поддерживает информацию о слушателях, курсах и преподавателях в актуальном состоянии.

## Описание страниц
С любой страницы можно перейти на главную, нажав соответствующую кнопку.

![Alt text](docs/navigation.png)

### Главная страница
- Ссылки на списки всех студентов, преподавателей, курсов и компаний
- Ссылка на генератор расписания для студента/преподавателя
- Ссылка на страницу для редактирования расписания
- Адрес центра, "расписание звонков"

### Страница со списком преподавателей
- Нажав на ФИО, пользователь попадает на персональную страницу преподавателя
- Сортировка по ФИО или названию компании-работодателя, поиск по ФИО
- Нажав на кнопку "Добавить", пользователь попадает на шаблон страницы преподавателя, который должен заполнить для добавления в базу 

### Страница со списком студентов
- Нажав на ФИО, пользователь попадает на персональную страницу студента
- Поиск по ФИО
- Нажав на кнопку "Добавить", пользователь попадает на шаблон страницы студента, который должен заполнить для добавления в базу 

### Страница со списком курсов
- Нажав на курс, пользователь попадает на страницу курса
- Сортировка по названию курса или компании
- Нажав на кнопку "Добавить", пользователь попадает на шаблон страницы курса, который должен заполнить для добавления в базу 

### Страница со списком компаний
- Нажав на компанию, пользователь попадает на страницу компании
- Нажав на кнопку "Добавить", пользователь попадает на шаблон страницы компании, который должен заполнить для добавления в базу 

### Страница со списком групп
- Нажав на номер группы, пользователь попадает на страницу группы со списком студентов в ней
- Нажав на кнопку "Добавить", пользователь попадает на шаблон страницы группы, который должен заполнить для добавления в базу 

### Страницы для групп
- Номер группы и список ссылок на студентов этой группы
- Ссылка на курс, который слушает эта группа
- Редактирование списка студентов, курса

### Персональные страницы преподавателей
- Указаны ФИО, контактные данные, краткая биография
- Возможность редактирования данных
- Ссылки на проводимые курсы и компанию-работодателя

### Персональные страницы студентов
- Указаны ФИО, контактные данные
- Возможность редактирования данных
- Ссылки на все курсы, которые слушал/слушает

### Страницы для курсов
- Указаны название курса, количество часов, краткое описание
- Возможность редактирования данных
- Ссылка на преподавателя, который читает курс

### Страницы для компаний
- Указаны название компании и адрес
- Возможность редактирования данных
- Ссылки на курсы компаниии

### Генератор расписания
- Выбор режима: расписание для студента/преподавателя
- Поля для ФИО, начальной и конечной даты в расписании

### Страница с готовым расписанием
- Просмотр сгенерированного расписания
- Кнопка для сохранения расписания на стороне клиента

### Редактор расписания
- Выбор режима редактирования: добавление занятия, изменение существующего и удаление
- Поля для заполнения данных о занятии, которое необходимо добавить/изменить/удалить
- В случае редактирования уже существующего занятия поля для новых данных

## Схема базы данных
![Alt text](docs/schema.png)

## Сценарии использования

### Получение расписания на заданный интервал
- Перейти на главную
- Перейти на страницу с генератором расписания, нажав на кнопку "Получить расписание"
- Выбрать режим работы: "Расписание студента"/"Расписание преподавателя"
- Ввести корректные ФИО, начало и конец интервала
    - При некорректных ФИО или в случае, когда даты перепутаны, выводится сообщение об ошибке и просьба повторно ввести корректные данные
- Переход на страницу с готовым расписанием

### Добавление занятия в расписание
- Перейти на главную
- Перейти на страницу с редактором расписания, нажав на кнопку "Изменить расписание"
- Выбрать режим работы: "Добавить занятие"
- Заполнить данные о занятии: номер группы, дату проведения занятия, номер "пары" в соответствии с расписанием звонков, номер аудитории
- Подтвердить изменения

### Редактирование занятия в расписании
- Перейти на главную
- Перейти на страницу с редактором расписания, нажав на кнопку "Изменить расписание"
- Выбрать режим работы: "Изменить занятие"
- Заполнить данные о занятии, которое необходимо отредактировать
- Заполнить новые данные о занятии
- Подтвердить изменения

### Удаление занятия из расписания
- Перейти на главную
- Перейти на страницу с редактором расписания, нажав на кнопку "Изменить расписание"
- Выбрать режим работы: "Удалить занятие"
- Заполнить данные о занятии, которое необходимо удалить
- Подтвердить удаление

### Редактирование информации о студенте/преподавателе/курсе/компании/группе
- Перейти на главную
- Перейти на страницу с необходимым списком
- Найти в списке нужный элемент, нажать на него
- На персональной странице нажать на кнопку "Редактировать"
- Изменить желаемую информацию, подтвердить изменение

### Добавление студента/преподавателя/курса/компании
- Перейти на главную
- Перейти на страницу с необходимым списком
- На этой странице нажать на кнопку "Добавить", после пользователя перенаправит на шаблон страницы, которую он должен заполнить и сохранить