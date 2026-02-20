# 🏥 Hospital Management System (JDBC Project)

## 📌 Project Overview
The Hospital Management System is a console-based Java application developed using JDBC and MySQL.
It allows users to manage patients, doctors, and appointments efficiently through a simple menu-driven interface.

This project demonstrates practical implementation of:
- JDBC Connectivity
- PreparedStatement
- ResultSet Handling
- Database Relationships
- Basic Validation & Availability Checking
---
## 🚀 Features
- ➕ Add New Patient
- 📋 View All Patients
- 👨‍⚕️ View All Doctors
- 📅 Book Appointment
- ✅ Doctor Availability Check
- 🔍 Patient & Doctor Validation
---
## 🛠️ Technologies Used
- Java
- JDBC (Java Database Connectivity)
- MySQL
- IntelliJ IDEA
- Git & GitHub
---
## 🗄️ Database Structure
Database Name: `hospital`

### Tables:
1. **patients**
   - id (Primary Key, Auto Increment)
   - name
   - age
   - gender

2. **doctors**
   - id (Primary Key, Auto Increment)
   - name
   - specialization

3. **appointments**
   - id (Primary Key, Auto Increment)
   - patient_id (Foreign Key)
   - doctor_id (Foreign Key)
   - appointment_date
---
## ⚙️ How to Run the Project

1. Clone the repository:
   git clone https://github.com/your-username/Hospital-Management-System-JDBC.git
2. Open the project in IntelliJ IDEA or any Java IDE.
3. Create MySQL database:
   CREATE DATABASE hospital;
   USE hospital;
4. Create required tables (patients, doctors, appointments).
5. Update database credentials inside:
   private static final String username = "root";
   private static final String password = "your_password";
6. Add MySQL Connector JAR to project libraries.
7. Run `HospitalManagementSystem.java`.
---

## 🖥️ Sample Menu
HOSPITAL MANAGEMENT SYSTEM
1. Add Patient
2. View Patients
3. View Doctors
4. Book Appointment
5. Exit
---
## 💡 Key Concepts Implemented
- Constructor Injection
- PreparedStatement (Prevents SQL Injection)
- ResultSet Processing
- COUNT() query for availability checking
- Menu-driven CLI architecture

---
#💻 How the Program Works:

- **Load configuration**:Reads database credentials from config.properties to avoid hardcoding.
- **Connect to MySQL** :Establishes connection using JDBC driver.
- **Constructor injection** :Passes the database connection to Patient and Doctor classes.
- **Menu-driven flow** :Keeps the program running until the user exits.
- **Add/View Patients & Doctors** :Performs CRUD operations for records.
- **Book appointments** : Validates patient and doctor, checks doctor availability, and inserts appointment if available.
- **Check doctor availability** :Ensures the doctor is free on the selected date.
- **Key programming practices** :Uses PreparedStatement, ResultSet processing, COUNT() query, menu-driven CLI, and constructor injection.
- **Future improvements** : Add resource closing, input validation, GUI, MVC architecture, and better exception handling.

---

## 👨‍💻 Author
Baini Sainath 
B.Tech AIML Student  
Passionate about Java & Backend Development 🚀
