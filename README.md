# E-Commerce Application

## Table of Contents

- [E-Commerce Application Description](#description)
- [Architecture](#architecture)
- [Technologies](#technologies)
- [Spring Cloud Config Server](#spring-cloud-config-server)
- [Spring Cloud Netflix Eureka Discovery](#spring-cloud-netflix-eureka-discovery)

### Description

This is an e-commerce application that allows users to browse and purchase products.
It is built as a solution for commerce businesses that want to have an online presence.
This system is designed with domain-driven design principles in mind,
and it is built with a microservices' architecture.

### Architecture

The architecture of the system is based on microservices.
Each domain is a microservice that is responsible for its own data and business logic.
The communication between microservices is done through REST APIs and Apache Kafka.

The system is composed of the following microservices:

- Customer Service: Manages customer data and provides customer-related operations.
- Product Service: Manages product data and provides product-related operations
- Order Service: Manages order data and provides order-related operations
- Payment Service: Manages payment data and provides payment-related operations
- Notification Service: Manages notification data and provides notification-related operations

### Technologies

- Docker and Docker Compose for containerization
- Each domain uses Spring Framework
- MongoDB for data storage
- PostgresSQL for data storage
- Apache Kafka for messaging
- Angular for the frontend
- Java 21
- Eureka for service discovery


### Spring Cloud Config Server

Spring Cloud Config Server is a centralized configuration management tool for microservices architectures. It provides a
way to manage external properties for applications across different environments.

How it works:

1. Configuration Storage: The Config Server stores configuration properties in a centralized location. This can be a Git
   repository, a file system, or other supported backends.
2. Configuration Access: Applications (clients) fetch their configuration properties from the Config Server at startup
   or dynamically.
3. Environment-Specific Configuration: The Config Server supports environment-specific configuration, allowing you to
   manage different configurations for different environments (development, staging, production).

### Spring Cloud Netflix Eureka Discovery

The system uses Spring Cloud Eureka for service discovery.
Spring Cloud Netflix Eureka is a robust service discovery implementation based on Netflix's open-source Eureka service
discovery platform.
It's a core component of the Spring Cloud ecosystem,
providing a centralized registry for microservices to discover and interact with each other.
A discovery service is a mechanism that automatically detects and locates devices and services within a computer
network.
In the context of microservices, a discovery service is used to locate and communicate with other services in the
system.

How a discovery service works:

1. Service Registration: Services register themselves with the discovery service, providing information like their
   hostname, port, and other relevant metadata.
2. Service Discovery: When a client needs to interact with a service, it queries the discovery service to find the
   location of that service.
3. Load Balancing: In many cases, discovery services also offer load balancing capabilities, distributing traffic across
   multiple instances of a service.

In essence, a discovery service acts as a directory for network services, making it easier for applications to find and
communicate with each other.