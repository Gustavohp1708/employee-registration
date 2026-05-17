create table addresses (

    id bigint not null auto_increment,

    street varchar(255) not null,
    neighborhood varchar(255) not null,
    postal_code varchar(25) not null,
    number varchar(50) not null,
    complement varchar(255) not null,
    city varchar(255) not null,
    state varchar(255) not null,

    primary key (id)
);