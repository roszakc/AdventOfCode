import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day1 {

    static final int reportSize = 200;

    public static void main(String[] args) throws FileNotFoundException {
        int[] expenseReport = readData(args[0]);
        Arrays.sort(expenseReport);
        int number = getExpenseNumbers(expenseReport);

        System.out.println(number);
    }

    /* Function uses linear and binary search to find numbers
     *
     */
    private static int getExpenseNumbers(int[] expenseReport) {
        int firstNum = 0;
        int secondNum = 0;
        int total = 0;
        int neededDiff = 0;
        int i = 0, j = 1;

        while(total != 2020){
            firstNum = expenseReport[i];
            while(j < reportSize && total != 2020){
                secondNum = expenseReport[j];
                int difference = 2020-(firstNum+secondNum);
                neededDiff = binarySearch(expenseReport, difference);

                if(neededDiff != -1){
                    total = firstNum + secondNum + expenseReport[neededDiff];
                }
                j++;
            }
            i++;
            j = i+1;
        }
        return firstNum * secondNum * expenseReport[neededDiff];

    }

    private static int binarySearch(int[] arr, int x)
    {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x)
                return m;
            if (arr[m] < x)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    private static int[] readData(String fileName) throws FileNotFoundException {
        Scanner readIn = new Scanner(new File(fileName));
        int i = 0;
        int[] data = new int[reportSize];
        while(readIn.hasNextLine()){
            data[i] = Integer.parseInt(readIn.nextLine());
            i++;
        }

        return data;
    }

}
