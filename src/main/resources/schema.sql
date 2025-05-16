--DROP TABLE IF EXISTS timeslots;
--DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users(
    userid BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE  IF NOT EXISTS timeslots(
    timeslotid BIGSERIAL PRIMARY KEY,
    localdate DATE,
    starttime TIME,
    endtime TIME,
    duration VARCHAR(50),
    userid INT NOT NULL REFERENCES users(userid)
);