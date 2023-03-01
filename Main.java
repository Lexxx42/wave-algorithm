public class Main {
    public static void main(String[] args) {
        int[][] table = MapGenerator.generateMaze(13, 13, true, 1);  // Generated Map
        String tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);

        WaveAlgorithm.colorize(table);
        tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);

        System.out.println(PrintPretty.printBlockMap(table));
        String testPath = SearchWay.SearchWays(table);
        System.out.println(testPath);

        int[][] testMaze1 = PrintPretty.showPath(table, testPath);
        System.out.println(PrintPretty.printBlockMap(testMaze1));
    }
}
