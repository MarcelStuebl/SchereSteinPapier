<div align="center">

# 🎮 Rock Paper Scissors Well

### A Modern JavaFX Desktop Game

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-007396?style=for-the-badge&logo=java&logoColor=white)](https://openjfx.io/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-Educational-blue?style=for-the-badge)](LICENSE)

*A classic hand game with a twist – featuring the "Well" extension! *

---

[Features](#-features) • [Installation](#-installation) • [How to Play](#-how-to-play) • [Architecture](#️-architecture) • [Authors](#-authors)

</div>

---

## 📖 About

**Rock Paper Scissors Well** (*Schere Stein Papier Brunnen*) is a desktop application that brings the classic hand game to your screen with a modern JavaFX interface. This project extends the traditional game with the **"Well"** (Brunnen) option, adding an extra layer of strategy! 

This game was developed as part of the **ITP (Informationstechnische Projekte)** course at **HTBLA Steyr**, Austria. 

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🎨 **Clean UI** | Modern, intuitive JavaFX interface with smooth animations |
| 🤖 **Bot Opponent** | Play against a randomized computer opponent |
| 🏆 **High Score System** | Track your winning streak with persistent high scores |
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

<details>
<summary><b>📊 Full Win Matrix</b></summary>

| Player ↓ / Bot → | Scissors | Rock | Paper | Well |
|------------------|----------|------|-------|------|
| **Scissors** | Draw | ❌ Lose | ✅ Win | ❌ Lose |
| **Rock** | ✅ Win | Draw | ❌ Lose | ❌ Lose |
| **Paper** | ❌ Lose | ✅ Win | Draw | ✅ Win |
| **Well** | ✅ Win | ✅ Win | ❌ Lose | Draw |

</details>

---

## 🚀 Installation

### Prerequisites

- ☕ **Java 17** or higher
- 📦 **Maven 3.9+** (or use included Maven Wrapper)
- 💻 **Windows/macOS/Linux**

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/MarcelStuebl/SchereSteinPapier.git
   cd SchereSteinPapier
   ```

2. **Run the game**
   
   **Windows:**
   ```cmd
   .\mvnw. cmd javafx:run
   ```
   
   **macOS/Linux:**
   ```bash
   ./mvnw javafx:run
   ```

3. **Build executable JAR** *(optional)*
   ```bash
   ./mvnw clean package
   ```

### IDE Setup

<details>
<summary><b>IntelliJ IDEA</b></summary>

1. Open the project folder in IntelliJ IDEA
2.  Wait for Maven to import dependencies
3. Navigate to `src/main/java/htl/steyr/scheresteinpapier/Launcher.java`
4. Right-click and select **Run 'Launcher. main()'**

</details>

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

### Controls

| Button | Action |
|--------|--------|
| `Scissors` / `Rock` / `Paper` / `Well` | Select your gesture |
| `Reset` | Start a new round |
| `Volume Slider` | Adjust background music |

---

## 🏗️ Architecture

### Project Structure

```
SchereSteinPapier/
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/htl/steyr/scheresteinpapier/
│   │   │   ├── 📄 Launcher.java          # Application entry point
│   │   │   ├── 📄 GameApplication.java   # JavaFX Application class
│   │   │   ├── 📄 GameController.java    # Main game logic & UI control
│   │   │   ├── 📄 Player.java            # Player model with gesture
│   │   │   └── 📁 Model/
│   │   │       └── 📄 Gesture.java       # Gesture data class
│   │   └── 📁 resources/htl/steyr/scheresteinpapier/
│   │       ├── 📄 game-view.fxml         # UI layout definition
│   │       ├── 📄 style.css              # Custom styling
│   │       └── 📁 img/                   # Gesture icons & assets
├── 📄 pom.xml                            # Maven configuration
├── 📄 mvnw / mvnw.cmd                    # Maven Wrapper scripts
└── 📄 README.md                          # You are here! 
```

### Component Overview

```
┌─────────────────────────────────────────────────────────────┐
│                      GameApplication                         │
│                    (JavaFX Entry Point)                      │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────────┐
│                     GameController                           │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐  │
│  │   UI Logic  │  │ Game Logic  │  │   Animation/Audio   │  │
│  │  - Buttons  │  │  - Winner   │  │  - Progress Bar     │  │
│  │  - Display  │  │  - Score    │  │  - Background Music │  │
│  └─────────────┘  └─────────────┘  └─────────────────────┘  │
└─────────────────────────┬───────────────────────────────────┘
                          │
          ┌───────────────┴───────────────┐
          ▼                               ▼
┌─────────────────┐             ┌─────────────────┐
│     Player      │             │     Player      │
│   (Human/Bot)   │             │      (Bot)      │
│  ┌───────────┐  │             │  ┌───────────┐  │
│  │  Gesture  │  │             │  │  Gesture  │  │
│  └───────────┘  │             │  └───────────┘  │
└─────────────────┘             └─────────────────┘
```

### Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java 17+** | Core programming language |
| **JavaFX 21** | GUI framework |
| **Maven** | Build automation & dependency management |
| **FXML** | Declarative UI layout |
| **CSS** | UI styling |

---

## 🛠️ Development

### Customization

| What | Where |
|------|-------|
| UI Layout | `src/main/resources/htl/steyr/scheresteinpapier/game-view. fxml` |
| Styling | `src/main/resources/htl/steyr/scheresteinpapier/style. css` |
| Game Logic | `src/main/java/htl/steyr/scheresteinpapier/GameController.java` |
| Assets | `src/main/resources/htl/steyr/scheresteinpapier/img/` |

### Building

```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Package as JAR
./mvnw clean package

# Run the application
./mvnw javafx:run
```

---

## 👥 Authors

<table>
  <tr>
    <td align="center">
      <b>Marcel STÜBL</b><br>
      <a href="mailto:mstuebl@htl-steyr. ac.at">📧 mstuebl@htl-steyr.ac.at</a>
    </td>
    <td align="center">
      <b>Moritz RASCHKO</b><br>
      <a href="mailto:mraschk1@htl-steyr.ac.at">📧 mraschk1@htl-steyr.ac.at</a>
    </td>
  </tr>
</table>

---

## 🏫 About HTBLA Steyr

This project was created as part of the **ITP (Informationstechnische Projekte)** course at **[HTBLA Steyr](https://www.htl-steyr.ac.at/)**, a technical college in Upper Austria specializing in mechanical engineering, electrical engineering, and computer science.

---

## 📝 License

This project was created for educational purposes at HTBLA Steyr.   
For licensing inquiries, please contact the authors.

---

<div align="center">

**Made with ☕ Java and 💙 at HTBLA Steyr**

⭐ Star this repo if you enjoyed the game!  ⭐

</div>
