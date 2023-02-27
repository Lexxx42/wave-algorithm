public class Main {
    public static void main(String[] args) {
        //int[][] table = GenerateTable.getMap();  // Static Map
        int[][] table = MapGenerator.generateMaze(13, 13);  // Generated Map
        table[1][1] = 1;
        table[9][9] = -2;
        String tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);

        WaveAlgorithm.colorize(table);
        tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);

        System.out.println(PrintPretty.printBlockMap(table));
        //String a = "1:4,1:3,2:3,3:3,4:3,4:2,4:1,3:1,2:1,1:1";
        String testPath = SearchWay.SearchWays(table);
        //System.out.println(a);
        System.out.println(testPath);


        int[][] testMaze1 = PrintPretty.showPath(table, testPath);
        System.out.println(PrintPretty.printBlockMap(testMaze1));
        //int[][] testMaze2 = PrintPretty.showPath(table, a);
        //System.out.println(PrintPretty.printBlockMap(testMaze2));
    }
}
