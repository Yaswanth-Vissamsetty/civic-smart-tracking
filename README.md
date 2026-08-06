# Civic Smart Tracking System

A full-stack web application developed using **Java**, **Spring Boot**, **Spring Data JPA**, **MySQL**, **Thymeleaf**, **HTML5**, **CSS3**, **JavaScript**, and **Bootstrap 5**. Designed specifically as a college final-year B.Tech academic project.

---

## 📌 Project Description

The **Civic Smart Tracking System** is a digital municipal grievance redressal application that enables citizens to report public infrastructure and sanitation issues (such as water leakages, water supply outages, drainage blockages, garbage collection delays, and broken street lights). 

Citizens can track the live progress of their complaints using a unique auto-generated Complaint ID, while municipal administrators can manage, search, update resolution status (*Submitted*, *In Progress*, *Resolved*), provide official remarks, and view registered citizens.

---

## 🚀 Tech Stack

* **Backend**: Java 17+, Spring Boot 3.2.5, Spring Data JPA, Spring MVC
* **Database**: MySQL / H2 Embedded Database (Zero-Config Development Mode)
* **Frontend**: HTML5, CSS3 (Government Blue & White Theme), JavaScript (ES6), Bootstrap 5
* **Template Engine**: Thymeleaf
* **Build Tool**: Maven

---

## ✨ Features

### 👤 Citizen Module
* **Account Registration & Authentication**: Secure sign-up and login for citizens.
* **Citizen Dashboard**: Summary cards displaying total, pending, and resolved complaints.
* **Lodge Complaint**: Easy-to-use form with auto-filled contact information, category selection, location details, incident date, and automatic initial status set to `Submitted`.
* **View My Complaints**: Comprehensive list of submitted complaints with category and status badges.
* **Track Complaint Status**: Visual resolution progress bar timeline (*Submitted* → *In Progress* → *Resolved*) with admin remarks.
* **Edit Profile**: Update full name, mobile number, and password.

### 🛠️ Admin Module
* **Admin Login**: Secure login for municipal administrators (`admin@civic.gov.in` / `admin123`).
* **Admin Dashboard**: System-wide analytics showing total complaints, new submissions, in-progress tasks, resolved complaints, and registered citizens count.
* **Manage Complaints**: View all civic complaints, search by Complaint ID or Citizen Name, filter, update complaint status with resolution remarks, and delete complaints.
* **Registered Citizens Directory**: View list of registered citizens with email, phone, and registration timestamps.

---

## 🗄️ Database Tables & Schema

### `users` Table
| Field | Type | Description |
|---|---|---|
| `id` | BIGINT (PK, Auto-Increment) | User Primary Key |
| `name` | VARCHAR(100) | Citizen Full Name |
| `email` | VARCHAR(100) (Unique) | Account Email |
| `password` | VARCHAR(100) | Account Password |
| `mobile` | VARCHAR(15) | Citizen Mobile Number |
| `role` | VARCHAR(20) | User Role (`CITIZEN` / `ADMIN`) |
| `created_at` | TIMESTAMP | Registration Timestamp |

### `complaints` Table
| Field | Type | Description |
|---|---|---|
| `id` | BIGINT (PK, Auto-Increment) | Complaint Primary Key |
| `complaint_code` | VARCHAR(20) (Unique) | Auto-Generated Code (e.g. `CIVIC-1001`) |
| `citizen_name` | VARCHAR(100) | Name of Citizen |
| `email` | VARCHAR(100) | Citizen Email |
| `mobile` | VARCHAR(15) | Citizen Mobile |
| `category` | VARCHAR(50) | Complaint Category |
| `description` | TEXT | Detailed Description |
| `location` | VARCHAR(200) | Address / Location |
| `date` | VARCHAR(20) | Date of Incident |
| `status` | VARCHAR(30) | Status (`Submitted`, `In Progress`, `Resolved`) |
| `remarks` | VARCHAR(255) | Administrative Action Remarks |
| `user_id` | BIGINT (FK) | Reference to `users.id` |

---

## 💻 Sample MySQL Database Script

```sql
CREATE DATABASE IF NOT EXISTS civic_db;
USE civic_db;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'CITIZEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Complaints Table
CREATE TABLE IF NOT EXISTS complaints (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    complaint_code VARCHAR(20) NOT NULL UNIQUE,
    citizen_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile VARCHAR(15) NOT NULL,
    category VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    location VARCHAR(200) NOT NULL,
    date VARCHAR(20) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'Submitted',
    remarks VARCHAR(255) DEFAULT 'Complaint received and queued for investigation.',
    user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Seed Default Admin
INSERT INTO users (id, name, email, password, mobile, role) 
VALUES (1, 'Municipal Admin', 'admin@civic.gov.in', 'admin123', '9876543210', 'ADMIN');

-- Seed Sample Citizen
INSERT INTO users (id, name, email, password, mobile, role) 
VALUES (2, 'Rahul Sharma', 'rahul@gmail.com', 'citizen123', '9811223344', 'CITIZEN');

-- Seed Sample Complaints
INSERT INTO complaints (id, complaint_code, citizen_name, email, mobile, category, description, location, date, status, remarks, user_id)
VALUES 
(1, 'CIVIC-1001', 'Rahul Sharma', 'rahul@gmail.com', '9811223344', 'Water Leakage', 'Main pipeline leaking near Sector 4 community center entrance.', 'Sector 4, Main Road', '2026-08-01', 'In Progress', 'Maintenance team dispatched to inspect pipeline.', 2),
(2, 'CIVIC-1002', 'Rahul Sharma', 'rahul@gmail.com', '9811223344', 'Street Light Issue', 'Three consecutive street lights are out near Block B residential park.', 'Block B, Green Park', '2026-08-03', 'Submitted', 'Complaint registered and assigned to Electrical Department.', 2);
```

---

## ⚡ How to Run The Project

### Option 1: Run with H2 Embedded Database (Instant Zero Setup)

1. Open your terminal in the project directory:
   ```bash
   cd civic-smart-tracking
   ```
2. Run using Maven:
   ```bash
   mvn spring-boot:run
   ```
3. Open your browser and visit:
   - **Application**: [http://localhost:8080](http://localhost:8080)
   - **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:civicdb`)

### Option 2: Run with MySQL Database

1. Create database in MySQL:
   ```sql
   CREATE DATABASE civic_db;
   ```
2. Update `src/main/resources/application.properties`:
   Uncomment the MySQL lines and configure your credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/civic_db?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=your_mysql_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## 🔑 Default Credentials

| User Type | Email | Password |
|---|---|---|
| **Municipal Admin** | `admin@civic.gov.in` | `admin123` |
| **Demo Citizen** | `rahul@gmail.com` | `citizen123` |

---

## 📁 Project Folder Structure

```
civic-smart-tracking/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/civic/smarttracking/
    │   │   ├── CivicSmartTrackingApplication.java
    │   │   ├── entity/
    │   │   │   ├── User.java
    │   │   │   └── Complaint.java
    │   │   ├── repository/
    │   │   │   ├── UserRepository.java
    │   │   │   └── ComplaintRepository.java
    │   │   ├── service/
    │   │   │   ├── UserService.java
    │   │   │   └── ComplaintService.java
    │   │   └── controller/
    │   │       ├── HomeController.java
    │   │       ├── CitizenController.java
    │   │       └── AdminController.java
    │   └── resources/
    │       ├── application.properties
    │       ├── schema.sql
    │       ├── data.sql
    │       ├── static/
    │       │   ├── css/style.css
    │       │   └── js/main.js
    │       └── templates/
    │           ├── fragments/
    │           │   ├── navbar.html
    │           │   └── footer.html
    │           ├── index.html
    │           ├── login.html
    │           ├── register.html
    │           ├── citizen-dashboard.html
    │           ├── register-complaint.html
    │           ├── my-complaints.html
    │           ├── track-complaint.html
    │           ├── edit-profile.html
    │           ├── admin-login.html
    │           ├── admin-dashboard.html
    │           ├── admin-complaints.html
    │           └── admin-users.html
```
