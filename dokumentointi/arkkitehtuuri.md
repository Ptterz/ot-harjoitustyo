# Arkkitehtuurikuvaus
## Rakenne
Sovellus on toteutettu kolmitasoisella arkkitehtuurilla. Rakenne on esitelty alla olevassa kuvassa.

![rakenne](http://yuml.me/d61edbcb.png)

Pakkauksessa appen.domain on kaikki sovelluslogiikka ja tarvittavat luokat tietojen käsittelyyn.
Pakkaus appen.ui sisältää JavaFX:llä tehdyn käyttöliittymän.

Appen.dao-pakkauksen luokat vastaavat tiedon tallentamisesta tietokantaan.

## Käyttöliittymä
Käyttöliittymä on luotu yhteen luokkaan pakkauksessa appen.ui. 

Sovelluksessa on useita eri näkymiä.
Jokainen näkymä on luotu omana oliona, joista vain yksi on näkyvissä kerrallaan. 

Käyttöliittymä hoitaa vain ja ainoastaan näkymien hallinnan ja tiedon välittämisen. Kaikki varsinainen työ tehdään Management-luokassa kutsumalla sopivia metodeita. Management on osa sovelluslogiikkaa.
 
## Sovelluslogiikka
Keskeinen osa sovellusta ovat sen käyttäjät ja tehtävät, joita käsitellään luokkien Player ja Exercise avulla.

Luokassa Database määritellään tietokanta, sen taulut ja yhteydet valmiiksi. Luokasta löytyy metodi, joka palauttaa Connection-olion. Tätä hyödynnetään appen.dao-pakkauksen luokissa. 

Matemaattisia tehtäviä varten sovelluksen käytössä on Calculator-luokka, jossa on vain yksi metodi: calculate(String s). Metodi laskee annetun lausekkeen arvon.

Kaikki varsinainen toiminta löytyy luokasta Management, joka ohjaa kaikkea sovelluksen toimintaa. Kuten alla olevasta kuvasta ilmenee, Management toimii yhdessä UI:n kanssa ja hyödyntää sekä lähes kaikkia appen.domain-pakkauksen luokkia
että appen.dao-pakkauksen luokkia. Management ei suoraan käytä Database luokkaa ja sen olioita, vaan se tapahtuu epäsuorasti Daojen välityksellä.

![arkkitehtuuri](http://yuml.me/e37a4183.png)

## Tietojen tallennus

Appen.dao-pakkaus pitää sisällään luokat, jotka vastaavat tiedon tallentamisesta. Luokat on toteutettu [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) -suunnittelumallilla.

Sovelluksen tiedot tallennetaan tietokantaan, joka on määritelty Database-luokan oliolle sovelluksen käynnistyessä. Sekä tehtävät että käyttäjät tallennetaan samaan tietokantaan omiin tauluihin.

Player-taulu koostuu vain kahdesta sarakkeesta: nimimerkki ja salasana. Player-taulun pääavaimena toimii käyttäjän nimimerkki, joka on uniikki.  

Exercise-taulussa on kolme saraketta: kysymys, vastaus ja taso. Kysymys toimii pääavaimena, koska kahta identtisen näköistä kysymystä ei haluta tallentaa.

## Toiminnallisuuksista
Alla olevassa kuvassa on esitelty sovelluksen käynnistys, kun muun muassa Management-luokan olio luodaan, ja calculate-metodin kutsu esimerkin omaisesti.

![Sekvenssikaavio](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=VUktPiptYW5hZ2U6IG5ldyBNAAcFbWVudCh0cnVlKTsKABgGLT4qZGIAHQZEYXRhYmFzZShsYXJhZGlnYXBwZW4uZGIAIAxwZABJBlBsYXllckRhbygAEg5lABkHRXhlcmNpc2UAEBJwZXJmRAA-B2VyZm9ybWFuYwAVE2NhbGMAgTUGQ2FsY3VsYXRvcigAgSsMcmFuZG9tAIFYBlIABwUoKTsKClVJLT4rAIFyCGMANAdlKC4uLgCBaQsrAFgGdHJ5ewAVDn0Agg8JLT5VSTogY2F0Y2h7cmV0dXJuIGZhbHNlfTsKY2FsYy0-LQCCVwgAFwdMb25nAIJMCgAOD2NyZWF0ZQCCDggAfhBlZACBBQcAIQUoAII1DACBNAUAcSZlZACBAxJ0cnVlAINhCi1VSQALDwCCIQ5nZXQAgRoJAIEMFGxpc3QoAIIGHiJlcnJvciIAgH8QTGlzdDwAhA0IPiBsaXN0AIMDCwCDQQp4dEludChsaXN0LnNpemUoKSk7CgCDYwYAgl8SaW50IHgAgmARbGFzdEV4ZSA9AGAFLmdldCh4AIVjCwCBfgUAHQcuZ2V0UXVlc3Rpb24AhCoSZ2V0QW5zd2UAhGkNAC8QABkJ&s=default)

## Parannettavaa

Koko käyttöliittymä on toteutettu yhtenä suurena luokkana, jossa esiintyy paljon toistoa. Eri näkymien eriyttäminen omiksi metodeiksi parantaisi koodin luettavuutta ja ylläpitoa, mutta se vaatisi monien komponenttien tekemistä
globaaleiksi, jotta eri metodi pääsisi niihin käsiksi.  
