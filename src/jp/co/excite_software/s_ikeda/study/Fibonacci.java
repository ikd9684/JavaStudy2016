package jp.co.excite_software.s_ikeda.study;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    public List<Integer> getFibonacciNumberList(int n) {

        ArrayList<Integer> result = new ArrayList<>();

        result.add(0);
        for (int m = 1; m < n; ) {
            result.add(m);

            int n2 = result.get(result.size() - 2);
            int n1 = result.get(result.size() - 1);
            m = n2 + n1;
        }

        return result;
    }

    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci();

        System.out.println(fibonacci.getFibonacciNumberList(100));
    }
}
