import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrintPretty {
    public static String printBlockMap(int[][] arr) {
        StringBuilder blocs_map_str = new StringBuilder(arr.length);
        int gradient_max = find2DMaxStream(arr);

        for (int[] row : arr) {
            for (int cell : row) {
                switch (cell) {
                    case -1:
                        blocs_map_str.append(Character.toString(0x2588));
                        break;
                    case 1:
                        blocs_map_str.append(Character.toString(0x25B2)); //00d7 tiny  cross ,25C9 - bullseye, slightly wide, 25E2 - лесенка
                        break;
                    case 0:
                        blocs_map_str.append(" ");
                        break;
                    case -2:
                        blocs_map_str.append(Character.toString(0x25BC)); // значок прицела 2316
                        break;
                    default:
                        if (cell > gradient_max * 2 / 3)
                            blocs_map_str.append(Character.toString(0x2593));
                        else if (cell > gradient_max / 3)
                            blocs_map_str.append(Character.toString(0x2592));
                        else
                            blocs_map_str.append(Character.toString(0x2591));
                        break;
                }
            }

            blocs_map_str.append("\n");
        }

        return blocs_map_str.toString();
    }

    public static void showPath(int[][] arr, String path_str) {

    }

    // статистические встроенные методы полсе JAVA 8. Работают со Stream
    public static int find2DMaxStream(int[][] arr) {
        IntSummaryStatistics stats = Stream.of(arr)
                .flatMapToInt(IntStream::of)
                .summaryStatistics();
        return stats.getMax();
    }

    public static int find2DMaxLoop(int[][] arr) {
        int min = Integer.MIN_VALUE;
        for (int[] row : arr) {
            for (int cell : row) {
                if (cell > min) {
                    min = cell;
                }
            }
        }
        return min;
    }
}
