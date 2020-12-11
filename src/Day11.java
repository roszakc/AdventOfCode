import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day11 {

    private static final int maxRows = 98;
    private static final int maxCols = 91;
    private static final String emptySeat = "L";
    private static final String floor = ".";
    private static final String occupiedSeat = "#";


    public static void main(String[] args) throws FileNotFoundException {
        String[][] board = readData(args[0]);
        //printBoard(board);

        board = stabilizeBoard(board);
        int seatsOccupied = countOccupied(board);

        System.out.println(seatsOccupied);
    }

    private static int countOccupied(String[][] board) {
        int count = 0;
        for (int i = 0; i < maxRows; i++){
            for (int j = 0; j < maxCols; j++){
                if(board[i][j].equals(occupiedSeat)){
                    count++;
                }
            }
        }
        return count;
    }

    private static String[][] stabilizeBoard(String[][] board) {
        String[][] oldBoard = board;

        boolean boardUpdated = false, breakOut = false;
        int i = 0, j = 0;
        while(!breakOut){
            String[][] newBoard = new String[maxRows][maxCols];
            while(i < maxRows){
                while(j < maxCols){
                    if(oldBoard[i][j].equals(emptySeat)){
                        if(checkAdjFree(oldBoard, i, j)){
                            newBoard[i][j] = occupiedSeat;
                            boardUpdated = true;
                        } else {
                            newBoard[i][j] = oldBoard[i][j];
                        }
                    } else if(oldBoard[i][j].equals(occupiedSeat)){
                        if(checkAdjOccupied(oldBoard, i, j) > 4){
                            newBoard[i][j] = emptySeat;
                            boardUpdated = true;
                        } else {
                            newBoard[i][j] = oldBoard[i][j];
                        }
                    } else {
                        newBoard[i][j] = floor;
                    }
                    j++;
                }
                i++;
                j = 0;
            }
            i = 0;
            j = 0;
            if(!boardUpdated){
                breakOut = true;
            }
            boardUpdated = false;
            oldBoard = newBoard;
            //printBoard(oldBoard);
        }



        return oldBoard;
    }

    private static boolean checkAdjFree(String[][] oldBoard, int row, int col) {
        boolean sit = true;
        boolean hit = false;
        int i = row-1, j = col-1;
        //Straight row
        while(i >= 0 && sit && !hit){
            if(oldBoard[i][col].equals(occupiedSeat)){
                hit = true;
                sit = false;
            } else if(oldBoard[i][col].equals(emptySeat)){
                hit = true;
            }
            i--;
        }
        i = row+1;
        hit = false;
        while(i < maxRows && sit && !hit){
            if(oldBoard[i][col].equals(occupiedSeat)){
                hit = true;
                sit = false;
            } else if(oldBoard[i][col].equals(emptySeat)){
                hit = true;
            }
            i++;
        }

        //Straight col
        hit = false;
        while(j >= 0 && sit && !hit){
            if(oldBoard[row][j].equals(occupiedSeat)){
                hit = true;
                sit = false;
            } else if(oldBoard[row][j].equals(emptySeat)){
                hit = true;
            }
            j--;
        }

        j = col+1;
        hit = false;
        while(j < maxCols && sit && !hit){
            if(oldBoard[row][j].equals(occupiedSeat)){
                hit = true;
                sit = false;
            } else if(oldBoard[row][j].equals(emptySeat)){
                hit = true;
            }
            j++;
        }

        hit = false;
        j = col-1;
        i = row-1;
        while (i >= 0 && j >= 0 && sit && !hit){
            if(oldBoard[i][j].equals(occupiedSeat)){
                sit = false;
                hit = true;
            }else if(oldBoard[i][j].equals(emptySeat)){
                hit = true;
            }
            i--;
            j--;
        }
        j = col+1;
        i = row+1;
        hit = false;
        while (i < maxRows && j < maxCols && sit && !hit){
            if(oldBoard[i][j].equals(occupiedSeat)){
                sit = false;
                hit = true;
            }else if(oldBoard[i][j].equals(emptySeat)){
                hit = true;
            }
            i++;
            j++;
        }

        j = col+1;
        i = row-1;
        hit = false;
        while (i >= 0 && j < maxCols && sit && !hit){
            if(oldBoard[i][j].equals(occupiedSeat)){
                sit = false;
                hit = true;
            }else if(oldBoard[i][j].equals(emptySeat)){
                hit = true;
            }
            i--;
            j++;
        }

        j = col-1;
        i = row+1;
        hit = false;
        while (i < maxRows && j >= 0 && sit && !hit){
            if(oldBoard[i][j].equals(occupiedSeat)){
                sit = false;
                hit = true;
            }else if(oldBoard[i][j].equals(emptySeat)){
                hit = true;
            }
            i++;
            j--;
        }

        return sit;
    }

    private static int checkAdjOccupied(String[][] oldBoard, int row, int col) {
        int full = 0;
        int i = row-1, j = col-1;
        boolean hit = false;
        //Straight row
        while(i >= 0 && !hit){
            if(oldBoard[i][col].equals(occupiedSeat)){
                full++;
                hit = true;
            } else if(oldBoard[i][col].equals(emptySeat)){
                hit = true;
            }
            i--;
        }

        hit = false;
        i = row+1;
        while(i < maxRows && !hit){
            if(oldBoard[i][col].equals(occupiedSeat)){
                full++;
                hit = true;
            } else if(oldBoard[i][col].equals(emptySeat)){
                hit = true;
            }
            i++;
        }

        //Straight col
        hit = false;
        while(j >= 0 && !hit){
            if(oldBoard[row][j].equals(occupiedSeat)){
                full++;
                hit = true;
            } else if(oldBoard[row][j].equals(emptySeat)){
                hit = true;
            }
            j--;
        }

        j = col+1;
        hit = false;
        while(j < maxCols && !hit){
            if(oldBoard[row][j].equals(occupiedSeat)){
                full++;
                hit = true;
            } else if(oldBoard[row][j].equals(emptySeat)){
                hit = true;
            }
            j++;
        }

        //diags
        j = col-1;
        i = row-1;
        hit = false;
        while (i >= 0 && j >= 0 && !hit){
            if(oldBoard[i][j].equals(occupiedSeat)){
                full++;
                hit = true;
            } else if(oldBoard[i][j].equals(emptySeat)){
                hit = true;
            }
            i--;
            j--;
        }
        j = col+1;
        i = row+1;
        hit = false;
        while (i < maxRows && j < maxCols && !hit){
            if(oldBoard[i][j].equals(occupiedSeat)){
                full++;
                hit = true;
            } else if(oldBoard[i][j].equals(emptySeat)){
                hit = true;
            }
            i++;
            j++;
        }

        j = col+1;
        i = row-1;
        hit = false;
        while (i >= 0 && j < maxCols && !hit){
            if(oldBoard[i][j].equals(occupiedSeat)){
                full++;
                hit = true;
            } else if(oldBoard[i][j].equals(emptySeat)){
                hit = true;
            }
            i--;
            j++;
        }

        j = col-1;
        i = row+1;
        hit = false;
        while (i < maxRows && j >= 0 && !hit){
            if(oldBoard[i][j].equals(occupiedSeat)){
                full++;
                hit = true;
            } else if(oldBoard[i][j].equals(emptySeat)){
                hit = true;
            }
            i++;
            j--;
        }
        System.out.println(full);
        return full;
    }

    private static void printBoard(String[][] board) {
        for (int i = 0; i < maxRows; i++){
            for (int j = 0; j < maxCols; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    private static String[][] readData(String fileName) throws FileNotFoundException {
        String[][] board = new String[maxRows][maxCols];
        Scanner sc = new Scanner(new File(fileName));
        int i = 0;
        while (sc.hasNextLine()){
            String[] row = sc.nextLine().split("");
            for (int j = 0; j < maxCols; j++){
                board[i][j] = row[j];
            }
            i++;
        }
        return board;
    }

}
