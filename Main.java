public class Main {
    public static void main(String[] args) {
        //int[][] table = GenerateTable.getMap();  // Static Map
        int[][] table = MapGenerator.generateMaze(12, 12);  // Generated Map
        String tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);

        WaveAlgorithm.colorize(table);
        tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);

        System.out.println(PrintPretty.printBlockMap(table));
        String testPath = "1:4,1:3,2:3,3:3,4:3,4:2,4:1,3:1,2:1,1:1";
        int[][] testMaze = PrintPretty.showPath(table, testPath);
        System.out.println(PrintPretty.printBlockMap(testMaze));
    }
}
