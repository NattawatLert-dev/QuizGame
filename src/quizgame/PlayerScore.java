package quizgame;

public class PlayerScore implements Comparable<PlayerScore> {
    private String name;
    private int score;
    private int correctAnswers;
    private int totalQuestions;
    private String category;
    private String date;

    public PlayerScore(String name, int score, int correctAnswers, int totalQuestions, String category, String date) {
        this.name = name;
        this.score = score;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
        this.category = category;
        this.date = date;
    }

    public String getName()          { return name; }
    public int getScore()            { return score; }
    public int getCorrectAnswers()   { return correctAnswers; }
    public int getTotalQuestions()   { return totalQuestions; }
    public String getCategory()      { return category; }
    public String getDate()          { return date; }

    public String getAccuracyStr() {
        return String.format("%.0f%%", (correctAnswers * 100.0) / totalQuestions);
    }

    // Sort descending by score
    @Override
    public int compareTo(PlayerScore other) {
        return Integer.compare(other.score, this.score);
    }

    // Format for saving to file: name,score,correct,total,category,date
    public String toCSV() {
        return String.join(",", name, String.valueOf(score),
                String.valueOf(correctAnswers), String.valueOf(totalQuestions),
                category, date);
    }

    public static PlayerScore fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length < 6) return null;
        try {
            return new PlayerScore(
                parts[0],
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                parts[4],
                parts[5]
            );
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
