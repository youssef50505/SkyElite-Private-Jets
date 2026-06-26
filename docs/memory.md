# Project Memory: SkyElite Private Jets (Flight Booking Platform)

> [!IMPORTANT]
> **STRICT INSTRUCTION FOR ALL SUBSEQUENT AGENTS:**
> This file is the absolute source of truth for the project. **Do not delete any content from this file under any circumstances.** You are only permitted to **append** new information, decisions, and statuses. Ensure you read this document thoroughly before commencing any work.

## 1. System Overview
**SkyElite Private Jets** is a premium, accessible flight booking and management platform designed to deliver an elite experience. It bridges the gap between passengers seeking private jet travel and the operational/agent teams managing the flights.
**Core Features:**
- Seamless flight search, booking, and seat selection.
- Comprehensive passenger dashboard for managing itineraries.
- Advanced Agent Portal for managing customer bookings.
- Operations Center for flight dispatch and status monitoring.
- Analytics & Revenue dashboards for business oversight.

## 2. Technology Stack & Architecture
- **Frontend:** Angular 18+ (Standalone Components, Signals)
- **Backend:** Java 21 & Spring Boot 4.1.0
- **Database:** PostgreSQL
- **Architecture Type:** Layered REST API Architecture (Clean Architecture, SOLID)
- **Styling:** **TailwindCSS** (Adopted for rapid implementation of 3D, Glassmorphism, and Premium Dark-Mode styling), GSAP for micro-animations.

## 3. Actors
1. **Passenger:** Can search flights, book seats, and manage reservations via the Passenger Dashboard.
2. **Agent:** Uses the Agent Portal to manage bookings on behalf of clients, handle inquiries, and process payments.
3. **Operations Staff:** Uses the Operations Center to monitor fleet status, dispatch flights, and track analytics & revenue.
4. **System Admin:** Manages user roles, fleet settings, and global configurations.

## 4. Screen Inventory (From UI Designs)
Images and mockups have been organized into the `docs/mockups/` directory for clarity.
1. **Passenger Homepage (Web & Mobile):** Landing page showcasing premium offerings (`index.html`).
2. **Flight Search (Web & Mobile):** Interface for searching availability, dates, and destinations.
3. **Booking & Seat Selection (Web & Mobile):** Flow for selecting seats and finalizing reservations.
4. **Passenger Dashboard (Web & Mobile):** Personal hub for users to view upcoming flights and history.
5. **Agent Portal (Web & Mobile):** Dashboard for agents to manage client bookings and CRM.
6. **Operations Center (Web & Mobile):** Fleet and flight tracking interface for staff.
7. **Analytics & Revenue (Web & Mobile):** KPI dashboard for high-level management.

## 5. Frontend Development Rules (Angular 18+)
- **Architecture:** 100% Standalone components. Use modern Angular control flows (`@if`, `@for`, `input()`).
- **State Management:** Leverage Angular Signals (`signal`, `computed`) and `NgRx SignalStore` for reactive, stateless management.
- **Styling & UI/UX:**
  - **TailwindCSS** configured globally with specific CSS variables (`styles.scss`) for brand colors (Gold, Deep Obsidian, Pearl White).
  - Typography: **Inter** and **Outfit** (Google Fonts).
  - Icons: **Remix Icons**. Emojis are strictly prohibited.
  - Implement a highly premium aesthetic with **Dark/Light mode** support using a custom `ThemeService`.
  - Micro-animations: Use **GSAP** (e.g., `GsapFadeDirective`) for staggered element entrances and smooth transitions. Force hardware acceleration (`will-change: transform, opacity`).
- **Responsiveness:** Must adhere strictly to the 2025–2026 viewport matrix defined in `DESIGN-HANDOFF.md` (from 360x800 up to 1920x1080).

### 5.1. Frontend Development State (Completed Baseline)
- **Framework:** Angular 18+ successfully initialized with TailwindCSS.
- **Routing & State:** `app.routes.ts` configured for standalone components. `AuthService` implemented using Angular Signals (`signal`, `computed`).
- **Security:** `JwtInterceptor` created to attach tokens. `auth.guard` and `role.guard` implemented.
- **Services:** `FleetService`, `FlightService`, and `BookingService` fully typed with Interfaces mapping to `endpoints.json`.
- **Components Built:** 
  - `HomeComponent`: Features a 3D parallax background and premium typography.
  - `FleetComponent`: Implements Glassmorphism cards fetching from backend.
  - `BookingWizardComponent`: A 4-step reactive booking wizard.
  - `DashboardComponent`: Passenger tracking dashboard with KPI metrics.
- **Next Steps:** Polishing, final API integration testing, and bug fixing.

## 6. Backend Architecture & Development State (Spring Boot 4.1.0 & Java 21)
> [!NOTE]
> The backend implementation is **100% complete and verified**.

### 6.1. Entities & Database
- `User` (Passengers, Agents, Operations, Admin)
- `Aircraft` (Tail numbers, capacity, range)
- `Flight` (Departure/Arrival schedules, Statuses, linked to Aircraft)
- `Airport` (IATA, location, landing fees)
- `CharterBooking` (Links Users to Flights with specific booking logic)
- `Payment` (Tracks transactions and statuses)
- `MaintenanceLog` (Tracks aircraft health and maintenance cycles)
- BaseEntity class tracks `createdAt` and `updatedAt`.
- The database is successfully mapped using Hibernate to PostgreSQL (`sAir` database).

### 6.2. Security Architecture
- Stateless JWT Authentication mechanism using `JwtAuthFilter` and `JwtUtils`.
- `SecurityConfig` set up with Spring Security 7 lambda DSL.
- Passwords encrypted via `BCryptPasswordEncoder`.

### 6.3. API Controllers & DTOs
- Implemented and documented in `endpoints.json`.
- `AuthController`, `FleetController`, `FlightOperationsController`, `BookingController`.
- All requests/responses mapped using `MapStruct` exclusively (e.g., `BookingMapper`, `FlightMapper`).
- Uses Java 21 `Record` types for all DTOs (e.g., `CharterBookingResponse`, `CreateFlightRequest`).

### 6.4. Error Handling
- Complete `GlobalExceptionHandler` via `@ControllerAdvice`.
- Returns standard **RFC 7807 ProblemDetail** JSON for exceptions (e.g., `MethodArgumentNotValidException`, `ResourceNotFoundException`).

## 7. Third-Party API Integrations
### 7.1. Flight Tracking (AviationStack)
- `AviationStackClient` implemented via modern `RestClient` (Spring 6.1+).
- Wraps API calls securely, ensuring `access_key` is not exposed to the frontend.
- Caching implemented (`@Cacheable` in `CacheConfig`) to mitigate rate-limiting costs.

## 8. Operational Guidelines
- **Think Before Creating:** It is mandatory to think deeply and more than once before creating or implementing any new component, feature, or logic.
- **Design Handoff Fidelity:** Treat the Figma/HTML exports as a visual contract. Match the exported pixels and behaviors precisely before refactoring internals.
- **Continuous Documentation:** Any new decision, feature, endpoint, or structural change made MUST be immediately documented and appended to this `memory.md` file.

## 10. Phase 6: Ascend Global Redesign (2026-06-22)

### 10.1. Rebranding & UI Refresh
- The project was rebranded from "SkyElite Private Jets" to "Ascend Global".
- Changed the primary brand color to Deep Blue (`#1E3A8A`) by replacing the `var(--ink)` tokens with `var(--primary)`.
- Updated `tailwind.config.js` and `styles.scss` with the new color palette, rounded buttons (`.btn`), and sans-serif fonts.

### 10.2. Component Updates
- **NavbarComponent**: Rewrote navigation to match the Ascend Global mockup (left-aligned Book/My Trips, center Logo, right-aligned controls).
- **HomeComponent**: Completely redesigned the landing page. Added a full-width hero section, a floating flight search widget, feature sections (Premium Assurance, Sky Rewards, Elite Service), Curated Escapes image cards, and a Global Footprint stats map.
- **Auth Flow**: Updated `LoginComponent` to use the new aesthetic (clean white cards, blue accents) and added a brand new `RegisterComponent` for user signups.
- **App Routes**: Registered the new `/register` route in `app.routes.ts`.
- **Inner Pages**: Migrated `FleetComponent`, `DashboardComponent`, and `BookingWizardComponent` to use the new `var(--primary)` color system and standard button classes (`btn-primary`, `btn-secondary`).
- **Document Title**: Updated `index.html` title to "Ascend Global".
## 9. Phase 5: Frontend Design-Fidelity Rewrite (2026-06-22)

### 9.1. Critical Decision: Light Theme (Not Dark)
The Visily design export (`docs/index.html`) uses a **light theme** with:
- Background: `--gray-50: #f9fafb`
- Primary text: `--ink: #202A36`
- Font: **Inter** only (400, 500, 600, 700)
- Buttons: rounded-full pills with `--ink` background or `--gray-300` background
- Video hero with centered two-line headline ("Premium." / "Accessible.")

The previous agent had built a dark-mode gold/obsidian theme that did not match the design export. All components were rewritten to match the Visily pixel contract.

### 9.2. Design Tokens Extracted
```css
--ink: #202A36;       /* Primary foreground / buttons */
--ink-hover: #1a2229; /* Button hover state */
--gray-50: #f9fafb;   /* Page background */
--gray-300: #d1d5db;  /* Secondary button bg */
--gray-400: #9ca3af;  /* Secondary button hover */
--gray-500: #6b7280;  /* Muted headline text */
--gray-600: #4b5563;  /* Subtitle / eyebrow text */
```

### 9.3. Files Created/Rewritten
- `tailwind.config.js` — Ink + surface tokens only
- `styles.scss` — Light base with CSS custom properties matching export
- `NavbarComponent` — Light transparent-to-white glassmorphism nav with SVG hamburger icons
- `HomeComponent` — Pixel-match of Visily hero: video bg, eyebrow, two-line headline, Discover/Book Now CTAs
- `LoginComponent` — White card on gray-50 bg, ink-colored CTA
- `FleetComponent` — White cards with subtle shadows, emerald/amber status badges
- `BookingWizardComponent` — 3-step wizard with progress dots, white card, rounded-xl inputs
- `DashboardComponent` — Metric cards + booking list, light theme
- `index.html` — Added Google Fonts (Inter), Remix Icons CDN, SEO meta
- `app.html` — Replaced 20KB Angular CLI default with `<app-navbar>` + `<router-outlet>`
- `app.ts` — Imports NavbarComponent
- `app.routes.ts` — Added /login route, re-enabled authGuard on /dashboard

## 11. Phase 7: Comprehensive Passenger UI & DB Seeding (2026-06-26)

### 11.1. Database Seeding & Security
- Overhauled `db-seeder/seed.js` to use `bcryptjs` for generating proper Spring Security-compatible password hashes (`password123`).
- Created a comprehensive `README.md` containing running instructions and 5 pre-generated test accounts for each role (Passenger, Agent, Operations, Admin).

### 11.2. Role-Based Navigation
- Refactored `NavbarComponent` to inject `AuthService`.
- Navigation links and action buttons are now dynamically toggled based on authentication state and user roles (`PASSENGER`, `AGENT`, `OPERATIONS`, `ADMIN`).
- Implemented a premium dark-mode glassmorphism aesthetic (`backdrop-blur-lg` with `dark:bg-ink/90`).

### 11.3. Passenger Homepage (`/home`)
- **Backend:** Added `findTop5ByFlightTypeAndStatusOrderByScheduledDepartureAsc` to `FlightRepository` and exposed it via `GET /api/v1/flights/featured`.
- **Frontend:** Built the Passenger Homepage with a floating Quick Flight Search widget (ReactiveForms), Value Propositions section, and a Featured Empty-Legs Carousel that dynamically pulls from the database.

### 11.4. Flight Search Module (`/search`)
- **Routing:** Automatically parses `origin`, `destination`, `date`, and `passengers` from URL query parameters.
- **Data:** Hooks into `flightService.searchFlights()` to pull live results.
- **UI:** Features a sleek horizontal search bar for inline updates and renders results using "Dynamic Pricing Cards" showing tiers (Economy, Premium, Business, First Class) depending on available data.

### 11.5. Passenger Dashboard (`/dashboard`)
- **Data:** Connects to secure `/api/v1/bookings/me` endpoint to fetch user itineraries.
- **Logic:** Sorts payload into `upcomingFlights` and `pastFlights`, extracts the absolute closest flight for the "Next Trip" feature, and calculates mock SkyRewards points.
- **UI:** Features an immersive "Next Trip" hero widget showing exact origin/destination, times, and aircraft tail numbers. Below, it displays a clean table of past booking history with status badges.

## 12. Instructions for Future Agents
> [!IMPORTANT]
> - **Read this Document First:** Treat this `memory.md` file as the holy grail. It contains the exact state of the project.
> - **Tool Usage:** Always prioritize the most specific tool for the job. Do not use generic bash scripts (like `cat` for file creation) when a dedicated API tool exists (e.g., `write_to_file`).
> - **Architecture Adherence:** Ensure any new Angular components are 100% Standalone and use modern control flows (`@if`, `@for`). For the backend, adhere strictly to the existing Layered REST API Architecture utilizing `MapStruct` and Java 21 Records.
> - **Aesthetics:** The project strictly follows a premium, dark-mode-compatible UI using TailwindCSS. Ensure all new UI components are responsive, utilize GSAP for micro-animations when appropriate, and align with the existing glassmorphism aesthetic.

## 13. Full Stack Architecture Deep-Dive (Source of Truth)

To ensure any developer (or agent) can instantly understand the mechanics of this system, below is the comprehensive breakdown of the entire Full Stack architecture.

### 13.1. Database Schema & Core Entities (PostgreSQL)
The backend utilizes Spring Data JPA to map Java Objects to PostgreSQL tables.
- **`User` (`users` table):** Stores all actors. Fields: `id` (UUID), `firstName`, `lastName`, `email` (Unique), `password` (BCrypt), `phoneNumber`, `role` (Enum: PASSENGER, AGENT, OPERATIONS, ADMIN).
- **`Aircraft` (`aircrafts` table):** The physical jets. Fields: `id`, `tailNumber`, `model`, `passengerCapacity`, `rangeNauticalMiles`, `hourlyRate`, `currentLocationIata`, `status` (Enum: AVAILABLE, IN_FLIGHT, MAINTENANCE).
- **`Flight` (`flights` table):** Scheduled trips. Fields: `id`, `aircraft_id` (FK), `departureAirportIata`, `arrivalAirportIata`, `scheduledDeparture`, `scheduledArrival`, `flightType` (Enum: CHARTER, EMPTY_LEG, REPOSITIONING), `status` (Enum: SCHEDULED, EN_ROUTE, LANDED, DELAYED, CANCELLED).
- **`CharterBooking` (`charter_bookings` table):** Links Passengers to Flights. Fields: `id`, `bookingReference` (Unique String), `flight_id` (FK), `passenger_id` (FK User), `agent_id` (FK User, optional), `bookingDate`, `specialRequests`, `totalAmount`, `status` (Enum: PENDING_PAYMENT, CONFIRMED, CANCELLED, COMPLETED).
- **`Airport` (`airports` table):** IATA codes, Names, Cities, Countries, Lat/Lon coordinates, baseLandingFee.

### 13.2. Complete REST API Surface (`/api/v1/*`)
All endpoints consume and produce `application/json` and are protected by Spring Security (excluding `/auth/*` and public `/search`).
- **Auth Controller:**
  - `POST /auth/login` - Authenticates credentials, returns `{token, email, role}`.
  - `POST /auth/register` - Creates a new user, hashes password, returns JWT.
- **Fleet Controller (Requires OPERATIONS/ADMIN):**
  - `GET /aircrafts` - Returns all fleet assets.
  - `POST /aircrafts` - Adds a new aircraft to the fleet.
  - `PATCH /aircrafts/{id}/status` - Updates physical aircraft status.
- **Flight Controller:**
  - `GET /flights` - Returns all flights.
  - `GET /flights/search` - Searches flights by origin, destination.
  - `GET /flights/featured` - Returns top upcoming "EMPTY_LEG" flights for the homepage.
  - `POST /flights` - Schedules a new flight (Operations/Admin).
  - `PATCH /flights/{id}/status` - Updates flight operational status.
- **Booking Controller:**
  - `POST /bookings` - Passenger creates a new charter booking.
  - `GET /bookings/me` - Passenger fetches their own booking history.
  - `GET /bookings` - Agent/Operations fetch all system bookings.
  - `PATCH /bookings/{reference}/status` - Agent/Operations update booking payment/lifecycle status.

### 13.3. Frontend Module Structure (Angular 18+)
The frontend is strictly constructed using Standalone Components routed in `app.routes.ts`.
- **`core/`:** Contains singletons. `AuthService`, `BookingService`, `FlightService`, `FleetService`. Contains `JwtInterceptor` (attaches Bearer token) and Auth/Role Guards (`auth.guard.ts`, `role.guard.ts`).
- **`shared/`:** Contains reusable UI like `NavbarComponent` (handles dynamic role-based rendering and logout).
- **`features/auth/`:** `LoginComponent` & `RegisterComponent` (Form controls, JWT parsing).
- **`features/home/`:** The public landing/homepage featuring the Quick Search Widget and dynamic Featured Empty-Legs carousel.
- **`features/search/`:** Flight search results engine utilizing URL query params (`origin`, `destination`) to fetch and display Dynamic Pricing Cards.
- **`features/dashboard/`:** The Passenger Dashboard. Fetches `/bookings/me` to separate itineraries into `upcomingFlights` and `pastFlights`, surfacing the most imminent trip in a hero widget.
- **`features/fleet/`:** Operations dashboard for tracking physical aircraft statuses.
- **`features/agent-portal/` (Planned):** Will consume `GET /bookings` for global booking management.

### 13.4. Security Flow (End-to-End)
1. **Frontend Login:** User submits credentials to `/auth/login`.
2. **Backend Auth:** Spring `AuthenticationManager` verifies the BCrypt hash. If valid, `JwtUtils` signs a token containing the user's email and `ROLE_X` claims.
3. **Frontend Storage:** `AuthService` stores the JWT in `localStorage` and decodes it to signal the active user state across the app.
4. **Subsequent Requests:** The Angular `JwtInterceptor` intercepts outbound HTTP calls and injects `Authorization: Bearer <token>`.
5. **Backend Verification:** `JwtAuthFilter` intercepts inbound requests, validates the token signature, and populates the `SecurityContextHolder`.
6. **Authorization:** Controller methods utilize `@PreAuthorize("hasRole('PASSENGER')")` to physically block unauthorized access.
7. **Frontend Guards:** `auth.guard.ts` blocks unauthenticated routing, and `role.guard.ts` verifies the stored token role against the route's `data: { roles: [...] }` array, kicking unauthorized users to the homepage.
