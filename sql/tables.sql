create table if not exists user
(
    id         bigint AUTO_INCREMENT PRIMARY KEY,
    user_name  varchar(50),
    first_name varchar(25),
    last_name  varchar(25)
);

create table if not exists task
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    name        varchar(100),
    description varchar(250),
    date_time   datetime,
    user_id     bigint,
    FOREIGN KEY (user_id)
        REFERENCES user (id)
);