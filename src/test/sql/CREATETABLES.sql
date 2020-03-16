CREATE TABLE user_account
(
    email    VARCHAR(80) PRIMARY KEY,
    username VARCHAR(80)
);

CREATE TABLE room
(
    name     VARCHAR(80) PRIMARY KEY,
    location VARCHAR(80),
    capacity INTEGER
);

CREATE TABLE label
(
    name VARCHAR(80) PRIMARY KEY
);

CREATE TABLE booking
(
    reservation_owner VARCHAR(80) REFERENCES user_account (email),
    room_name         VARCHAR(80) REFERENCES room (name),
    begin_time        TIMESTAMP,
    end_time          TIMESTAMP,
    PRIMARY KEY (reservation_owner, room_name, begin_time)
);

CREATE TABLE room_label
(
    roomname VARCHAR(80) REFERENCES room (name),
    label    VARCHAR(80) REFERENCES label (name),
    PRIMARY KEY (roomname, label)
);