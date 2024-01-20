--liquibase formatted sql

--changeset vitalik.savilov@gmail.com:1

CREATE TABLE t_user_info
(
    INFO_ID           BIGINT AUTO_INCREMENT NOT NULL,
    INFO_U_FIRSTNAME  VARCHAR(255) NULL,
    INFO_U_LASTNAME   VARCHAR(255) NULL,
    INFO_U_PATRONYMIC VARCHAR(255) NULL,
    INFO_U_EMAIL      VARCHAR(255) NULL,
    CONSTRAINT pk_t_user_info PRIMARY KEY (INFO_ID)
);

--changeset vitalik.savilov@gmail.com:2

CREATE TABLE t_role
(
    R_ID     BIGINT AUTO_INCREMENT NOT NULL,
    U_R_NAME VARCHAR(255) NULL,
    CONSTRAINT pk_t_role PRIMARY KEY (R_ID)
);

--changeset vitalik.savilov@gmail.com:3

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
