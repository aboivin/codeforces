package C1141;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] numbersAsString = input.lines()
                                        .skip(1)
                                        .findFirst()
                                        .get()
                                        .split(" ");
        String result = compute(numbersAsString).stream()
                                                .map(String::valueOf)
                                                .collect(joining(" "));
        System.out.println(result);
    }

    public static List<Integer> compute(String[] numbersAsString) {
        List<Integer> numbers = Arrays.stream(numbersAsString)
                                      .map(Integer::valueOf)
                                      .collect(toList());
        double cum = cum(numbers);
        double a = ((numbers.size() + 2) / 2.0) - cum/(numbers.size()+1);
        if((int) a != a) {
            return Arrays.asList(-1);
        }
        List<Integer> result = new ArrayList<>();
        result.add((int) a);
        for (int i = 0; i < numbers.size(); i++) {
            result.add(result.get(i) + numbers.get(i));
        }

        List<Integer> check = result.stream().sorted().collect(toList());
        int i = 1;
        for (Integer value : check) {
            if(value != i++) {
                return Arrays.asList(-1);
            }
        }
        return result;
    }

    private static double cum(List<Integer> numbers) {
        double result = 0;
        double last = 0;
        for (Integer number : numbers) {
            last = last + number;
            result += last;
        }
        return result;
    }
}
