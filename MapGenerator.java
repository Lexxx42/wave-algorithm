
import java.util.Random;

// https://github.com/Serrjik/Maze/blob/master/generatMaze.js


class Point2I {
	public Point2I(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int x;
	public int y;
}



public class MapGenerator {
    
	private static int tag_wall = -1;
	private static int tag_space = 0;
	private static int tag_enter = 1;
	private static int tag_exit = -2;


	private static int tag_left  = 10;
	private static int tag_right = 11;
	private static int tag_up    = 12;
	private static int tag_down  = 13;


    /*
	public static void print(int columnsNumber, int rowsNumber, int[][] map) {
		for (int y = 0; y < rowsNumber; y++) {
			for (int x = 0; x < columnsNumber; x++) {
				if(map[y][x] == tag_wall)
					System.out.print("\u2588\u2588");
				else
					System.out.print("  ");		
			}
			System.out.println();
		}
	}
    */

	public static int[][] generateMaze (int columnsNumber, int rowsNumber) {
		int nx = columnsNumber % 2 != 0 ? columnsNumber-2: columnsNumber-2;
		int ny = rowsNumber % 2 != 0 ? rowsNumber-2: rowsNumber-2;
		int[][] temp = generateMaze_no_boarder(nx, ny);

		//MapGenerator.print(nx, ny, temp);
		//System.out.println();

		int[][] map = wall_map(columnsNumber, rowsNumber);

		for (int y = 1; y < rowsNumber - 1; y++)
			for (int x = 1; x < columnsNumber - 1; x++)
				map[y][x] = temp[y-1][x-1];

		return map;
	}

	static int[][] wall_map(int columnsNumber, int rowsNumber)
	{
		int[][] map = new int[rowsNumber][];
		for (int y = 0; y < rowsNumber; y++) {
			map[y] = new int[columnsNumber];
			for (int x = 0; x < columnsNumber; x++)
				map[y][x] = tag_wall;
		}
		return map;
	}

    
    // функция построит лабиринт (процесс построения на экране виден не будет)
	static int[][] generateMaze_no_boarder (int columnsNumber, int rowsNumber) {

		int tractorsNumber = 3;
		int[][] map = wall_map(columnsNumber, rowsNumber);

		// Тракторы, которые будут очищать дорожки в лабиринте
		Point2I[] tractors = new Point2I[tractorsNumber];

		Random r = new Random();
		int startX = r.nextInt(columnsNumber / 2) * 2;
		int startY = r.nextInt(rowsNumber    / 2) * 2;

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
	static int getRandomFrom (int [] array) {
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
	static boolean isEven (int n) {
		return n % 2 == 0;
	}

	// функция проверяет, готов лабиринт или ещё нет
	// возвращает true, если лабиринт готов, false если ещё нет
	static boolean isMaze (int columnsNumber, int rowsNumber, int[][] map) {
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
	static void moveTractors (int columnsNumber, int rowsNumber, int[][] map, Point2I[] tractors) {
		for (Point2I tractor:  tractors) {
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

			if(direct == tag_left) {
				if (map[tractor.y][tractor.x - 2] == tag_wall) {
					map[tractor.y][tractor.x - 1] = tag_space;
					map[tractor.y][tractor.x - 2] = tag_space;
				}
				tractor.x -= 2;
			}
			else if(direct == tag_right) {
				if (map[tractor.y][tractor.x + 2] == tag_wall) {
					map[tractor.y][tractor.x + 1] = tag_space;
					map[tractor.y][tractor.x + 2] = tag_space;
				}
				tractor.x += 2;
			}
			else if(direct == tag_up) {
				if (map[tractor.y - 2][tractor.x] == tag_wall) {
					map[tractor.y - 1][tractor.x] = tag_space;
					map[tractor.y - 2][tractor.x] = tag_space;
				}
				tractor.y -= 2;
			}
			else if(direct == tag_down) {
				if (map[tractor.y + 2][tractor.x] == tag_wall) {
					map[tractor.y + 1][tractor.x] = tag_space;
					map[tractor.y + 2][tractor.x] = tag_space;
				}
				tractor.y += 2;
			}
		}
	}


}
