# Job Scheduler Application

A Spring Boot-based application for scheduling, managing, and monitoring jobs using Quartz Scheduler. Provides a web interface for CRUD operations and real-time job control.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen) ![Quartz](https://img.shields.io/badge/Quartz-2.3.2-blue) ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.2-green)

## Features

- **Job Management**
  - Schedule new jobs with cron expressions
  - Update existing job schedules
  - Delete jobs from the scheduler
  - Pause/Resume jobs in real-time
- **Web Interface**
  - List all scheduled jobs
  - Responsive UI with Bootstrap
  - Status messages for operations
- **Persistent Storage**
  - JPA/Hibernate integration
  - Transactional database operations

## Technology Stack

**Backend**
- Spring Boot 3
- Quartz Scheduler
- Spring Data JPA
- Hibernate
- Maven
- MySql

**Frontend**
- Thymeleaf Templates
- Bootstrap 5
- HTML5/CSS3

## Installation

1. **Clone Repository**
   ```bash
   git clone https://github.com/your-username/job-scheduler.git
