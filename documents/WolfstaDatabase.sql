DROP USER wolfstaUser CASCADE;

CREATE USER wolfstaUser
identified by p4ssw0rd
default tablespace users
TEMPORARY TABLESPACE temp
QUOTA 10m on users;



GRANT CONNECT TO wolfstaUser;
GRANT RESOURCE TO wolfstaUser;
grant create session to wolfstaUser;
grant create table to wolfstaUser;
grant create view to wolfstaUser;



CREATE TABLE card_lookup (
    card_id       NUMBER NOT NULL,
    card_number   NUMBER,
    game_id       NUMBER NOT NULL,
    player_id     NUMBER NOT NULL,
    state         NUMBER NOT NULL,
    meld_id       NUMBER
);

ALTER TABLE card_lookup ADD CONSTRAINT card_lookup_pk PRIMARY KEY ( card_id );

CREATE TABLE friend_lookup (
    friendship_id   NUMBER NOT NULL,
    user_id_1       NUMBER,
    user_id_2       NUMBER
);

ALTER TABLE friend_lookup ADD CONSTRAINT friend_pk PRIMARY KEY ( friendship_id );

CREATE TABLE game (
    game_id           NUMBER NOT NULL,
    game_name         VARCHAR2(50),
    game_password     NUMBER,
    players           NUMBER,
    turn              NUMBER,
    time_limit        NUMBER,
    game_start_date   NUMBER,
    game_resolved     TIMESTAMP WITH TIME ZONE,
    game_won          NUMBER,
    game_lost         NUMBER
);

ALTER TABLE game ADD CONSTRAINT game_pk PRIMARY KEY ( game_id );

ALTER TABLE game ADD CONSTRAINT game__un UNIQUE ( game_name,game_password );

CREATE TABLE player (
    player_id       NUMBER NOT NULL,
    player_number   NUMBER,
    score           NUMBER,
    user_id         NUMBER NOT NULL,
    game_id         NUMBER NOT NULL,
    team_team_id    NUMBER NOT NULL,
    team_number     NUMBER
);

ALTER TABLE player ADD CONSTRAINT player_pk PRIMARY KEY ( player_id );

CREATE TABLE reward (
    reward_id     NUMBER NOT NULL,
    user_id       NUMBER NOT NULL,
    reward_rule   NUMBER
);

ALTER TABLE reward ADD CONSTRAINT reward_pk PRIMARY KEY ( reward_id );

CREATE TABLE team (
    team_id             NUMBER NOT NULL,
    team_games_won      NUMBER,
    team_games_played   NUMBER,
    tour_id             NUMBER NOT NULL,
    user_id1            NUMBER NOT NULL,
    user_id2            NUMBER NOT NULL
);

ALTER TABLE team ADD CONSTRAINT team_pk PRIMARY KEY ( team_id );

CREATE TABLE tour (
    tour_id     NUMBER NOT NULL,
    tour_type   NUMBER
);

ALTER TABLE tour ADD CONSTRAINT tour_pk PRIMARY KEY ( tour_id );

CREATE TABLE "user" (
    user_id             NUMBER NOT NULL,
    username            VARCHAR2(50) NOT NULL,
    password            VARCHAR2(50) NOT NULL,
    avatar              VARCHAR2(25),
    user_games_won      NUMBER,
    user_games_played   NUMBER,
    card_front          NUMBER,
    card_back           NUMBER
);

ALTER TABLE "user" ADD CONSTRAINT user_pk PRIMARY KEY ( user_id );

ALTER TABLE "user" ADD CONSTRAINT user__un UNIQUE ( username,password );

CREATE TABLE user_session (
    token        NUMBER NOT NULL,
    user_id      NUMBER NOT NULL,
    start_time   TIMESTAMP WITH LOCAL TIME ZONE
);

ALTER TABLE user_session ADD CONSTRAINT user_session_pk PRIMARY KEY ( token );

ALTER TABLE card_lookup
    ADD CONSTRAINT card_lookup_game_fk FOREIGN KEY ( game_id )
        REFERENCES game ( game_id )
            ON DELETE CASCADE;

ALTER TABLE card_lookup
    ADD CONSTRAINT card_lookup_player_fk FOREIGN KEY ( player_id )
        REFERENCES player ( player_id )
            ON DELETE CASCADE;

ALTER TABLE player
    ADD CONSTRAINT player_game_fk FOREIGN KEY ( game_id )
        REFERENCES game ( game_id )
            ON DELETE CASCADE;

ALTER TABLE player
    ADD CONSTRAINT player_team_fk FOREIGN KEY ( team_team_id )
        REFERENCES team ( team_id );

ALTER TABLE player
    ADD CONSTRAINT player_user_fk FOREIGN KEY ( user_id )
        REFERENCES "user" ( user_id )
            ON DELETE CASCADE;

ALTER TABLE reward
    ADD CONSTRAINT reward_user_fk FOREIGN KEY ( user_id )
        REFERENCES "user" ( user_id )
            ON DELETE CASCADE;

ALTER TABLE team
    ADD CONSTRAINT team_tour_fk FOREIGN KEY ( tour_id )
        REFERENCES tour ( tour_id )
            ON DELETE CASCADE;

ALTER TABLE team
    ADD CONSTRAINT team_user_fk FOREIGN KEY ( user_id1 )
        REFERENCES "user" ( user_id )
            ON DELETE CASCADE;

ALTER TABLE team
    ADD CONSTRAINT team_user_fkv2 FOREIGN KEY ( user_id2 )
        REFERENCES "user" ( user_id )
            ON DELETE CASCADE;

ALTER TABLE user_session
    ADD CONSTRAINT user_session_user_fk FOREIGN KEY ( user_id )
        REFERENCES "user" ( user_id )
            ON DELETE CASCADE;

--create a sequence to use for insert statments

CREATE SEQUENCE card_id_seq START WITH 1;
CREATE SEQUENCE friendship_id_seq START WITH 1;
CREATE SEQUENCE game_id_seq START WITH 1;
CREATE SEQUENCE player_id_seq START WITH 1;
CREATE SEQUENCE reward_id_seq START WITH 1;
CREATE SEQUENCE team_id_seq START WITH 1;
CREATE SEQUENCE tour_id_seq START WITH 1;
CREATE SEQUENCE token_seq START WITH 1;

-- create the Triggers for insert/update statments

DROP TRIGGER friendship_key_trigger;

create or replace trigger friendship_key_trigger
before insert or update on friend_lookup
for each row
begin
  if INSERTING then
    select friendship_ID_SEQ.NEXTVAL into :new.friendship_id from dual;
  elsif UPDATING then
    select :old.friendship_id into :new.friendship_id from dual;
 end if;
end;
/

create or replace trigger token_key_trigger
before insert or update on user_session
for each row
begin
  if INSERTING then
    select token_SEQ.NEXTVAL into :new.token from dual;
  elsif UPDATING then
    select :old.token into :new.token from dual;
 end if;
end;
/

drop trigger token_key_trigger;

create or replace trigger tour_key_trigger
before insert or update on tour
for each row
begin
  if INSERTING then
    select tour_ID_SEQ.NEXTVAL into :new.tour_id from dual;
  elsif UPDATING then
    select :old.tour_id into :new.tour_id from dual;
 end if;
end;
/

create or replace trigger team_key_trigger
before insert or update on team
for each row
begin
  if INSERTING then
    select team_ID_SEQ.NEXTVAL into :new.team_id from dual;
  elsif UPDATING then
    select :old.team_id into :new.team_id from dual;
 end if;
end;
/

create or replace trigger reward_key_trigger
before insert or update on reward
for each row
begin
  if INSERTING then
    select reward_ID_SEQ.NEXTVAL into :new.reward_id from dual;
  elsif UPDATING then
    select :old.reward_id into :new.reward_id from dual;
 end if;
end;
/

create or replace trigger player_key_trigger
before insert or update on player
for each row
begin
  if INSERTING then
    select player_ID_SEQ.NEXTVAL into :new.player_id from dual;
  elsif UPDATING then
    select :old.player_id into :new.player_id from dual;
 end if;
end;
/

create or replace trigger game_key_trigger
before insert or update on game
for each row
begin
  if INSERTING then
    select game_ID_SEQ.NEXTVAL into :new.game_id from dual;
  elsif UPDATING then
    select :old.game_id into :new.game_id from dual;
 end if;
end;
/

create or replace trigger card_lookup_key_trigger
before insert or update on card_lookup
for each row
begin
  if INSERTING then
    select CARD_ID_SEQ.NEXTVAL into :new.card_id from dual;
  elsif UPDATING then
    select :old.card_id into :new.card_id from dual;
 end if;
end;
/