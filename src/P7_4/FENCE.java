package P7_4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FENCE {
    static int[] fences;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            fences = new int[N];
            for (int n = 0; n < N; n++) {
                fences[n] = Integer.parseInt(st.nextToken());
            }
            System.out.println(getMaxFenceSize(0, N - 1));
        }
    }

    static int getMaxFenceSize(int start, int end) {
        if (start == end) {
            return fences[start];
        }

        int mid = (start + end) / 2;
        int ret = Math.max(getMaxFenceSize(start, mid), getMaxFenceSize(mid + 1, end));

        int left = mid, right = mid + 1;
        int height = Math.min(fences[left], fences[right]);

        ret = Math.max(ret, height * 2);

        while (start < left || right < end ) {
            if (right < end && (left == start || fences[left - 1] < fences[right + 1])) {
                right += 1;
                height = Math.min(height, fences[right]);
            }
            else {
                left -= 1;
                height = Math.min(height, fences[left]);
            }
            ret = Math.max(ret, height * (right - left + 1));
        }

        return ret;
    }
}
