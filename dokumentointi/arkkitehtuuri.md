# Arkkitehtuurikuvaus
## Rakenne
Sovellus on toteutettu kolmitasoisella arkkitehtuurilla. Rakenne on esitelty alla olevassa kuvassa.

![rakenne](http://yuml.me/d61edbcb.png)

Pakkauksessa appen.domain on kaikki sovelluslogiikka ja tarvittavat luokat tietojen käsittelyyn.
Pakkaus appen.ui sisältää JavaFX:llä tehdyn käyttöliittymän.
Appen.dao-pakkauksen luokat vastaavat tiedon tallentamisesta tietokantaan.

## Käyttöliittymä
Käyttöliittymä on luotu yhteen luokkaan pakkauksessa appen.ui. Sovelluksessa on useita eri näkymiä.
Jokainen näkymä on luotu omana oliona, joista vain yksi on näkyvissä kerrallaan. 
Käyttöliittymä hoitaa vain ja ainoastaan näkymien hallinnan ja tiedon välittämisen. Kaikki varsinainen työ tehdään Management-luokassa kutsumalla sopivia metodeita. Management on osa sovelluslogiikkaa.
 
## Sovelluslogiikka
Keskeinen osa sovellusta ovat sen käyttäjät ja tehtävät, joita käsitellään luokkien Player ja Exercise avulla.
Luokassa Database määritellään tietokanta, sen taulut ja yhteydet valmiiksi. Luokasta löytyy metodi, joka palauttaa Connection-olion. Tätä hyödynnetään appen.dao-pakkauksen luokissa. 
Matemaattisia tehtäviä varten sovelluksen käytössä on Calculator-luokka, jossa on vain yksi metodi: calculate(String s). Metodi laskee annetun lausekkeen arvon.
Kaikki varsinainen toiminta löytyy luokasta Management, joka ohjaa kaikkea sovelluksen toimintaa. Kuten alla olevasta kuvasta ilmenee, Management toimii yhdessä UI:n kanssa ja hyödyntää sekä kaikkia appen.domain-pakkauksen luokkia
että appen.dao-pakkauksen luokkia.
![arkkitehtuuri](http://yuml.me/6f282b06.png)

## Tietojen tallennus

## Toiminnallisuuksista

![SekvenssikaavioCreate](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=VUktPipkYjogbmV3IERhdGFiYXNlKGxhcmFkaWdhcHBlbi5kYik7CgAjBXBkACIGUGxheWVyRGFvKAASCmUAFQdFeGVyY2lzZQAQDm1hbmFnZQBgBk0ABwVtZW50KHBkLCBlZCk7CgAaBi0-KmNhbGMAgQcGQ2FsY3VsYXRvcigpOwoAfAUrAD8IYwAVB2UoLi4uADQLKwA5BnRyeXsAFQ59AFoJLT5VSTogY2F0Y2h7cmV0dXJuIGZhbHNlfTsKY2FsYy0-LQCBJAgAFwdMb25nAIEXCgAOD2NyZWF0ZQCBbAgAfhBlZACBBQcAIQUoAIITDACBNAUpfTsKZWQtAEwKAIECFQCBLA0AgScFADEFAIEYEXRydWUAgkAKLVVJAAsPCg&s=default)
