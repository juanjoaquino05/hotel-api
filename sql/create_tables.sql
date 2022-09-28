create sequence country_sequence;

alter sequence country_sequence owner to postgres;

create sequence hotel_sequence;

alter sequence hotel_sequence owner to postgres;

create sequence reservation_sequence;

alter sequence reservation_sequence owner to postgres;

create sequence room_sequence;

alter sequence room_sequence owner to postgres;

create sequence user_sequence;

alter sequence user_sequence owner to postgres;

create table country
(
    id   bigint not null
        primary key,
    name text   not null
);

alter table country
    owner to postgres;

create table hotel
(
    id         bigint not null
        primary key,
    name       text   not null
        constraint hotel_name_unique
            unique,
    country_id bigint not null
        constraint country_hotel_fk
            references country
);

alter table hotel
    owner to postgres;

create table room
(
    id          bigint    not null
        primary key,
    created_at  timestamp not null,
    room_number text      not null,
    hotel_id    bigint    not null
        constraint hotel_room_fk
            references hotel
);

alter table room
    owner to postgres;

create table usert
(
    id         bigint    not null
        primary key,
    created_at timestamp not null,
    name       text      not null
        constraint user_name_unique
            unique
);

alter table usert
    owner to postgres;

create table reservation
(
    id                bigint                not null
        primary key,
    created_date      timestamp             not null,
    end_date          timestamp             not null,
    is_cancelled      boolean default false not null,
    last_updated_date timestamp             not null,
    start_date        timestamp             not null,
    room_id           bigint                not null
        constraint reservation_room_fk
            references room,
    user_id           bigint                not null
        constraint reservation_user_fk
            references usert
);

alter table reservation
    owner to postgres;

insert into country values (nextval('country_sequence'), 'canada');
insert into hotel values (nextval('hotel_sequence'), 'test_hotel', 1 );
insert into room values (nextval('room_sequence'), CURRENT_TIMESTAMP, 'R01', 1 );
insert into usert values (nextval('user_sequence'), CURRENT_TIMESTAMP, 'tester');