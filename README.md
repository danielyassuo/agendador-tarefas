# API Agendador de Tarefas

API REST responsável pelo agendamento e gerenciamento de tarefas dos usuários, desenvolvida em **Java com Spring Boot**.

## 🚀 Tecnologias

- Java + Spring Boot
- Spring Security + JWT (autenticação/autorização)
- Spring Data MongoDB
- MapStruct (conversão entre DTOs e entidades)
- OpenFeign (integração com outros microsserviços, como o sistema de notificações)
- Docker

## 🔗 Integração

Este serviço faz parte de um ecossistema de microsserviços que inclui:

- [API de Gestão de Usuários](https://github.com/danielyassuo/usuarios)
- [Sistema de Notificação](https://github.com/danielyassuo/sistema-notificacao)
- [BFF do Agendador de Tarefas](https://github.com/danielyassuo/bff-agendador-tarefas) (consome esta API)

## 🔧 Como executar

```bash
git clone https://github.com/danielyassuo/agendador-tarefas.git
cd agendador-tarefas
docker build -t agendador-tarefas .
./gradlew bootRun
```
------------------------------------------------------------------------------------------------------------

# Task Scheduler API

REST API responsible for scheduling and managing users' tasks, built with **Java and Spring Boot**.

## 🚀 Tech Stack

- Java + Spring Boot
- Spring Security + JWT (authentication/authorization)
- Spring Data MongoDB
- MapStruct (DTO ↔ entity mapping)
- OpenFeign (integration with other microservices, such as the notification system)
- Docker

## 🔗 Integration

This service is part of a microservices ecosystem that includes:

- [User Management API](https://github.com/danielyassuo/usuarios)
- [Notification System](https://github.com/danielyassuo/sistema-notificacao)
- [Task Scheduler BFF](https://github.com/danielyassuo/bff-agendador-tarefas) (consumes this API)

## 🔧 How to run

```bash
git clone https://github.com/danielyassuo/agendador-tarefas.git
cd agendador-tarefas
docker build -t agendador-tarefas .
./gradlew bootRun
```

## 👤 Author

**Daniel Yassuo da Rocha Rodrigues**
Backend Developer | Java & Spring Boot
[LinkedIn](https://linkedin.com/in/danielYassuo) · [GitHub](https://github.com/danielyassuo)
