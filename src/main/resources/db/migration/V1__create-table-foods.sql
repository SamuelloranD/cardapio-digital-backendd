CREATE TABLE foods(
    id SERIAL PRIMARY KEY,
    title TEXT not null,
    price INTEGER NOT NULL,
    image TEXT
);