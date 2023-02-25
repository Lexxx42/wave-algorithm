public class GenerateTable {

    private GenerateTable() {
        throw new IllegalStateException("Utility class");
    }

    protected static final int[][] STATIC_MAP = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -2, 0, 0, 0, -1},
            {-1, -1, -1, -1, 0, -1},
            {-1, 0, 0, 0, 0, -1},
            {-1, 1, 0, 0, 0, -1},
            {-1, -1, -1, -1, -1, -1}};

    public static int[][] getMap() {
        return STATIC_MAP;
    }
}
