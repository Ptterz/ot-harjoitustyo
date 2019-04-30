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

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/Createaccount.png">

Luodaksesi käyttäjätilin, syötä nimimerkkikenttään haluamasi nimimerkki ja varmista alla olevalla painikkeella, että se on vapaana.

Kun nimimerkki on varmistettu, syötä haluamasi salasana ja paina _Create_-painiketta. Jos tunnusten luonti onnistui, näkymä palaa kirjautumisikkunaan. Muutoin sovellus ilmoittaa virheestä.

## Toiminnot

Kirjauduttua sisään avautuu päävalikko, josta löytyy kaikki ohjelman toiminnot. 

- _Play_-painikkeella pääsee pelitilaan, jossa ensin valitaan vaikeustaso.
- _Create_-painike avaa tehtävien luontiin tarkoitetun tilan, jossa valitaan, minkä tasoisia tehtäviä haluaa luoda.
- _My account_-painike vie omien tietojen esikatseluun, jossa voi muun muassa muuttaa salasanaa.
- _Logout_-painike kirjaa nykyisen käyttäjän ulos ja palaa kirjautumisikkunaan.
- _Quit_ kirjaa käyttäjän ulos ja sulkee sovelluksen. 

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/Mainmenu.png">

### Tasovalinta

Valittaessa joko pelitoiminto tai luontitoiminto pelaajalle avautuu eteen tasonvalintaikkuna. Luotaessa tehtäviä pelaaja voi valita jonkin kolmesta tasosta. Pelatessa valintoja on neljä: samat kolme kuin luontitoiminnossa ja
_Bring it on_-valinta, jossa sovellus arpoo minkä tahansa tehtävän. 
Alla näkyy pelitilan tasovalinta.

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/ChooseLevel.png">

### Pelitila

Pelitilassa tehtävänanto ja kaikki tarvittava tehtävän ratkaisemiseen näytetään yläosassa. Näiden alta löytyy vastauskenttä. Vastauskenttään annetaan vastaus tehtävään. 

**Matemaattisen tehtävän vastaus pyöristetään tarvittaessa lähimpään kokonaislukuun.** 

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/Game.png">

Jos vastaus on oikein, aukeaa tulosnäkymä, jossa on yhteenveto yritysten määrästä, kuluneesta ajasta ja kuinka hyvin pelaaja menestyi muihin verrattuna. Myös pelaajan aiemmat suoritukset huomioidaan tässä prosentissa.

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/Result.png">

### Luontitila

Luontitilassa pelaajalta pyydetään tarvittavat tiedot tehtävää varten. 

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/Create.png">

Pelaaja ei itse anna vastausta keksimälleen tehtävälle, vaan sovellus laskee sille vastauksen.

### Pelaajan tiedot

Pelaajan tiedoissa näkyy nimimerkki ja mahdollisuus muuttaa salasanaa. Painamalla _Change password_-painiketta saa näkyviin tarvittavat kentät salasanan muuttamiseen.

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/Playerinfo.png">

### Uloskirjautuminen ja lopetus

Painaessa _Logout_-painiketta, sovellus kirjaa pelaajan ulos ja palaa kirjautumisikkunaan. 

_Quit_-painike kirjaa pelaajan ulos ja sulkee sovelluksen muutaman sekunnin kuluttua.
