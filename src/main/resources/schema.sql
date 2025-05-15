CREATE TABLE IF NOT EXISTS users(
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE  IF NOT EXISTS timeslots(
    timeslot_id BIGSERIAL PRIMARY KEY,
    local_date DATE,
    start_time TIME,
    end_time TIME,
    duration INTERVAL,
    user_id int NOT NULL REFERENCES users(user_id)
);