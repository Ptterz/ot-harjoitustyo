# Laradigappen
Sovelluksella on tarkoitus harjoitella muun muassa matemaattisten yhtälöiden
ratkaisua. Käyttääkseen sovellusta on luotava käyttäjätunnukset.
Sovellus on toteutettu osana Ohjelmistotekniikka-kurssia.

## Dokumentaatio

[Arkkitehtuuri](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/maarittelydoc.md)

[Työaikakirjanpito](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

## Komentorivitoiminnot

### Suoritettavan jarin generointi

Komento
```
mvn package
```
luo hakemistoon target suoritettavan jar-tiedoston Laradigappen-1.0-SNAPSHOT.jar.

### Käynnistys

Voit käynnistää sovelluksen komentoriviltä
```
mvn compile exec:java -Dexec.mainClass=appen.ui.Start
```

### Testaus

Saat ajettua testit ja luotua testikattavuusraportin
```
mvn test jacoco:report
```

### Checkstyle

Koodin ulkoasun voit tarkastaa komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Komento suorittaa tiedostossa [checkstyle.xml](https://github.com/Ptterz/ot-harjoitustyo/blob/master/Laradigappen/checkstyle.xml) määritellyt tarkistukset.
Tarkempi yhteenveto mahdollisista virheistä löytyy target/site/checkstyle.html. 
Tarkastelun ulkopuolelle on jätetty käyttöliittymä ja rekursiivinen laskurimetodi, joka pituutensa vuoksi tuottaa virheilmoituksen. 



