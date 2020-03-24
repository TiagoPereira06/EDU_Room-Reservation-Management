drop table if exists roomLabels;
drop table if exists labels;
drop table if exists bookings;
drop table if exists users;
drop table if exists rooms;

CREATE TABLE users
(
    email    VARCHAR(80) PRIMARY KEY,
    username VARCHAR(80)
);

CREATE TABLE rooms
(
    name     VARCHAR(80) PRIMARY KEY,
    location VARCHAR(80),
    capacity INTEGER,
    description VARCHAR(500)

);

CREATE TABLE labels
(
    name VARCHAR(80) PRIMARY KEY
);

CREATE TABLE bookings
(
    bid serial primary key,
    reservationOwner VARCHAR(80) REFERENCES users (email),
    roomName         VARCHAR(80) REFERENCES rooms (name),
    beginTime        TIMESTAMP,
    endTime          TIMESTAMP
);

CREATE TABLE roomLabels
(
    roomName VARCHAR(80) REFERENCES rooms (name),
    label    VARCHAR(80) REFERENCES labels (name),
    PRIMARY KEY (roomName, label)
);