# Käyttöohje

Lataa uusimman [version](https://github.com/Ptterz/ot-harjoitustyo/releases) jar-tiedosto.

## Käynnistys

Saat käynnistettyä ohjelman komennolla

```
java -jar todoapp.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään. Sovelluksen käyttö vaatii käyttäjätunnukset. 

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/Login.png">

Jos tunnuksia ei vielä ole, voi siirtyä tunnusten luontiin _Create an account_-painikkeella.

Muutoin kirjaudu sisään syöttämällä käyttäjätunnus ja salasana.

## Käyttäjätilin luominen

Luodaksesi käyttäjätilin, syötä nimimerkkikenttään haluamasi nimimerkki ja varmista alla olevalla painikkeella, että se on vapaana.

Kun nimimerkki on varmistettu, syötä haluamasi salasana ja paina _Create_-painiketta. Jos tunnusten luonti onnistui, näkymä palaa kirjautumisikkunaan.

## Toiminnot

Kirjauduttua sisään avautuu päävalikko, josta löytyy kaikki ohjelman toiminnot. 

- _Play_-painikkeella pääsee pelitilaan, jossa ensin valitaan vaikeustaso.
- _Create_-painike avaa tehtävien luontiin tarkoitetun tilan, jossa valitaan, minkä tasoisia tehtäviä haluaa luoda.
- _My account_-painike vie omien tietojen esikatseluun, jossa voi muun muassa muuttaa salasanaa.
- _Logout_-painike kirjaa nykyisen käyttäjän ulos ja palaa kirjautumisikkunaan.
- _Quit_ kirjaa käyttäjän ulos ja sulkee sovelluksen. 

### Tasovalinta

Valittaessa joko pelitoiminto tai luontitoiminto pelaajalle avautuu eteen tasonvalintaikkuna. Luotaessa tehtäviä pelaaja voi valita jonkin kolmesta tasosta. Pelatessa valintoja on neljä: samat kolme kuin luontitoiminnossa ja
_Bring it on_-valinta, jossa sovellus arpoo minkä tahansa tehtävän. 
