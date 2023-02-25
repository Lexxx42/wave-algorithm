public class PrintTable {
    private PrintTable() {
        throw new IllegalStateException("Utility class");
    }

    public static String getMap(int[][] map) {
        //return Arrays.deepToString(map);
        StringBuilder result = new StringBuilder();
        result.append('\n');
        for (int[] arr : map) {
            for (int element : arr) {
                result.append('\t');
                result.append(element);
            }
            result.append('\n');
        }
        return result.toString();
    }

    public static void printMap(String array) {
        MyLogger.writeLog(array);
    }
}
