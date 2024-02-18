import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class NumberGame {
 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int minRange = 1;
        int maxRange = 100;
        int noOfAttempts = 10;
        int roundsWon = 0;
        int TotalPoints = 0;
        
        cls();

        System.out.println("Welcome to the Number Guessing Game! \n If you win each round you will get 100 points !!");

        while (true) {
            int targetNo = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            int userGuess;

            System.out.println("\nNew round! Guess the number between " + minRange + " and " + maxRange);

            while (true) {
                System.out.print("Enter the number you have guessed : ");
                userGuess = scanner.nextInt();
                cls();
                attempts++;


                if (userGuess == targetNo)
                {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    roundsWon++;
                    TotalPoints = TotalPoints + 100;
                    break;
                }
                else if (userGuess < targetNo && ((userGuess < minRange) == false) && attempts != noOfAttempts)
                {
                    System.out.println("Too low! Try again. \n you have " + (noOfAttempts - attempts) + " left !! \n");
                }
                else if(userGuess > targetNo && ((userGuess > maxRange) == false) && attempts != noOfAttempts)
                {
                    System.out.println("Too high! Try again. \n you have " + (noOfAttempts - attempts) + " left !! \n");
                }
                else if((userGuess < minRange) == true && attempts != noOfAttempts)
                {
                    System.out.println("OOPS!! your guessed number has crossed the minimum range! Try again \n you have " + (noOfAttempts - attempts) + " left !! \n");
                }
                else if((userGuess > maxRange) == false && attempts != noOfAttempts)
                {
                    System.out.println("OOPS!! your guessed number has crossed the maximum range! Try again \n you have " + (noOfAttempts - attempts) + " left !! \n");
                }

                if (attempts == noOfAttempts)
                {
                    System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNo);
                    break;
                }
            }

            System.out.print("Do you want to play again? Enter (yes - y /no - n): ");
            String playAgain = scanner.next().toLowerCase();

            cls();

            if (!playAgain.equals("y")) {
                break;
            }

        }

        System.out.println("Game over! You won " + roundsWon + " round(s). \n Total points = " + TotalPoints + "");
        scanner.close();
    }

    public static void cls()
    {
        try
        {	
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }
}
