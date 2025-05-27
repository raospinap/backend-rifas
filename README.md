# 🎯 Rifa Interactiva Asíncrona

Este es un proyecto académico desarrollado como parte del curso de **Profundización en Programación Orientada a Objetos II** en la Universidad del Tolima. El objetivo es demostrar el manejo avanzado de conceptos como:

- Arquitectura hexagonal
- Spring Boot + RabbitMQ + PostgreSQL
- React para el frontend
- Seguridad con JWT y OTP
- Mensajería asíncrona y programación reactiva
- Uso de APIs externas (OpenAI)

---

## 🚀 Tecnologías utilizadas

| Componente | Tecnología |
|-----------|------------|
| Backend   | Java 17 + Spring Boot |
| Frontend  | React + Vite |
| Base de datos | PostgreSQL |
| Mensajería | RabbitMQ (Colas: participación, eventos, OTP) |
| IA        | OpenAI API para generar premios automáticamente |
| Seguridad | JWT + Verificación OTP por correo |

---

## 🔐 Acceso al sistema

Este repositorio es **privado** y su acceso está limitado a miembros del curso con dominio `@ut.edu.co`. Para ingresar:

1. Solicita acceso al autor del repositorio.
2. Asegúrate de que tu cuenta de GitHub esté asociada a tu correo institucional.
3. Acepta la invitación para poder clonar y revisar el código.

---

## 📦 Estructura del proyecto

```bash
backend/
├── adapters/
│   ├── in/             # REST Controllers y Consumers de RabbitMQ
│   ├── out/            # Producers de eventos, persistencia, servicios externos
├── application/        # Lógica de aplicación
├── domain/             # Entidades y puertos
├── config/             # Configuraciones generales
├── RifaApplication.java

frontend/
├── src/
│   ├── pages/          # Páginas: Home, Admin, Pantalla, Login
│   ├── api/            # Llamadas HTTP
│   ├── utils/          # Token y roles
```

---

## 🧪 Funcionalidades principales

- Registro automático de usuarios
- Participación en rifas activas
- Sorteos automáticos por tiempo o manuales
- Ranking y premios en tiempo real
- Panel de administrador
- Dashboard público en pantalla grande
- Historial de premios por usuario

---

## 🧠 Autor

- **Rolando Ospina** — Estudiante de Ingeniería de Sistemas, 5to semestre — UT

---

## 📅 Última actualización

_2025-05-27_
