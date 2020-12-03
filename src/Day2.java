import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Roszakc
 *
 * Advent Of Code Day 2
 */
public class Day2 {

    private static final int colMax = 1000;
    private static final int rowMax = 2;

    public static void main(String[] args) throws FileNotFoundException {
        String[][] passwordFile = readData(args[0]);
        int totalValid = 0;
        for (int i = 0; i < colMax; i++){
            totalValid += isValidPassword(passwordFile[i]);
        }

        System.out.println("num valid: "+totalValid);
    }

    /* Returns 0 on non-valid and 1 on valid
     *
     */
    private static int isValidPassword(String[] line) {
        String regex = line[0];
        String password = line[1];
        int valid = 0;
        int min = getFirst(regex);
        int max = getLast(regex);
        String character = getChar(regex);
        valid = checkPassword(min, max, character, password.split(""));

        return valid;
    }

    private static int checkPassword(int first, int last, String character, String[] password) {
        int count = 0;
        int ret = 0;
        System.out.println(password[first].equals(character) + " "+password[last].equals(character));

        if(password[first].equals(character)){
            count++;
        }
        if(password[last].equals(character)){
            count++;
        }

//        for (String letter : password.split("")){
//            if(letter.equals(character)){
//                count++;
//            }
//        }
        if(count == 1){
            ret = 1;
        }
        return ret;
    }

    private static String getChar(String regex) {
        return regex.substring(regex.length()-1);
    }

    private static int getLast(String regex) {
        return Integer.parseInt(regex.split("-")[1].split(" ")[0]);
    }

    private static int getFirst(String regex) {
        return Integer.parseInt(regex.split("-")[0]);
    }

    private static String[][] readData(String fileName) throws FileNotFoundException {
        String[][] file = new String[colMax][rowMax];
        Scanner readIn = new Scanner(new File(fileName));
        for (int i = 0; i < colMax; i++){
            String[] line = readIn.nextLine().split(":");
            file[i][0] = line[0];
            file[i][1] = line[1];
        }
        return file;
    }

}
