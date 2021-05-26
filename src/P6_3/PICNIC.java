package P6_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PICNIC {
    static int n;
    static boolean[][] friends;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int m, y, x;

        String C = br.readLine();
        for (int tcIdx = 0; tcIdx < Integer.parseInt(C); tcIdx++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            friends = new boolean[n][n];
            st = new StringTokenizer(br.readLine());

            for (int mIdx = 0; mIdx < m; mIdx++) {
                y = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                friends[y][x] = true;
            }
            System.out.println(countPair(new boolean[n], 0));
        }

    }

    static int countPair(boolean[] taken, int firstIndex) {
        boolean finished = true;
        for (int i = 0; i < n; i++) if (!taken[i]) finished = false;
        if (finished) return 1;

        int ret = 0;
        for (int i = firstIndex; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!taken[i] && !taken[j] && friends[i][j]) {
                    taken[i] = taken[j] = true;
                    ret += countPair(taken, i);
                    taken[i] = taken[j] = false;
                }
            }
        }
        return ret;
    }
}
