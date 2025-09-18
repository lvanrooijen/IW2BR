CREATE SEQUENCE IF NOT EXISTS flashcard_decks_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS flashcard_decks(
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    environment_id BIGINT REFERENCES public.environments(id)
);

CREATE SEQUENCE IF NOT EXISTS flashcards_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS flashcards(
    id BIGINT PRIMARY KEY,
    front_body VARCHAR(255),
    back_body VARCHAR(255),
    positive_flags INTEGER,
    negative_flags INTEGER,
    last_seen DATE,
    flashcard_deck_id BIGINT REFERENCES public.flashcard_decks(id),
    tag_id BIGINT REFERENCES public.tags(id)
);
