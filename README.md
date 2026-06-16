🏠 Nekretnine

Full-stack web aplikacija za oglašavanje i pretraživanje nekretnina, izrađena kao timski projekt na FSRE. 
Omogućuje korisnicima pregled oglasa, upravljanje vlastitim profilom i agencijskim računom, te bookmarkanje oglasa.

⚙️ Tech Stack
Sloj                 Tehnologije
Frontend             React React Router v6, CSS Modules
Backend              Java, Spring Boot, Spring Security (JWT)
Baza podataka        PostgreSQL (Neon cloud)
Storage              Supabase Storage (slike oglasa i avatara)
ORM / Mapping        JPA/Hibernate, MapStruct

📁 Struktura repozitorija
nekretnine/
├── backend/          # Spring Boot aplikacija
│   └── src/
│       └── main/java/
│           ├── controller/
│           ├── service/
│           ├── repository/
│           ├── entity/
│           ├── dto/
│           └── mapper/
├── frontend/         # React aplikacija
│   └── src/
│       ├── components/
│       ├── pages/
│       ├── context/
│       └── api/
├── ER-dijagram-nekretnine.jpeg
└── baza.jpeg

🚀 Pokretanje lokalno
Preduvjeti

Java 17+
Node.js 18+
PostgreSQL baza (ili Neon cloud account)
Supabase projekt (za file storage)

cd backend
# Konfigurirati application.properties:
# - spring.datasource.url
# - spring.datasource.username / password
# - jwt.secret
# - supabase.url / supabase.key

./mvnw spring-boot:run
Backend je dostupan na http://localhost:8080.

cd frontend
npm install
npm run dev
http://localhost:5173.

Kreirati .env datoteku u frontend/ direktoriju:
VITE_API_URL=http://localhost:8080

✨ Funkcionalnosti
Autentifikacija — registracija i prijava s JWT tokenom, zaštićene rute
Oglasi nekretnina — pregled, pretraživanje, dodavanje, uređivanje i brisanje oglasa
Upload slika — slike oglasa i avatar profila pohranjuju se na Supabase Storage
Korisnički profil — prikaz i uređivanje osobnih podataka i avatara
Agencijski profil — zaseban tip računa za agencije s vlastitim oglasima
Bookmarks — spremi i pregledaj omiljene oglase u bočnoj traci
Responzivan dizajn — mobilna navigacija s hamburger menijem
