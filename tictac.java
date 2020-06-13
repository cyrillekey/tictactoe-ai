
//import java.util.Scanner;
import java.util.Scanner;

class createboard {
    Scanner input = new Scanner(System.in);
    String[][] board = new String[3][3];
    String[][] layout = { { "0", "1", "2" }, { "3", "4", "5" }, { "6", "7", "8" } };

    void insertNumbers() {
        for (int x = 0; x < 3; x++) {
            for (int i = 0; i < 3; i++) {
                board[x][i] = ".";

            }
        }
    }

    //
    void printboard(final String y[][]) {
        for (int x = 0; x < y.length; ++x) {
            System.out.println("-------------------------");
            System.out.print("|");
            for (int i = 0; i < y.length; ++i) {
                System.out.print("   " + y[x][i] + "   |");
            }

            System.out.println();
        }
    }// print position to know where to play

    void printposition() {

        for (int i = 0; i < layout.length; i++) {
            System.out.println("-------------------------");
            System.out.print("|");
            for (int j = 0; j < layout.length; j++) {
                System.out.print("   " + layout[i][j] + "   |");
            }
            System.out.println();
        }

    }
    //Funtion that places the Humans X on the board

    void placex() {
        try {
            System.out.println("Player one enter choice ?");
            final int x = input.nextInt();
            final int row = (int) (x / board.length);
            final int column = (x % board.length);
            if (board[row][column] == ".") {
                board[row][column] = "X";
                layout[row][column] = "X";
            } else if (board[row][column] == "X" || board[row][column] == "O") {
                // System.out.println("Place is occupied");
                placex();
            } else {
                System.out.println("Number not in board");
            }
        } catch (final IndexOutOfBoundsException e) {
            System.out.println("number enterd is not o board");
            placex();
        }

    }
    //Funtion that the ai plays O on the board

    void placeO(final int x) {
        try {
            // System.out.println("Cpu plays");
            final int row = (int) (x / board.length);
            final int column = x % board.length;

            if (board[row][column] == ".") {
                board[row][column] = "O";
                layout[row][column] = "O";
            } else {
                System.out.println("Number not in board");
            }
        } catch (final IndexOutOfBoundsException e) {
            System.out.println("Number does not reside on board");
            placeO(findbestMove(board));
        }

    }
    //incase of multiplayer method to place O on the board
    void humanpalyer() {
        try {
            System.out.println("Player two enter choice ?");
            final int x = input.nextInt();
            final int row = (int) (x / board.length);
            final int column = (x % board.length);
            if (board[row][column] == ".") {
                board[row][column] = "O";
                layout[row][column] = "O";
            } else if (board[row][column] == "X" || board[row][column] == "O") {
                // System.out.println("Place is occupied");
                humanpalyer();
            } else {
                System.out.println("Number not in board");
            }
        } catch (final IndexOutOfBoundsException e) {
            System.out.println("number enterd is not o board");
           humanpalyer();
        }

    }
//check diaganols,rows and column for winner.return 1 if cpu wins and negative if cpu loses
    int checkboard() {
        String win = "";
        int status = 0;
        // if won status =1 if still playing status =0
        // check the rows to find winner

        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                win = board[row][0];
                if (win == "X") {
                    status -= 1;
                } else if (win == "O") {
                    status += 1;
                }
            }
        }
        // check the columns for winner
        for (int row = 0; row < board.length; row++) {
            if (board[0][row] == board[1][row] && board[1][row] == board[2][row]) {
                win = board[0][row];
                if (win == "X") {
                    status -= 1;
                } else if (win == "O") {
                    return status + 1;
                }
            }
        }
        //check diaganols for winner
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            win = board[0][2];
            if (win == "X") {
                status -= 1;
            } else if (win == "O") {
                return status += 1;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            win = board[0][0];
            if (win == "X") {
                return status -= 1;
            } else if (win == "O") {
                return status += 1;
            }
        }
        return status;
    }
    //check if no moves are available therefore game is a draw
    boolean fullgame() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ".") {
                    return false;
                }
            }
        }
        return true;
    }
    //minimax method that traverses board to find move
    int minimax(final String x[][], final int depth, final boolean isMax) {
        final int score = checkboard();

        if (score == 1) {
            // cpu has won the game

            return score;
        }
        if (score == -1) {
            // player has won the game
            return score;
        }
        if (fullgame()) {
            return 0;
        }
        // if its maximizers tun
        if (isMax) {
            int best = -1000;
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x.length; j++) {
                    if (x[i][j] == ".") {

                        x[i][j] = "O";
                        best = Math.max(best, minimax(x, depth + 1, !isMax));
                        x[i][j] = ".";
                    }
                }
            }
            return best;
        } else {
            int best = 1000;
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x.length; j++) {
                    if (x[i][j] == ".") {
                        x[i][j] = "X";
                        best = Math.min(best, minimax(x, depth + 1, !isMax));
                        x[i][j] = ".";
                    }
                }
            }
            return best;
        }
    }
//recursively call minimax to find the best position to play
    int findbestMove(final String[][] y) {
        int bestVal = -1000;
        int play = 0;
        for (int i = 0; i < y.length; i++) {
            for (int j = 0; j < y.length; j++) {
                if (y[i][j] == ".") {
                    y[i][j] = "O";
                    final int moveval = minimax(y, 0, false);
                    y[i][j] = ".";
                    if (moveval > bestVal) {
                        bestVal = moveval;
                        play = i * 3 + j;
                    }
                }
            }
        }
        return play;
    }

};

class tictac {
    //method that plays the game if game is against cpu
    static void playgame() {
        final createboard one = new createboard();
        one.insertNumbers();
        int check = 0;
        while (true) {

            one.printposition();
            System.out.println();
            one.placex();
            check = one.checkboard();
            if (one.fullgame()) {
                System.out.println("Game tied!");
                one.printposition();
                break;
            }
            if (check == 1) {
                System.out.println("Cpu wins");
                one.printposition();
                break;
            }

            if (check == -1) {
                System.out.println("Player wins");
                one.printposition();
                break;
            }

            // one.printposition();
            one.placeO(one.findbestMove(one.board));
            check = one.checkboard();
            if (one.fullgame()) {
                System.out.println("Game is tied!");
                one.printposition();
                break;
            }
            if (check == 1) {
                System.out.println("Cpu wins");
                one.printposition();
                break;
            }
            if (check == -1) {
                System.out.println("Player wins");
                one.printposition();
                break;
            }

        }
    }

    static void multiplayer() {
        final createboard one = new createboard();
        one.insertNumbers();
        int check = 0;
        while (true) {
            one.printposition();
            one.placex();
            check = one.checkboard();
            if (check == 1) {
                System.out.println("Cpu wins");
                one.printposition();
                break;
            } else if (check == -1) {
                System.out.println("Player wins");
                one.printposition();
                break;
            } else if (one.fullgame()) {
                System.out.println("Game tied!");
                one.printposition();
                break;
            }
            // one.printposition();
            one.humanpalyer();
            check = one.checkboard();
            if (check == 1) {
                System.out.println("Cpu wins");
                one.printposition();
                break;
            } else if (check == -1) {
                System.out.println("Player wins");
                one.printposition();
                break;
            } else if (one.fullgame()) {
                System.out.println("Game is tied!");
                one.printposition();
                break;
            }
        }
    }

    public static void main(final String[] args) {
        System.out.print("Which mode are you playing 1:play against cpu \n2.Play against human");
        final Scanner input = new Scanner(System.in);
        final int x = input.nextInt();
        switch (x) {
            case 1:
                try {
                    System.out.println("starting a new game");
                    playgame();
                } catch (final Exception e) {
                    System.out.println("An error occured try again");
                    playgame();

                }
                break;
            case 2:
                try {
                    System.out.println("starting a new game");
                    multiplayer();
                } catch (final Exception e) {
                    System.out.println("An error occured try again");
                    multiplayer();

                }
                break;
            default:
                System.out.println("You did not choose anything");
                break;

        }
        input.close();
    }
}