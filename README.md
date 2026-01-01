# Banking Simulator Backend

This repository contains the backend code for a **Banking Simulator** project built using **Java** and **Spring Boot**.  
It simulates core banking operations such as account management, transactions, and alerts.

## Features
- **Account Management:** Create, update, and delete bank accounts.
- **Transaction Handling:** Deposit, withdraw, and transfer funds between accounts.
- **Alerts and Notifications:** Notify users about transactions or account changes.

## Technologies Used
- **Java** – Core programming language
- **Spring Boot** – Framework for building REST APIs
- **MySQL** – Relational database for storing account and transaction data
- **Maven** – Build and dependency management

## Prerequisites
- Java 11 or higher
- MySQL installed and running
- Maven installed
- IDE like Eclipse or IntelliJ

## How to Run
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/your-repo-name.git

    Configure MySQL database:

        Create a database (e.g., banking_db)

        Update application.properties with your database username and password

    Import project in Eclipse or IntelliJ

    Build the project using Maven

mvn clean install

Run the application

    java -jar target/banking-simulator-backend.jar

    The server will start on http://localhost:8080

API Endpoints

    /accounts – Manage bank accounts

    /transactions – Perform transactions

    /alerts – Retrieve alerts

Contribution

Feel free to fork the repository and create pull requests. Issues and suggestions are welcome.
