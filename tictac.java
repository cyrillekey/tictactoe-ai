
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
        for (int x = 0; x < y.length; ++x) {
            System.out.println("-------------------------");
            System.out.print("|");
            for (int i = 0; i < y.length; ++i) {
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
        int row = (int) (x / board.length);
        int column = (x % board.length);
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
    //ai that will play as x


    void placeO( int x) {
        try{
       // System.out.println("Cpu plays");
        int row = (int) (x /board.length);
        int column = x % board.length;

        if (board[row][column] == ".") {
            board[row][column] = "O";
            layout[row][column]="O";
        }else {
            System.out.println("Number not in board");
        }}
        catch(IndexOutOfBoundsException e){
            System.out.println("Number does not reside on board");
            placeO(findbestMove(board));
        }

    }

    
    void place1O() {
        try{
        System.out.println("Player two enter choice ?");
        int x = input.nextInt();
        int row = (int) (x / board.length);
        int column = (x % board.length);
        if (board[row][column] == ".") {
            board[row][column] = "O";
            layout[row][column]="O";
        } else if (board[row][column] == "X" || board[row][column] == "O") {
          //  System.out.println("Place is occupied");
            place1O();
        } else {
            System.out.println("Number not in board");
        }}
        catch(IndexOutOfBoundsException e){
            System.out.println("number enterd is not o board");
            place1O();
        }

    }

    int checkboard() {
        String win = "";
        int status = 0;
        // if won status =1 if still playing status =0
        //check the rows to find winner
        
        for (int row = 0; row < board.length; row++) {
            if(board[row][0]==board[row][1] && board[row][1]==board[row][2]){
                win=board[row][0];
                if(win=="X"){
                    status-=1;
                }else if(win=="O"){
                     status+=1;
                }
            }
        }
        //check the columns for winner
        for (int row = 0; row <board.length; row++) {
            if(board[0][row]==board[1][row] && board[1][row]==board[2][row]){
                win=board[0][row];
                if(win=="X"){
                     status-=1;
                }else if(win=="O"){
                    return status+1;
                }
            }
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            win = board[0][2];
            if(win=="X"){
                status-=1;
            }else if(win=="O"){
                return status+=1;
            }
        }if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            win = board[0][0];
            if(win=="X"){
                return status-=1;
            }else if(win=="O"){
                return status+=1;
            }
        }
        return status;
    }
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

int minimax(String x[][],int depth,boolean isMax){
    int score=checkboard();
    
    if(score==1){
        //cpu has won the game
        
        return score;
    }
    if(score==-1){
        //player has won the game
        return score;
    }
    if(fullgame()){
        return 0;
    }
    //if its maximizers tun
    if(isMax){
        int best=-1000;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j <x.length; j++) {
               if(x[i][j]=="."){
                   
                   x[i][j]="O";
                   best=Math.max(best,minimax(x, depth+1, !isMax));
                   x[i][j]=".";
               } 
            }
        }
        return best;
    }else{
        int best=1000;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j <x.length; j++) {
                if(x[i][j]=="."){
                    x[i][j]="X";
                    best=Math.min(best, minimax(x, depth+1, !isMax));
                    x[i][j]=".";
                }
            }
        }
        return best;
    }
}

int findbestMove(String[][] y){
    int bestVal=-1000;
    int play=0;
    for (int i = 0; i < y.length; i++) {
        for (int j = 0; j < y.length; j++) {
            if(y[i][j]=="."){
               y[i][j]="O";
                int moveval=minimax(y, 0, false);
                y[i][j]=".";
                if(moveval>bestVal){
                    bestVal=moveval;
                    play=i*3+j;
                }
            }
        }
    }
    return play;
}



};

class tictac {
    static void playgame(){
        createboard one = new createboard();
        one.insertNumbers();
        int check=0;
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

                if(check==-1){
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
                if(check==-1){
                    System.out.println("Player wins");
                    one.printposition();
                    break;
                }
               
            } 
            }
            static void multiplayer(){
                createboard one = new createboard();
        one.insertNumbers();
        int check=0;
        while (true) {
                one.printposition();
                one.placex();
                check = one.checkboard();
                if (check == 1) {
                    System.out.println("Cpu wins");
                    one.printposition();
                    break;
                }
                else if(check==-1){
                    System.out.println("Player wins");
                    one.printposition();
                    break;
                }
                else if (one.fullgame()) {
                    System.out.println("Game tied!");
                    one.printposition();
                    break;
                }
               // one.printposition();
                one.place1O();
                 check = one.checkboard();
                if (check == 1) {
                    System.out.println("Cpu wins");
                    one.printposition();
                    break;
                }
                else if(check==-1){
                    System.out.println("Player wins");
                    one.printposition();
                    break;
                }
                else if (one.fullgame()) {
                    System.out.println("Game is tied!");
                    one.printposition();
                    break;
                }
            } 
            }
        
    public static void main(String[] args) {
        int x=0;
     
        try{
            System.out.println("starting a new game");
           playgame();
        }catch(Exception e){
            System.out.println("An error occured try again");
            playgame();

        }
        
    }
}