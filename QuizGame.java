import java.util.Scanner;

// This program simulates a quiz game with five multiple-choice questions
public class QuizGame {
    public static void main(String[] args) {
        // Initialize Scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Arrays to store questions, options, and correct answers
        String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is 2 + 2?",
            "Which language is used for Android app development?",
            "What is the largest mammal?"
        };
        
        String[][] options = {
            {"A. Paris", "B. London", "C. Berlin", "D. Madrid"},
            {"A. Jupiter", "B. Mars", "C. Venus", "D. Mercury"},
            {"A. 3", "B. 22", "C. 4", "D. 5"},
            {"A. Python", "B. Java", "C. C++", "D. Ruby"},
            {"A. Elephant", "B. Blue Whale", "C. Giraffe", "D. Hippopotamus"}
        };
        
        char[] correctAnswers = {'A', 'B', 'C', 'B', 'B'};
        
        // Variable to track score
        int score = 0;
        int totalQuestions = questions.length;
        
        // Display welcome message
        System.out.println("Welcome to the Quiz Game!");
        System.out.println("Answer each question by entering A, B, C, or D.");
        System.out.println("Let's begin!\n");
        
        // Loop through each question
        for (int i = 0; i < totalQuestions; i++) {
            // Display question and options
            System.out.println("Question " + (i + 1) + ": " + questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }
            
            // Prompt for user input
            System.out.print("Your answer: ");
            String userInput = scanner.nextLine().toUpperCase();
            
            // Input validation
            while (!isValidInput(userInput)) {
                System.out.println("Invalid input! Please enter A, B, C, or D.");
                System.out.print("Your answer: ");
                userInput = scanner.nextLine().toUpperCase();
            }
            
            // Check answer using switch-case
            switch (userInput.charAt(0)) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                    if (userInput.charAt(0) == correctAnswers[i]) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Incorrect. The correct answer is " + correctAnswers[i]);
                    }
                    break;
                default:
                    System.out.println("Unexpected error in answer processing.");
            }
            System.out.println(); // Blank line for readability
        }
        
        // Calculate and display final score
        double percentage = (double) score / totalQuestions * 100;
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + " out of " + totalQuestions);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        
        // Close scanner
        scanner.close();
    }
    
    // Method to validate user input
    private static boolean isValidInput(String input) {
        if (input.length() != 1) {
            return false;
        }
        char answer = input.charAt(0);
        return answer == 'A' || answer == 'B' || answer == 'C' || answer == 'D';
    }
}