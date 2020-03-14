INSERT INTO user_account(email,username) VALUES
('marciogarcia@gmail.com','Marcio Garcia'),
('adel15220@live.com.pt','Adelaide Sousa'),
('rubenmaster@slb.pt','Ruben Dias'),
('ttavares@slb.pt','Tomas Tavares'),
('bernassilva@slb.pt','Bernardo Silva');

INSERT INTO room(name,location,capacity) VALUES
('LS1','Building F floor -1',53),
('LS2','Building F floor -1',48),
('AURS','Building C floor 3',125),
('LAC','Building G floor 0',12),
('LH1','Building F floor 0',33);

INSERT INTO label(name) VALUES
('easy access'),
('video projector availability'),
('windows'),
('microphone'),
('no chairs');

INSERT INTO booking(reservation_owner,room_name,begin_time,end_time) VALUES
('marciogarcia@gmail.com','LS1','2020-04-01 10:00:00','2020-04-01 11:00:00'),
('marciogarcia@gmail.com','LS2','2020-04-01 10:00:00','2020-04-01 11:00:00'),
('adel15220@live.com.pt','LH1','2020-04-04 20:00:00','2020-04-04 22:30:00'),
('ttavares@slb.pt','LAC','2020-04-13 08:30:00','2020-04-13 11:00:00'),
('ttavares@slb.pt','LS1','2020-04-30 18:00:00','2020-04-30 20:00:00'),
('bernassilva@slb.pt','AURS','2020-04-27 09:00:00','2020-04-28 09:00:00');

INSERT INTO room_label(roomname,label) VALUES
('LS1','easy access'),
('LS2','video projector availability'),
('LS2','easy access'),
('AURS','windows'),
('AURS','video projector availability'),
('AURS','easy access'),
('LAC','no chairs'),
('LAC','microphone'),
('LH1','windows');
