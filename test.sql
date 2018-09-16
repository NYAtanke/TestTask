USE test;

DROP TABLE IF EXISTS part;
CREATE TABLE part(
	id INT(11) NOT NULL AUTO_INCREMENT,
	title VARCHAR(100) NULL,
	isNeeded TINYINT NULL,
	quantity INT NULL,
	PRIMARY KEY (id)
)
  ENGINE = innoDB
  DEFAULT CHARACTER SET = utf8;

INSERT INTO part (title, isNeeded, quantity) VALUES
  ('материнская плата', TRUE, 8),
  ('звуковая карта',   FALSE, 2),
  ('процессор', TRUE,   45),
  ('подсветка корпуса',   FALSE,   0),
  ('видеокарта',   FALSE,   11),
  ('HDD диск',   FALSE, 1),
  ('корпус',   TRUE,   10),
  ('память',   TRUE,   10),
  ('SSD диск',   TRUE,   15),
  ('блок питания',  TRUE,   17),
  ('кулер',   TRUE,   5),
  ('радиатор',   TRUE,   4),
  ('монитор',   TRUE,   9),
  ('дисковод',   FALSE,   1),
  ('привод оптических дисков',   FALSE,   3),
  ('картридер',   FALSE,   5),
  ('ТВ-тюнер',   FALSE,   25),
  ('клавиатура',   TRUE,   78),
  ('компьютерная мышь',   TRUE,   108),
  ('наушники',  FALSE,   8),
  ('колонки',   FALSE,   19),
  ('джойстик',   FALSE, 4),
  ('микрофон',   FALSE,   64),
  ('WEB-камера',   FALSE,   35),
  ('модем',   TRUE,   41),
  ('роутер',   FALSE,   14),
  ('принтер',   FALSE,   47),
  ('сканер',   FALSE,   52),
  ('плоттер',   FALSE,   6),
  ('маршрутизатор',   FALSE,   7),
  ('копир',   FALSE,   6),
  ('проектор',FALSE,   13);
  
