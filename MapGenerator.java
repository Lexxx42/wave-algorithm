
import java.util.Random;

// https://github.com/Serrjik/Maze/blob/master/generatMaze.js


class Point2I {
    public Point2I(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int distance(Point2I p1, Point2I p2) {
        int x = p1.x - p2.x;
        int y = p1.y - p2.y;
        return (int) Math.sqrt(x * x + y * y);
    }

    public static Point2I average(java.util.List<Point2I> points) {
        int x = 0;
        int y = 0;
        for (Point2I p : points) {
            x += p.x;
            y += p.y;
        }
        return new Point2I(x / points.size(), y / points.size());
    }

    public int x;
    public int y;
}


class Point3I {
    public Point3I(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point2I to_point2I() {
        return new Point2I(x, y);
    }

    public int x;
    public int y;
    public int z;
}

class Sortbyroll implements java.util.Comparator<Point3I> {
    public int compare(Point3I p1, Point3I p2) {
        return p2.z - p1.z;
    }
}


class MapGenerator {

    static int tag_wall = -1;
    static int tag_space = 0;
    static int tag_enter = 1;
    static int tag_exit = -2;


    static int tag_left = 10;
    static int tag_right = 11;
    static int tag_up = 12;
    static int tag_down = 13;

    public static void print(int columnsNumber, int rowsNumber, int[][] map) {
        for (int y = 0; y < rowsNumber; y++) {
            for (int x = 0; x < columnsNumber; x++) {
                if (map[y][x] == tag_wall)
                    System.out.print("\u2588\u2588");
                else if (map[y][x] == tag_enter)
                    System.out.print("00");
                else if (map[y][x] == tag_exit)
                    System.out.print("XX");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }


    public static int[][] generateMaze(int columnsNumber, int rowsNumber,
                                       boolean set_enter, int set_n_exits) {
        int[][] temp = generateMaze_no_boarder(columnsNumber - 2, rowsNumber - 2);
        int[][] map = wall_map(columnsNumber, rowsNumber);

        for (int y = 1; y < rowsNumber - 1; y++)
            for (int x = 1; x < columnsNumber - 1; x++)
                map[y][x] = temp[y - 1][x - 1];

        set_gateways(columnsNumber, rowsNumber, map, set_enter, set_n_exits);

        return map;
    }


    static void set_gateways(int columnsNumber, int rowsNumber, int[][] map,
                             boolean set_enter, int set_n_exits) {
        java.util.List<Point2I> traps = get_traps(columnsNumber, rowsNumber, map);
        if (traps.size() < (set_enter ? 1 : 0) + set_n_exits)
            return;

        Point2I aver_trap = Point2I.average(traps);
        java.util.List<Point3I> points = new java.util.ArrayList<>();
        for (Point2I t : traps)
            points.add(new Point3I(t.x, t.y, Point2I.distance(t, aver_trap)));

        java.util.List<Point2I> doorways = new java.util.ArrayList<>();

        points.sort(new Sortbyroll());
        if (set_enter) {
            Point2I enter = new Point2I(points.get(0).x, points.get(0).y);
            points.remove(0);
            map[enter.y][enter.x] = tag_enter;
            doorways.add(enter);
        }

        for (int n = 0; n < set_n_exits; ++n) {
            java.util.List<Point3I> temp = new java.util.ArrayList<>();
            for (Point3I p : points) {
                int d = 0;
                for (Point2I dp : doorways)
                    d += Point2I.distance(p.to_point2I(), dp);
                temp.add(new Point3I(p.x, p.y, d));
            }
            temp.sort(new Sortbyroll());
            Point2I exit = new Point2I(temp.get(0).x, temp.get(0).y);
            doorways.add(exit);
            map[exit.y][exit.x] = tag_exit;
            temp.remove(0);
            points = temp;
        }
    }


    static java.util.List<Point2I> get_traps(int columnsNumber, int rowsNumber, int[][] map) {
        java.util.List<Point2I> result = new java.util.ArrayList<>();
        for (int y = 1; y < rowsNumber - 1; y++)
            for (int x = 1; x < columnsNumber - 1; x++) {
                if (map[y][x] == tag_space) {
                    int count = 0;
                    if (map[y + 1][x] == tag_wall)
                        ++count;
                    if (map[y - 1][x] == tag_wall)
                        ++count;
                    if (map[y][x + 1] == tag_wall)
                        ++count;
                    if (map[y][x - 1] == tag_wall)
                        ++count;
                    if (count > 2)
                        result.add(new Point2I(x, y));
                }
            }
        return result;
    }


    static int[][] wall_map(int columnsNumber, int rowsNumber) {
        int[][] map = new int[rowsNumber][];
        for (int y = 0; y < rowsNumber; y++) {
            map[y] = new int[columnsNumber];
            for (int x = 0; x < columnsNumber; x++)
                map[y][x] = tag_wall;
        }
        return map;
    }

    // функция построит лабиринт (процесс построения на экране виден не будет)
    public static int[][] generateMaze_no_boarder(int columnsNumber, int rowsNumber) {

        int tractorsNumber = 3;
        int[][] map = wall_map(columnsNumber, rowsNumber);

        // Тракторы, которые будут очищать дорожки в лабиринте
        Point2I[] tractors = new Point2I[tractorsNumber];

        Random r = new Random();
        int startX = r.nextInt(columnsNumber / 2) * 2;
        int startY = r.nextInt(rowsNumber / 2) * 2;

        // создаем тракторы
        for (int i = 0; i < tractorsNumber; i++)
            tractors[i] = new Point2I(startX, startY);

        // сделаем ячейку, в которой изначально стоит трактор, пустой
        map[startY][startX] = tag_space;

        // если лабиринт ещё не готов, рисовать трактор и регистрировать функцию tick() ещё раз
        while (!isMaze(columnsNumber, rowsNumber, map))
            moveTractors(columnsNumber, rowsNumber, map, tractors);

        return map;
    }


    // функция возвращает случайный элемент из переданного ей массива
    static int getRandomFrom(int[] array) {
        // получаем случайным образом индекс элемента массива
        // число будет в диапазоне от 0 до количества элементов в массиве - 1
        Random r = new Random();
        int index = r.nextInt(array.length);
        // возвращаем элемент массива с полученным случайным индексом
        return array[index];
    }


    /*
        функция проверяет четное число или нет
        если возвращает true - четное
    */
    static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // функция проверяет, готов лабиринт или ещё нет
    // возвращает true, если лабиринт готов, false если ещё нет
    static boolean isMaze(int columnsNumber, int rowsNumber, int[][] map) {
        for (int x = 0; x < columnsNumber; x++) {
            for (int y = 0; y < rowsNumber; y++) {
                if (isEven(x) && isEven(y) && map[y][x] == tag_wall) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
        функция заставляет трактора двигаться
        трактор должен двигаться на 2 клетки
        если вторая клетка со стеной, то нужно очистить первую и вторую
    */
    static void moveTractors(int columnsNumber, int rowsNumber, int[][] map, Point2I[] tractors) {
        for (Point2I tractor : tractors) {
            // массив с возможными направлениями трактора
            java.util.List<Integer> directs = new java.util.ArrayList<>();

            if (tractor.x > 0)
                directs.add(tag_left);

            if (tractor.x < columnsNumber - 2)
                directs.add(tag_right);

            if (tractor.y > 0)
                directs.add(tag_up);

            if (tractor.y < rowsNumber - 2)
                directs.add(tag_down);

            // случайным образом выбрать направление, в котором можно пойти
            Random r = new Random();
            int index = r.nextInt(directs.size());
            int direct = directs.get(index).intValue();

            if (direct == tag_left) {
                if (map[tractor.y][tractor.x - 2] == tag_wall) {
                    map[tractor.y][tractor.x - 1] = tag_space;
                    map[tractor.y][tractor.x - 2] = tag_space;
                }
                tractor.x -= 2;
            } else if (direct == tag_right) {
                if (map[tractor.y][tractor.x + 2] == tag_wall) {
                    map[tractor.y][tractor.x + 1] = tag_space;
                    map[tractor.y][tractor.x + 2] = tag_space;
                }
                tractor.x += 2;
            } else if (direct == tag_up) {
                if (map[tractor.y - 2][tractor.x] == tag_wall) {
                    map[tractor.y - 1][tractor.x] = tag_space;
                    map[tractor.y - 2][tractor.x] = tag_space;
                }
                tractor.y -= 2;
            } else if (direct == tag_down) {
                if (map[tractor.y + 2][tractor.x] == tag_wall) {
                    map[tractor.y + 1][tractor.x] = tag_space;
                    map[tractor.y + 2][tractor.x] = tag_space;
                }
                tractor.y += 2;
            }
        }
    }


}
