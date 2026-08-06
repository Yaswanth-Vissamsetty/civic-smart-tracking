# Civic Smart Tracking System

A Spring Boot web application for citizens to register civic complaints and
track their resolution status. Administrators can review complaints, update
their status, and view registered citizens.

## Links

- Live application: https://civic-smart-tracking.onrender.com
- GitHub repository: https://github.com/Yaswanth-Vissamsetty/civic-smart-tracking

## Features

### Citizen

- Register and log in
- Submit a civic complaint
- View submitted complaints
- Track a complaint using its complaint ID
- Edit profile details

### Administrator

- Admin login
- View all complaints
- Search complaints
- Update complaint status: Submitted, In Progress, or Resolved
- Add resolution remarks
- Delete a complaint
- View registered citizens

## Complaint Categories

- Water Leakage
- Water Supply Issue
- Drainage Problem
- Garbage Collection
- Street Light Issue

## Technology Used

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- Bootstrap 5
- HTML, CSS, and JavaScript
- H2 database for local development
- PostgreSQL on Render for deployment
- Maven

## Run Locally

### Requirements

- Java 17 or later
- Maven

### Steps

```bash
git clone https://github.com/Yaswanth-Vissamsetty/civic-smart-tracking.git
cd civic-smart-tracking
mvn spring-boot:run
```

Open http://localhost:8080 in a browser.

The project uses an embedded H2 database locally, so no database installation
is required.

## Demo Login Details

| Account | Email | Password |
| --- | --- | --- |
| Administrator | admin@civic.gov.in | admin123 |
| Citizen | rahul@gmail.com | citizen123 |

## Deployment

The application is deployed on Render using Docker and a managed PostgreSQL
database. The `render.yaml` file creates the web service and database, while
the `Dockerfile` builds and runs the Spring Boot application.

## Project Structure

```text
src/main/java/com/civic/smarttracking/
├── controller/     # Request handling
├── service/        # Business logic
├── repository/     # Database access
├── entity/         # JPA entities
└── CivicSmartTrackingApplication.java

src/main/resources/
├── static/         # CSS and JavaScript
├── templates/      # Thymeleaf pages
├── application.properties
├── schema.sql
└── data.sql
```

## Status Flow

```text
Submitted → In Progress → Resolved
```
