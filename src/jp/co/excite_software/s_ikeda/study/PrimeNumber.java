package jp.co.excite_software.s_ikeda.study;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {

    public List<Integer> getPrimeNumberList(final int n, final int m) {

        ArrayList<Integer> primeNumberList = new ArrayList<>();

        for (int i = (n <= 2 ? 2 : n); i < m; i++) {

            int j = 2;
            for (; j < i; j++) {

                if (i % j == 0) {
                    break;
                }
            }

            if (i == j) {
                primeNumberList.add(i);
            }
        }

        return primeNumberList;
    }

    public static void main(String[] args) {

        PrimeNumber prime = new PrimeNumber();

        System.out.println(prime.getPrimeNumberList(0, 100));
    }
}
