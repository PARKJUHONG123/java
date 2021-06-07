package P8_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TRIANGLEPATH {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] input = new int[N][];
        int[][] dp = new int[N][];

        for (int i = 1; i <= N; i++) {
            input[i - 1] = new int[i];
            dp[i - 1] = new int[i];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i; j++) {
                input[i - 1][j] = Integer.parseInt(st.nextToken());
                dp[i - 1][j] = input[i - 1][j];
            }
        }

        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                dp[i - 1][j] = Math.max(dp[i][j], dp[i][j + 1]) + input[i - 1][j];
            }
        }

        System.out.println(dp[0][0]);
    }
}
