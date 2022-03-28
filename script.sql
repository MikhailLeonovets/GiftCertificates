create table gift_certificate
(
    id                   bigserial
        constraint gift_certificate_pk
            primary key,
    name                 varchar(255)                        not null,
    description          text,
    price                numeric(8)                          not null,
    date_of_creation     timestamp default CURRENT_TIMESTAMP not null,
    date_of_modification timestamp default CURRENT_TIMESTAMP,
    duration             interval                            not null
);

alter table gift_certificate
    owner to postgres;

create unique index gift_certificate_id_uindex
    on gift_certificate (id);

create unique index gift_certificate_name_uindex
    on gift_certificate (name);

create table tag
(
    id   bigserial
        constraint tag_pk
            primary key,
    name varchar(255) not null
);

alter table tag
    owner to postgres;

create unique index tag_id_uindex
    on tag (id);

create unique index tag_name_uindex
    on tag (name);

create table gift_certificate_tag
(
    id                  bigserial
        constraint gift_certificate_tag_pk
            unique,
    gift_certificate_id bigint not null
        constraint gift_certificate_fk
            references gift_certificate
            on update cascade on delete cascade,
    tag_id              bigint not null
        constraint tag_fk
            references tag
            on update cascade on delete cascade,
    constraint gift_certificate_tag_pk_2
        primary key (tag_id, gift_certificate_id)
);

alter table gift_certificate_tag
    owner to postgres;

create unique index gift_certificate_tag_id_uindex
    on gift_certificate_tag (id);


