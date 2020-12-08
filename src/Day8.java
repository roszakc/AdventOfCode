import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Day8 {

    public static class Instruction{
        public String instructionName;
        public int number;
        public boolean hasRun;

        public Instruction(String name){
            instructionName = name;
            number = 0;
            hasRun = false;
        }

        public void setNumber(int num){
            number = num;
        }

        public void hasUsed(){
            this.hasRun = true;
        }

    }

    private static final int maxInstr = 644;

    public static void main(String[] args) throws FileNotFoundException {
        String[] instructions = readData(args[0]);
        ArrayList<Instruction> instructionsList = getInstructionList(instructions);
        int accumNum = runProgram(instructionsList);

        System.out.println("done " +accumNum);

    }

    private static int runProgram(ArrayList<Instruction> instructionsList) {
        int accumulator = 0;
        boolean breakLoop;
        int cmdCount = 0;
        int nopCount = 0;

        for(int i = 0; i < 74; i++){
            System.out.println(i);
            breakLoop = false;
            accumulator = 0;
            int jmpCount = 0;
            resetData(instructionsList);
            Instruction curInstruction = instructionsList.get(0);

            while(!curInstruction.hasRun && !breakLoop){
                int next = instructionsList.indexOf(curInstruction);
                cmdCount++;
                switch (curInstruction.instructionName) {
                    case "nop" -> {
                        nopCount++;
                        next++;
                    }
                    case "jmp" -> {
                        if(jmpCount != i){
                            next += curInstruction.number;
                        } else {
                            next++;
                        }

                        jmpCount++;
                    }
                    case "acc" -> {
                        accumulator += curInstruction.number;
                        next++;
                    }
                }
                curInstruction.hasRun = true;

                if(next >= instructionsList.size()){
                    breakLoop = true;
                } else {
                    curInstruction = instructionsList.get(next);
                }

                if(curInstruction.hasRun){
//                    System.out.println(cmdCount);
//                    System.out.println(nopCount);
//                    System.out.println(jmpCount);
                    System.out.println("oopsies");
                }
            }

            if(breakLoop){
                System.out.println("BROKEN");
                break;
            }

        }


        return accumulator;
    }

    private static void resetData(ArrayList<Instruction> instructionsList) {
        for(Instruction i : instructionsList){
            i.hasRun = false;
        }
    }

    private static ArrayList<Instruction> getInstructionList(String[] instructions) {
        ArrayList<Instruction> retList = new ArrayList<>();
        for(String singleInstr : instructions){
            String[] structSp = singleInstr.split(" ");
            Instruction newStruct = new Instruction(structSp[0]);
            newStruct.setNumber(Integer.parseInt(structSp[1]));
            retList.add(newStruct);
        }
        return retList;
    }

    private static String[] readData(String fileName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fileName));
        String[] retInstr = new String[maxInstr];
        int i = 0;

        while (reader.hasNextLine()){
            retInstr[i] = reader.nextLine();
            i++;
        }

        return retInstr;
    }


}
