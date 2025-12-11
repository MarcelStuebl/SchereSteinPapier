<div align="center">

# 🎮 Rock Paper Scissors

### A Modern JavaFX Desktop Game

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-007396?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)

*A classic hand game with a twist – featuring the "Well" extension!*

---

[Features](#-features) • [Installation](#-installation) • [How to Play](#-how-to-play) • [Architecture](#️-architecture) • [Authors](#-authors)

</div>

---

## 📖 About

**Rock Paper Scissors** (*Schere Stein Papier*) is a desktop application that brings the classic hand game to your screen with a modern JavaFX interface. This project extends the traditional game rules by adding the **Well** (*Brunnen*) element.

This game was developed as part of the **ITP (Informationstechnische Projekte)** course at **[HTBLA Steyr](https://www.htl-steyr.ac.at/)**, Austria.

> 📄 **Looking for detailed game specifications?** Check out the [Spielbeschreibung Schere Stein Papier.pdf](Spielbeschreibung%20Schere%20Stein%20Papier.pdf)

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🎨 **Clean UI** | Modern, intuitive JavaFX interface with smooth animations |
| 🤖 **Bot Opponent** | Play against a randomized computer opponent |
| 🏆 **High Score System** | Track your winning streak with persistent high scores (Database supported) |
| 🎵 **Background Music** | Immersive audio experience with volume control |
| ⏳ **Progress Animation** | Suspenseful reveal with animated progress bar |
| 🖼️ **Visual Gestures** | Beautiful icons for all game gestures |
| 🔄 **Quick Reset** | Instantly restart for another round |

---

## 🎯 Game Rules

The game follows the classic **Rock Paper Scissors** rules with an additional **Well** option:

```
✂️  Scissors  →  beats  →  Paper, loses to Rock & Well
🪨  Rock      →  beats  →  Scissors, loses to Paper & Well  
📄  Paper     →  beats  →  Rock & Well, loses to Scissors
🕳️  Well      →  beats  →  Scissors & Rock, loses to Paper
```

---

## 🚀 Installation

### Prerequisites

- ☕ **Java 17** or higher ([Download](https://adoptium.net/))
- 📦 **Maven 3.9+** (or use included Maven Wrapper)
- 🗄️ **SQL Database** (required for High Scores)
- 💻 **Windows / macOS / Linux**

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/MarcelStuebl/SchereSteinPapier.git
   cd SchereSteinPapier
   ```

2. **Setup Database**
   To enable high score tracking, you need to create the database table. Run the following SQL command in your database:
   
   ```sql
   CREATE TABLE highscores (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(256) NOT NULL,
       highscore INT NOT NULL
   );
   ```

3. **Update the database settings!**
   Change the values in the .env:
   
   ```.env
   DB_HOST=HERE YOUR HOSTNAME
   DB_DATABASE=HERE YOUR DATABASENAME
   DB_USER=HERE YOUR USERNAME
   DB_PASSWORD=HERE YOUR PASSWORD
   ```
---

## 🎮 How to Play

1. **Launch** the application
2. **Choose** your gesture by clicking one of the four buttons:
   - ✂️ Scissors (*Schere*)
   - 🪨 Rock (*Stein*)
   - 📄 Paper (*Papier*)
   - 🕳️ Well (*Brunnen*)
3. **Watch** the progress bar as the bot makes its choice
4. **See** the result – Win, Lose, or Draw! 
5. **Track** your winning streak on the scoreboard
6. **Reset** and play again! 

---

## 🏗️ Architecture

### Project Structure

```
SchereSteinPapier/
├── 📁src/ main/
│   ├── 📁java/htl/steyr/scheresteinpapier/
│   │   ├── 📁database/
│   │   │   ├── 📄DatabaseConnection.java
│   │   │   └── 📄DatabaseUser.java
│   │   │
│   │   ├── 📁Model/
│   │   │   ├── 📄Gesture.java
│   │   │   ├── 📄Player.java
│   │   │   └── 📄User.java
│   │   │
│   │   ├── 📄GameController.java
│   │   ├── 📄Launcher.java
│   │   ├── 📄StartApplication.java
│   │   └── 📄StartController.java
│   │
│   └── 📁resources/htl/steyr/scheresteinpapier/
│       ├── 📁img/
│       ├── 📁sound/
│       ├── 📄game-view.fxml
│       ├── 📄start-view.fxml
│       ├── 📄style.css
│       └── 📄styleLogin.css
│
├── 📄.env
├── 📄.gitignore
├── 📄pom.xml
├── 📄README.md
└── 📄Spielebeschreibung Schere Stein Papier.pdf

```

---

## 👥 Authors

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/MarcelStuebl">
        <img src="https://github.com/MarcelStuebl.png" width="100px;" style="border-radius:50%;" alt="Marcel Stübl"/>
      </a>
      <br />
      <h3>Marcel STÜBL</h3>
      <img src="https://img.shields.io/badge/Backend-007396?style=for-the-badge&logo=java&logoColor=white" alt="Backend"/>
      <br /><br />
      <a href="mailto:mstuebl@htl-steyr.ac.at">📧 mstuebl@htl-steyr.ac.at</a>
    </td>
    <td align="center">
      <a href="https://github.com/1Mauritz2">
        <img src="https://github.com/1Mauritz2.png" width="100px;" style="border-radius:50%;" alt="Moritz Raschko"/>
      </a>
      <br />
      <h3>Moritz RASCHKO</h3>
      <img src="https://img.shields.io/badge/Frontend-FF6B6B?style=for-the-badge&logo=css3&logoColor=white" alt="Frontend"/>
      <br /><br />
      <a href="mailto:mraschk1@htl-steyr.ac.at">📧 mraschk1@htl-steyr.ac.at</a>
    </td>
  </tr>
</table>

---

## 🏫 About HTBLA Steyr

This project was created as part of the **ITP (Informationstechnische Projekte)** course at **[HTBLA Steyr](https://www.htl-steyr.ac.at/)**, a technical college in Upper Austria specializing in mechanical engineering, electronics, and IT.

---

