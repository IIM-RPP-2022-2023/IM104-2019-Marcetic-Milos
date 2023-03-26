----------- Banka -----------------------------------------------------

INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('banka_seq'), 'Komercijalna Banka', '+381 11 30 50 505', '12548');
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('banka_seq'), 'OTP Banka', '+381 11 20 72 200', '688144');
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('banka_seq'), 'Raiffeissen Banka', '+381 11 3143 171', '225478');
INSERT INTO "banka"("id", "naziv", "kontakt", "pib") VALUES(nextval('banka_seq'), 'Sber Banka', '+381 11 3773 600', '335145');


----------- Korisnik Usluge -----------------------------------------------------

INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Mirko', 'Antic', '0912563521478');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Milan', 'Belic', '2015479054872');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Stevan', 'Simic', '0512485632145');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Milica', 'Maric', '0126305487904');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Milka', 'Protic', '1025305048952');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Ana', 'Belic', '1925487963521');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Zorka', 'Kozomara', '1623524575154');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Aleksandra', 'Jeremic', '1485963625147');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Aleksa', 'Orlovic', '2214589658465');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Slavica', 'Markovic', '1254789785463');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Branko', 'Dinic', '2154879525647');
INSERT INTO "korisnik_usluge"("id", "ime", "prezime", "maticni_broj") VALUES(nextval('korisnik_usluge_seq'), 'Luka', 'Ostojic', '15498563254788');


----------- Filijala -----------------------------------------------------

INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES (nextval('filijala_seq'), 'Rumska 15, Ruma', 1, true, 3);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES (nextval('filijala_seq'), 'Bulevar Oslobodjenja 120, Novi Sad', 3, false, 1);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES (nextval('filijala_seq'), 'Milana Protica 31, Beograd', 2, true, 4);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES (nextval('filijala_seq'), 'Stevana Sindjelica 18, Vrsac', 4, true, 1);
INSERT INTO "filijala"("id", "adresa", "broj_pultova", "poseduje_sef", "banka") VALUES (nextval('filijala_seq'), 'Bulevar Mihajla Pupina 41, Nis', 3, false, 2);


----------- Usluga -----------------------------------------------------

INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Platni racun', 'Gasenje platnog racuna', to_date('08.03.2023.', 'dd.mm.yyyy.'), 549, 1, 3);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Uplata na platni racun', 'Uplata na dinarski racun', to_date('15.03.2023.', 'dd.mm.yyyy.'), 254,2, 1);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Izdavanje cekova', 'Izdavanje dinarskih cekova u vrednosti od 10000', to_date('10.03.2023.', 'dd.mm.yyyy.'), 300, 4, 1);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'E bankarstvo', 'Odrzavanje elektronskih usluga banke', to_date('01.03.2023.', 'dd.mm.yyyy.'), 650, 2, 4);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Placanje na rate', 'Izmirenje obaveza prema prodavcu', to_date('01.03.2023.', 'dd.mm.yyyy.'), 142, 3, 4);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Uplata na platni racun', 'Uplata na dinarski racun', to_date('01.03.2023.', 'dd.mm.yyyy.'), 130, 1, 4);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Platni racun', 'Otvaranje platnog racuna', to_date('02.03.2023.', 'dd.mm.yyyy.'), 2002, 2, 1);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Platni racun', 'Vodjenje platnog racuna', to_date('01.03.2023.', 'dd.mm.yyyy.'), 300, 3, 2);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Izdavanje platne kartice', 'Izdavanje debitne Mastercard kartice', to_date('25.03.2023.', 'dd.mm.yyyy.'), 0, 5, 1);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Placanje na rate', 'Izmirenje obaveza prema prodavcu', to_date('04.03.2023.', 'dd.mm.yyyy.'), 140, 5, 3);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Izdavanje platne kartice', 'Izdavanje debitne DinaCard kartice', to_date('06.03.2023.', 'dd.mm.yyyy.'), 0, 3, 1);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Placanje na rate', 'Izmirenje obaveza prema prodavcu', to_date('01.03.2023.', 'dd.mm.yyyy.'), 215, 4, 4);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Izdavanje cekova', 'Izdavanje dinarskih cekova u vrednosti od 20000', to_date('21.03.2023.', 'dd.mm.yyyy.'), 600, 3 ,3);
INSERT INTO "usluga"("id", "naziv", "opis_usluge", "datum_ugovora", "provizija", "filijala", "korisnik") VALUES (nextval('usluga_seq'), 'Placanje na rate', 'Izmirenje obaveza prema prodavcu', to_date('01.03.2023.', 'dd.mm.yyyy.'), 164, 5, 5);
