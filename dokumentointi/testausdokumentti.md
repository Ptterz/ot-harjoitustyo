# Testausdokumentti

Ohjelmaa on testattu sekä manuaalisesti että JUnitilla. 

## JUnit testit

Käyttöliittymä on jätetty näiden testien ulkopuolelle. 

JUnit-testejä on muutoin luotu jokaiselle luokalle, joilla varmistetaan, että kaikki perustoiminnot toimivat, kuten niiden oletetaan. 

Management-luokan osalta testeissä on annettu luokalle parametrina boolean arvo _false_, jolloin tietokantayhteys ohjautuu testitietokantaan.

Osalla luokista saattaa olla vähemmän testejä kuin toisilla, kun esimerkiksi Management-testit kattavat paljon myös muiden luokkien toimintoja.

### Testikattavuus 

JUnit-testeillä on saavutettu varsin hyvä kattavuusprosentti.

<img src="https://github.com/Ptterz/ot-harjoitustyo/blob/master/dokumentointi/snapshots/testikattavuus.png" width="800">

Kuten kuvasta näkyy, aivan kaikkea ei ole saatu testattua. Esimerkiksi _AnimationTimer_ ja suorituksesta riippuvainen _getPerformance()_ ovat sellaisia, joita on hyvin haastava testata. Toisaalta, ensimmäiselle noista ei oikeastaan 
ole edes tarvetta tehdä testejä.

## Manuaaliset testit

Sovellusta on testattu jatkuvasti ja paljon manuaalisesti, että siirtymät ja toiminnot toimivat odotetusti. Testausta on toteutettu vain mac OS X -järjestelmässä. 
