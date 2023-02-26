//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.Queue;
//
//public class Sergey {
//    public static void main(String[] args) {
//
//        var mg = new MapGenerator();
//
//        System.out.println(
//                new MapPrinter().rawData(
//                        mg.getMap())
//
//        );
//
//        var lee = new WaveAlgorithm(mg.getMap());
//
//        lee.Colorize(new Point2D(3, 3));
//
//        System.out.println(
//                new MapPrinter().rawData(
//                        mg.getMap())
//
//        );
//        // lee.getRoad(new Point2D(10, 10), new Point2D(10, 20), new Point2D(10, 30));
//
//    }
//}
//
//class Point2D {
//    int x, y;
//
//    public Point2D(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("x: %d  y: %d", x, y);
//    }
//}
//
//class MapGenerator {
//    int[][] map;
//
//    public MapGenerator() {
//        int[][] map = {
//                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
//                { -1, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, 00, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, -1, 00, 00, -1, 00, 00, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, -1, 00, -1, -1, -1, -1, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, -1 },
//                { -1, -1, -1, 00, -1, 00, -1, 00, 00, -1, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, -1, 00, -1, 00, 00, -1, -1, -1, 00, 00, -1 },
//                { -1, 00, 00, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, -1, 00, 00, 00, 00, -1, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, -1, -1, -1, -1, -1, -1, -1, 00, 00, 00, -1 },
//                { -1, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, -1 },
//                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
//        };
//
//        this.map = map;
//    }
//
//    public int[][] getMap() {
//        return map;
//    }
//
//    public void setCat(Point2D pos) {
//        map[pos.x][pos.y] = -2;
//    }
//
//    public void setExit(Point2D pos) {
//        map[pos.x][pos.y] = -3;
//    }
//}
//
//class MapPrinter {
//
//    public MapPrinter() {
//    }
//
//    public String rawData(int[][] map) {
//        StringBuilder sb = new StringBuilder();
//
//        for (int row = 0; row < map.length; row++) {
//            for (int col = 0; col < map[row].length; col++) {
//                sb.append(String.format("%5d", map[row][col]));
//            }
//            sb.append("\n");
//        }
//        for (int i = 0; i < 3; i++) {
//            sb.append("\n");
//        }
//
//        return sb.toString();
//    }
//
//    public String mapColor(int[][] map) {
//        StringBuilder sb = new StringBuilder();
//
//        for (int row = 0; row < map.length; row++) {
//            for (int col = 0; col < map[row].length; col++) {
//                switch (map[row][col]) {
//                    case 0:
//                        sb.append("в–‘");
//                        break;
//                    case -1:
//                        sb.append("в–“");
//                        break;
//                    case -2:
//                        sb.append("Рљ");
//                        break;
//                    case -3:
//                        sb.append("E");
//                        break;
//                    default:
//                        break;
//                }
//            }
//            sb.append("\n");
//        }
//        for (int i = 0; i < 3; i++) {
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//}
//
//class WaveAlgorithm {
//    int[][] map;
//
//    public WaveAlgorithm(int[][] map) {
//        this.map = map;
//    }
//
//    public void Colorize(Point2D startPoint) {
//        Queue<Point2D> queue = new LinkedList<Point2D>();
//        queue.add(startPoint);
//        map[startPoint.x][startPoint.y] = 1;
//
//        while (queue.size() != 0) {
//            Point2D p = queue.remove();
//
//            if (map[p.x - 1][p.y] == 0) {
//                queue.add(new Point2D(p.x - 1, p.y));
//                map[p.x - 1][p.y] = map[p.x][p.y] + 1;
//            }
//            if (map[p.x][p.y - 1] == 0) {
//                queue.add(new Point2D(p.x, p.y - 1));
//                map[p.x][p.y - 1] = map[p.x][p.y] + 1;
//            }
//            if (map[p.x + 1][p.y] == 0) {
//                queue.add(new Point2D(p.x + 1, p.y));
//                map[p.x + 1][p.y] = map[p.x][p.y] + 1;
//            }
//            if (map[p.x][p.y + 1] == 0) {
//                queue.add(new Point2D(p.x, p.y + 1));
//                map[p.x][p.y + 1] = map[p.x][p.y] + 1;
//            }
//        }
//    }
//
//    public ArrayList<Point2D> getRoad(Point2D exit) {
//        ArrayList<Point2D> road = new ArrayList<>();
//        ///
//        return road;
//    }
//}