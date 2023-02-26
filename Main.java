public class Main {
    public static void main(String[] args) {
        MazeMap maze = new MazeMap();
        String tableForPrint = PrintTable.getMap(maze.map);
        PrintTable.printMap(tableForPrint);

        Point2D start = new Point2D(1, 4);
        start.placeStart(maze.map);
        tableForPrint = PrintTable.getMap(maze.map);
        PrintTable.printMap(tableForPrint);

        Point2D exit = new Point2D(1, 1);
        exit.placeExit(maze.map);
        tableForPrint = PrintTable.getMap(maze.map);
        PrintTable.printMap(tableForPrint);
    }
}
