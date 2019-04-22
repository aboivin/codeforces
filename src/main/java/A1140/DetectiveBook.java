package A1140;

import static java.util.stream.Collectors.toList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class DetectiveBook {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> pages = Arrays.stream(input.lines()
                                                .skip(1)
                                                .findFirst()
                                                .orElseThrow(IllegalStateException::new)
                                                .split(" "))
                                   .map(Integer::valueOf)
                                   .collect(toList());
        System.out.println(compute(pages));
    }

    protected static int compute(List<Integer> pages) {
        int max = 0;
        int count = 0;
        for (int index = 1; index <= pages.size(); index++) {
            if(pages.get(index-1) > max) {
                max = pages.get(index-1);
            }
            if(max == index) {
                count++;
            }
        }
        return count;
    }
}
