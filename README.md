Vaadin projektin ohjeet
Kaisa Ukkonen

Data ja entiteetit. Jokaiselle pitää olla toteutettuna Vaatimella CRUD operaatioista, haku ja tallennus ja repository käyttöliittymältä taustapalvelimen kautta tietokantaan.
Yksi entiteetti
1 pistettä	Toinen entiteetti, jolle edelliselle toteutettuna 1 to 1 relaatio
2 pistettä	Kolmas entiteetti, sille vähintään toteutettuna 1 to m relaatio
3 pistettä	Neljäs entiteetti ja toteutus m to m relaatiosta
4 pistettä	Kaikille edellä oleville myös muokkaus ja poisto
5 pistettä

1–5 p. Työssä on toteutettu neljä entiteettiä ja kaikilla on käytössä haku, talletus, muokkaus ja poisto CRUD-operaatiot käyttöliittymällä ja Postmanilla. Jokaisella entiteetillä on oma repository (PersonRepository, AddressRepository, HobbyRepository, WeightMeasurementRepository)
-	Person 
-	Person ↔ Address on 1 to 1
-	Person ↔ WeightMeasurement on 1 to Many
-	Person ↔ Hobby Many to Many

 

 

Suodattimet. Täytyy löytyä GRID-elementti, jolle tehdään erilaisia suodatuksia
Yksinkertainen suodatus yhdelle sarakkeelle
1 pistettä	Yksinkertainen suodatus kahden tai useamman sarakkeen mukaan
2 pistettä	Suodatus relaatiossa olevan entiteetin osalta
3 pistettä	Tee vielä yksi hakuehto suodattimeen
4 pistettä	Tee vielä viides hakuehto suodattimeen. Saatata joutua lisäämään entiteeteille ominaisuuden, jotta saat tämän tehtyä
5 pistettä

1–5 p. Sovellukseen toteutettiin Vaadin Grid -komponentti, johon lisättiin useita suodattimia. Yksinkertaisin hakutoiminto on etunimen perusteella, minkä lisäksi voidaan hakea myös sukunimellä. Relaatioon liittyvä suodatus toteutettiin harrastusten avulla: käyttäjä voi hakea henkilöt sen perusteella, mikä harrastus heillä on tallennettuna. Lisäksi haku onnistuu kaupungin perusteella, jolloin osoitetieto toimii hakuehtona. Viimeisenä lisättiin päivämääräsuodatus, jolla voidaan hakea kaikki henkilöt, jotka ovat syntyneet tietyn päivän jälkeen. Näiden viiden hakuehdon avulla sovellus täyttää kaikki vaaditut pistekriteerit.
 
Tyylit
Globaalien tyylien muuttaminen
1 pistettä	Tyylien muokkaaminen suoraan yksittäiselle komponentille
2 pistettä	Tyylien muokkaaminen näkymässä yksittäiselle komponentille
3 pistettä	Vaihda käyttöön eri Lumo Utility luokkia
4 pistettä	Kustomoidun tyylitiedoston lisääminen ja sen käyttäminen luokkamäärittelyllä
5 pistettä

 1 p. Globaalien tyylien muuttaminen:
Sovelluksen fontiksi vaihdettu Montserrat, määriteltynä styles.css tiedostossa.
 
2 p. Tyylien muokkaaminen suoraan yksittäiselle komponentille:
 
Lisäsin ”Lisää henkilö” buttonille oman class-nimen Javassa
 
styles.css-tiedostossa on oma muokkaus tälle:     
Näin vain tämä yksi nappi sai vihreän ulkoasun, eikä muutos vaikuttanut muihin nappeihin.

3 p. Tyylien muokkaaminen näkymässä yksittäiselle komponentille
Annoin suoraan filters-komponentille tyylit PersonsView -luokassa, ilman CSS-tiedostoa.
 
 

4 p. Vaihda käyttöön eri Lumo Utility luokkia
Lumo Utility on otettu käyttöön MainLayoutissa olevaan otsikkoon.
 
 
5 p. Kustomoidun tyylitiedoston lisääminen ja sen käyttäminen luokkamäärittelyllä
Styles.css -tiedosto muokkaa sovelluksen ulkonäköä. Kaikki napit saivat yhtenäisen keltaisen teeman ja linkit erottuivat sovelluksessa. Lisäksi yhtä nappia muokattiin omalla luokalla (add-person vihreäksi).
 

Ulkoasu
SPA-sovellus, jossa on päänäkymä
1 pistettä	Header
2 pistettä	Toimiva navigointipalkki
3 pistettä	Footer
4 pistettä	Selkeästi erityyppisiä sisältösivuja vähintään kolme kappaletta. Edelliset pitää toimia näiden kanssa
5 pistettä

________________________________________
 1–4 p. Spa-sovellu: kaikki navigointi tapahtuu Vaadinin reitityksen avulla ilman selaimen uudelleenlatausta. Päänäkymä on toteutettu MainLayout-luokassa, johon on lisätty otsikko (Header), navigointilinkit (Henkilöt / Harrastukset / Tietoa) ja footer (© 2025 Oma Sovellus). Kaikki muut näkymät (esim. Henkilöt, Harrastukset, Tietoa) käyttävät tätä päänäkymää layout = MainLayout.class -määrittelyn kautta.
5 p. Henkilöt eli PersonsView on toteutettu Grid, suodattimet, CRUD-lomakkeet ja relaatiot. Harrastukset eli HobbiesView on oma CRUD-lomake harrastuksille. Tietoa eli AboutView kerrotaan sovelluksesta (staattinen sisältö, lokalisoitu teksti).
 
 
 
Autentikointi ja tietoturva
Security-palikan käyttöönotto
1 pistettä	Sisäänkirjautumissivun luominen
2 pistettä	Käyttäjäentiteetin luominen ja roolien määrittäminen (Admin/user)
3 pistettä	Toteuta: - Kaikki käyttäjät näkevät päänäkymän - User ja Admin käyttäjät näkevät jonkun sivun - Sivu pelkästään ADMIN käyttäjille
4 pistettä	Kustomoitu virheviesti jos user yrittää admin-sivulle
5 pistettä

1 p. SecurityConfig otettu käyttöön Spring Securitylle.  

2 p. Käytössä Springin tarjoama login-sivu.
 
 

3 p. Käyttäjät ja roolit määritelty user ja admin
 

4 p. Kaikki näkevät päänäkymän. User ja Admin näkee Henkilöt -sivun. Admin käyttäjä näkee vain Harrastukset -sivun.


5 p. Jos user yrittää mennä adminin Harrastukset sivulle, hän saa virheviestin.
  

Lisätoiminnallisuudet (1 piste/toiminnallisuus) Poikkeus, ei tarvitse täyttyä alemman tason ylemmän saavuttamiseksi
Julkaise työ GIT:iin
1 pistettä	Salasanojen salaus jollain menetelmällä
2 pistettä	Toteuta sovelluksessasi server push https://vaadin.com/docs/latest/building-apps/presentation-layer/server-push
3 pistettä	Toteuta lokalisointi vähintään yhdellä sivulla
4 pistettä	Tee työstäsi Docker-image. Palauta pelkkä DockerFile, jonka pitää toimia!
5 pistettä	Määritä Docker-composen avulla sekä tietokanta, että sovellus
6 pistettä
					

1 p.  GitHub linkki työhön: https://github.com/UkkonenKaisa/harjoitusty-
2 p. Salasanoja ei näytetä selkokielisenä vaan ne hashataan BCrypt-algoritmilla. Tein tätä varten testiluokan PasswordTestConfig, joka tulostaa salasanat terminaaliin.

 
  

3 p. AppShellConfig ottaa server pushin käyttöön koko sovelluksessa. Testi mielessä tein Mainviewiin napin, josta näkyy päivitys.
 
 
 
 
4 p. Lokalisointi toteutettu Tietoja- sivulla.    Näissä kahdessa tiedostossa on avain-arvo pareja, joita AboutView käyttää kielen valinnassa.  
Eli jos selaimeni on suomenkielinen, tekstit ovat suomeksi, mutta jos muutan selaimen englanninkielelle tekstit muuttuvat englanniksi. 
 

5 p. Docker-image ja Docker-compose.
Latasin Dockerin koneelleni. Lisäsin työhöni DockerFile. 
sekä docker-compose.yml.
  
DockerFilellä tein monivaiheiden buildin ja määrittelen käynnistyksen. docker-compose.yml. tiedostolla sain lisättyä MySQL tietokannan ja sovelluksen omiin kontteihinsa toimimaan yhdessä dockerin sisäisessä verkossa. Näin sain sovelluksen pyörimään osoitteessa: http://localhost:8080 docker compose up --build

