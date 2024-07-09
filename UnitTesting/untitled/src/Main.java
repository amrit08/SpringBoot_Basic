import java.util.Scanner;
import java.util.*;

public class Main {

        static final int MOD = 1000000007;

        public static void main(String[] args) {

            int N=5;
            int[] A ;
            A= new int[]{3,5,8,2,10};
            int K=2;

            System.out.println(solve(N, A, K));
        }

        static int[] sieveOfEratosthenes(int n) {
            int[] spf = new int[n + 1];
            Arrays.fill(spf, 2, n + 1, 2);

            for (int i = 3; i * i <= n; i += 2) {
                if (spf[i] == i) {
                    for (int j = i * i; j <= n; j += i) {
                        if (spf[j] == j) {
                            spf[j] = i;
                        }
                    }
                }
            }

            return spf;
        }

        static long maxPrimeFactors(int num, int[] spf) {
            Set<Integer> uniqueFactors = new HashSet<>();
            while (num != 1) {
                uniqueFactors.add(spf[num]);
                num /= spf[num];
            }
            return uniqueFactors.size();
        }

        static long solve(int N, int[] A, int K) {
            int[] spf = sieveOfEratosthenes(Arrays.stream(A).max().getAsInt());

            long score = 1;
            for (int operation = 0; operation < K; operation++) {
                int left = -1, right = -1, idx = -1;
                long maxUniqueFactors = -1;

                for (int l = 0; l < N; l++) {
                    for (int r = l; r < N; r++) {
                        long uniqueFactors = maxPrimeFactors(A[r], spf);
                        if (uniqueFactors > maxUniqueFactors) {
                            maxUniqueFactors = uniqueFactors;
                            left = l;
                            right = r;
                            idx = r;
                        }
                    }
                }

                score = (score * A[idx]) % MOD;
                A[idx] = 1;
            }

            return score;
        }
}