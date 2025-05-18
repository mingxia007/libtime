--DROP TABLE IF EXISTS timeslots;
--DROP TABLE IF EXISTS users;


CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE  IF NOT EXISTS timeslots(
    id BIGSERIAL PRIMARY KEY,
    local_date DATE NOT NULL,
    start_time TIME,
    end_time TIME,
    duration VARCHAR(50),
    user_id INT NOT NULL REFERENCES users(id)
);

CREATE TABLE  IF NOT EXISTS tasks(
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(100) NOT NULL,
    finished BOOLEAN NOT NULL DEFAULT FALSE,
    user_id INT NOT NULL REFERENCES users(id)
);
