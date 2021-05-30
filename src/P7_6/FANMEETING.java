package P7_6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FANMEETING {
    static String member;
    static String fan;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(bf.readLine());
        for (int c = 0; c < C; c++) {
            member = bf.readLine();
            fan = bf.readLine();
            System.out.println(hugs());
        }
    }

    static int hugs() {
        int N = member.length(), M = fan.length();
        ArrayList<Integer> A = new ArrayList<Integer>(N);
        ArrayList<Integer> B = new ArrayList<Integer>(M);

        for (int i = 0; i < N; i++) A.add(0);
        for (int i = 0; i < M; i++) B.add(0);

        for (int i = 0; i < N; i++) {
            Integer temp = (member.charAt(i) == 'M') ? 1 : 0;
            A.set(i, temp);
        }
        for (int i = 0; i < M; i++) {
            Integer temp = (fan.charAt(i) == 'M') ? 1 : 0;
            B.set(M - i - 1, temp);
        }

        ArrayList<Integer> C = Karatsuba.karatsuba(A, B);

        int allHugs = 0;
        for (int i = N - 1; i < M; i++) {
            if (C.get(i) == 0) allHugs += 1;
        }
        return allHugs;
    }
}

class Karatsuba {
    static ArrayList<Integer> karatsuba(ArrayList<Integer> a, ArrayList<Integer> b) {
        int a_size = a.size();
        int b_size = b.size();
//A>B여야 한다
        if (a_size < b_size)
            return karatsuba(b, a);
//만약에 비어있는 애 오면 끝
        if (a_size == 0 || b_size == 0)
            return null;
//50이하에서는 보통 기본 곱셈이 빠르다

        if (a_size == 1)
            return multiply(a, b);

        int half = a_size / 2;
//a>b일때 b0 = null이 될 때도 있다. karatsuba에서나, sub, sum할때 예외처리를 해주자.
        ArrayList<Integer> a0 = new ArrayList<Integer>(a.subList(0, half));
        ArrayList<Integer> a1 = new ArrayList<Integer>(a.subList(half, a.size()));
        ArrayList<Integer> b0 = new ArrayList<Integer>(b.subList(0, Math.min(b.size(), half)));
        ArrayList<Integer> b1 = new ArrayList<Integer>(b.subList(Math.min(b.size(), half), b.size()));
// z2 = a1 * b1
        ArrayList<Integer> z2 = karatsuba(a1, b1);
// z0 = a0 * b0
        ArrayList<Integer> z0 = karatsuba(a0, b0);
// a0 = a0 + a1; b0 = b0 + b1
        a0 = karatsubasum(a0, a1, 0);
        b0 = karatsubasum(b0, b1, 0);
// z1 = (a0 * b0) - z0 - z2;
        ArrayList<Integer> z1 = karatsuba(a0, b0);
        z1 = karatsubasub(z1, z0);
        z1 = karatsubasub(z1, z2);
// ret = z0 + z1 * 10^half + z2 * 10^(half*2)
        ArrayList<Integer> ret = new ArrayList<Integer>();
        ret = karatsubasum(ret, z0, 0);
        ret = karatsubasum(ret, z1, half);
        ret = karatsubasum(ret, z2, half * 2);
        return ret;
    }
    public static ArrayList<Integer> ensureSize(ArrayList<Integer> list, int size) {
        list.ensureCapacity(size);
        while (list.size() < size) {
            list.add(0);
        }
        return list;
    }
    static ArrayList<Integer> multiply(List<Integer> a, List<Integer> b){
        ArrayList<Integer> c = new ArrayList<Integer>();
        c = ensureSize(c, a.size()+b.size()+1);
        for(int i =0; i<a.size(); i++) {
            for(int j =0; j<b.size(); j++) {
                c.set(i+j, c.get(i+j) + a.get(i)*b.get(j));
            }
        }
        c = normalize(c);
        return c;
    }
    //a = a + b*(10^k);
    public static ArrayList<Integer> karatsubasum(ArrayList<Integer> a, ArrayList<Integer> b, int k){
        if(b == null) {
            return a;
        }
        a = ensureSize(a, Math.max(a.size(), b.size() + k));
        for (int i = 0; i < b.size(); i++) {
            a.set(i + k, a.get(i + k) + b.get(i));
        }
        a = normalize(a);
        return a;
    }
    //a= a-b ; a>=b 일때
    public static ArrayList<Integer> karatsubasub(ArrayList<Integer> a, ArrayList<Integer> b){
        if(b == null) {
            return a;
        }
        a = ensureSize(a, Math.max(a.size(), b.size()) + 1);
        for (int i = 0; i < b.size(); i++) {
            a.set(i, a.get(i) - b.get(i));
        }
        a = normalize(a);
        return a;
    }
    public static ArrayList<Integer> normalize(ArrayList<Integer> num) {
        num.add(0);
        for (int i = 0; i < num.size() - 1; i++) {
            if (num.get(i) < 0) {
                int borrow = (Math.abs(num.get(i)) + 9) / 10;
                num.set(i + 1, num.get(i + 1) - borrow);
                num.set(i, num.get(i) + borrow * 10);
            } else {
                num.set(i + 1, num.get(i + 1) + num.get(i) / 10);
                num.set(i, num.get(i) % 10);
            }
        }
        if (num.get(num.size() - 1) == 0)
            num.remove(num.size() - 1);
        return num;
    }
}