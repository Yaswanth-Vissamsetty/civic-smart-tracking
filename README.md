# 🚀 Civic Smart Tracking System

<div align="center">

<h3>🏛️ Smart Civic Complaint Registration & Tracking Portal</h3>

<p>
A full-stack web application built using <strong>Java Spring Boot</strong> that enables citizens to register civic complaints and track their resolution status while allowing administrators to efficiently manage and resolve issues.
</p>

<p>

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge\&logo=springboot)
![Spring MVC](https://img.shields.io/badge/Spring-MVC-success?style=for-the-badge)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-blue?style=for-the-badge)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template-green?style=for-the-badge)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5-7952B3?style=for-the-badge\&logo=bootstrap)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?style=for-the-badge\&logo=postgresql)

</p>

🌐 **Live Demo**
https://civic-smart-tracking.onrender.com

📂 **GitHub Repository**
https://github.com/Yaswanth-Vissamsetty/civic-smart-tracking

</div>

---

# 📑 Table of Contents

* 📖 Overview
* ✨ Features
* 🏛️ System Modules
* 📋 Complaint Categories
* 🔄 Complaint Workflow
* 🛠️ Technology Stack
* 🏗️ Architecture
* 📁 Project Structure
* 🗄️ Database Design
* 🚀 Application Flow
* 💻 Installation
* 🔑 Demo Credentials
* 🌍 Deployment
* 📸 Screenshots
* 📈 Future Enhancements
* 👨‍💻 Developer

---

# 📖 Overview

Civic Smart Tracking System is a web-based complaint management portal designed to simplify communication between citizens and local authorities.

Citizens can quickly register complaints related to public services, monitor the progress of their complaints, and receive status updates. Administrators can manage complaints, update their progress, add resolution remarks, and oversee registered users from a centralized dashboard.

The application follows the **Spring Boot MVC architecture**, ensuring clean separation of concerns and maintainable code.

---

# ✨ Features

### 👤 Citizen

* ✅ User Registration
* ✅ Secure Login
* ✅ Raise Civic Complaints
* ✅ Track Complaint Status
* ✅ View Complaint History
* ✅ Edit Profile

### 👨‍💼 Administrator

* ✅ Secure Admin Login
* ✅ View All Complaints
* ✅ Search Complaints
* ✅ Update Complaint Status
* ✅ Add Resolution Remarks
* ✅ Delete Complaints
* ✅ View Registered Citizens

---

# 🏛️ System Modules

## Citizen Module

* Registration
* Login
* Raise Complaint
* Complaint Tracking
* Complaint History
* Profile Management

---

## Admin Module

* Complaint Management
* User Management
* Status Updates
* Complaint Resolution
* Dashboard

---

# 📋 Complaint Categories

| Category               | Description                   |
| ---------------------- | ----------------------------- |
| 💧 Water Leakage       | Report water leakage issues   |
| 🚰 Water Supply        | Water supply interruptions    |
| 🚧 Drainage            | Drainage blockage complaints  |
| 🗑️ Garbage Collection | Waste collection issues       |
| 💡 Street Light        | Damaged or non-working lights |

---

# 🔄 Complaint Workflow

```text
Citizen
    │
    ▼
Register/Login
    │
    ▼
Raise Complaint
    │
    ▼
Complaint Stored
    │
    ▼
Admin Reviews
    │
    ▼
Status Updated
    │
    ▼
Citizen Tracks Progress
```

Complaint Status

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

### Development

* H2 Database

### Production

* PostgreSQL

## Deployment

* Docker
* Render

---

# 🏗️ Architecture

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

### Layer Responsibilities

**Controller**

* Handles HTTP Requests
* Returns Views

**Service**

* Business Logic
* Validation

**Repository**

* Database Operations using Spring Data JPA

**Entity**

* Database Table Mapping

---

# 📁 Project Structure

```text
src
├── main
│
├── java
│   └── com.civic.smarttracking
│       ├── controller
│       ├── service
│       ├── repository
│       ├── entity
│       └── CivicSmartTrackingApplication.java
│
└── resources
    ├── static
    │   ├── css
    │   ├── js
    │   └── images
    │
    ├── templates
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
Database
        │
        ▼
Admin Reviews Complaint
        │
        ▼
Update Status
        │
        ▼
Citizen Tracks Complaint
```

---

# 💻 Installation

## Requirements

* Java 17+
* Maven

Clone Repository

```bash
git clone https://github.com/Yaswanth-Vissamsetty/civic-smart-tracking.git
```

Navigate

```bash
cd civic-smart-tracking
```

Run

```bash
mvn spring-boot:run
```

Open

```text
http://localhost:8080
```

---

# 🔑 Demo Credentials

## 👨‍💼 Administrator

Email

```text
admin@civic.gov.in
```

Password

```text
admin123
```

---

## 👤 Citizen

Email

```text
rahul@gmail.com
```

Password

```text
citizen123
```

---

# 🌍 Deployment

* ✅ Render Web Service
* ✅ Docker Container
* ✅ PostgreSQL Database

---

# 📸 Screenshots

## 🏠 Home Page

<img width="1920" height="3595" alt="fullpage_snapshot_localhost_2026-08-06-21-33-51" src="https://github.com/user-attachments/assets/e1ba1a2c-3100-4a49-939a-8ef19987c363" />


## 👤 Citizen Dashboard

<img width="1897" height="813" alt="user" src="https://github.com/user-attachments/assets/8ddafa28-c2a7-46e4-a1b8-faa6df6ec881" />


## 📋 Complaint Registration



## 👨‍💼 Admin Dashboard

<img width="1920" height="879" alt="fullpage_snapshot_localhost_2026-08-06-21-36-13" src="https://github.com/user-attachments/assets/c93e68f0-9b40-45e4-8343-7df44854a64c" />


---

# 📈 Future Enhancements

* 📧 Email Notifications
* 📍 Google Maps Integration
* 📸 Complaint Image Upload
* 🔔 Real-time Notifications
* 📊 Complaint Analytics Dashboard
* 🔍 Advanced Search & Filters
* 🔐 Spring Security Authentication
* 📱 Fully Responsive Mobile UI

---

# 💡 Learning Outcomes

Through this project, I gained hands-on experience with:

* Java Programming
* Spring Boot MVC Architecture
* Spring Data JPA
* CRUD Operations
* Thymeleaf Templates
* Database Integration
* RESTful Design Principles
* Git & GitHub
* Docker Deployment
* PostgreSQL
* Render Cloud Deployment

---

# 👨‍💻 Developed By

**Yaswanth Vissamsetty**

🎓 B.Tech – Computer Science & Engineering

💻 Java Full Stack Developer

📂 GitHub: https://github.com/Yaswanth-Vissamsetty

⭐ If you found this project helpful, please consider giving it a **Star** on GitHub!
