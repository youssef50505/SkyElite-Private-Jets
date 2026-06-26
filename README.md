# SkyElite Private Jets

Welcome to the SkyElite Private Jets web application. This system consists of an Angular 18/19 frontend and a Spring Boot (Java) backend using a PostgreSQL database.

## Project Structure
- `/frontend` - Angular Application (UI)
- `/backend` - Spring Boot Application (API)
- `/db-seeder` - NodeJS script to automatically reset and populate the database with test data.

## Running the Application

### Quick Start (Recommended for Windows)
To launch the entire stack with a single click, simply double-click the `start.bat` file in the root directory.

This robust script will automatically:
1. Verify Java and Node.js are installed.
2. Install dependencies for and run the Database Seeder (resetting the DB).
3. Open a terminal to boot the Spring Boot Backend.
4. Install dependencies for and launch the Angular Frontend, automatically opening your browser.

```bat
.\start.bat
```

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
| **ADMIN** | admin@skyelite.com |
| **OPERATIONS** | ops@skyelite.com |
| **AGENT** | agent@skyelite.com |
| **PASSENGER** | passenger@skyelite.com |

*(Note: These are static test accounts that are guaranteed to exist after running the database seeder.)*
