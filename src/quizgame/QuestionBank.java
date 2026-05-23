package quizgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

    private List<Question> questions = new ArrayList<>();

    public QuestionBank() {
        loadQuestions();
    }

    private void loadQuestions() {
        // ── Science ──────────────────────────────────────────────
        questions.add(new Question(
            "What is the chemical symbol for water?",
            new String[]{"H2O", "CO2", "O2", "NaCl"},
            0, "Science", 1
        ));
        questions.add(new Question(
            "How many planets are in our solar system?",
            new String[]{"7", "8", "9", "10"},
            1, "Science", 1
        ));
        questions.add(new Question(
            "What gas do plants absorb during photosynthesis?",
            new String[]{"Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"},
            2, "Science", 2
        ));
        questions.add(new Question(
            "What is the speed of light (approx.) in km/s?",
            new String[]{"150,000", "300,000", "500,000", "1,000,000"},
            1, "Science", 2
        ));
        questions.add(new Question(
            "What is the atomic number of Carbon?",
            new String[]{"4", "6", "8", "12"},
            1, "Science", 3
        ));
        questions.add(new Question(
            "Which subatomic particle has a negative charge?",
            new String[]{"Proton", "Neutron", "Electron", "Positron"},
            2, "Science", 3
        ));

        // ── Technology ───────────────────────────────────────────
        questions.add(new Question(
            "What does CPU stand for?",
            new String[]{"Central Processing Unit", "Computer Personal Unit", "Core Power Unit", "Central Program Utility"},
            0, "Technology", 1
        ));
        questions.add(new Question(
            "Which programming language was Java inspired by?",
            new String[]{"Python", "C++", "Pascal", "BASIC"},
            1, "Technology", 1
        ));
        questions.add(new Question(
            "What does HTML stand for?",
            new String[]{"HyperText Markup Language", "High Transfer Markup Language", "HyperText Machine Language", "HyperTool Markup Language"},
            0, "Technology", 1
        ));
        questions.add(new Question(
            "What is the base of the binary number system?",
            new String[]{"8", "10", "2", "16"},
            2, "Technology", 2
        ));
        questions.add(new Question(
            "Which data structure uses LIFO order?",
            new String[]{"Queue", "Stack", "Heap", "Tree"},
            1, "Technology", 2
        ));
        questions.add(new Question(
            "What does SQL stand for?",
            new String[]{"Structured Query Language", "Simple Query Logic", "System Query Language", "Structured Question Logic"},
            0, "Technology", 2
        ));
        questions.add(new Question(
            "What is the time complexity of binary search?",
            new String[]{"O(n)", "O(n²)", "O(log n)", "O(1)"},
            2, "Technology", 3
        ));
        questions.add(new Question(
            "Which sorting algorithm has the best average-case complexity?",
            new String[]{"Bubble Sort", "Insertion Sort", "Merge Sort", "Selection Sort"},
            2, "Technology", 3
        ));

        // ── Math ─────────────────────────────────────────────────
        questions.add(new Question(
            "What is 12 × 12?",
            new String[]{"124", "144", "132", "148"},
            1, "Math", 1
        ));
        questions.add(new Question(
            "What is the value of π (pi) to 2 decimal places?",
            new String[]{"3.12", "3.41", "3.14", "3.18"},
            2, "Math", 1
        ));
        questions.add(new Question(
            "What is the square root of 144?",
            new String[]{"11", "12", "13", "14"},
            1, "Math", 1
        ));
        questions.add(new Question(
            "What is 15% of 200?",
            new String[]{"25", "30", "35", "40"},
            1, "Math", 2
        ));
        questions.add(new Question(
            "What is the sum of interior angles of a triangle?",
            new String[]{"90°", "180°", "270°", "360°"},
            1, "Math", 2
        ));
        questions.add(new Question(
            "Solve: 2x + 5 = 17, what is x?",
            new String[]{"5", "6", "7", "8"},
            1, "Math", 2
        ));
        questions.add(new Question(
            "What is 2^10?",
            new String[]{"512", "1024", "2048", "256"},
            1, "Math", 3
        ));

        // ── General Knowledge ────────────────────────────────────
        questions.add(new Question(
            "What is the capital of Japan?",
            new String[]{"Osaka", "Kyoto", "Tokyo", "Hiroshima"},
            2, "General", 1
        ));
        questions.add(new Question(
            "How many colors are in a rainbow?",
            new String[]{"5", "6", "7", "8"},
            2, "General", 1
        ));
        questions.add(new Question(
            "Which country is the largest by area?",
            new String[]{"China", "USA", "Canada", "Russia"},
            3, "General", 1
        ));
        questions.add(new Question(
            "In what year did World War II end?",
            new String[]{"1943", "1944", "1945", "1946"},
            2, "General", 2
        ));
        questions.add(new Question(
            "What is the longest river in the world?",
            new String[]{"Amazon", "Nile", "Yangtze", "Mississippi"},
            1, "General", 2
        ));
    }

    public List<Question> getByCategory(String category) {
        List<Question> result = new ArrayList<>();
        for (Question q : questions) {
            if (q.getCategory().equalsIgnoreCase(category)) {
                result.add(q);
            }
        }
        return result;
    }

    public List<Question> getAll() {
        List<Question> all = new ArrayList<>(questions);
        Collections.shuffle(all);
        return all;
    }

    public List<String> getCategories() {
        List<String> cats = new ArrayList<>();
        for (Question q : questions) {
            if (!cats.contains(q.getCategory())) {
                cats.add(q.getCategory());
            }
        }
        return cats;
    }
}
