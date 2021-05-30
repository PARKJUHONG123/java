package P7_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class QUADTREE {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(bf.readLine());

        for (int c = 0; c < C; c++) {
            String input = bf.readLine();
            System.out.println(reverseTree(input));
        }
    }

    static String shuffle(String[] input) {
        String[] retArr = new String[4];
        retArr[2] = input[0];
        retArr[3] = input[1];
        retArr[0] = input[2];
        retArr[1] = input[3];

        StringBuilder ret = new StringBuilder("x");
        for (String value : retArr) {
            ret.append(value);
        }

        return ret.toString();
    }

    static String reverseTree(String input) {
        int index = 0;
        if (input.charAt(index) == 'x') {
            String[] xArr = new String[4];
            index += 1;
            for (int i = 0; i < 4; i++) {
                xArr[i] = reverseTree(input.substring(index));
                index += xArr[i].length();
            }
            return shuffle(xArr);
        }
        else {
            return String.valueOf(input.charAt(index));
        }
    }
}
