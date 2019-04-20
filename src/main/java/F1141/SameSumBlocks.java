package F1141;

import static java.lang.Integer.compare;
import static java.util.Comparator.comparingInt;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SameSumBlocks {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] numbersAsString = input.lines()
                                        .skip(1)
                                        .findFirst()
                                        .get()
                                        .split(" ");
        int[] numbers = Arrays.stream(numbersAsString).mapToInt(Integer::parseInt).toArray();
        List<PairOfInt> sameSumBlocks = findSameSumBlocks(numbers);
        System.out.println(sameSumBlocks.size());
        sameSumBlocks.forEach(System.out::println);
    }

    static List<PairOfInt> findSameSumBlocks(int[] numbers) {

        List<List<PairOfInt>> potentials = buildPotentialsSum(numbers);

        List<List<PairOfInt>> sortedPairList = potentials.stream()
                                                         .filter(p -> p.size() > 0)
                                                         .sorted((l1, l2) -> compare(l2.size(), l1.size()))
                                                         .collect(Collectors.toList());

        List<PairOfInt> max = new ArrayList<>();
        max.add(PairOfInt.of(1, 1));
        for (int i = 0; i < sortedPairList.size(); i++) {
            List<PairOfInt> result = sortedPairList.get(i);
            result.sort(comparingInt(p -> p.b));
            List<PairOfInt> maxDisjoint = maxDisjointPair(result);
            if (maxDisjoint.size() > max.size()) {
                max = maxDisjoint;
                if (i + 1 < sortedPairList.size() && maxDisjoint.size() >= sortedPairList.get(i + 1).size()) {
                    return maxDisjoint;
                }
            }
        }

        return max;
    }

    public static List<PairOfInt> maxDisjointPair(List<PairOfInt> result) {
        if (result.size() == 0)
            return result;

        boolean over = false;
        while (!over) {
            int i;
            for (i = 1; i < result.size(); i++) {
                if (result.get(i).a <= result.get(i - 1).b) {
                    result.remove(result.get(i));
                    break;
                }
            }
            if (i == result.size()) {
                over = true;
            }
        }
        return result;
    }

    private static List<List<PairOfInt>> buildPotentialsSum(int[] numbers) {
        Map<Integer, List<PairOfInt>> result = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int blockSum = 0;
            for (int j = i; j < numbers.length; j++) {
                blockSum += numbers[j];
                final int tmpi = i, tmpj = j;
                result.compute(blockSum, (k, l) -> {
                    if (l == null) {
                        l = new ArrayList<>();
                    }
                    l.add(PairOfInt.of(tmpi + 1, tmpj + 1));
                    return l;
                });
                if(blockSum == 0) {
                    break;
                }
            }
        }
        return new ArrayList<>(result.values());
    }

    public static class PairOfInt {
        final int a, b;

        public static PairOfInt of(int a, int b) {
            return new PairOfInt(a, b);
        }

        private PairOfInt(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public String toString() {
            return a + " " + b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PairOfInt pairOfInt = (PairOfInt) o;
            return a == pairOfInt.a &&
                    b == pairOfInt.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

    }
}

