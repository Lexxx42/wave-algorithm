public class Main {
    public static void main(String[] args) {
        MazeMap maze = new MazeMap();
        String tableForPrint = PrintTable.getMap(maze.map);
        PrintTable.printMap(tableForPrint);
    }
}
