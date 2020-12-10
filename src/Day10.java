import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.Collections;
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

        LinkedList<Integer> copy = (LinkedList<Integer>) numList.clone();

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

        Collections.sort(copy);
        //int len = longestAmount(copy);

        System.out.println(oneDiffCount);
        System.out.println(threeDiffCount);

        long a = (long) Math.pow(2.0, (float) oneDiffCount);
        System.out.println(a);
        long b = (long) Math.pow(7.0, threeDiffCount);
        System.out.println(b);

        System.out.println("num "+ a+b);

        return threeDiffCount * oneDiffCount;
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
