drop table if exists users;
drop table if exists room;
drop table if exists label;
drop table if exists booking;
drop table if exists roomLabels;

CREATE TABLE users
(
    email    VARCHAR(80) PRIMARY KEY,
    username VARCHAR(80)
);

CREATE TABLE rooms
(
    name     VARCHAR(80) PRIMARY KEY,
    location VARCHAR(80),
    capacity INTEGER
);

CREATE TABLE labels
(
    name VARCHAR(80) PRIMARY KEY
);

CREATE TABLE bookings
(
    reservationOwner VARCHAR(80) REFERENCES users (email),
    roomName         VARCHAR(80) REFERENCES rooms (name),
    beginTime        TIMESTAMP,
    endTime          TIMESTAMP,
    PRIMARY KEY (reservationOwner, roomName, beginTime)
);

CREATE TABLE roomLabels
(
    roomname VARCHAR(80) REFERENCES rooms (name),
    label    VARCHAR(80) REFERENCES labels (name),
    PRIMARY KEY (roomname, label)
);