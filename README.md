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
   - H2 database console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, username: `sa`, password: check in console)

### Frontend
1. Navigate to `frontend/`
2. Run `npm install`
3. Run `npx tailwindcss init` if tailwind.config.js is missing from the cloned repository.
4. Run `npm start`
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



Task Management System
This is a full-stack Task Management System with a Spring Boot backend and a React frontend. It supports user authentication, task CRUD operations, task filtering, and role-based access (ADMIN, USER). The backend uses an H2 in-memory database, and the frontend uses Tailwind CSS for styling.
Project Structure
task-management-system/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/taskmanagement/
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── entity/
│   │   │   │   ├── exception/
│   │   │   │   ├── repository/
│   │   │   │   ├── service/
│   │   │   │   └── util/
│   │   │   └── resources/
│   │   │       └── application.properties
│   ├── pom.xml
│   └── README.md
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   │   ├── AuthForm.js
│   │   │   ├── TaskBoard.js
│   │   │   ├── TaskCard.js
│   │   │   ├── TaskForm.js
│   │   │   └── TaskModal.js
│   │   ├── context/
│   │   │   └── AuthContext.js
│   │   ├── pages/
│   │   │   ├── Dashboard.js
│   │   │   ├── Login.js
│   │   │   └── Register.js
│   │   ├── services/
│   │   │   └── api.js
│   │   ├── App.js
│   │   ├── index.js
│   │   └── index.css
│   ├── package.json
│   ├── tailwind.config.js
│   └── README.md
└── README.md

Prerequisites

Ubuntu (tested on 20.04+)
Java 17+ (OpenJDK)
Maven (3.6+)
Node.js 18+ and npm (8+)
Git
Browser (e.g., Firefox, Chrome)
GitHub Account for cloning

Install Prerequisites on Ubuntu
# Update package list
sudo apt update

# Install Java 17
sudo apt install openjdk-17-jdk
java -version

# Install Maven
sudo apt install maven
mvn -version

# Install Node.js 18 and npm
curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
sudo apt install -y nodejs
node -v
npm -v

# Install Git
sudo apt install git
git --version

# Configure Git
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Optional: Set up SSH for GitHub
ssh-keygen -t ed25519 -C "your.email@example.com"
cat ~/.ssh/id_ed25519.pub
# Copy the key to GitHub: Settings > SSH and GPG keys > New SSH key
ssh -T git@github.com

Cloning the Project
You can clone the project as a single repository or separate repositories for backend and frontend.
Option 1: Single Repository

Clone the repository:git clone git@github.com:your-username/task-management-system.git
cd task-management-system



Option 2: Separate Repositories

Clone backend:
mkdir -p ~/task-management-system
cd ~/task-management-system
git clone git@github.com:your-username/task-management-backend.git backend


Clone frontend:
git clone git@github.com:your-username/task-management-frontend.git frontend



Running the Backend (Spring Boot)

Navigate to the backend folder:
cd ~/task-management-system/backend


Install dependencies:
mvn clean install


Run the backend:
mvn spring-boot:run


The backend runs on http://localhost:8080.
The H2 database console is at http://localhost:8080/h2-console:
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (empty)




Verify seed data:

Access H2 console and run:SELECT * FROM users;


Expected: Two users (admin@example.com:admin123, user@example.com:user123).


Test API endpoints (e.g., with curl):
# Register a new user
curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d '{"username":"test","email":"test@example.com","password":"test123","role":"USER"}'

# Login (returns JWT token)
curl -X POST http://localhost:8080/api/auth/login -H "Content-Type: application/json" -d '{"email":"admin@example.com","password":"admin123"}'


Stop the backend: Ctrl + C.


Running the Frontend (React)

Navigate to the frontend folder:
cd ~/task-management-system/frontend


Install dependencies:
npm install


Verify Tailwind CSS setup:

Ensure tailwind.config.js exists:/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx}"],
  theme: { extend: {} },
  plugins: [],
}


If missing, generate it:npx tailwindcss init


Ensure src/index.css has:@tailwind base;
@tailwind components;
@tailwind utilities;




Start the frontend:
npm start


Opens http://localhost:3000 in your browser.
The frontend proxies API calls to http://localhost:8080 (configured in package.json).


Test the app:

Go to http://localhost:3000/login.
Login with admin@example.com:admin123 or user@example.com:user123.
Verify the dashboard shows tasks in TODO, IN_PROGRESS, DONE columns.
Test task creation, editing, deletion, and filtering.


Stop the frontend: Ctrl + C.


Testing the Application
Backend Tests

Two unit tests are provided (TaskServiceTest, UserServiceTest) in backend/src/test/java/com/example/taskmanagement/service/.
Run tests:cd ~/task-management-system/backend
mvn test


Expected: Both tests pass, verifying task status transitions and user registration.

Frontend Tests

No frontend tests are included (bonus feature not implemented).
To add tests, use react-scripts test:cd ~/task-management-system/frontend
npm test


Create test files (e.g., src/components/AuthForm.test.js) using Jest and React Testing Library.

Manual Testing

Authentication:

Register a new user at http://localhost:3000/register.
Login at http://localhost:3000/login.
Verify redirection to the dashboard.


Task Management:

Create a task with title, description, priority, and assignee.
Edit a task’s status (TODO → IN_PROGRESS → DONE).
Filter tasks by status and assignee.
Delete a task.
Verify ADMIN can access /api/users, USER cannot.


H2 Console:

Access http://localhost:8080/h2-console to inspect users and tasks tables.
Run:SELECT * FROM tasks;





Troubleshooting

H2 Console Access:

If sa with empty password fails:
Verify application.properties has spring.h2.console.enabled=true and spring.datasource.url=jdbc:h2:mem:testdb.
Check SecurityConfig.java allows /h2-console/**:.requestMatchers("/h2-console/**").permitAll()


Try file-based DB:spring.datasource.url=jdbc:h2:file:./data/testdb

mkdir -p backend/data






Credentials Not Working:

Check DataInitializer.java for seed users.
Register a new user via API:curl -X POST http://localhost:8080/api/auth/register -H "Content-Type: application/json" -d '{"username":"newuser","email":"newuser@example.com","password":"newpass","role":"USER"}'




Frontend Styles Missing:

Rebuild Tailwind:npx tailwindcss -i ./src/index.css -o ./src/output.css


Verify package.json has tailwindcss, autoprefixer, postcss.


CORS Issues:

Ensure WebConfig.java allows http://localhost:3000.
Verify package.json has "proxy": "http://localhost:8080".


Dependency Issues:

Backend:rm -rf ~/.m2/repository
mvn clean install


Frontend:rm -rf node_modules package-lock.json
npm install

Notes

The backend seeds two users: admin@example.com:admin123 (ADMIN), user@example.com:user123 (USER).
The frontend uses React Context API for state management and Tailwind CSS for styling.


