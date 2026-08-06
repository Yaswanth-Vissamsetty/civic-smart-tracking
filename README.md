# 🚀 Civic Smart Tracking System

<div align="center">

### 🏛️ A Spring Boot Based Civic Complaint Registration & Tracking Portal

Empowering citizens to report civic issues and track their resolution efficiently.

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge\&logo=springboot)
![Spring MVC](https://img.shields.io/badge/Spring-MVC-success?style=for-the-badge)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data-JPA-blue?style=for-the-badge)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template-green?style=for-the-badge)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple?style=for-the-badge\&logo=bootstrap)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge\&logo=postgresql)

🌐 **Live Demo:** https://civic-smart-tracking.onrender.com

📂 **GitHub Repository:** https://github.com/Yaswanth-Vissamsetty/civic-smart-tracking

</div>

---

# 📖 Overview

Civic Smart Tracking System is a web-based complaint management application developed using **Java Spring Boot**.

The application allows citizens to register civic complaints related to public services such as water supply, drainage, garbage collection, and street lighting. Citizens can monitor the progress of their complaints while administrators can review, manage, and update complaint statuses through a dedicated admin portal.

The project follows the **Spring Boot MVC Architecture** and uses **Spring Data JPA** for database operations.

---

# ✨ Key Features

## 👤 Citizen Module

* User Registration
* Secure Login
* Submit Civic Complaints
* View Submitted Complaints
* Track Complaint using Complaint ID
* Update Profile Information

---

## 🛠️ Administrator Module

* Admin Login
* View All Complaints
* Search Complaints
* Update Complaint Status
* Add Resolution Remarks
* Delete Complaints
* View Registered Citizens

---

# 📋 Complaint Categories

* 💧 Water Leakage
* 🚰 Water Supply Issue
* 🚧 Drainage Problem
* 🗑️ Garbage Collection
* 💡 Street Light Issue

---

# 🔄 Complaint Status Workflow

```text
Submitted
      │
      ▼
In Progress
      │
      ▼
Resolved
```

---

# 🛠️ Technology Stack

## Backend

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* Maven

## Frontend

* Thymeleaf
* HTML5
* CSS3
* Bootstrap 5
* JavaScript

## Database

### Local Development

* H2 Database

### Production

* PostgreSQL

---

# 🏗️ Project Architecture

```text
Browser
      │
      ▼
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
Database
```

The application follows the standard **Spring Boot MVC Architecture**, where:

* Controller handles incoming HTTP requests.
* Service contains business logic.
* Repository interacts with the database.
* Entity represents database tables.

---

# 📁 Project Structure

```text
src
 ├── main
 │
 ├── java
 │     └── com.civic.smarttracking
 │           ├── controller
 │           ├── service
 │           ├── repository
 │           ├── entity
 │           └── CivicSmartTrackingApplication.java
 │
 └── resources
       ├── static
       │      ├── css
       │      └── js
       │
       ├── templates
       │
       ├── application.properties
       ├── schema.sql
       └── data.sql
```

---

# 🗄️ Database Design

## Users

| Field    | Type   |
| -------- | ------ |
| id       | Long   |
| name     | String |
| email    | String |
| password | String |
| mobile   | String |

---

## Complaints

| Field         | Type   |
| ------------- | ------ |
| id            | Long   |
| complaintId   | String |
| category      | String |
| description   | String |
| location      | String |
| complaintDate | Date   |
| status        | String |
| remarks       | String |

---

# 🚀 Application Flow

```text
Citizen Registration
        │
        ▼
Citizen Login
        │
        ▼
Raise Complaint
        │
        ▼
Complaint Stored in Database
        │
        ▼
Admin Reviews Complaint
        │
        ▼
Admin Updates Status
        │
        ▼
Citizen Tracks Complaint
```

---

# 💻 Run Locally

## Prerequisites

* Java 17+
* Maven

Clone the repository

```bash
git clone https://github.com/Yaswanth-Vissamsetty/civic-smart-tracking.git
```

Navigate to the project

```bash
cd civic-smart-tracking
```

Run the application

```bash
mvn spring-boot:run
```

Open

```
http://localhost:8080
```

---

# 🔑 Demo Credentials

## Administrator

Email

```
admin@civic.gov.in
```

Password

```
admin123
```

---

## Citizen

Email

```
rahul@gmail.com
```

Password

```
citizen123
```

---

# 🌍 Deployment

The application is deployed on **Render**.

Production Database

* PostgreSQL

Deployment includes:

* Docker
* Render Web Service
* Managed PostgreSQL Database

---

# 📸 Screenshots

Add screenshots here after deployment.

Example:

Home page
<img width="1920" height="3595" alt="fullpage_snapshot_localhost_2026-08-06-21-33-51" src="https://github.com/user-attachments/assets/7ee0f613-8281-447f-8872-0e3733efb4e5" />

User Page
<img width="1897" height="813" alt="user" src="https://github.com/user-attachments/assets/47fe6682-80d1-4900-b6f3-3c41fcb2b4dd" />

Admin Page
<img width="1920" height="879" alt="fullpage_snapshot_localhost_2026-08-06-21-36-13" src="https://github.com/user-attachments/assets/4fee9389-feaf-4b44-898e-ce3931d4513f" />




# 📈 Future Enhancements

* Email Notifications
* Complaint Image Upload
* SMS Alerts
* Location Integration
* Complaint Analytics
* Mobile Responsive Improvements
* Search & Filters
* Role-based Security using Spring Security

---

# 👨‍💻 Developed By

**Yaswanth Vissamsetty**

B.Tech - Computer Science & Engineering

Java Full Stack Developer

GitHub:
https://github.com/Yaswanth-Vissamsetty

---

# ⭐ If you like this project

Give this repository a ⭐ on GitHub.
