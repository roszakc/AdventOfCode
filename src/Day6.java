import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String> planeAnswers = readInput(args[0]);
        int sumAnswers = sumAnswers(planeAnswers);
        System.out.println(sumAnswers);
    }

    private static int sumAnswers(LinkedList<String> planeAnswers) {
        int sum = 0;
        for (String s : planeAnswers){
            sum += s.length();
        }
        return sum;
    }

    private static LinkedList<String> readInput(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));
        LinkedList<String> answers = new LinkedList<>();

        while(reader.hasNextLine()){
            String line = reader.nextLine();
            String strToAdd = line;
            if(reader.hasNextLine()){
                line = reader.nextLine();
                while (!line.equals("")){
                    strToAdd = union(strToAdd, line);
//                String[] lineSp = line.split("");
//                for (String letter : lineSp){
//                    if(!stringB.toString().contains(letter)){
//                        stringB.append(letter);
//                    }
//                }
                    if(reader.hasNextLine()){
                        line = reader.nextLine();
                    } else{
                        line = "";
                    }
                }
            }

            answers.add(strToAdd);
        }
        return answers;
    }

    private static String union(String s1, String s2){
        StringBuilder sb = new StringBuilder();
        for(String letter2 : s2.split("")){
            for (String letter1 : s1.split("")){
                if(letter2.equals(letter1)){
                    sb.append(letter2);
                }
            }
        }
        return sb.toString();
    }

}
