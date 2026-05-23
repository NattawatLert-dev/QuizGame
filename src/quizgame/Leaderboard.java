package quizgame;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {

    private static final String FILE_PATH = "data/leaderboard.txt";
    private List<PlayerScore> scores = new ArrayList<>();

    public Leaderboard() {
        loadFromFile();
    }

    public void addScore(PlayerScore score) {
        scores.add(score);
        Collections.sort(scores);
        saveToFile();
    }

    public List<PlayerScore> getTopScores(int limit) {
        return scores.subList(0, Math.min(limit, scores.size()));
    }

    public void display() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    🏆  LEADERBOARD  🏆                   ║");
        System.out.println("╠══════╦══════════════════╦════════╦═══════════╦══════════╣");
        System.out.println("║ Rank ║ Name             ║ Score  ║ Accuracy  ║ Category ║");
        System.out.println("╠══════╬══════════════════╬════════╬═══════════╬══════════╣");

        List<PlayerScore> top = getTopScores(10);
        if (top.isEmpty()) {
            System.out.println("║               No scores yet. Be the first!               ║");
        } else {
            String[] medals = {"🥇", "🥈", "🥉"};
            for (int i = 0; i < top.size(); i++) {
                PlayerScore ps = top.get(i);
                String rank = (i < 3) ? medals[i] + " " : String.format(" #%-2d", i + 1);
                System.out.printf("║ %-4s ║ %-16s ║ %-6d ║ %-9s ║ %-8s ║%n",
                        rank,
                        truncate(ps.getName(), 16),
                        ps.getScore(),
                        ps.getAccuracyStr(),
                        truncate(ps.getCategory(), 8));
            }
        }

        System.out.println("╚══════╩══════════════════╩════════╩═══════════╩══════════╝");
    }

    private String truncate(String s, int max) {
        return s.length() > max ? s.substring(0, max - 1) + "…" : s;
    }

    private void saveToFile() {
        File dir = new File("data");
        if (!dir.exists()) dir.mkdirs();

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (PlayerScore ps : scores) {
                pw.println(ps.toCSV());
            }
        } catch (IOException e) {
            System.out.println("[Warning] Could not save leaderboard: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    PlayerScore ps = PlayerScore.fromCSV(line.trim());
                    if (ps != null) scores.add(ps);
                }
            }
            Collections.sort(scores);
        } catch (IOException e) {
            System.out.println("[Warning] Could not load leaderboard: " + e.getMessage());
        }
    }
}
