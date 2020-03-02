package test;

import org.junit.Test;

import java.util.Arrays;

public class ZTest {

    @Test
    public void testZAlgorithm() {
        String s = "GEEKS$GEEKS";
        int n = s.length();
        int[] z = new int[n];

        int L = 0, R = 0;

        for (int i=1; i<n; i++) {
            if (i > R) {
                L = R = i;

                while (R < n && s.charAt(R-L) == s.charAt(R)) {
                    R++;
                }
                z[i] = R-L;
                R--;
            } else {
                int k = i - L;

                if (z[k] < R - i + 1) {
                    z[i] = z[k];
                } else {
                    L = i;

                    while (R < n && s.charAt(R-L) == s.charAt(R)) {
                        R++;
                    }
                    z[i] = R-L;
                    R--;
                }
            }
        }
    }


    @Test
    public void test_ZAlgorithmAgain() {
        String s = "GEEKS$GEEKS FOR GEEKS";
        int n = s.length();
        int[] z = new int[n];

        int L = 0, R = 0;

        for (int i=1; i<n; i++) {
            if (i > R) {
                L = R = i;

                while (R < n && s.charAt(R) == s.charAt(R-L)) {
                    R++;
                }
                z[i] = R-L;
                R--;
            } else {
                int k = i - L;

                if (z[k] < R-i+1) {
                    z[i] = z[k];
                } else {
                    L = i;

                    while(R < n && s.charAt(R) == s.charAt(R-L)) {
                        R++;
                    }
                    z[i] = R-L;
                    R--;
                }
            }
        }
    }

    @Test
    public void test_ZAlgorithmAgainAgain() {
        String s = "GEEKS$GEEKS FOR GEEKS";

        int[] z = new int[s.length()];


    }
}
