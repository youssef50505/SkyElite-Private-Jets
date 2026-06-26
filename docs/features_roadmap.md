# Features Roadmap

This document outlines the core functional concepts and modules extracted from the project's UI mockups. These concepts define the blueprint for our application's frontend architecture and future feature development.

## 1. Passenger-Facing Modules

### Homepage (`visily-passenger-homepage`)
- **Hero Banner:** Premium visual entry point emphasizing luxury and accessibility.
- **Quick Flight Search:** A compact widget for users to immediately input origin, destination, dates, and passengers.
- **Value Propositions:** Highlighted benefits of using the platform (e.g., speed, luxury, cost-efficiency).
- **Featured Flights Carousel:** A dynamic display highlighting discounted empty-leg flights currently available for immediate booking.

### Flight Search & Booking (`visily-flight-search`, `visily-booking-&-seat-selection`)
- **Advanced Search Parameters:** One-way vs. Round-trip vs. Multi-city toggles, date pickers, and passenger count.
- **Dynamic Pricing Cards:** Display of search results showing different aircraft options, estimated flight times, and clear pricing tiers (Economy, Premium, Business, First Class equivalents).
- **Interactive Seat Map:** A visual, clickable aircraft layout allowing users to select specific seats during the booking flow.
- **Passenger Intake Form:** Collection of mandatory traveler details (Passport info, preferences).
- **Secure Payment Gateway:** Integrated checkout flow for credit card or bank transfer processing.

### Passenger Dashboard (`visily-passenger-dashboard`)
- **Trips Tracker:** Visual timeline of upcoming and past flights.
- **Loyalty Status Overview:** Progress bar or badge displaying the user's current tier (None, Bronze, Silver, Gold, Platinum).
- **Profile & Preferences Management:** A section to update dietary restrictions, preferred seating, and saved travel documents.

---

## 2. Agent & Partner Modules

### Agent Portal (`visily-agent-portal`)
- **Client Roster:** A CRM-lite interface for travel agents to manage their clients and profiles.
- **Commission & Performance Tracking:** Graphs and tables showing earnings, completed bookings, and pending payments.
- **Bulk Booking Workflow:** Specialized tools for booking multiple flights or chartering entire jets on behalf of clients.
- **Custom Quoting Tools:** Interfaces to generate and send custom price quotes for charter flights.

---

## 3. Operations & Administrative Modules

### Operations Center (`visily-operations-center`)
- **Real-Time Fleet Tracking:** A live, interactive map showing the current location and status (IN_FLIGHT, AVAILABLE, MAINTENANCE) of all aircraft.
- **Flight Scheduling Matrix:** A calendar/Gantt chart view of all scheduled flights, highlighting potential conflicts or delays.
- **Crew Management:** Tools to assign pilots and flight attendants to specific routes.
- **Weather Integration:** Live weather data overlays to assist dispatchers in routing.

### Analytics & Revenue (`visily-analytics-&-revenue`)
- **Financial Dashboards:** High-level charts showing daily, weekly, and monthly revenue.
- **Fleet Utilization Metrics:** Reports on how efficiently aircraft are being used (e.g., hours flown vs. idle time).
- **Conversion Tracking:** Metrics detailing how many flight searches convert into completed bookings.
