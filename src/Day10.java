import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day10 {


    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Integer> numbers = readData(args[0]);

        int getJoltages = getJoltage(numbers);

        System.out.println("answer: "+getJoltages);


    }

    private static LinkedList<Integer> readData(String fileName) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(fileName));
        LinkedList<Integer> numList = new LinkedList<>();
        while (sc.hasNextLine()){
            numList.add(Integer.parseInt(sc.nextLine()));
        }
        return numList;
    }

    private static int getJoltage(LinkedList<Integer> numList){
        int oneDiffCount = 0;
        int twoDiffCount = 0;
        int threeDiffCount = 0;
        int curJolt = 0;

        while(!numList.isEmpty()){
            int isSmallestDiffIndex = getJoltDiff(curJolt, numList);
            int curDiff = numList.get(isSmallestDiffIndex) - curJolt;
            switch (curDiff) {
                case 1 -> oneDiffCount++;
                case 2 -> twoDiffCount++;
                case 3 -> threeDiffCount++;
            }
            curJolt = numList.get(isSmallestDiffIndex);
            numList.remove(isSmallestDiffIndex);
        }
        threeDiffCount++;

        return oneDiffCount * threeDiffCount;
    }

    private static int getJoltDiff(int curJolt, LinkedList<Integer> numList){
        int i = 0;
        int curMin = Integer.MAX_VALUE;
        int index = -1;

        while(i < numList.size()){
            if(numList.get(i) - curJolt <= 3 && numList.get(i) - curJolt < curMin){
                curMin = numList.get(i) - curJolt;
                index = i;

            }
            i++;
        }

        return index;
    }




}
