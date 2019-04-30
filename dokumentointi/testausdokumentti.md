# Testausdokumentti

Ohjelmaa on testattu sekä manuaalisesti että JUnitilla. 

## JUnit testit

Käyttöliittymä on jätetty näiden testien ulkopuolelle. 

JUnit-testejä on muutoin luotu jokaiselle luokalle, joilla varmistetaan, että kaikki perustoiminnot toimivat, kuten niiden oletetaan. 

Management-luokan osalta testeissä on annettu luokalle parametrina boolean arvo _false_, jolloin tietokantayhteys ohjautuu testitietokantaan.

Osalla luokista saattaa olla vähemmän testejä kuin toisilla, kun esimerkiksi Management-testit kattavat paljon myös muiden luokkien toimintoja.
