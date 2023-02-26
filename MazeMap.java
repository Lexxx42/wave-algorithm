public class MazeMap {
    public int[][] map;
    private int[][] STATIC_MAP = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -2, 0, 0, 0, -1},
            {-1, -1, -1, -1, 0, -1},
            {-1, 0, 0, 0, 0, -1},
            {-1, 1, 0, 0, 0, -1},
            {-1, -1, -1, -1, -1, -1}};

    public MazeMap() {
        map = STATIC_MAP;
    }
}
