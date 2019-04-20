package B1140;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class GoodString {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = input.lines().collect(Collectors.toList());
        int numberOfTestCases = Integer.parseInt(lines.get(0));
        for (int i = 0; i < numberOfTestCases; i++) {
            String[] chars = lines.get(2 * i + 2).split("");
            System.out.println(compute(chars));
        }
    }

    protected static int compute(String[] chars) {
        int index=0;
        while (index < (chars.length - index - 1)) {
            if(chars[index].equals(">") || chars[chars.length - index - 1].equals("<")) {
                return index;
            }
            index++;
        }
        return index;
    }
}
