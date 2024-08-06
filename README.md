# E-Commerce Application

## Table of Contents

- [E-Commerce Application Description](#description)
- [Architecture](#architecture)
- [Technologies](#technologies)

### Description

This is an e-commerce application that allows users to browse and purchase products.
It is built as a solution for commerce businesses that want to have an online presence.
This system is designed with domain-driven design principles in mind,
and it is built with a microservices' architecture.
The main domains are:

- Customer
- Product
- Order
- Payment
- Notification

### Technologies

- Docker and Docker Compose for containerization
- Each domain uses Spring Framework
- MongoDB for data storage
- PostgresSQL for data storage
- Apache Kafka for messaging
- Angular for the frontend
- Java 21

### Architecture

The architecture of the system is based on microservices.
Each domain is a microservice that is responsible for its own data and business logic.