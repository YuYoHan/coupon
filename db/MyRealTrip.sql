# 데이터 베이스 생성
create database myRealTrip;
use myRealTrip;

# 유저 테이블
create table users
(
    user_id    bigint primary key auto_increment,
    user_email varchar(300)  not null,
    user_name  varchar(300)  not null,
    user_phone varchar(300)  not null,
    user_addr  varchar(1000) not null,
    user_role  varchar(300)  not null
);

# 관리자
create table admin
(
    admin_id    bigint primary key auto_increment,
    admin_email varchar(300)  not null,
    admin_name  varchar(300)  not null,
    admin_phone varchar(300)  not null,
    admin_addr  varchar(1000) not null,
    admin_role  varchar(300)  not null
);

# 문의하기
create table questions
(
    qu_id     bigint primary key auto_increment,
    qu_title  varchar(300) not null,
    qu_answer varchar(3000),
    user_id   bigint references users (user_id)
);


# 숙소
create table room
(
    room_id      bigint primary key auto_increment,
    room_name    varchar(300)  not null,
    apply_count  int           not null,
    room_details varchar(3000) not null
);

# 숙소 옵션
create table options
(
    option_id     bigint primary key auto_increment,
    bed_plus      varchar(300),
    add_breakfast varchar(300),
    room_id       bigint references room (room_id)
);


# 관리자 답변 테이블
create table answers
(
    ans_id   bigint primary key auto_increment,
    answer   varchar(3000) not null,
    admin_id bigint references admin (admin_id)
);

# 공항 테이블
create table airports
(
    airport_id       bigint primary key auto_increment,
    airport_name     varchar(300)  not null,
    airport_location varchar(1000) not null
);

# 비행기 테이블
create table airplanes
(
    airplane_id       bigint primary key auto_increment,
    airplane_model    varchar(300) not null,
    airplane_company  varchar(300) not null,
    airplane_capacity int          not null,
    airport_id        bigint references airports (airport_id)
);

# 운행 테이블
create table operations
(
    operation_id            bigint primary key auto_increment,
    operation_num           varchar(300) not null,
    operation_departureTime DATETIME,
    operation_arriveTime    DATETIME,
    airplane_id             bigint references airplanes (airplane_id)
);

# 공지사항
create table notices
(
    notice_id      bigint primary key auto_increment,
    notice_title   varchar(300)  not null,
    notice_details varchar(3000) not null,
    admin_id       bigint references admin (admin_id)
);

# 숙소 예약
create table room_reservations
(
    room_reservations_id      bigint primary key auto_increment,
    room_reservations_start   varchar(300),
    room_reservations_end     varchar(300),
    room_reservations_regTime datetime default now(),
    room_id                   bigint references room (room_id),
    user_id                   bigint references users (user_id)
);

# 비행기 예매
create table airplane_reservations
(
    airplane_reservation_id      bigint primary key auto_increment,
    airplane_reservation_regTime datetime default now(),
    airplane_departureTime       datetime,
    airplane_arriveTime          datetime,
    airplane_id                  bigint references airplanes (airplane_id),
    user_id                      bigint references users (user_id)
);

# 투어티켓
create table tourTicket
(
    ticket_id      bigint primary key auto_increment,
    review         varchar(3000),
    ticket_details varchar(3000) not null,
    airplane_id    bigint references airplanes (airplane_id),
    room_id        bigint references room (room_id)
);

# 찜하기
create table pickList
(
    pick_id   bigint primary key auto_increment,
    user_id   bigint references users (user_id),
    ticket_id bigint references tourTicket (ticket_id)
);

# 상품
create table items
(
    item_id     bigint primary key auto_increment,
    airplane_id bigint references airplanes (airplane_id),
    room_id     bigint references room (room_id),
    ticket_id   bigint references tourTicket (ticket_id)
);


# 유저 정보 보기
create table myInfo
(
    code_id                 bigint primary key auto_increment,
    room_reservations_id    bigint references room_reservations (room_reservations_id),
    airplane_reservation_id bigint references airplane_reservations (airplane_reservation_id),
    qu_id                   bigint references questions (qu_id),
    pick_id                 bigint references pickList (pick_id),
    user_id                 bigint references users (user_id)
);


