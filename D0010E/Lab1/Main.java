package Lab1;

/*
javac -d out Lab1/*.java
java -cp out Lab1.Main
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Choose program:");
        System.out.println("1) Arithmetic Quiz");
        System.out.println("2) Spellcheck");
        System.out.print("> ");

        int choice = in.nextInt();

        if (choice == 1) ArithmeticQuiz.main(new String[0]);
        else if (choice == 2) Spellcheck.main(new String[0]);
        else System.out.println("Invalid choice.");

        in.close();
    }
}