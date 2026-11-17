# Fuel-Delivery-Project
# Fuel-Delivery-Project
# Fuel Delivery Project

## Projekto aprašymas

Fuel Delivery Project yra Java Maven programa, skirta kuro pristatymų duomenų apdorojimui. Programa nuskaito duomenis iš JSON failo, apskaičiuoja kuro pristatymo kainas, atlieka duomenų validaciją ir sugeneruoja CSV ataskaitas.

## Funkcionalumas

### Task 1

Programa:

- Nuskaito duomenis iš `shipments.json`
- Apskaičiuoja:
  - Net Fuel Price
  - Logistics Cost
  - Excise Tax
  - VAT
  - Grand Total
- Sugeneruoja `task1.csv`

### Task 2

Programa leidžia vartotojui įvesti:

- Individualų PVM tarifą (%)
- Individualų benzino akcizo tarifą

Pagal įvestas reikšmes sugeneruojamas:

- `task2.csv`

### Duomenų validacija

Programa tikrina:

- MISSING_FIELD
- UNKNOWN_TYPE
- UNKNOWN_CLIENT
- INVALID_DATE
- INVALID_VALUE
- DUPLICATE_ID

Jeigu randamos klaidos, sugeneruojamas failas:

- `errors.csv`

## Projekto struktūra

```text
src/main/java/org/example
│
├── Main.java
├── Shipment.java
├── JsonReader.java
├── PriceCalculator.java
├── Validator.java
├── ErrorRecord.java
└── CsvWriter.java

src/main/resources
└── shipments.json

output
├── task1.csv
├── task2.csv
└── errors.csv
```

## Naudotos technologijos

- Java
- Maven
- Jackson Databind
- BigDecimal
- IntelliJ IDEA
- Git
- GitHub

## Skaičiavimo logika

### Net Fuel Price

Nuolaidos:

- daugiau nei 5 000 l → 5 %
- daugiau nei 20 000 l → 10 %

### Logistics Cost

```text
distance × 1.80
```

Jeigu:

```text
volume < 1000
```

papildomai pridedama:

```text
150.00
```

### Excise Tax

- GASOLINE → 0.47
- DIESEL → 0.33
- LPG → 0.18

### VAT

- EXEMPT → 0 %
- PRIVATE → 21 %

### Grand Total

```text
Net Fuel Price
+ Logistics Cost
+ Excise Tax
+ VAT
```

## Kaip paleisti projektą

1. Klonuoti repozitoriją:

```bash
git clone <repository-url>
```

2. Atidaryti projektą IntelliJ IDEA.

3. Paleisti:

```text
Main.java
```

4. Task 2 metu įvesti:

```text
Enter custom VAT rate for Private clients (%):
Enter custom Excise rate for Gasoline:
```

## Projekto tikslas

Projektas sukurtas kaip Java programavimo ir duomenų apdorojimo praktinė užduotis. Įgyvendinant projektą buvo pritaikyti OOP principai, JSON duomenų apdorojimas, CSV generavimas, duomenų validacija bei tikslūs finansiniai skaičiavimai naudojant BigDecimal.
