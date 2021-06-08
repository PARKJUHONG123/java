package P8_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LIS {
    static int[] cache, S;
    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        cache = new int[n + 1];
        S = new int[n];
        for (int i = 0; i < n; i++) {
            cache[i] = -1;
            S[i] = Integer.parseInt(st.nextToken());
        }
        cache[n] = -1;
        System.out.println(lis3(-1) - 1);
    }

    static int lis3(int start) {
        int ret = cache[start + 1];
        if (ret != -1) return ret;

        ret = 1;
        for (int next = start + 1; next < n; next++) {
            if (start == -1 || S[start] < S[next]) {
                ret = Math.max(ret, lis3(next) + 1);
            }
        }
        cache[start + 1] = ret;
        return ret;
    }
}
