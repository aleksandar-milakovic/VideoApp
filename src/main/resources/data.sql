

INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'a1@gmail.com','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'b1@gmail.com','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga )
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO prevoznik (id,naziv,adresa,pib) VALUES (1,'Banat Trans' ,'Veljka Petrovica 30', '0123A');
INSERT INTO prevoznik (id,naziv,adresa,pib) VALUES (2,'Sever Trans' ,'Gogoljeva 20', '0123B');
INSERT INTO prevoznik (id,naziv,adresa,pib) VALUES (3,'Nis Ekspres' ,'Njegoseva 12a', '0123C');
INSERT INTO prevoznik (id,naziv,adresa,pib) VALUES (4,'Lasta' ,'Partizanskih Odreda', '0123D');



INSERT INTO linija (id, broj_mesta, cena_karte,vreme_polaska, destinacija,prevoznik_id) VALUES (1, 3,550.0,'12:30','Zrenjanin',1);
INSERT INTO linija (id, broj_mesta, cena_karte,vreme_polaska, destinacija,prevoznik_id) VALUES (2, 3,530.0,'11:30','Novi Sad',2);
INSERT INTO linija (id, broj_mesta, cena_karte,vreme_polaska, destinacija,prevoznik_id) VALUES (3, 3,555.0,'10:30','Beograd',3);
INSERT INTO linija (id, broj_mesta, cena_karte,vreme_polaska, destinacija,prevoznik_id) VALUES (4, 3,565.0,'13:30','Zrenjanin',4);



INSERT INTO  rezervacija (id,linija_id) VALUES (1, 3);
INSERT INTO  rezervacija (id,linija_id) VALUES (2, 3);
INSERT INTO  rezervacija (id,linija_id) VALUES (3, 4);
INSERT INTO  rezervacija (id,linija_id) VALUES (4, 4);






