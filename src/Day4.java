import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day4 {

    public static class Passport{
        public String byr;
        public String iyr;
        public String eyr;
        public String hgt;
        public String hcl;
        public String ecl;
        public String pid;
        public String cid;
        public int fieldsSet;

        Passport (){
            this.fieldsSet = 0;
        }
    }

    private static final int birthMin = 1920;
    private static final int birthMax = 2002;
    private static final int issueMin = 2010;
    private static final int issueMax = 2020;
    private static final int expMin = 2020;
    private static final int expMax = 2030;

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Passport> passportFile = readData(args[0]);
        int validPassports = getValidPassports(passportFile);

        System.out.println(validPassports);
    }

    private static int getValidPassports(LinkedList<Passport> passportFile) {
        int total = 0;
        for (Passport passport : passportFile){
            if(passport.fieldsSet == 7){
                total++;
            }
        }
        return total;
    }

    private static LinkedList<Passport> readData(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));
        LinkedList<Passport> passportList = new LinkedList<>();
        Passport newPass;

        while(reader.hasNextLine()){
            String line = reader.nextLine();
            newPass = new Passport();
            while (!line.equals("")){
                String[] lineSp = line.split(" ");
                for (String word: lineSp){
                    readInput(newPass, word);
                }
                if(reader.hasNextLine()){
                    line = reader.nextLine();
                } else{
                    line = "";
                }
            }
            passportList.add(newPass);
        }
        return passportList;

    }

    private static void readInput(Passport newPass, String word) {

        String[] wordSp = word.split(":");
//        switch (wordSp[0]) {
//            case "byr" -> {
//                newPass.byr = wordSp[1];
//                if(isValidInt(word, birthMin, birthMax)){
//                    newPass.fieldsSet++;
//                }
//            }
//            case "iyr" -> {
//                newPass.iyr = wordSp[1];
//                if(isValidInt(word, issueMin, issueMax)){
//                    newPass.fieldsSet++;
//                }
//            }
//            case "eyr" -> {
//                newPass.eyr = wordSp[1];
//                if(isValidInt(word, expMin, expMax)){
//                    newPass.fieldsSet++;
//                }
//            }
//            case "hgt" -> {
//                newPass.hgt = wordSp[1];
//                if(wordSp[1].matches("^(1([5-8][0-9]|9[0-3])cm)|(59|6[0-9]|7[0-6])in$")) {
//                    newPass.fieldsSet++;
//                }
//            }
//            case "hcl" -> {
//                newPass.hcl = wordSp[1];
//                if(wordSp[1].matches("^#(\\d|[abcdef]){6}$")){
//                    newPass.fieldsSet++;
//                }
//            }
//            case "ecl" -> {
//                newPass.ecl = wordSp[1];
//                if(wordSp[1].matches("^(amb|blu|brn|gry|grn|hzl|oth)$")){
//                    newPass.fieldsSet++;
//                }
//            }
//            case "pid" -> {
//                newPass.pid = wordSp[1];
//                if(wordSp[1].matches("^\\d{9}$")){
//                    newPass.fieldsSet++;
//                }
//            }
//            case "cid" -> {
//                newPass.cid = wordSp[1];
//            }
//        }

    }

    private static boolean isValidInt(String word, int min, int max) {
        int year = Integer.parseInt(word.split(":")[1]);
        boolean ret = false;
        if(year >= min && year <= max){
            ret = true;
        }
        return ret;
    }


}
