import java.util.*;

class Quiz {
    private String quizName;
    private List<String> questions;
    private List<String> answers;

    public Quiz(String quizName) {
        this.quizName = quizName;
        this.questions = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    // Method to add a question and its answer to the quiz
    public void addQuestion(String question, String answer) {
        questions.add(question);
        answers.add(answer);
    }

    // Getter method for retrieving the quiz name
    public String getQuizName() {
        return quizName;
    }

    // Getter method for retrieving the list of questions
    public List<String> getQuestions() {
        return questions;
    }

    // Getter method for retrieving the list of answers
    public List<String> getAnswers() {
        return answers;
    }
}

public class Main {
    private static Map<String, Quiz> quizzes = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Main loop for handling user commands
        while (true) {
            System.out.println("Enter a command: (create, take, view, list, exit)");
            String command = scanner.nextLine();

            if (command.equals("create")) {
                createQuiz(scanner);
            } else if (command.equals("take")) {
                takeQuiz(scanner);
            } else if (command.equals("view")) {
                viewQuiz(scanner);
            } else if (command.equals("list")) {
                listQuizzes();
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    // Method to create a new quiz
    private static void createQuiz(Scanner scanner) {
        System.out.println("Enter quiz name:");
        String quizName = scanner.nextLine();
        Quiz quiz = new Quiz(quizName);

        System.out.println("Enter number of questions:");
        int numQuestions = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Loop for adding questions and answers to the quiz
        for (int i = 0; i < numQuestions; i++) {
            System.out.println("Enter question " + (i + 1) + ":");
            String question = scanner.nextLine();

            System.out.println("Enter answer for question " + (i + 1) + ":");
            String answer = scanner.nextLine();

            quiz.addQuestion(question, answer);
        }

        // Add the created quiz to the map of quizzes
        quizzes.put(quizName, quiz);
        System.out.println("Quiz created successfully.");
    }

    // Method to take a quiz
    private static void takeQuiz(Scanner scanner) {
        System.out.println("Enter the name of the quiz you want to take:");
        String quizName = scanner.nextLine();

        Quiz quiz = quizzes.get(quizName);
        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        List<String> questions = quiz.getQuestions();
        List<String> answers = quiz.getAnswers();
        int score = 0;

        // Loop for displaying questions and collecting answers
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + (i + 1) + ": " + questions.get(i));
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(answers.get(i))) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! Correct answer: " + answers.get(i));
            }
        }

        // Display the quiz completion message with the score
        System.out.println("Quiz completed! Your score: " + score + "/" + questions.size());
    }

    // Method to view details of a quiz
    private static void viewQuiz(Scanner scanner) {
        System.out.println("Enter the name of the quiz you want to view:");
        String quizName = scanner.nextLine();

        Quiz quiz = quizzes.get(quizName);
        if (quiz == null) {
            System.out.println("Quiz not found.");
            return;
        }

        System.out.println("Quiz Name: " + quiz.getQuizName());
        List<String> questions = quiz.getQuestions();
        List<String> answers = quiz.getAnswers();
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("Question " + (i + 1) + ": " + questions.get(i));
            System.out.println("Answer: " + answers.get(i));
        }
    }

    // Method to list available quizzes
    private static void listQuizzes() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        System.out.println("Available quizzes:");
        for (String quizName : quizzes.keySet()) {
            System.out.println("- " + quizName);
        }
    }
}
