package P8_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WILDCARD {
    static String W, S;
    static int[][] cache;
    static int len, wLen, sLen;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(bf.readLine());
        for (int c = 0; c < C; c++) {
            W = bf.readLine();
            int N = Integer.parseInt(bf.readLine());
            wLen = W.length();

            for (int n = 0; n < N; n++) {
                S = bf.readLine();
                sLen = S.length();
                int len = Math.max(wLen, sLen) + 1;
                cache = new int[len][len];

                for (int i = 0; i < len; i++) {
                    for(int j = 0; j < len; j++) {
                        cache[i][j] = -1;
                    }
                }

                if (checkFileName(0, 0)) {
                    System.out.println(S);
                }

            }
        }
    }

    static boolean checkFileName(int w, int s) {
        int ret = cache[w][s];
        if (ret != -1) {
            return ret == 1;
        }

        /*
        while (s < sLen && w < wLen && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            w += 1;
            s += 1;
        }
        */
        if (s < sLen && w < wLen && (W.charAt(w) == '?' || W.charAt(w) == S.charAt(s))) {
            boolean c = checkFileName(w + 1, s + 1);
            cache[w][s] = c ? 1 : 0;
            return c;
        }

        if (w == wLen) {
            ret = (s == sLen) ? 1 : 0;
            cache[w][s] = ret;
            return s == sLen;
        }

        /*
        if (W.charAt(w) == '*') {
            for (int skip = 0; skip + s <= sLen; skip++) {
                if (checkFileName(w + 1, s + skip)) {
                    cache[w][s] = 1;
                    return true;
                }
            }
        }
         */

        if (W.charAt(w) == '*') {
            if (checkFileName(w + 1, s) || (s < sLen && checkFileName(w, s + 1))) {
                cache[w][s] = 1;
                return true;
            }
        }

        cache[w][s] = 0;
        return false;
    }
}
