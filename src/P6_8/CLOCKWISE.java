package P6_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CLOCKWISE {
    static int[][] switchArr = {{0, 1, 2}, {3, 7, 9, 11}, {4, 10, 14, 15}
                                , {0, 4, 5, 6, 7}, {6, 7, 8, 10, 12}, {0, 2, 14, 15}
                                , {3, 14, 15}, {4, 5, 7, 14, 15}, {1, 2, 3, 4, 5}, {3, 4, 5, 9, 13}};
    static int[] clockArr;
    static int[] switchClickArr;
    static int clockTotal = 16;
    static int switchTotal = 10;
    static int INF = 9999;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;

        int C = Integer.parseInt(bufferedReader.readLine());
        for (int c = 0; c < C; c++) {
            clockArr = new int[clockTotal];
            switchClickArr = new int[switchTotal];
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int i = 0; i < clockTotal; i++) {
                clockArr[i] = Integer.parseInt(stringTokenizer.nextToken());
            }

            System.out.println(minSwitchClick(0));
        }
    }

    static boolean clockAll12() {
        for (int i = 0; i < clockTotal; i++) {
            if (clockArr[i] != 12) {
                return false;
            }
        }
        return true;
    }

    static int minSwitchClick(int switchIndex) {
        int ret = INF;
        if (switchIndex == switchTotal) {
            if (clockAll12()) return 0;
            return INF;
        }

        for (int i = 0; i < 4; i++) {
            ret = Math.min(ret, i + minSwitchClick(switchIndex + 1));
            for (int j = 0; j < switchArr[switchIndex].length; j++) {
                int clockIndex = switchArr[switchIndex][j];
                clockArr[clockIndex] = (clockArr[clockIndex] == 12 ? clockArr[clockIndex] = 3 : clockArr[clockIndex] + 3);
            }
        }
        return ret;
    }

}
