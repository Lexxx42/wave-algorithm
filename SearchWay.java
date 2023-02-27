public class SearchWay {
    public static String SearchWays(int[][] arr) {
        StringBuilder str = new StringBuilder();
        int x = 0;
        int y = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == -2) {
                    x = i;
                    y = j;
                    str.append(x + ":" + y + ",");
                }
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }

            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                while (max > 0) {
                    if (x > 0 && y > 0) {
                        if (arr[x + 1][y] == max) {
                            x = x + 1;
                            str.append(x + ":" + y + ",");
                        }
                    }
                    if (x > 0 && y > 0) {
                        if (arr[x - 1][y] == max) {
                            x = x - 1;
                            str.append(x + ":" + y + ",");
                        }
                    }
                    if (x > 0 && y > 0) {
                        if (arr[x][y + 1] == max) {
                            y = y + 1;
                            str.append(x + ":" + y + ",");
                        }
                    }
                    if (x > 0 && y > 0) {
                        if (arr[x][y - 1] == max) {
                            y = y - 1;
                            str.append(x + ":" + y + ",");
                        }
                    }
                    max--;
                }

            }
        }
        return str.reverse().deleteCharAt(0).toString();
    }
}



