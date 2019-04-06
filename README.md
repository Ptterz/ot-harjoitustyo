# Laradigappen
Sovelluksella on tarkoitus harjoitella muun muassa matemaattisten yhtälöiden
ratkaisua. Käyttääkseen sovellusta on luotava käyttäjätunnukset.
Sovellus on toteutettu osana Ohjelmistotekniikka-kurssia.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/maarittelydoc.md)
[Työaikakirjanpito](https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

## Komentorivitoiminnot

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



