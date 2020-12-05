import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Day5 {

    public static class Seat implements Comparable<Seat> {
        public int row;
        public int col;
        public int id;

        public Seat(){
            this.row = 0;
            this.col = 0;
            this.id = 0;
        }

        @Override
        public int compareTo(Seat o) {
            if(o.id > this.id){
                return -1;
            } else {
                return 1;
            }
        }
    }

    private static final int maxSeats = 859;
    private static final int maxRows = 127;
    private static final int maxCols = 7;


    public static void main(String[] args) throws FileNotFoundException {
        String[] seatLocations = getSeatLocations(args[0]);
        LinkedList<Seat> seatManifest = getSeats(seatLocations);
        Collections.sort(seatManifest);
        int maxID = getMaxId(seatManifest);
        System.out.println("My seat "+maxID);
    }

    private static int getMaxId(LinkedList<Seat> seatManifest) {
        int mySeat = 0;
        int i = 300;
        while(mySeat == 0){
            if( (((seatManifest.get(i).id) + 1) != seatManifest.get(i+1).id)){
                mySeat = seatManifest.get(i).id+1;
            }
            i++;
        }

        return mySeat;
    }

    private static String[] getSeatLocations(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));
        String[] seats = new String[maxSeats];
        int i = 0;
        while (reader.hasNextLine()) {
            seats[i] = reader.nextLine();
            i++;
        }
        return seats;
    }

    private static LinkedList<Seat> getSeats(String[] seatLocations) {
        LinkedList<Seat> seatList = new LinkedList<>();
        for(String singleSeatLoc : seatLocations){
            Seat newSeat = new Seat();
            newSeat.row = getSeatRow(singleSeatLoc.substring(0,7).split(""));
            newSeat.col = getSeatCol(singleSeatLoc.substring(7,10).split(""));
            newSeat.id = (newSeat.row * 8) + newSeat.col;
            seatList.add(newSeat);
        }
        return seatList;
    }

    private static int getNumber(String[] command) {
        int col = 0;
        for (String letter : command){
            if(letter.equals("R") || letter.equals("B")){
                col = (col << 1) | 1;
            } else{
                col = col << 1;
            }
        }
        return col;
    }

    private static int getSeatCol(String[] command) {
        return getNumber(command);
    }

    private static int getSeatRow(String[] command) {

        return getNumber(command);
    }
}
