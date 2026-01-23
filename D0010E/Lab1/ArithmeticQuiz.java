package Lab1;

import java.util.Scanner;

public class ArithmeticQuiz {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the Arithmetic Quiz!");
        int points = 0;

        for (int i = 0; i < 10; i++) {
            int num = (int)(Math.random() * 4) + 1;
            char op = ' ';

            switch (num) {
                case 1:
                    op = '+';
                    break;
                case 2:
                    op = '-';
                    break;
                case 3:
                    op = '*';
                    break;
                case 4:
                    op = '/';
                    break;
            }

            System.out.println("Question " + (i + 1) + ":");
            ArithmeticProblem problem = new ArithmeticProblem(op);
            System.out.println(problem.getQuestion());
            int user_answer = in.nextInt();
            
            if (user_answer == problem.getAnswer()) {
                System.out.println("Correct! You get 10 points.");
                points += 10;
            } else {
                System.out.println("Incorrect. Try again.");
                System.out.println(problem.getQuestion());
                user_answer = in.nextInt();
                if (user_answer == problem.getAnswer()) {
                    System.out.println("Correct! You get 5 points.");
                    points += 5;
                } else 
                    System.out.println("Incorrect. You get 0 points. \nThe correct answer is " + problem.getAnswer() + ".");
            }
        }
        System.out.println("Your total points: " + points + " out of 100.");
        in.close();
    }
}

