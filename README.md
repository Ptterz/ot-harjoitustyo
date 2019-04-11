# Laradigappen
Sovelluksella on tarkoitus harjoitella erilaisia tehtäviä, joiden aihealue ja vaikeustaso riippuu valinnoista. 
Käyttääkseen sovellusta on luotava käyttäjätunnukset (nimimerkki + salasana). 
Sovelluksessa ei ole valmiina tehtäviä. Tehtäviä luodessa käyttäjän tarvitsee antaa vain tehtävänannon kannalta oleelliset tiedot. 
Sovelluksen sisään toteutettu laskin hoitaa vastauksen laskemisen!
Sovellus on toteutettu osana Ohjelmistotekniikka-kurssia.

## Dokumentaatio

[Arkkitehtuuri](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Vaatimusmäärittely](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/maarittelydoc.md)

[Työaikakirjanpito](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

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

### JavaDoc

JavaDocin voit luoda komennolla 
```
mvn javadoc:javadoc
```
Tämän tarkastelu onnistuu avaamalla selaimessa target/site/apidocs/index.html.

