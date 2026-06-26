const { Client } = require('pg');
const { faker } = require('@faker-js/faker');
const crypto = require('crypto');
const bcrypt = require('bcryptjs');

const client = new Client({
  connectionString: 'postgresql://postgres:password@localhost:5432/sAir'
});

async function seed() {
  await client.connect();
  console.log("Connected to PostgreSQL");

  try {
    const uuid = () => crypto.randomUUID();
    const now = new Date().toISOString();
    const defaultPasswordHash = bcrypt.hashSync('password123', 10);

    console.log("Clearing existing data...");
    await client.query(`
      DELETE FROM refund_requests;
      DELETE FROM payments;
      DELETE FROM charter_bookings;
      DELETE FROM flight_amenities;
      DELETE FROM flights;
      DELETE FROM users;
      DELETE FROM passenger_preferences;
      DELETE FROM passenger_profiles;
      DELETE FROM pricing_rules;
      DELETE FROM maintenance_logs;
      DELETE FROM aircrafts;
      DELETE FROM airports;
    `);

    console.log("Generating Airports...");
    const airports = [];
    for (let i = 0; i < 15; i++) {
      const iata = faker.airline.airport().iataCode;
      const res = await client.query(`
        INSERT INTO airports (iata_code, icao_code, name, city, country, latitude, longitude, landing_fee, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10)
        ON CONFLICT (iata_code) DO NOTHING
        RETURNING iata_code;
      `, [
        iata,
        faker.string.alpha(4).toUpperCase(),
        faker.airline.airport().name,
        faker.location.city(),
        faker.location.country(),
        faker.location.latitude(),
        faker.location.longitude(),
        faker.commerce.price({ min: 500, max: 2000 }),
        now, now
      ]);
      if (res.rows[0]) airports.push(res.rows[0].iata_code);
    }

    console.log("Generating Aircrafts...");
    const aircrafts = [];
    const statuses = ['AVAILABLE', 'MAINTENANCE', 'IN_FLIGHT'];
    for (let i = 0; i < 12; i++) {
      const id = uuid();
      await client.query(`
        INSERT INTO aircrafts (id, tail_number, model, passenger_capacity, range_nautical_miles, hourly_rate, current_airport_iata, image_url, status, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11)
      `, [
        id,
        faker.airline.airplane().iataTypeCode + '-' + faker.string.numeric(3),
        faker.airline.airplane().name,
        faker.number.int({ min: 4, max: 20 }),
        faker.number.int({ min: 1000, max: 6000 }),
        faker.commerce.price({ min: 2000, max: 15000 }),
        faker.helpers.arrayElement(airports),
        faker.image.url(),
        faker.helpers.arrayElement(statuses),
        now, now
      ]);
      aircrafts.push(id);
    }

    console.log("Generating Pricing Rules...");
    for (let i = 0; i < 10; i++) {
      await client.query(`
        INSERT INTO pricing_rules (id, name, rule_type, conditions, multiplier, is_active, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8)
      `, [
        uuid(),
        faker.commerce.productName() + ' Rule',
        faker.helpers.arrayElement(['GLOBAL_MULTIPLIER', 'REGIONAL_SURGE']),
        'Condition ' + i,
        faker.number.float({ min: 0.8, max: 1.5 }),
        true,
        now, now
      ]);
    }

    console.log("Generating Passenger Profiles...");
    const profiles = [];
    for (let i = 0; i < 15; i++) {
      const id = uuid();
      await client.query(`
        INSERT INTO passenger_profiles (id, date_of_birth, gender, passport_number, passport_expiry_date, nationality, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8)
      `, [
        id,
        faker.date.birthdate(),
        faker.person.sex(),
        faker.string.alphanumeric(9).toUpperCase(),
        faker.date.future(),
        faker.location.country(),
        now, now
      ]);
      
      await client.query(`
        INSERT INTO passenger_preferences (profile_id, preference) VALUES ($1, $2)
      `, [id, faker.helpers.arrayElement(['Vegan', 'Window Seat', 'Extra Luggage'])]);

      profiles.push(id);
    }

    console.log("Generating Users...");
    const users = [];
    const roles = ['PASSENGER', 'AGENT', 'OPERATIONS', 'ADMIN'];
    const loyalties = ['NONE', 'SILVER', 'GOLD', 'PLATINUM'];
    console.log("\n--- TEST USER ACCOUNTS ---");
    for (let i = 0; i < 15; i++) {
      const id = uuid();
      const email = faker.internet.email();
      const role = faker.helpers.arrayElement(roles);
      await client.query(`
        INSERT INTO users (id, first_name, last_name, email, password_hash, phone_number, role, loyalty_status, passenger_profile_id, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11)
      `, [
        id,
        faker.person.firstName(),
        faker.person.lastName(),
        email,
        defaultPasswordHash, // real bcrypt hash for 'password123'
        faker.phone.number(),
        role,
        faker.helpers.arrayElement(loyalties),
        profiles[i],
        now, now
      ]);
      if (i < 5) console.log(`Role: ${role} | Email: ${email} | Password: password123`);
      users.push(id);
    }
    console.log("--------------------------\n");

    console.log("Generating Flights...");
    const flights = [];
    const flightTypes = ['CHARTER', 'EMPTY_LEG', 'MAINTENANCE'];
    const flightStatuses = ['SCHEDULED', 'EN_ROUTE', 'LANDED', 'CANCELLED'];
    for (let i = 0; i < 15; i++) {
      const id = uuid();
      await client.query(`
        INSERT INTO flights (id, aircraft_id, departure_airport_iata, arrival_airport_iata, scheduled_departure, scheduled_arrival, actual_departure, actual_arrival, flight_type, flight_number, economy_price, premium_economy_price, business_price, first_class_price, status, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15, $16, $17)
      `, [
        id,
        faker.helpers.arrayElement(aircrafts),
        faker.helpers.arrayElement(airports),
        faker.helpers.arrayElement(airports),
        faker.date.future(),
        faker.date.future(),
        null, null,
        faker.helpers.arrayElement(flightTypes),
        'SE' + faker.number.int({ min: 100, max: 999 }),
        faker.commerce.price({ min: 500, max: 1500 }),
        faker.commerce.price({ min: 1500, max: 3000 }),
        faker.commerce.price({ min: 3000, max: 6000 }),
        faker.commerce.price({ min: 6000, max: 12000 }),
        faker.helpers.arrayElement(flightStatuses),
        now, now
      ]);
      
      await client.query(`
        INSERT INTO flight_amenities (flight_id, amenity) VALUES ($1, $2)
      `, [id, faker.helpers.arrayElement(['Wi-Fi', 'Premium Catering', 'Lie-flat seats'])]);

      flights.push(id);
    }

    console.log("Generating Charter Bookings...");
    const bookings = [];
    const bookingStatuses = ['PENDING_PAYMENT', 'CONFIRMED', 'COMPLETED', 'CANCELLED'];
    for (let i = 0; i < 15; i++) {
      const id = uuid();
      await client.query(`
        INSERT INTO charter_bookings (id, booking_reference, passenger_id, flight_id, agent_id, booking_date, special_requests, cabin_class, total_amount, status, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12)
      `, [
        id,
        faker.string.alphanumeric(8).toUpperCase(),
        faker.helpers.arrayElement(users),
        faker.helpers.arrayElement(flights),
        faker.helpers.arrayElement(users),
        faker.date.recent(),
        'None',
        faker.helpers.arrayElement(['ECONOMY', 'FIRST_CLASS']),
        faker.commerce.price({ min: 5000, max: 50000 }),
        faker.helpers.arrayElement(bookingStatuses),
        now, now
      ]);
      bookings.push(id);
    }

    console.log("Generating Payments...");
    const payments = [];
    const payMethods = ['CREDIT_CARD', 'BANK_TRANSFER'];
    const payStatuses = ['SUCCESS', 'FAILED', 'REFUNDED', 'PENDING'];
    for (let i = 0; i < 15; i++) {
      const id = uuid();
      await client.query(`
        INSERT INTO payments (id, booking_id, amount, payment_date, payment_method, transaction_reference, status, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)
      `, [
        id,
        faker.helpers.arrayElement(bookings),
        faker.commerce.price({ min: 5000, max: 50000 }),
        faker.date.recent(),
        faker.helpers.arrayElement(payMethods),
        faker.string.uuid(),
        faker.helpers.arrayElement(payStatuses),
        now, now
      ]);
      payments.push(id);
    }

    console.log("Generating Refund Requests...");
    const refundPriorities = ['HIGH', 'MEDIUM', 'LOW'];
    const refundStatuses = ['PENDING', 'APPROVED', 'REJECTED'];
    for (let i = 0; i < 10; i++) {
      await client.query(`
        INSERT INTO refund_requests (id, passenger_id, booking_id, amount, reason, priority, status, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)
      `, [
        uuid(),
        faker.helpers.arrayElement(users),
        faker.helpers.arrayElement(bookings),
        faker.commerce.price({ min: 500, max: 5000 }),
        faker.lorem.sentence(),
        faker.helpers.arrayElement(refundPriorities),
        faker.helpers.arrayElement(refundStatuses),
        now, now
      ]);
    }

    console.log("Generating Maintenance Logs...");
    for (let i = 0; i < 10; i++) {
      await client.query(`
        INSERT INTO maintenance_logs (id, aircraft_id, description, start_date, end_date, cost, created_at, updated_at)
        VALUES ($1, $2, $3, $4, $5, $6, $7, $8)
      `, [
        uuid(),
        faker.helpers.arrayElement(aircrafts),
        faker.lorem.paragraph(),
        faker.date.recent(),
        faker.date.recent(),
        faker.commerce.price({ min: 1000, max: 20000 }),
        now, now
      ]);
    }

    console.log("✅ Seed complete! Successfully inserted 10+ records into all tables.");

  } catch (error) {
    console.error("❌ Seeding failed:", error);
  } finally {
    await client.end();
  }
}

seed();
