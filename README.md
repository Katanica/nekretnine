Full-stack web application for advertising and browsing real estate listings, built as a team project at FSRE. Allows users to browse listings, manage their own profile and agency account, and bookmark listings.

✨ Features

Authentication — registration and login with JWT token, protected routes
Real estate listings — browse, search, add, edit, and delete listings
Image upload — listing images and profile avatars stored on Supabase Storage
User profile — view and edit personal info and avatar
Agency profile — separate account type for agencies with their own listings
Bookmarks — save and browse favourite listings in the sidebar
Responsive design — mobile navigation with hamburger menu

⚙️ Tech Stack

Layer          Technologies
Frontend       React, React Router v6, CSS Modules
Backend        Java, Spring Boot, Spring Security (JWT)
Database       PostgreSQL (Neon cloud)
Storage        Supabase Storage (listing images and avatars)
ORM / Mapping  JPA/Hibernate, MapStruc

MapStruct

📁 Repository Structure

nekretnine/
├── backend/                  # Spring Boot application
│   └── src/
│       └── main/java/
│           ├── controller/
│           ├── service/
│           ├── repository/
│           ├── entity/
│           ├── dto/
│           └── mapper/
├── frontend/                 # React application
│   └── src/
│       ├── components/
│       ├── pages/
│       ├── context/
│       └── api/
├── ER-dijagram-nekretnine.jpeg
└── baza.jpeg

🚀 Running Locally
Prerequisites: 
Java 17+
Node.js 18+
PostgreSQL database (or Neon cloud account)
Supabase project (for file storage

cd backend

Configure application.properties:

spring.datasource.url
spring.datasource.username / password
jwt.secret
supabase.url / supabase.key

./mvnw spring-boot:run
Backend available at http://localhost:8080.

Frontend:
cd frontend
npm install
npm run dev

App available at http://localhost:5173.

Create a .env file in the frontend/ directory.
VITE_API_URL=http://localhost:8080
