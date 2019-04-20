package B1141;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PolyRest {


    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] numbersAsString = input.lines()
                                        .skip(1)
                                        .findFirst()
                                        .get()
                                        .split(" ");
        System.out.println(compute(numbersAsString));
    }

    public static Integer compute(String[] numbersAsString) {
        List<Integer> numbers = Arrays.stream(numbersAsString).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> result = new ArrayList<>();
        result.addAll(numbers);
        result.addAll(numbers);
        int max = 0;
        int count = 0;
        for (Integer integer : result) {
            if (integer == 1) {
                count++;
            } else {
                if (count > max) {
                    max = count;
                }
                count = 0;
            }
        }
        return max;
    }
}
