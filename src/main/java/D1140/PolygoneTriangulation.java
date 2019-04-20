package D1140;

import static java.util.stream.Collectors.toList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class PolygoneTriangulation {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = input.lines().collect(toList());
        Integer number = Integer.valueOf(lines.get(0));
        System.out.println(compute(number));
    }

    protected static int compute(Integer number) {
        int score = 0;
        for (int i = 3; i <= number; i++) {
            score += i * (i - 1);
        }
        return score;
    }
}
