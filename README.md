# mtg-guessing-game

## About

This project is a game where users guess **Magic: The Gathering** cards based on various hints. The objective is to identify the card as quickly as possible using the fewest hints possible. 

### Key Features
- The backend is implemented using Spring Boot, retrieving card data from the [Scryfall API](https://scryfall.com/docs/api). The data is processed into a series of hints for the user.
- The frontend is built with React and communicates with the backend to display hints and gather user input. The frontend also fetches data direclty from the scryfall api for card name suggestions.
- **The project is currently a work in progress, but you can view a demo [here](https://mtg-guessing-game.vercel.app).**

## Technologies Used

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![HTML](https://img.shields.io/badge/HTML-239120?style=for-the-badge&logo=html5&logoColor=white)
![CSS](https://img.shields.io/badge/CSS-254BDD?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)


## How to Run

### Backend (Spring Boot)
1. Clone the repository.
2. Navigate to the backend directory.
3. Build the project using **Gradle**:
   ```bash
   ./gradlew build
   ```
4. Run the Spring Boot application:
   ```bash
   ./gradlew bootRun
   ```

### Frontend (React)
1. Navigate to the frontend directory.
2. Install dependencies using **npm**:
   ```bash
   npm install
   ```
3. Start the development server with **Vite**:
   ```bash
   npm run dev
   ```
4. Open your browser and navigate to the displayed localhost URL.
