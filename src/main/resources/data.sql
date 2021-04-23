

INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'a1@gmail.com','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga,opis_kanala,blokiran,datum_reg)
              VALUES (2,'b1@gmail.com','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK','kanal o satovima',false,'2021-04-05');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga,opis_kanala,blokiran,datum_reg )
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK','kanal o kolima',false,'2020-04-06');
              
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga,opis_kanala,blokiran,datum_reg )
              VALUES (4,'ana@maildrop.cc','ana','$2y$12$mvzvppYcGtzwOZPrM73LJOrEZwOyauiWEqguIPAeq/YE33lukC9J.','Ana','Nikolasevic','KORISNIK','kanal o putovanjima',false,'2020-04-03');              

              

INSERT INTO korisnik_pratioci (korisnik_id,pratilac_id) VALUES (2,4);
INSERT INTO korisnik_pratioci (korisnik_id,pratilac_id) VALUES (3,2);
INSERT INTO korisnik_pratioci (korisnik_id,pratilac_id) VALUES (4,2);

INSERT INTO video (id,video,slicica,opis,vidljivost,vidljivost_rejtinga,dozvoljeni_komentari,blokiran,broj_pregleda,datum_kreiranja, vlasnik_id)
VALUES (1,'https://www.youtube.com/watch?v=mTOoLCtrjkc&t=638s&ab_channel=SatnaRuci','http://i3.ytimg.com/vi/mTOoLCtrjkc/maxresdefault.jpg','OPIS 1','JAVNI',true,true,false,45,'2020-04-06',1);

INSERT INTO video (id,video,slicica,opis,vidljivost,vidljivost_rejtinga,dozvoljeni_komentari,blokiran,broj_pregleda,datum_kreiranja, vlasnik_id) 
VALUES (2,'https://www.youtube.com/watch?v=Qu1d1kjEMWk&ab_channel=SatnaRuci','http://i3.ytimg.com/vi/Qu1d1kjEMWk/maxresdefault.jpg','OPIS 2','JAVNI',true,true,false,150,'2020-04-07',1);

INSERT INTO video (id,video,slicica,opis,vidljivost,vidljivost_rejtinga,dozvoljeni_komentari,blokiran,broj_pregleda,datum_kreiranja, vlasnik_id)
VALUES (3,'https://www.youtube.com/watch?v=GC5w63ZOufk&ab_channel=SatnaRuci','http://i3.ytimg.com/vi/GC5w63ZOufk/maxresdefault.jpg','OPIS 3','JAVNI',true,true,false,250,'2020-04-08',1);

INSERT INTO video (id,video,slicica,opis,vidljivost,vidljivost_rejtinga,dozvoljeni_komentari,blokiran,broj_pregleda,datum_kreiranja, vlasnik_id)
VALUES (4,'https://www.youtube.com/watch?v=CPOv0hkLLQ8&t=42s&ab_channel=MirkoRasic','http://i3.ytimg.com/vi/CPOv0hkLLQ8/maxresdefault.jpg','OPIS 4','JAVNI',true,false,false,1500,'2020-04-06',2);

INSERT INTO video (id,video,slicica,opis,vidljivost,vidljivost_rejtinga,dozvoljeni_komentari,blokiran,broj_pregleda,datum_kreiranja, vlasnik_id)
VALUES (5,'https://www.youtube.com/watch?v=fVq5k9u99Bg&t=126s&ab_channel=MirkoRasic','http://i3.ytimg.com/vi/fVq5k9u99Bg/maxresdefault.jpg','OPIS 5','JAVNI',true,true,false,25000,'2020-04-08',2);

INSERT INTO video (id,video,slicica,opis,vidljivost,vidljivost_rejtinga,dozvoljeni_komentari,blokiran,broj_pregleda,datum_kreiranja, vlasnik_id)
VALUES (6,'https://www.youtube.com/watch?v=JJIc2aDbJIs&ab_channel=KAMBERizam','http://i3.ytimg.com/vi/JJIc2aDbJIs/maxresdefault.jpg','OPIS 6','PRIVATNI',true,true,false,25050,'2020-04-12',3);

INSERT INTO video (id,video,slicica,opis,vidljivost,vidljivost_rejtinga,dozvoljeni_komentari,blokiran,broj_pregleda,datum_kreiranja, vlasnik_id)
VALUES (7,'https://www.youtube.com/watch?v=U86Qy6-3UMk&ab_channel=KAMBERizam','http://i3.ytimg.com/vi/U86Qy6-3UMk/maxresdefault.jpg','OPIS 7','UNLISTED',true,true,false,17000,'2020-04-08',3);

INSERT INTO komentar (id,sadrzaj,datum_kreiranja,vlasnik_id,video_id) 
VALUES (1,'SADRZAJ 1','2020-04-20',1,1);
INSERT INTO komentar (id,sadrzaj,datum_kreiranja,vlasnik_id,video_id) 
VALUES (2,'SADRZAJ 2','2020-04-21',2,5);


INSERT INTO like_dislike (id,isit_like,datum_kreiranja,video_id)
VALUES(1,true,'2020-04-22',6);
INSERT INTO like_dislike (id,isit_like,datum_kreiranja,komentar_id)
VALUES(2,true,'2020-04-23',2);


