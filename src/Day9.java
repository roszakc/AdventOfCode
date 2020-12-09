import jdk.dynalink.linker.support.DefaultInternalObjectFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {

    public static class SketchyPreamble{

        public LinkedList<Long> list;
        public long total;

        public SketchyPreamble(){
            list = new LinkedList<>();
            total = 0;
        }

        public void addNum(long numToAdd){
            total += numToAdd;
            list.add(numToAdd);
        }

        public void popNum(){
            total -= list.getFirst();
            list.removeFirst();
        }

        public long getWeaknessNum(){
            long max = Collections.max(list);
            long min = Collections.min(list);

            return max+ min;

        }

    }

    private static final int preambleNum = 25;

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Long> numbers = readData(args[0]);
        LinkedList<Long> numbersToUse = (LinkedList<Long>) numbers.clone();
        LinkedList<Long> preamble = getPreamble(numbersToUse);
        removePreamble(numbersToUse);

        long firstWrongInt = getBadData(numbersToUse, preamble);

        SketchyPreamble weakness = getWeaknessNum(firstWrongInt, numbers);

        long weaknessNum = weakness.getWeaknessNum();

        System.out.println("answer: " + weaknessNum);

    }



    private static void printList(LinkedList<Long> preamble) {
        for(Long i : preamble){
            System.out.println(i);
        }
    }

    private static SketchyPreamble getWeaknessNum(long firstWrongInt, LinkedList<Long> numbers) {
        SketchyPreamble sketch = new SketchyPreamble();
        sketch.addNum(numbers.get(0));
        sketch.addNum(numbers.get(1));
        int curIndex = 2;


        while(curIndex < numbers.size() && sketch.total != firstWrongInt){
            if(sketch.total > firstWrongInt){
                sketch.popNum();
            } else {
                sketch.addNum(numbers.get(curIndex));
                curIndex++;
            }
        }
        return sketch;
    }

    private static long getBadData(LinkedList<Long> numbers, LinkedList<Long> preamble) {
        long curNum = numbers.get(0);
        int diffIndex = 0;
        long foundNum = 0;
        int j = 0;
        long badNumber = -1;

        while(badNumber != curNum){
            long difference  = curNum - preamble.get(j);

            while(j < preambleNum && foundNum != difference){
                difference  = curNum - preamble.get(j);
                LinkedList<Long> sortedPreamble = (LinkedList<Long>) preamble.clone();
                Collections.sort(sortedPreamble);

                diffIndex = binarySearch(sortedPreamble, difference);
                if(diffIndex != -1){
                    // found a valid number
                    foundNum = sortedPreamble.get(diffIndex);
                    preamble.removeFirst();
                    preamble.add(numbers.getFirst());
                    numbers.removeFirst();
                }
                j++;
            }
            curNum = numbers.getFirst();
            if(j == preambleNum){
                //no valid two found
                badNumber = curNum;
            }
            foundNum = 0;
            j = 0;
        }

        return badNumber;
    }

    private static int binarySearch(LinkedList<Long> arr, long x)
    {
        int l = 0, r = arr.size()-1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr.get(m) == x)
                return m;
            if (arr.get(m) < x)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    private static LinkedList<Long> getPreamble(LinkedList<Long> numbers) {
        LinkedList<Long> preamble = new LinkedList<>();
        for(int i = 0; i < preambleNum; i++){
            preamble.add(numbers.get(i));
        }
        return preamble;
    }
    private static LinkedList<Long> removePreamble(LinkedList<Long> numbers) {
        for(int i = 0; i < preambleNum; i++){
            numbers.removeFirst();
        }
        return numbers;

    }


    private static LinkedList<Long> readData(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        LinkedList<Long> numList = new LinkedList<>();
        while (sc.hasNextLine()){
            numList.add(Long.parseLong(sc.nextLine()));
        }
        return numList;
    }


}
