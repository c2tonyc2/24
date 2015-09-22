import java.util.Scanner;
public class Launcher24 {
    private static Deck currentDeck;
    private static Card[][] tableCards;
    private static int[] cardValues;
    private static PossibilityTester tester = new PossibilityTester();
    private static boolean onlyValid;
    private static int COUNT = 4;
    private static String notAnInt = "Unrecognized input, "
                                    +"please try again with "
                                    +"a non-negative integer.";

    // Simulate a game of 24
    public static void main(String[] args) {
        initGame();
        int N = 2;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(true) { 
            drawTable(N);
            if (StdDrawPlus.isSpacePressed()) { // Any way to change sample rate?
                dealCheck();
            }
            if (StdDrawPlus.isPPressed()) { // Any way to change sample rate?
                if (tester.isPossible(cardValues)) {
                    System.out.println("This is a valid hand, there exists at least 1 solution."); 
                } else {
                    System.out.println("No Solution, Try another set of cards!");
                }
            }
            if (StdDrawPlus.isSPressed()) { // Any way to change sample rate?
                Scanner input = new Scanner(System.in);
                System.out.println("How many solutions would you like printed?");
                try {
                    int amount = input.nextInt();
                    if (amount >= 0) {
                        for (String solution : tester.getSolutions(cardValues, amount)) {
                            System.out.println(solution);
                        }
                    } else {
                        System.out.println(notAnInt);
                    }
                } catch (Exception e) {
                    System.out.println(notAnInt);
                }
            }
            StdDrawPlus.show(100);
        }
    }

    private static void drawTable(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tableCards != null) { // Special thanks to Diana Ly for producing all images in the img directory. 
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + String.valueOf(tableCards[i][j].type()) 
                    + String.valueOf(tableCards[i][j].value()) + String.valueOf(tableCards[i][j].suit()) + ".png", .75, .9);
                }
            }
        }
    }

    // Redeals a new hand of 4 cards
    private static void dealCheck() {
        wipe();
        reDeal();
        while (onlyValid && !tester.isPossible(cardValues)) {
            wipe();
            reDeal();
        }
    }

    private static void reDeal() {
        Card[] cards = currentDeck.deal(COUNT);
        for (int i = 0; i < COUNT; i += 1) {
            cardValues[i] = cards[i].value();
        }
        int counter = 0;
        for (int i = 0; i < 2; i += 1) {
            for (int j = 0; j < 2; j += 1) {
                tableCards[i][j] = cards[counter];
                counter += 1;
            }
        }
    }

    private static void wipe() {
        tableCards = new Card[2][2];
        cardValues = new int[COUNT];
    }

    private static void initGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("Initiliazing a game of 24.....");

        System.out.println("Would you like the deck to include royals? (y/n)");
        String command = input.nextLine();
        if (command.startsWith("y") || command.startsWith("Y")) {
            currentDeck = new Deck("royals");
        } else {
            currentDeck = new Deck("no royals");
        }
        System.out.println("Would you like to be only dealt hands with valid solutions? (y/n)");
        command = input.nextLine();
        if (command.startsWith("y") || command.startsWith("Y")) {
            onlyValid = true;
        }
        System.out.println("Press Space to Deal/Redeal a hand");
        System.out.println("Press p to see if there exists at "
                          +"least 1 solution for this hand.");
        System.out.println("Press s and enter a non-negative "
                          +"number x, to print out up to x solutions");
    }
}
