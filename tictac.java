
//import java.util.Scanner;
import java.util.*;

class createboard {
    Scanner input = new Scanner(System.in);
    String[][] board = new String[3][3];
    String[][] layout={{"0","1","2"},{"3","4","5"},{"6","7","8"}};

    void insertNumbers() {
        for (int x = 0; x < 3; x++) {
            for (int i = 0; i < 3; i++) {
                board[x][i] = ".";

            }
        }
    }
    //
    void printboard(String y[][]) {
        for (int x = 0; x < 3; ++x) {
            System.out.println("-------------------------");
            System.out.print("|");
            for (int i = 0; i < 3; ++i) {
                System.out.print("   " + y[x][i] + "   |");
            }

            System.out.println();
        }
    }//print position to know where to play
    void printposition(){
        
        for (int i = 0; i < layout.length; i++) {
            System.out.println("-------------------------");
            System.out.print("|");
            for (int j = 0; j < layout.length; j++) {
                System.out.print("   " +layout[i][j]+ "   |");
            }
            System.out.println();
        }

    }

    void placex() {
        try{
        System.out.println("Player one enter choice ?");
        int x = input.nextInt();
        int row = (int) (x / 3);
        int column = (x % 3);
        if (board[row][column] == ".") {
            board[row][column] = "X";
            layout[row][column]="X";
        } else if (board[row][column] == "X" || board[row][column] == "O") {
          //  System.out.println("Place is occupied");
            placex();
        } else {
            System.out.println("Number not in board");
        }}
        catch(IndexOutOfBoundsException e){
            System.out.println("number enterd is not o board");
            placex();
        }

    }

    void placeO( int x) {
        try{
        System.out.println("Cpu plays");
        int row = (int) (x / 3);
        int column = x % 3;

        if (board[row][column] == ".") {
            board[row][column] = "O";
            layout[row][column]="O";
        } else if (board[row][column] == "X" || board[row][column] == "O") {
            System.out.println("Place is occupied");
            placeO(aiplayer());
        } else {
            System.out.println("Number not in board");
        }}
        catch(IndexOutOfBoundsException e){
            System.out.println("Number does not reside on board");
            placeO(aiplayer());
        }

    }

    int checkboard() {
        String win = "";
        int status = 0;
        // if won status =1 if still playing status =0
        if (board[0][0] == board[0][1] && board[0][0] == board[0][2]) {
            win = board[0][0];
        } else if (board[1][0] == board[1][1] && board[1][2] == board[1][0]) {
            win = board[1][0];
        } else if (board[2][0] == board[2][1] && board[2][0] == board[2][2]) {
            win = board[2][0];
        } else if (board[0][0] == board[1][0] && board[1][0] == board[2][0]) {
            win = board[0][0];
        } else if (board[0][1] == board[1][1] && board[1][1] == board[2][1]) {
            win = board[1][1];
        } else if (board[0][2] == board[1][2] && board[1][2] == board[2][2]) {
            win = board[2][2];
        } else if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            win = board[1][1];
        } else if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            win = board[1][1];
        }

        if (win == "X") {
            System.out.println("Player One wins");
            status = 1;
        } else if (win == "O") {
            System.out.println("Cpu wins");
            status = 1;
        }
        return status;
    }
/*
    String[] createmixed() {
        int incre = 0;
        String[] merged = {};
        for (int x = 0; x < 3; x++) {
            for (int i = 0; i < board.length; i++) {
                merged[incre] = board[x][i];
                incre++;
            }
        }
        return merged;
    }
*/
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
    int aiplayer(){
        int incre = 0;
        String[] merged=new String[9];
        int[] remaining=new int[8];
        for (int x = 0; x <board.length; x++) {
            for (int i = 0; i < board.length; i++) {
                merged[incre] = board[x][i];
                incre++;
            }
        }//create array of remaining items
        int x=0;
        for (int i = 0; i < merged.length; i++) {
            if(merged[i]=="."){
                
                remaining[x]=i;
                x++;
            }
        }
        Random generator=new Random();
        int randomnumber=generator.nextInt(remaining.length);
        
        return remaining[randomnumber];
    }
    


}

class tictac {
    public static void main(String[] args) {
        createboard one = new createboard();
        one.insertNumbers();
        
        while (true) {
            try {
                one.printposition();
                one.placex();
                int check = one.checkboard();
                if (check == 1) {
                    one.printposition();
                    break;
                }
                if (one.fullgame()) {
                    System.out.println("Game tied!");
                    one.printposition();
                    break;
                }
               // one.printposition();
                one.placeO(one.aiplayer());
                int check2 = one.checkboard();
                if (check2 == 1) {
                    one.printposition();
                    break;
                }
                if (one.fullgame()) {
                    System.out.println("Game is tied!");
                    one.printposition();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error occured");
                break;
            }
        }
        
        
    }
}