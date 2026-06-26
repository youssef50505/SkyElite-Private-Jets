# Backend API Endpoints

This document outlines all the REST API endpoints available in the backend.

## Auth Controller
- `POST /api/v1/auth/login` - Authenticate a user.
- `POST /api/v1/auth/register` - Register a new user.

## Flight Controller
- `GET /api/v1/flights` - Retrieve all flights.
- `GET /api/v1/flights/search` - Search flights by `origin` and `destination`.
- `POST /api/v1/flights` - Schedule a new flight. Requires `OPERATIONS` or `ADMIN` role.
- `PATCH /api/v1/flights/{id}/status` - Update flight status. Requires `OPERATIONS` or `ADMIN` role.

## Booking Controller
- `POST /api/v1/bookings` - Create a charter booking. Requires `PASSENGER` role.
- `GET /api/v1/bookings/me` - Retrieve bookings for the authenticated passenger. Requires `PASSENGER` role.
- `GET /api/v1/bookings` - Retrieve all bookings. Requires `AGENT`, `OPERATIONS`, or `ADMIN` role.
- `PATCH /api/v1/bookings/{reference}/status` - Update booking status. Requires `AGENT`, `OPERATIONS`, or `ADMIN` role.

## Fleet Controller
- `GET /api/v1/aircrafts` - Retrieve all aircraft in the fleet. Requires `OPERATIONS` or `ADMIN` role.
- `POST /api/v1/aircrafts` - Add a new aircraft to the fleet. Requires `OPERATIONS` or `ADMIN` role.
- `PATCH /api/v1/aircrafts/{id}/status` - Update aircraft status. Requires `OPERATIONS` or `ADMIN` role.

## Analytics Controller
- `GET /api/v1/analytics/operations-command` - Retrieve operations command metrics.
- `GET /api/v1/analytics/revenue-management` - Retrieve revenue management metrics.

## Agent Controller
- `GET /api/v1/agent/clients` - Retrieve client portfolio for agents.
