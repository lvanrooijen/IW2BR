CREATE SEQUENCE IF NOT EXISTS exam_attempts_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS exam_attempts(
    id BIGINT PRIMARY KEY,
    score DOUBLE PRECISION,
    is_completed BOOLEAN,
    started_at TIMESTAMP,
    completed_at TIMESTAMP,
    exam_id BIGINT REFERENCES public.exams(id),
    environment_id BIGINT REFERENCES public.environments(id)
);

CREATE SEQUENCE IF NOT EXISTS question_answers_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE IF NOT EXISTS question_answers(
    id BIGINT PRIMARY KEY,
    actual_correct_answers INTEGER,
    expected_correct_answers INTEGER,
    question_id BIGINT REFERENCES public.questions(id),
    exam_attempt_id BIGINT REFERENCES public.exam_attempts(id)
);

CREATE SEQUENCE question_answers_answers_seq START WITH 1 INCREMENT BY 50;

-- Join table for question_answers and answers
CREATE TABLE IF NOT EXISTS question_answers_answers(
        question_answer BIGINT REFERENCES public.question_answers(id),
        answers BIGINT REFERENCES public.answers(id)
);