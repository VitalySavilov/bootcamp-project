CREATE TABLE t_user_info
(
    INFO_ID           BIGINT AUTO_INCREMENT NOT NULL,
    INFO_U_FIRSTNAME  VARCHAR(255) NULL,
    INFO_U_LASTNAME   VARCHAR(255) NULL,
    INFO_U_PATRONYMIC VARCHAR(255) NULL,
    INFO_U_EMAIL      VARCHAR(255) NULL,
    CONSTRAINT pk_t_user_info PRIMARY KEY (INFO_ID)
);

CREATE TABLE t_role
(
    R_ID     BIGINT AUTO_INCREMENT NOT NULL,
    U_R_NAME VARCHAR(255) NULL,
    CONSTRAINT pk_t_role PRIMARY KEY (R_ID)
);

CREATE TABLE t_user
(
    USER_ID BIGINT AUTO_INCREMENT NOT NULL,
    INFO_ID BIGINT NULL,
    R_ID    BIGINT NULL,
    CONSTRAINT pk_t_user PRIMARY KEY (USER_ID)
);

ALTER TABLE t_user
    ADD CONSTRAINT FK_T_USER_ON_INFO FOREIGN KEY (INFO_ID) REFERENCES t_user_info (INFO_ID);

ALTER TABLE t_user
    ADD CONSTRAINT FK_T_USER_ON_R FOREIGN KEY (R_ID) REFERENCES t_role (R_ID);

INSERT INTO t_role (R_ID, U_R_NAME)
VALUES ('1', 'Administrator'),
       ('2', 'Sale User'),
       ('3', 'Customer User'),
       ('4', 'Secure API User');

INSERT INTO t_user_info (INFO_ID, INFO_U_FIRSTNAME, INFO_U_LASTNAME, INFO_U_PATRONYMIC, INFO_U_EMAIL)
VALUES ('1', 'Ivan', 'Ivanov', 'Ivanovich', 'ivan@gmail.com'),
       ('2', 'Petr', 'Petrov', 'Petrovich', 'petr@gmail.com'),
       ('3', 'Sergey', 'Sergeev', 'Sergeevich', 'sergey@gmail.com'),
       ('4', 'Egor', 'Egorov', 'Egorovich', 'egor@gmail.com'),
       ('5', 'Aleksey', 'Alekseev', 'Alekseevich', 'aleksey@gmail.com'),
       ('6', 'Igor', 'Igorev', 'Igorevich', 'igor@gmail.com'),
       ('7', 'Semen', 'Semenov', 'Semenovich', 'semen@gmail.com'),
       ('8', 'Roman', 'Romanov', 'Romanovich', 'roman@gmail.com'),
       ('9', 'Timofei', 'Timofeev', 'Timofeevich', 'timofei@gmail.com'),
       ('10', 'Denis', 'Denisov', 'Denisovich', 'denis@gmail.com'),
       ('11', 'Fedor', 'Fedorov', 'Fedorovich', 'fedor@gmail.com'),
       ('12', 'Artem', 'Artemov', 'Artemovich', 'artem@gmail.com'),
       ('13', 'Pavel', 'Pavlov', 'Pavlovich', 'pavel@gmail.com'),
       ('14', 'Oleg', 'Olegov', 'Olegovich', 'oleg@gmail.com'),
       ('15', 'Mark', 'Markov', 'Markovich', 'mark@gmail.com'),
       ('16', 'Nikolay', 'Nikolaev', 'Nikolaeevich', 'nikolay@gmail.com'),
       ('17', 'Vadim', 'Vadimov', 'Vadimovich', 'vadim@gmail.com'),
       ('18', 'Ruslan', 'Ruslanov', 'Ruslanovich', 'ruslan@gmail.com'),
       ('19', 'Timur', 'Timurov', 'Timurovich', 'timur@gmail.com'),
       ('20', 'Michail', 'Michailov', 'Michailovich', 'michail@gmail.com'),
       ('21', 'Boris', 'Borisov', 'Borisovich', 'boris@gmail.com'),
       ('22', 'Viktor', 'Viktorov', 'Viktorovich', 'viktor@gmail.com'),
       ('23', 'Gleb', 'Glebov', 'Glebovich', 'gleb@gmail.com');

INSERT INTO t_user (USER_ID, INFO_ID, R_ID)
VALUES ('1', '1', '1'),
       ('2', '2', '2'),
       ('3', '3', '3'),
       ('4', '4', '4'),
       ('5', '5', '1'),
       ('6', '6', '2'),
       ('7', '7', '3'),
       ('8', '8', '4'),
       ('9', '9', '1'),
       ('10', '10', '2'),
       ('11', '11', '3'),
       ('12', '12', '4'),
       ('13', '13', '1'),
       ('14', '14', '2'),
       ('15', '15', '3'),
       ('16', '16', '4'),
       ('17', '17', '1'),
       ('18', '18', '2'),
       ('19', '19', '3'),
       ('20', '20', '4'),
       ('21', '21', '1'),
       ('22', '22', '2'),
       ('23', '23', '3');
