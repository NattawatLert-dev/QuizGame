# 🎮 Quiz Game with Leaderboard

A console-based quiz game built with Java. Test your knowledge across multiple categories, earn points based on difficulty, and compete on the leaderboard!

## ✨ Features

- 🗂️ **4 Categories** — Science, Technology, Math, General Knowledge
- 🎯 **3 Difficulty Levels** — Easy (10pts), Medium (20pts), Hard (30pts)
- 🏆 **Persistent Leaderboard** — Scores are saved to a file and ranked automatically
- 📊 **Grade System** — S / A / B / C / D / F based on accuracy
- ✅ **Input Validation** — Handles all invalid inputs gracefully
- 🎨 **Styled UI** — ASCII art banner and box-drawing UI in console

## 📸 Demo

```
  ██████╗ ██╗   ██╗██╗███████╗     ██████╗  █████╗ ███╗   ███╗███████╗
 ██╔═══██╗██║   ██║██║╚══███╔╝    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝
 ...

🎮  with Leaderboard  |  Java Edition  🎮

┌────────────────────────────┐
│         MAIN MENU          │
├────────────────────────────┤
│  1. Play Quiz               │
│  2. View Leaderboard        │
│  3. How to Play             │
│  4. Exit                    │
└────────────────────────────┘
```

## 🚀 How to Run

### Prerequisites
- Java JDK 17 or higher

### Compile & Run

```bash
# 1. Clone the repo
git clone https://github.com/YOUR_USERNAME/QuizGame.git
cd QuizGame

# 2. Compile all source files
javac -d out src/quizgame/*.java

# 3. Run the game
java -cp out quizgame.Main
```

> ⚠️ Run from the **project root folder** (`QuizGame/`) so the leaderboard file saves correctly.

## 📁 Project Structure

```
QuizGame/
├── src/
│   └── quizgame/
│       ├── Main.java          ← Entry point
│       ├── Game.java          ← Game loop & UI
│       ├── Question.java      ← Question model
│       ├── QuestionBank.java  ← All quiz questions
│       ├── PlayerScore.java   ← Score record & CSV serialization
│       └── Leaderboard.java   ← Save/load/display leaderboard
├── data/
│   └── leaderboard.txt        ← Auto-generated, stores scores
└── README.md
```

## 🎯 How to Play

1. Enter your **player name**
2. Choose a **category** (or All Categories)
3. Select **how many questions** you want
4. Answer each question by typing **A, B, C, or D**
5. See your **score, grade, and rank** on the leaderboard!

## 📊 Scoring

| Difficulty | Points |
|-----------|--------|
| Easy      | 10     |
| Medium    | 20     |
| Hard      | 30     |

## 🏅 Grade System

| Grade | Accuracy |
|-------|----------|
| S 🌟  | 90–100%  |
| A 🎉  | 80–89%   |
| B 👍  | 70–79%   |
| C 😊  | 60–69%   |
| D 📖  | 50–59%   |
| F 💪  | 0–49%    |

## 🛠️ Concepts Used

- Object-Oriented Programming (OOP)
- ArrayList & Collections
- File I/O (BufferedReader / PrintWriter)
- Exception Handling
- Comparable interface (for sorting)
- Switch expressions (Java 14+)

## 📝 License

MIT License — feel free to use and modify!
