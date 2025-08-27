## Task Management System
This is a full-stack Task Management System with a Spring Boot backend and a React frontend. It supports user authentication, task CRUD operations, task filtering, and role-based access (ADMIN, USER). The backend uses an H2 in-memory database, and the frontend uses Tailwind CSS for styling.

## Prerequisites

- Java 17+ (OpenJDK)
- Maven (3.6+)
- Node.js 18+ and npm (8+)
- Git

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
- The backend seeds two users: admin@example.com:admin123 (ADMIN), user@example.com:user123 (USER).



# Setup and Running (*nix)

## Update package list
- `sudo apt update`

## Install Java 17
- `sudo apt install openjdk-17-jdk`
- `java -version`

## Install Maven
- `sudo apt install maven`
- `mvn -version`

## Install Node.js 18 and npm
- `curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -`
- `sudo apt install -y nodejs`
- `node -v`
- `npm -v`

## Install Git
- `sudo apt install git`
- `git --version`

## Configure Git
- `git config --global user.name "Your Name"`
- `git config --global user.email "your.email@example.com"`

## Cloning the Project

- `git clone https://github.com/chess254/smoothstack-task-management-system.git`

### Backend
1. Navigate to `backend/`
2. Run `mvn clean install`
3. Run `mvn spring-boot:run`
   - The backend runs on `http://localhost:8080`
   - H2 database console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, username: `sa`, password: check in console)

### Frontend
1. Navigate to `frontend/`
2. Run `npm install`
3. Run `npm start`
   - The frontend runs on `http://localhost:3000`


Opens http://localhost:3000 in your browser.
The frontend proxies API calls to http://localhost:8080 (configured in package.json).


## Test the app:

- Go to http://localhost:3000/login.
- Login with admin@example.com:admin123 or user@example.com:user123.
- Verify the dashboard shows tasks in TODO, IN_PROGRESS, DONE columns.
- Test task creation, editing, deletion, and filtering.

### Testing the Application

Two unit tests are provided (TaskServiceTest, UserServiceTest) in backend/src/test/java/com/example/taskmanagement/service/
- Run tests: `cd ~/smoothstack-task-management-system/backend`
- `mvn test`

## Troubleshooting

### CORS Issues:

- Ensure WebConfig.java allows http://localhost:3000.
- Verify package.json has "proxy": "http://localhost:8080".


### Dependency Issues:

Backend: `rm -rf ~/.m2/repository`
- `mvn clean install`


Frontend: `rm -rf node_modules package-lock.json`
- `npm install`





