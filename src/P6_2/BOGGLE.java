package P6_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOGGLE {
    static final int[] y_arr = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static final int[] x_arr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static char[][] gamePanel;
    static char[] wordArr;
    static int wordLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st;

        String C = br.readLine();
        int tcIdx = 0;
        for (; tcIdx < Integer.parseInt(C); tcIdx++) {
            gamePanel = new char[5][];
            for (int i = 0; i < 5; i++) {
                gamePanel[i] = br.readLine().toCharArray();
            }

            int N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                wordArr = word.toCharArray();
                wordLength = word.length();
                boolean flag = false;

                for (int y = 0; y < 5; y++) {
                    for (int x = 0; x < 5; x++) {
                        if (gamePanel[y][x] == wordArr[0]) {
                            flag = hasWord(y, x, 1);
                            if (flag) break;
                        }
                    }
                    if (flag) break;
                }
                System.out.println(word + " " + (flag ? "YES" : "NO"));
            }
        }
    }

    static boolean hasWord(int y, int x, int count) {
        boolean retValue = false;
        if (count == wordLength) {
            retValue = true;
        }
        else {
            for (int i = 0; i < 9; i++) {
                int ny = y + y_arr[i], nx = x + x_arr[i];
                if (0 <= ny && ny < 5 && 0 <= nx && nx < 5) {
                    if (gamePanel[ny][nx] == wordArr[count]) {
                        retValue = hasWord(ny, nx, count + 1);
                        if (retValue) return true;
                    }
                }
            }
        }
        return retValue;
    }
}
