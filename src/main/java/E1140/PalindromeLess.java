package E1140;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static java.util.stream.Collectors.toList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PalindromeLess {

    public static final BigInteger MOD_PRIME = valueOf(998244353);

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] lines = input.lines().toArray(String[]::new);
        int range = Integer.parseInt(lines[0].split(" ")[1]);
        List<Integer> numbers = Arrays.stream(lines[1].split(" "))
                                      .map(Integer::parseInt)
                                      .collect(toList());
        long badCount = compute3(numbers, range);
        System.out.println(badCount);
    }

    public static int compute(List<Integer> numbers, int range) {
        int countMinusOne = (int) numbers.stream().mapToInt(Integer::intValue).filter(i -> i == -1).count();
        BigInteger result = valueOf(range).pow(countMinusOne);
        return result.subtract(valueOf(badCount(numbers, 0, range))).intValue();
    }

    private static int badCount(List<Integer> numbers, int index, int range) {
        if (numbers.size() < 3) {
            return 0;
        } else if (numbers.size() == index) {
            for (int i = 1; i < index - 1; i++) {
                if (numbers.get(i - 1).equals(numbers.get(i + 1))) {
                    return 1;
                }
            }
            return 0;
        }
        int badCount = 0;

        if (numbers.get(index) == -1) {
            for (Integer i = 1; i <= range; i++) {
                numbers.remove(index);
                numbers.add(index, i);
                badCount += badCount(new ArrayList<>(numbers), index + 1, range);
            }
        } else {
            badCount += badCount(numbers, index + 1, range);
        }
        return badCount;
    }

    public static long compute3(List<Integer> numbers, int range) {
        List<Integer> odd = IntStream.range(0, numbers.size()).filter(i -> i % 2 == 1).mapToObj(numbers::get).collect(toList());
        List<Integer> even = IntStream.range(0, numbers.size()).filter(i -> i % 2 == 0).mapToObj(numbers::get).collect(toList());
        BigInteger score = computeScore(odd, valueOf(range));
        score = score.multiply(computeScore(even, valueOf(range)));
        score = score.mod(MOD_PRIME);
        return score.longValue();
    }

    private static BigInteger computeScore(List<Integer> numbers, BigInteger range) {
        BigInteger sameSide = ZERO, notSameSide = ONE;
        boolean inprogress = false;
        BigInteger score = ONE;
        int left, right, first = 0;
        if(numbers.size() == 1) {
            return numbers.get(0) == -1 ? range : ONE;
        }
        for (int i = 1; i < numbers.size(); i++) {
            left = numbers.get(i - 1);
            right = numbers.get(i);
            if (!inprogress) {
                inprogress = true;
                first = left;
                sameSide = ZERO;
                notSameSide = ONE;
            }

            if (right == -1) {
                if(i == numbers.size() -1 && first == -1) {
                    score = score.multiply(range.multiply((range.subtract(ONE).multiply(notSameSide).add(sameSide)).mod(MOD_PRIME)));
                } else if (i == numbers.size() -1 && first != -1) {
                    score = score.multiply((range.subtract(ONE).multiply(notSameSide).add(sameSide)));
                } else {
                    BigInteger temp = sameSide;
                    sameSide = range.subtract(ONE).multiply(notSameSide).mod(MOD_PRIME);
                    notSameSide = range.subtract(valueOf(2)).multiply(notSameSide).add(temp).mod(MOD_PRIME);
                }
            } else {
                if (first == -1) {
                    score = score.multiply((range.subtract(ONE).multiply(notSameSide).add(sameSide)));
                } else if (first == right) {
                    score = score.multiply(sameSide);
                } else {
                    score = score.multiply(notSameSide);
                }
                inprogress = false;
            }
            score = score.mod(MOD_PRIME);
        }
        return score;
    }
}
