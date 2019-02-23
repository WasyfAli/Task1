import java.util.*;;
 
public class TaskGameConsole {
    private User user;   //User Input
    private Computer computer; //Computers Input
//These vars are for scoring$will see later 
    private int userScore;
    private int computerScore;
    private int numberOfGames;
 
    private enum Move {
        ROCK, PAPER, SCISSORS;
        public int compareMoves(Move otherMove) {
            // Tie
            if (this == otherMove)
                return 0;
 
            switch (this) {
            case ROCK:
                return (otherMove == SCISSORS ? 1 : -1);
            case PAPER:
                return (otherMove == ROCK ? 1 : -1);
            case SCISSORS:
                return (otherMove == PAPER ? 1 : -1);
            }
 
            return 0;
        }
    }
 
    private class User {
        private Scanner inputScanner;
 
        public User() {
            inputScanner = new Scanner(System.in);
        }
 
        public Move getMove() {
            //This will be shown to user.. Starting from here
	    System.out.printf("----------------------------------------------------------\n");
                System.out.printf("  Let's Play Rock, Paper, Scissor!! Lets have some fun!!\n");
                System.out.printf("----------------------------------------------------------\n");
                System.out.printf("             Enter Your Choice:\n");
                System.out.printf("                Scissor\n");
                System.out.printf("                 Rock\n");
                System.out.printf("                 Paper\n");
                System.out.printf("----------------------------------------------------------\n");

 
            // Get the user input
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            char firstLetter = userInput.charAt(0);
            if (firstLetter == 'R' || firstLetter == 'P' || firstLetter == 'S') {
                // User has entered a valid input
                switch (firstLetter) {
                case 'R':
                    return Move.ROCK;
                case 'P':
                    return Move.PAPER;
                case 'S':
                    return Move.SCISSORS;
                }
            }
 
            // User has not entered a valid input. Prompt again.
            return getMove();
        }
 
        public boolean playAgain() {
            System.out.print("Do you want to play again? ");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            return userInput.charAt(0) == 'Y';
        }
    }
//Computer Will generate new by itself 
    private class Computer {
        public Move getMove() {
            Move[] moves = Move.values();
            Random random = new Random();
            int index = random.nextInt(moves.length);
            return moves[index];
        }
    }
 
    public TaskGameConsole() {
        user = new User();
        computer = new Computer();
        userScore = 0;
        computerScore = 0;
        numberOfGames = 0;
    }
 
    public void startGame() {
        Move userMove = user.getMove();
        Move computerMove = computer.getMove();
        System.out.println("\nYou played " + userMove + ".");
        System.out.println("Computer played " + computerMove + ".\n");
 
        // This will compare mooves 1 error found here
        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
        case 0: // Tie happens
            System.out.println("Tie!");
            break;
        case 1: // User wins
            System.out.println(userMove + " beats " + computerMove + ". You won!");
            userScore++;
            break;
        case -1: // Computer wins
            System.out.println(computerMove + " beats " + userMove + ". You lost.");
            computerScore++;
            break;
        }
        numberOfGames++;
 
        // Again this part will asks user to play again (I have to see here to correct the whole screen again{This thing not done})
        if (user.playAgain()) {
            System.out.println();
            startGame();
        } else {
            printGameStats();
        }
    }

   //Statistics of the Game 
    private void printGameStats() {
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins + ((double) ties) / 2) / numberOfGames;
 
        // Line
        System.out.print("+");
        printDashes(68);
        System.out.println("+");
 
        // Print titles
        System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
                "WINS", "LOSSES", "TIES", "GAMES PLAYED", "PERCENTAGE WON");
 
        // Line
        System.out.print("|");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(16);
        System.out.print("+");
        printDashes(18);
        System.out.println("|");
 
        // Print values
        System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
                wins, losses, ties, numberOfGames, percentageWon * 100);
 
        // Line
        System.out.print("+");
        printDashes(68);
        System.out.println("+");
    }
 
    private void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-");
        }
    }
 //Main methos want to give here one more thing
    public static void main(String[] args) {
        TaskGameConsole game = new TaskGameConsole();
        game.startGame();
    }
}


