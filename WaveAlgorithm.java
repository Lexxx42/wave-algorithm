import java.util.LinkedList;
import java.util.Queue;

public class WaveAlgorithm {
    public static void colorize(int[][] map) {
        boolean broke = false;
        Queue<int[]> queue = new LinkedList<>();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == 1) {
                    queue.add(new int[]{row, col});
                    broke = true;
                    break;
                }
            }
            if (broke) {
                break;
            }
        }
        while (queue.size() > 0) {
            int[] tile = queue.remove();
            if (map[tile[0] + 1][tile[1]] == 0 || map[tile[0] + 1][tile[1]] == -2) {
                map[tile[0] + 1][tile[1]] = map[tile[0]][tile[1]] + 1;
                queue.add(new int[]{tile[0] + 1, tile[1]});
            }
            if (map[tile[0]][tile[1] + 1] == 0 || map[tile[0]][tile[1] + 1] == -2) {
                map[tile[0]][tile[1] + 1] = map[tile[0]][tile[1]] + 1;
                queue.add(new int[]{tile[0], tile[1] + 1});
            }
            if (map[tile[0] - 1][tile[1]] == 0 || map[tile[0] - 1][tile[1]] == -2) {
                map[tile[0] - 1][tile[1]] = map[tile[0]][tile[1]] + 1;
                queue.add(new int[]{tile[0] - 1, tile[1]});
            }
            if (map[tile[0]][tile[1] - 1] == 0 || map[tile[0]][tile[1] - 1] == -2) {
                map[tile[0]][tile[1] - 1] = map[tile[0]][tile[1]] + 1;
                queue.add(new int[]{tile[0], tile[1] - 1});
            }
        }
    }
}
