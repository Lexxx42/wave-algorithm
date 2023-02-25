public class Main {
    public static void main(String[] args) {
        int[][] table = GenerateTable.getMap();
        String tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);
        WaveAlgorithm.colorize(table);
        tableForPrint = PrintTable.getMap(table);
        PrintTable.printMap(tableForPrint);
    }
}
