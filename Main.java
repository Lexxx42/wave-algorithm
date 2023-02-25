public class Main {
    public static void main(String[] args) {
        int[][] table = GenerateTable.getMap();
        String tableForPrint = PrintTable.getMap(table);
        PrintTable.printArray(tableForPrint);
    }
}
