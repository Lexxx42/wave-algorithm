import java.awt.*;

public class printPretty {

    public static void main(String[] args) {
        int[][] testMaze = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -2, 30, 25, 20, -1},
                {-1, -1, -1, -1, 15, -1},
                {-1, 2, 3, 11, 12, -1},
                {-1, 1, 2, 3, 11, -1},
                {-1, -1, -1, -1, -1, -1}};

//        System.out.println(PrintTable.getMap(GenerateTable.STATIC_MAP));
//        PrintTable.printMap(PrintTable.getMap(GenerateTable.STATIC_MAP));
        System.out.println(printBlockMap(testMaze));
    }

    public static String printBlockMap(int[][] arr){
        StringBuilder blocs_map_str = new StringBuilder(arr.length);

        for (int[] row:arr){
            for (int cell: row){
                switch (cell){
                    case -1:
                        blocs_map_str.append(Character.toString(0x2588));
                        break;
                    case 1:
                        blocs_map_str.append(Character.toString(0x25E2)); //00d7 tiny  cross ,25C9 - bullseye, slightly wide
                        break;
                    case 0:
                        blocs_map_str.append(" ");
                        break;
                    case -2:
                        blocs_map_str.append(Character.toString(0x25BC)); // значок прицела 2316
                    default:
                        break;
                }
            }
            blocs_map_str.append("\n");
            // System.out.println(Arrays.toString(row));
            // System.out.println(blocs_map_str);
        }

        return blocs_map_str.toString();
    }

    public static void showPath(int[][] arr, String path_str){

    }
}
