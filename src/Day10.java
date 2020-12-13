import com.sun.security.jgss.GSSUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Day10 {


    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Integer> numbers = readData(args[0]);
        numbers.push(0);
        numbers.add(numbers.getLast()+3);

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
        Collections.sort(numList);
        LinkedList<Integer> copyList = (LinkedList<Integer>) numList.clone();

        while(!numList.isEmpty()){
            int isSmallestDiffIndex = getJoltDiff(curJolt, numList);
            int curDiff = numList.get(isSmallestDiffIndex) - curJolt;
//            switch (curDiff) {
//                case 1 -> oneDiffCount++;
//                case 2 -> twoDiffCount++;
//                case 3 -> threeDiffCount++;
//            }
            curJolt = numList.get(isSmallestDiffIndex);
            numList.remove(isSmallestDiffIndex);
        }
        threeDiffCount++;

        int[] seqCounts = getConsectutiveLen(copyList);
        long maxPerms = (long) ((Math.pow(2, seqCounts[3]) * Math.pow(4,seqCounts[4])* Math.pow(7,seqCounts[5])));
        System.out.println("max perms: "+maxPerms);

        return threeDiffCount * oneDiffCount;
    }

    private static int[] getConsectutiveLen(LinkedList<Integer> copy) {
        int[] seqCountArray = new int[6];
        int count = 1;
        int index = 0;
        while (index+1 < copy.size()) {

            int cur = copy.get(index);
            int next = copy.get(index + 1);

            if (next - cur == 1) {
                count++;
            } else {
                seqCountArray[count]++;
                count = 1;
            }
            index++;
        }

        return seqCountArray;

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
