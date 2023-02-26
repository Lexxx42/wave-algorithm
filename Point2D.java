public class Point2D {
    int x, y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public void placeStart(int[][] map) {
        map[x,y] = 1;
    }
}
