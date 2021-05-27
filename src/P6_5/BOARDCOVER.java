package P6_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOARDCOVER {
    static int[][] hArr = {{1, 1}, {0, 1}, {1, 1}, {0, 1}};
    static int[][] wArr = {{-1, 0}, {1, 1}, {0, 1}, {1, 0}};
    static int H, W;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int left;

        int C = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < C; tc++) {
            left = 0;
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken()); // 3
            W = Integer.parseInt(st.nextToken()); // 7
            board = new char[H][W];
            for (int i = 0; i < H; i++) {
                board[i] = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    if (board[i][j] == '.') {
                        left += 1;
                    }
                }
            }
            if (left % 3 == 0) {
                System.out.println(locateBlock(0, 0, left));
            }
            else {
                System.out.println("0");
            }

        }
    }

    static boolean checkH(int h) {
        return 0 <= h && h < H;
    }

    static boolean checkW(int w) {
        return 0 <= w && w < W;
    }


    static int locateBlock(int h, int w, int left) {
        if (left == 0) {
            return 1;
        }

        if (w == W) {
            if (h == H - 1) {
                return 0;
            }
            else {
                w = 0;
                h += 1;
            }
        }

        int ret = 0;
        if (board[h][w] == '.') {
            for (int i = 0; i < 4; i++) {
                int first_h = h + hArr[i][0];
                int first_w = w + wArr[i][0];
                int second_h = h + hArr[i][1];
                int second_w = w + wArr[i][1];
                if (checkH(first_h) && checkH(second_h) && checkW(first_w) && checkW(second_w)) {
                    if (board[first_h][first_w] == '.' && board[second_h][second_w] == '.') {
                        board[h][w] = '#';
                        board[first_h][first_w] = '#';
                        board[second_h][second_w] = '#';

                        ret += locateBlock(h, w, left - 3);

                        board[h][w] = '.';
                        board[first_h][first_w] = '.';
                        board[second_h][second_w] = '.';
                    }
                }
            }
        }
        else {
            ret += locateBlock(h, w + 1, left);
        }
        return ret;
    }
}
