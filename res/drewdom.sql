CREATE TABLE zamowienie (
  nr_zamowienia    varchar(50) PRIMARY KEY NOT NULL UNIQUE,
  data             date DEFAULT CURRENT_DATE,
  data_realizacji  date DEFAULT NULL
);

CREATE TABLE mebel (
  nr_katalogowy  varchar(50) PRIMARY KEY NOT NULL UNIQUE,
  nazwa          varchar(50)
);

CREATE TABLE pracownik (
  id       integer PRIMARY KEY AUTOINCREMENT NOT NULL,
  imie     varchar(50),
  nawisko  varchar(50) NOT NULL
);

CREATE TABLE element (
  id            integer PRIMARY KEY AUTOINCREMENT NOT NULL,
  nazwa         varchar(50) NOT NULL,
  wymiar_x      real,
  wymiar_y      real,
  wymiar_z      real,
  pracownik_id  integer
);

CREATE TABLE pozycja_zamowienia (
  id             integer PRIMARY KEY AUTOINCREMENT NOT NULL,
  nr_zamowienia  varchar(50),
  mebel_nr       varchar(50),
  ilosc          integer
);

CREATE TABLE mebel_elementy (
  mebel_nr       varchar(50),
  element_id     integer,
  mebel_element  varchar(50),
  ilosc          integer
);

CREATE TABLE opakowanie (
  id        integer PRIMARY KEY AUTOINCREMENT NOT NULL,
  mebel_nr  varchar(50),
  wymiar_x  real,
  wymiar_y  real,
  wymiar_z  real
);

CREATE TABLE okucia (
  id     integer PRIMARY KEY AUTOINCREMENT NOT NULL,
  nazwa  varchar(50)
);