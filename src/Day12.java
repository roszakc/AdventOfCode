import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day12 {

    public static class Command{
        public String dir;
        public int distance;

        public Command(String way){
            dir = way;
            distance = 0;
        }

    }

    /* pos x = East
     * pos y = North
     * neg x = West
     * neg y = South
     *
     * W   N   E   S
     * 0 , 1 , 2 , 3
     *
     */

    public static class Ship{
        private int dirFacing;
        public int north;
        public int south;
        public int east;
        public int west;

        public Ship(){
            south = 0;
            north = 0;
            east = 0;
            west = 0;
            dirFacing = 202;
        }

        public int getDirFacing(){
            return dirFacing % 4;
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Command> commandList = readData(args[0]);
        Ship ship = new Ship();
        moveShip(ship, commandList);

        int distanceTotal = getTotalDist(ship);
        System.out.println("done: "+ distanceTotal);

    }

    private static int getTotalDist(Ship s){
        int dist = 0;

        dist += Math.abs(s.east - s.west);
        dist += Math.abs(s.north - s.south);

        return dist;
    }

    private static void moveShip(Ship ship, LinkedList<Command> commandList) {

        for (Command c : commandList){

            //System.out.println("moving "+ c.dir +" "+ c.distance+ " units");
            switch (c.dir){
                case "F":
                    moveDirection(ship, ship.getDirFacing(), c.distance);
                    break;
                case "R":
                    changeFacing(ship, c.distance, c.dir);
                    break;
                case "L":
                    changeFacing(ship, c.distance, c.dir);
                    break;
                default:
                    int dir = getDirFacing(c.dir);
                    moveDirection(ship, dir, c.distance);
            }
        }

    }

    /*
     * W   N   E   S
     * 0 , 1 , 2 , 3
     */

    private static void changeFacing(Ship s, int distance, String dir){


        //System.out.println("changing dir: "+ convert(s.getDirFacing()) + " to "+ dir+" by "+distance);
        if(dir.equals("R")){
            s.dirFacing = Math.abs(s.dirFacing + (distance/90));
        } else {
            s.dirFacing = Math.abs(s.dirFacing - (distance/90));
        }

        //System.out.println("new dir: "+ convert(s.getDirFacing()));



    }

    /*
     * W   N   E   S
     * 0 , 1 , 2 , 3
     */

    private static void moveDirection(Ship ship, int directionToGo, int distance){

        switch (directionToGo) {
            case 0:
                ship.west += distance;
                break;
            case 1:
                ship.north += distance;
                break;
            case 2:
                ship.east += distance;
                break;
            case 3:
                ship.south += distance;
                break;
        }

    }

    private static void printList(LinkedList<Command> list){
        for (Command c : list){
            System.out.println(c.dir + " for "+c.distance);
        }
    }

    private static LinkedList<Command> readData(String fileName) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(fileName));
        LinkedList<Command> commandList = new LinkedList<>();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            Command com =  new Command(line.substring(0,1));
            com.distance = Integer.parseInt(line.substring(1));
            commandList.add(com);
        }
        return commandList;
    }

    /*
     * W   N   E   S
     * 0 , 1 , 2 , 3
     */

    private static int getDirFacing(String dir){
        int ret = 0;
        switch (dir){
            case "N":
                ret = 1;
                break;
            case "S":
                ret = 3;
                break;
            case "E":
                ret = 2;
                break;
            case "W":
                ret = 0;
                break;
        }

        return ret;

    }

    private static String convert(int dir){
        String ret = "";
        switch (dir){
            case 1:
                ret = "N";
                break;
            case 3:
                ret = "S";
                break;
            case 2:
                ret = "E";
                break;
            case 0:
                ret = "W";
                break;
        }

        return ret;

    }


}
