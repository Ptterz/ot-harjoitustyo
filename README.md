# Laradigappen
Sovelluksella on tarkoitus harjoitella erilaisia tehtäviä, joiden aihealue ja vaikeustaso riippuu valinnoista.

Sovellus on toteutettu osana Ohjelmistotekniikka-kurssia.

## Julkaisut

[Versio 1.1](https://github.com/Ptterz/ot-harjoitustyo/releases)

Uutta versiossa 1.1
- Tulosikkunaan on lisätty vertailutoiminto, joka vertaa suoritustasi muihin tehtävän suorituksiin. 

## Dokumentaatio

[Arkkitehtuuri](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Käyttöohje](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/manual.md)

[Työaikakirjanpito](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/maarittelydoc.md)

## Komentorivitoiminnot

### Jar

Komento
```
mvn package
```
luo hakemistoon target suoritettavan jar-tiedoston Laradigappen-1.0-SNAPSHOT.jar.

### Käynnistys

Voit käynnistää sovelluksen komentoriviltä
```
mvn compile exec:java -Dexec.mainClass=appen.ui.LaraUI
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

### JavaDoc

JavaDocin voit luoda komennolla 
```
mvn javadoc:javadoc
```
Tämän tarkastelu onnistuu avaamalla selaimessa target/site/apidocs/index.html.

