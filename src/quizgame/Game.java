package quizgame;

import java.util.*;

public class Game {

    private final Scanner scanner = new Scanner(System.in);
    private final QuestionBank bank = new QuestionBank();
    private final Leaderboard leaderboard = new Leaderboard();

    public void start() {
        printBanner();
        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> playGame();
                case "2" -> leaderboard.display();
                case "3" -> printHelp();
                case "4" -> {
                    System.out.println("\n👋  Thanks for playing! See you next time.\n");
                    running = false;
                }
                default  -> System.out.println("❌  Invalid option. Please enter 1-4.");
            }
        }
    }

    // ── Main Menu ────────────────────────────────────────────────

    private void printMainMenu() {
        System.out.println();
        System.out.println("┌────────────────────────────┐");
        System.out.println("│         MAIN MENU          │");
        System.out.println("├────────────────────────────┤");
        System.out.println("│  1. Play Quiz               │");
        System.out.println("│  2. View Leaderboard        │");
        System.out.println("│  3. How to Play             │");
        System.out.println("│  4. Exit                    │");
        System.out.println("└────────────────────────────┘");
        System.out.print("Choose (1-4): ");
    }

    // ── Game Flow ────────────────────────────────────────────────

    private void playGame() {
        String playerName = askPlayerName();
        String category   = askCategory();
        int    numQ       = askNumQuestions(category);

        List<Question> questions = prepareQuestions(category, numQ);
        if (questions.isEmpty()) {
            System.out.println("⚠️  No questions available for this category.");
            return;
        }

        int score   = 0;
        int correct = 0;

        System.out.println("\n🎮  Starting quiz! Good luck, " + playerName + "!\n");
        System.out.println("─".repeat(60));

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.printf("%n[%d/%d] (%s | %s | +%dpts)%n",
                i + 1, questions.size(),
                q.getCategory(), q.getDifficultyLabel(), q.getPoints());
            System.out.println("Q: " + q.getQuestion());

            String[] choices = q.getChoices();
            for (int j = 0; j < choices.length; j++) {
                System.out.printf("  %c) %s%n", 'A' + j, choices[j]);
            }

            int answer = askAnswer();
            if (q.isCorrect(answer)) {
                System.out.println("✅  Correct! +" + q.getPoints() + " points");
                score   += q.getPoints();
                correct += 1;
            } else {
                System.out.printf("❌  Wrong! The answer was: %c) %s%n",
                    'A' + q.getCorrectIndex(), choices[q.getCorrectIndex()]);
            }
            System.out.println("─".repeat(60));
        }

        showResult(playerName, score, correct, questions.size(), category);
    }

    // ── Input Helpers ────────────────────────────────────────────

    private String askPlayerName() {
        String name;
        while (true) {
            System.out.print("\n👤  Enter your name: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty() && !name.contains(",")) break;
            System.out.println("❌  Name cannot be empty or contain commas.");
        }
        return name;
    }

    private String askCategory() {
        List<String> categories = bank.getCategories();
        categories.add(0, "All Categories");

        System.out.println("\n📚  Choose a category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("  %d. %s%n", i + 1, categories.get(i));
        }

        while (true) {
            System.out.print("Choose (1-" + categories.size() + "): ");
            try {
                int idx = Integer.parseInt(scanner.nextLine().trim()) - 1;
                if (idx >= 0 && idx < categories.size()) {
                    return categories.get(idx);
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("❌  Invalid choice.");
        }
    }

    private int askNumQuestions(String category) {
        List<Question> pool = category.equals("All Categories")
                ? bank.getAll()
                : bank.getByCategory(category);
        int max = pool.size();

        while (true) {
            System.out.printf("%n❓  How many questions? (1-%d): ", max);
            try {
                int n = Integer.parseInt(scanner.nextLine().trim());
                if (n >= 1 && n <= max) return n;
            } catch (NumberFormatException ignored) {}
            System.out.println("❌  Please enter a number between 1 and " + max + ".");
        }
    }

    private int askAnswer() {
        while (true) {
            System.out.print("Your answer (A/B/C/D): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 && input.charAt(0) >= 'A' && input.charAt(0) <= 'D') {
                return input.charAt(0) - 'A';
            }
            System.out.println("❌  Please enter A, B, C, or D.");
        }
    }

    // ── Result & Leaderboard ─────────────────────────────────────

    private void showResult(String name, int score, int correct, int total, String category) {
        double accuracy = (correct * 100.0) / total;
        String grade = getGrade(accuracy);

        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║            QUIZ COMPLETE! 🎉          ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf ("║  Player   : %-24s ║%n", name);
        System.out.printf ("║  Score    : %-24d ║%n", score);
        System.out.printf ("║  Correct  : %d / %-20d ║%n", correct, total);
        System.out.printf ("║  Accuracy : %-24s ║%n", String.format("%.1f%%", accuracy));
        System.out.printf ("║  Grade    : %-24s ║%n", grade);
        System.out.println("╚══════════════════════════════════════╝");

        String date = java.time.LocalDate.now().toString();
        PlayerScore ps = new PlayerScore(name, score, correct, total, category, date);
        leaderboard.addScore(ps);

        System.out.println("\n🏆  Score saved to Leaderboard!");
        leaderboard.display();
    }

    private String getGrade(double accuracy) {
        if (accuracy >= 90) return "S  🌟 Outstanding!";
        if (accuracy >= 80) return "A  🎉 Excellent!";
        if (accuracy >= 70) return "B  👍 Good job!";
        if (accuracy >= 60) return "C  😊 Not bad!";
        if (accuracy >= 50) return "D  📖 Keep studying!";
        return                     "F  💪 Don't give up!";
    }

    // ── Utilities ─────────────────────────────────────────────────

    private List<Question> prepareQuestions(String category, int num) {
        List<Question> pool = category.equals("All Categories")
                ? bank.getAll()
                : bank.getByCategory(category);
        Collections.shuffle(pool);
        return pool.subList(0, Math.min(num, pool.size()));
    }

    private void printBanner() {
        System.out.println();
        System.out.println("  ██████╗ ██╗   ██╗██╗███████╗     ██████╗  █████╗ ███╗   ███╗███████╗");
        System.out.println(" ██╔═══██╗██║   ██║██║╚══███╔╝    ██╔════╝ ██╔══██╗████╗ ████║██╔════╝");
        System.out.println(" ██║   ██║██║   ██║██║  ███╔╝     ██║  ███╗███████║██╔████╔██║█████╗  ");
        System.out.println(" ██║▄▄ ██║██║   ██║██║ ███╔╝      ██║   ██║██╔══██║██║╚██╔╝██║██╔══╝  ");
        System.out.println(" ╚██████╔╝╚██████╔╝██║███████╗    ╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗");
        System.out.println("  ╚══▀▀═╝  ╚═════╝ ╚═╝╚══════╝    ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝");
        System.out.println();
        System.out.println("                  🎮  with Leaderboard  |  Java Edition  🎮");
        System.out.println();
    }

    private void printHelp() {
        System.out.println();
        System.out.println("📖  HOW TO PLAY");
        System.out.println("─".repeat(50));
        System.out.println("1. Enter your name");
        System.out.println("2. Choose a category (or All Categories)");
        System.out.println("3. Choose how many questions to answer");
        System.out.println("4. Answer each question by typing A, B, C, or D");
        System.out.println();
        System.out.println("🏅  SCORING");
        System.out.println("─".repeat(50));
        System.out.println("  Easy   question = 10 points");
        System.out.println("  Medium question = 20 points");
        System.out.println("  Hard   question = 30 points");
        System.out.println();
        System.out.println("📊  GRADES");
        System.out.println("─".repeat(50));
        System.out.println("  S = 90-100%   A = 80-89%   B = 70-79%");
        System.out.println("  C = 60-69%    D = 50-59%   F = 0-49%");
        System.out.println();
        System.out.println("Your scores are saved automatically to the Leaderboard!");
    }
}
