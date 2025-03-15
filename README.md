# Job Scheduler Application

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![Docker](https://img.shields.io/badge/Docker-20.10-blue)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-purple)

A robust **Job Scheduler Application** built with **Spring Boot 3**, integrating **Quartz Scheduler** for job management, **Dkron** for distributed job scheduling, and a modern **Thymeleaf + Bootstrap 5** frontend. This application allows users to schedule, manage, and monitor jobs seamlessly.

---

## Features

- **Job Scheduling**: Create, update, pause, resume, and delete jobs using Quartz Scheduler.
- **Distributed Scheduling**: Integrates with **Dkron** for distributed and fault-tolerant job scheduling.
- **User-Friendly Interface**: Modern UI built with **Thymeleaf** and **Bootstrap 5**.
- **Database Integration**: Uses **Spring Data JPA** and **Hibernate** to store job metadata in **MySQL**.
- **Docker Support**: Easily deploy the application using Docker.
- **RESTful API**: Exposes endpoints for job management.
- **Responsive Design**: Works seamlessly on desktop and mobile devices.

---

## Technologies Used

- **Backend**:
  - Spring Boot 3
  - Quartz Scheduler
  - Spring Data JPA
  - Hibernate
  - MySQL
  - Dkron (for distributed scheduling)
- **Frontend**:
  - Thymeleaf Templates
  - Bootstrap 5
  - HTML5/CSS3
- **DevOps**:
  - Docker
  - Maven (for dependency management)
- **Other Tools**:
  - RESTful APIs
  - JSON for data exchange
  - Lombok (for reducing boilerplate code)

---

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17** (or higher)
- **MySQL 8.0** (or higher)
- **Docker** (optional, for containerized deployment)
- **Maven** (for building the project)

---

## Installation and Setup

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/job-scheduler.git
cd job-scheduler
```
### 2. Configure MySQL
- **Create a MySQL database named job_scheduler**
- **Update the application.properties file with your MySQL credentials:**
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/jobscheduler
spring.datasource.username=username
spring.datasource.password=password
#jpa configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
#thymeleaf
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```
### 3. Access the Application
- **open your browser to navigate to**
```bash
http://localhost:8080/jobs
```

---

## Docker Deployment

### 1. Build the Docker image
```bash
docker pull dkron/dkron
```
### 2.Run the docker container
```bash
docker run --name dkron -d -p 8081:8080 -p 8946:8946/udp -p 6868:6868 dkron/dkron agent --server
```

---

## Project Structure
```
job-scheduler/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/capstone/Jobscheduler/
│   │   │       ├── controllers/          # Spring MVC Controllers
│   │   │       ├── services/            # Business logic and service layer
│   │   │       ├── repositories/        # Spring Data JPA repositories
│   │   │       ├── entities/              # Entity classes
│   │   │       ├── dto/                 # Data Transfer Objects
│   │   │       └── config/              # Configuration classes
│   │   ├── resources/
│   │   │   ├── static/                  # Static assets (CSS, JS, images)
│   │   │   ├── templates/               # Thymeleaf templates
│   │   │   └── application.properties   # Configuration file
│   ├── test/                            # Unit and integration tests
├── pom.xml                              # Maven dependencies
└── README.md                            # Project documentation
```

---

## Screenshots

### Schedule Quartz job
![image](https://github.com/user-attachments/assets/a3002813-941f-4bd0-95d7-5301287eee55)

### Schedule Dkron job
![image](https://github.com/user-attachments/assets/74606515-70bb-4dab-baad-f61328d0330b)

### Dkron Ui
- **To check the scheduled dkron job browse**
```bash
http://localhost:8081/ui/
```
