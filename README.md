# TKS Projektni zadatak 2 — Testiranje web aplikacije

Ovaj projekat predstavlja rješenje drugog projektnog zadatka u okviru predmeta **Testiranje i kvalitet softvera** na Elektrotehničkom fakultetu Banja Luka. Projekat obuhvata testiranje klijentske i serverske strane web aplikacije za upravljanje studentima.

## Struktura projekta

```
Dajana_Popovic_1158-21/
├── Izvjestaj.docx
├── testovi_klijentska_aplikacija/
│   └── dajana_popovic/
│       ├── src/test/java/
│       │   └── StudentCRUDTests.java     # Selenium testovi
│       └── pom.xml
└── testovi_serverska_aplikacija/
    └── Dajana_Popovic.postman_collection.json  # Postman kolekcija
```

## Testiranje klijentske aplikacije (Selenium)

CRUD testovi za studente pokrivaju sljedeće scenarije:

- **Create** — Dodavanje novog studenta
- **Read** — Pregled liste studenata
- **Update** — Izmjena podataka o studentu
- **Delete** — Brisanje studenta

### Tehnologije

- **Java 11**
- **Selenium 4.25.0**
- **JUnit5 5.10.3**
- **Maven**

### Preduslovi

- Pokrenuta klijentska aplikacija na `http://localhost:4200`
- Instaliran **Google Chrome** i odgovarajući **ChromeDriver**

### Pokretanje testova

```bash
cd testovi_klijentska_aplikacija/dajana_popovic
mvn test
```

Ili otvorite projekat u **IntelliJ IDEA** i pokrenite `StudentCRUDTests.java`.

## Testiranje serverske aplikacije (Postman)

REST API testovi su implementirani kao Postman kolekcija.

### Pokretanje

1. Otvorite **Postman**
2. Importujte fajl `Dajana_Popovic.postman_collection.json`
3. Pokrenite kolekciju (server mora biti pokrenut)

## Autor

Dajana Popović (1158-21) — Elektrotehnički fakultet Banja Luka
