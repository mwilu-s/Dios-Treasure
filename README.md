# Dio's Treasure 🏴‍☠️

**High School Practical Assessment Task (PAT)**  
**Language:** Java | **Database:** Microsoft Access (JDBC) | **IDE:** NetBeans

---

## About the Project

Dio's Treasure is a 2D platformer math game developed as a high school Practical Assessment Task. Players take on the role of a pirate navigating through levels filled with enemies, cannons, and spikes — all while answering math questions to survive and score points.

The game is aimed at students in **grades 6–9** as an engaging and interactive way to practice fundamental math operations. It is designed to be accessible offline and free to use.

---

## Features

- **User Accounts** — Players can sign up and log in to their own personal accounts. Usernames and passwords are auto-generated and stored in a database. A screenshot of login credentials is saved to the user's computer upon sign-up.
- **Data Validation** — Account creation validates all user inputs and prevents duplicate personal information entries, ensuring each user is uniquely identified.
- **3 Game Levels** — Addition, Subtraction, and Multiplication, each as a separate platform level.
- **Platform Gameplay** — Navigate using the arrow keys and spacebar. Avoid enemies, cannons, and spikes while answering math questions.
- **Health Bar** — Players lose health from enemy attacks and incorrect answers.
- **Scoring System** — Scores are calculated based on correct answers. Top 5 high scores per level are tracked across all users.
- **Personal Profile** — Players can view their own top scores for each level.
- **Leaderboard** — Top 5 highest scores per level are displayed for all users.
- **Sound Settings** — Players can mute/unmute music and sound effects, and adjust volume via a slider.

---

## Object-Oriented Programming

The project applies core OOP principles throughout:

- **Classes and Objects** — Game entities (player, enemies, projectiles, platforms) are modelled as classes with attributes and methods
- **Encapsulation** — User data and game state are managed through well-defined class structures
- **Abstraction** — Game logic is separated into distinct classes for the game screen, UI panels, database handling, and user management
- **Inheritance** — Shared behaviour across game entities is implemented through class hierarchies

---

## Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Java |
| GUI | Java Swing |
| Database | Microsoft Access via JDBC |
| IDE | NetBeans |
| Game Engine | Custom Java 2D (KeyListener, Graphics) |

---

## How to Run

1. Clone the repository
2. Open the project in **NetBeans**
3. Ensure the **UCanAccess** JDBC driver is added to the project libraries for MS Access connectivity
4. Run the project — the main entry point will launch the application
5. Sign up for an account or log in to an existing one
6. Select a level and play!

> **Note:** The Microsoft Access database file (`.accdb`) must be present in the project directory for database functionality to work.

---

## Game Screens

| Screen | Description |
|--------|-------------|
| Main Page | Sign In / Sign Up / Exit |
| Sign Up | Enter and validate personal info to create account |
| New User Info | Displays generated username & password, screenshot option |
| Login | Enter credentials to access account |
| Main Menu | Play / Options / Quit |
| Level Menu | Choose Addition, Subtraction, or Multiplication |
| Game Screen | Platformer gameplay with math questions |
| Game Options | Volume controls, music/SFX toggle, profile access |
| Profile | View personal scores per level |
| Leaderboard | Top 5 scores per level across all users |

---

## Database Structure

The game uses **Microsoft Access** via JDBC to store:
- User account information (name, surname, grade, date of birth)
- Generated login credentials (username, password)
- Game scores per level per user

---

*Developed by Mwilu Siakachoma — High School Practical Assessment Task*
