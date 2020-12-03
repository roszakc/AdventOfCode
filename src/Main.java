import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Roszakc
 *
 * Advent of Code Day 3
 */
public class Main {

    private static final int repeatSize = 323;
    private static final int rowSize = 31;
    private static final String tree = "#";
    private static final String open = ".";
    private static final int[] slopeRight = {1,3,5,7,1};
    private static final int[] slopeDown = {1,1,1,1,2};

    public static void main(String[] args) throws FileNotFoundException {
        String [][] slopeMap = readData(args[0]);
        //printMap(slopeMap);
        long numTreesHitTotal = getTreesHit(slopeMap, slopeRight[0], slopeDown[0]);
        for (int i = 1; i < slopeRight.length; i++){
            int numTreesHit = getTreesHit(slopeMap, slopeRight[i], slopeDown[i]);
            numTreesHitTotal = numTreesHitTotal * numTreesHit;
        }

        System.out.println("Trees hit: "+numTreesHitTotal);
    }

    private static void printMap(String[][] slopeMap) {
        for (int i = 0; i < repeatSize; i++){
            for (int j = 0; j < rowSize; j++){
                System.out.print(slopeMap[i][j]);
            }
            System.out.println();
        }

    }

    private static int getTreesHit(String[][] slopeMap, int slopeR, int slopeD) {
        int treesHit = 0;
        int j = slopeR;
        for (int i = slopeD; i < repeatSize; i+=slopeD){
            if(slopeMap[i][j].equals(tree)){
                treesHit++;
            }
            j=(j+slopeR)%31;
        }
        return treesHit;
    }

    private static String[][] readData(String fileName) throws FileNotFoundException{
        String [][] slopeMap = new String[repeatSize][rowSize];
        Scanner readIn = new Scanner(new File(fileName));

        for (int j = 0; j < repeatSize; j++){
            String[] line = readIn.nextLine().split("");
            for (int i = 0; i < rowSize; i++){
                slopeMap[j][i] = line[i];
            }
        }
        return slopeMap;
    }


}
