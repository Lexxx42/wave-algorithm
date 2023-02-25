import java.util.Arrays;

public class printPretty {

    public static void main(String[] args) {

        System.out.println(PrintTable.getMap(GenerateTable.STATIC_MAP));
        PrintTable.printMap(PrintTable.getMap(GenerateTable.STATIC_MAP));
    }

    public static String printBlockMap(int[][] arr){
        StringBuilder blocs_str = new StringBuilder(arr.length);

        for (int[] row:arr){
            for (int cell: row){
                switch (cell){
                    case -1:

                }


            }
            System.out.println(row);
        }

        return blocs_str.toString();
    }

    public static void showPath(int[][] arr, String path_str){

    }
}
