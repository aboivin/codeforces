package A1141;

import static java.lang.Integer.parseInt;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game23 {
    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] numbersAsString = input.lines()
                                        .findFirst()
                                        .get()
                                        .split(" ");
        System.out.println(compute(parseInt(numbersAsString[0]), parseInt(numbersAsString[1])));
    }

    public static int compute(int first, int second) {
        if (first == 0) {
            return -1;
        }

        int count = 0;
        double div = (1.0 * second) / first;
        if (!isWhole(div)) {
            return -1;
        }

        double tmp = div;
        while (isWhole(tmp / 3)) {
            tmp = tmp / 3;
            count++;
        }

        while (isWhole(tmp / 2)) {
            tmp = tmp / 2;
            count++;
        }

        if(tmp != 1) {
            return -1;
        }

        return count;
    }

    private static boolean isWhole(double tmp) {
        return (int) tmp == tmp;
    }
}
