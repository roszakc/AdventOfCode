import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Day7 {

    public static class Bag{
        public String color;
        public LinkedList<Bag> adj;

        public Bag(String colorName){
            color = colorName;
            adj = new LinkedList<>();
        }

        public void addAdj(Bag b){
            adj.add(b);
        }

    }

    public static class BagGraph{
        public int size;
        public LinkedList<Bag> masterList;

        public BagGraph(){
            size = 0;
            masterList = new LinkedList<>();
        }

        public void addNode(Bag b){
            if(!masterList.contains(b)){
                masterList.add(b);
                size++;
            }
        }

        public Bag getBag(String name){
            for(Bag b : masterList){
                if(b.color.equals(name)){
                    return b;
                }
            }
            return null;
        }

        public Bag popBag(){
            size--;
            return masterList.poll();
        }

        public void print(){
            for (Bag b : this.masterList){
                System.out.println(b.color);
            }
        }

    }

    private static final int maxRules = 594;
    private static final String goldBag = "shiny gold";

    public static void main(String[] args) throws FileNotFoundException {
        String[] ruleList = getRules(args[0]);
        BagGraph masterBagList = getBagGraph(ruleList);
        masterBagList.print();
        LinkedList<Bag> graph = createGraph(masterBagList, ruleList);
        int findGoldBags = findGoldBag(graph, masterBagList.getBag(goldBag));

        System.out.println(findGoldBags);
    }

    private static int findGoldBag(LinkedList<Bag> graph, Bag goldBag) {
        int count = 0;
        LinkedList<Bag> queue = new LinkedList<>();

        return count;
    }



    private static LinkedList<Bag> createGraph(BagGraph masterBagList, String[] ruleList) {
        LinkedList<Bag> graph = new LinkedList<>();
        for (String rule : ruleList){
            String[] bag = rule.split("bags contain");
            Bag newBag = new Bag(bag[0]);
            if(!bag[1].contains("no other")){
                for(int i = 1; i < bag.length; i++){
                    String[] smallBags = bag[i].split(",");
//            -- add adj list
                    for (String singleSmallBag : smallBags){
                        int numBag = Integer.parseInt(singleSmallBag.split(" ")[1]);
                        for(int j = 0; j < numBag; j++){
                            newBag.addAdj(masterBagList.getBag(getSubstring(singleSmallBag)));
                        }
                    }

                }
            }
            graph.add(newBag);
        }
        return graph;
    }

    private static BagGraph getBagGraph(String[] ruleList) {
        BagGraph bagGraph = new BagGraph();
        for (String rule : ruleList){
            String[] bag = rule.split("bags contain");
            Bag newBag = new Bag(bag[0]);
            bagGraph.addNode(newBag);
        }

        return bagGraph;
    }

    private static String getSubstring(String singleSmallBag) {
        StringBuilder sb = new StringBuilder();
        String[] first = singleSmallBag.substring(1).split(" ");
        sb.append(first[1]);
        sb.append(" ");
        sb.append(first[2]);
        return sb.toString();
    }


    private static String[] getRules(String fileName) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(fileName));
        String[] rules = new String[9];
        int i = 0;
        while (sc.hasNextLine()) {
            rules[i] = sc.nextLine();
            i++;
        }
        return rules;
    }

}
