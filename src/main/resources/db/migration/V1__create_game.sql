create table public.game
(
    id             uuid not null
        primary key,
    completed_at   timestamp(6),
    correct_answer varchar(255),
    created_at     timestamp(6),
    image_url      text,
    language       varchar(255),
    score          integer,
    user_answer    varchar(255),
    user_nick_name varchar(255)
);
