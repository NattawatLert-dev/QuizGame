package quizgame;

public class Question {
    private String question;
    private String[] choices;
    private int correctIndex; // 0-3
    private String category;
    private int difficulty; // 1=Easy, 2=Medium, 3=Hard

    public Question(String question, String[] choices, int correctIndex, String category, int difficulty) {
        this.question = question;
        this.choices = choices;
        this.correctIndex = correctIndex;
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getQuestion()       { return question; }
    public String[] getChoices()      { return choices; }
    public int getCorrectIndex()      { return correctIndex; }
    public String getCategory()       { return category; }
    public int getDifficulty()        { return difficulty; }

    public String getDifficultyLabel() {
        return switch (difficulty) {
            case 1 -> "Easy";
            case 2 -> "Medium";
            case 3 -> "Hard";
            default -> "Unknown";
        };
    }

    public int getPoints() {
        return difficulty * 10;
    }

    public boolean isCorrect(int answerIndex) {
        return answerIndex == correctIndex;
    }
}
