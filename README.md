# ⚡ Resource Management System

A Java Spring Boot application to manage **metering points** and **connection points** across multiple countries. Each resource has a location and multiple characteristics. The system stores this data in a PostgreSQL database, publishes change notifications to Kafka, and is fully containerized using Docker Compose.

---

## 📋 Features

- Manage metering and connection point resources
- Store resource locations and characteristics
- Publish change notifications to Kafka topics
- PostgreSQL-backed persistence with database migration
- Containerized architecture (App + DB + Kafka + UIs)
- Kafka UI and PgAdmin for monitoring
- Fully tested with high code quality and coverage

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **Flyway** for database migrations
- **Kafka** for event publishing
- **Gradle** for build and dependency management
- **Testcontainers** for integration testing
- **Docker Compose** for orchestration
- **Kafka UI** and **PgAdmin** for administration

---

## 🐳 Run with Docker Compose

### Start All Services

```bash
docker-compose up --build
