# Task Management System

This is a full-stack Task Management System built with Spring Boot (backend) and React (frontend). It includes JWT-based authentication, task CRUD operations, role-based access, and a Kanban-style task board.

## Prerequisites
- Java 17+
- Node.js 18+
- Maven (for backend build)

## Setup and Running

### Backend
1. Navigate to `backend/`
2. Run `mvn clean install`
3. Run `mvn spring-boot:run`
   - The backend runs on `http://localhost:8080`
   - H2 database console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, username: `sa`, password: empty)

### Frontend
1. Navigate to `frontend/`
2. Run `npm install`
3. Run `npm start`
   - The frontend runs on `http://localhost:3000`

## Features
- User registration and login with JWT
- Role-based access (USER, ADMIN)
- Task CRUD with status transitions (TODO → IN_PROGRESS → DONE)
- Task filtering by status and assignee
- Dashboard with column-based task view
- Task creation/editing modal
- Seed data: 2 users (admin@example.com:admin123, user@example.com:user123) and 6 sample tasks

## Notes
- Uses H2 in-memory DB.
- Frontend uses Context API for state management, Axios for API calls, React Router for routing.
- Styling with Tailwind CSS (configured in package.json).
- Bonus: Optimistic updates on task status changes.