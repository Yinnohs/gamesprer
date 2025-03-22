### ###⚠️ THIS README IS A WIP ⚠️###

# Gamesprer 🎮

## Overview
Gamesprer is a sophisticated game price tracking and notification system that helps gamers find the best deals across multiple gaming platforms. The application monitors game prices and sends real-time notifications when price drops or new deals are found.

## 🎯 Purpose
Gamesprer solves the common problem of missing out on game deals by:
- Automatically monitoring prices across multiple gaming platforms
- Sending instant notifications when prices drop
- Tracking price history to help users make informed decisions
- Providing a centralized platform to manage game wishlists
- Helping gamers save money by finding the best deals

## 🚀 Features
- Real-time price monitoring across multiple gaming platforms
- WebSocket-based instant notifications
- User-specific price alerts
- Price history tracking
- Game wishlist management
- RESTful API for game management
- Secure user authentication

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.x**
- **Spring WebSocket** for real-time notifications
- **Spring Security** for authentication
- **MongoDB** for data persistence
- **Maven** for dependency management

### Frontend
- **Vue 3** with Composition API
- **TypeScript** for type safety
- **Pinia** for state management
- **TailwindCSS** for styling
- **SockJS** & **STOMP** for WebSocket communication
- **Vite** for build tooling

## 📋 Prerequisites
- Java 17 or higher
- Node.js 18 or higher
- MongoDB 6.0 or higher
- Maven 3.8 or higher
- npm or yarn

## 🚀 Getting Started

### Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/gamesprer.git
   cd gamesprer/backend
   ```

2. Configure MongoDB:
   - Ensure MongoDB is running locally or update `application.properties` with your MongoDB connection string

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The backend will start on `http://localhost:5052`

### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   # or
   yarn install
   ```

3. Start the development server:
   ```bash
   npm run dev
   # or
   yarn dev
   ```

The frontend will start on `http://localhost:5053`

## 🔧 Configuration

### Backend Configuration
The main configuration file is located at `backend/src/main/resources/application.properties`. Key configurations include:
- MongoDB connection settings
- WebSocket endpoint configuration
- JWT token settings
- Server port settings

### Frontend Configuration
Environment variables can be configured in `.env` files:
- `VITE_API_URL`: Backend API URL
- `VITE_WS_URL`: WebSocket server URL

## 🔐 API Documentation

### WebSocket Endpoints
- Connect: `ws://localhost:5052/ws`
- Subscribe to notifications: `/user/{userId}/topic/re-find`

### REST Endpoints
- `POST /api/v1/scraper-notifications/notify/user`: Send notification to user
- `GET /api/v1/scraper-notifications/test-connection/{userId}`: Test WebSocket connection

## 🧪 Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
npm run test
# or
yarn test
```

## 🤝 Contributing
We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors
- Your Name - Initial work

## 🙏 Acknowledgments
- Spring Boot team for the excellent framework
- Vue.js team for the amazing frontend framework
- MongoDB team for the powerful database

## Table of Contents
- [Project Title: Gamesprer](#project-title-gamesprer)
  - [Description](#description)
  - [Table of Contents](#table-of-contents)
  - [Installation Instructions](#installation-instructions)
  - [Usage](#usage)
  - [Features](#features)
  - [Contributing](#contributing)
  - [License](#license)
  - [Tests](#tests)

## Installation Instructions
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/gamesprer.git
    ```
2. Navigate to the project directory:
    ```sh
    cd gamesprer
    ```
3. Build the project using the provided Makefile:
    ```sh
    make
    ```
4. Run the application:
    ```sh
    ./gamesprer
    ```

## Usage
To start using Gamesprer, simply run the application and follow the on-screen instructions to create a user profile and start finding the best price for you games.

## Features
- Price fetching from diffrents websites

## Contributing
We welcome contributions from the community. To contribute:
1. Fork the repository.
2. Create a new branch:
    ```sh
    git checkout -b feature/your-feature-name
    ```
3. Commit your changes:
    ```sh
    git commit -m 'Add some feature'
    ```
4. Push to the branch:
    ```sh
    git push origin feature/your-feature-name
    ```
5. Open a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.