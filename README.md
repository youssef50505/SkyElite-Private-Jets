# SkyElite Private Jets

Welcome to the SkyElite Private Jets web application. This system consists of an Angular 18/19 frontend and a Spring Boot (Java) backend using a PostgreSQL database.

## Project Structure
- `/frontend` - Angular Application (UI)
- `/backend` - Spring Boot Application (API)
- `/db-seeder` - NodeJS script to automatically reset and populate the database with test data.

## Running the Application

### Quick Start (Recommended for Windows)
To launch both the backend and frontend simultaneously with a single click, simply double-click the `start.bat` file in the root directory, or run it from your terminal:
```bat
.\start.bat
```
This will open two new terminal windows (one for Spring Boot and one for Angular) and automatically launch the application in your browser once compiled.

---

### Manual Startup

**1. Database & Seeder**
The application relies on a local PostgreSQL database named `sAir`.
To reset the database and generate fresh data (including flights, bookings, and users):
```bash
cd db-seeder
node seed.js
```

**2. Backend (Spring Boot)**
To run the backend API server:
```bash
cd backend
./mvnw.cmd spring-boot:run
```
The API will be available at `http://localhost:8080`.

**3. Frontend (Angular)**
To run the web interface:
```bash
cd frontend
npm run start -- -o
```
This will automatically open your browser to `http://localhost:4200`.

---

## Test Accounts

The `db-seeder` automatically generates test users with proper bcrypt-hashed passwords. You can log into the application using any of the following accounts:

**Password for all accounts:** `password123`

| Role | Email |
|------|-------|
| **ADMIN** | Tyler.Koch@gmail.com |
| **OPERATIONS** | Rosamond_Spinka-Durgan72@gmail.com |
| **OPERATIONS** | Marilyn_Lang@hotmail.com |
| **OPERATIONS** | Summer.Quitzon@yahoo.com |
| **PASSENGER** | Austin.Batz@hotmail.com |

*(Note: These emails are randomly generated each time you run `node seed.js`. If you run the seeder again, simply look at the terminal output to see the new emails!)*
