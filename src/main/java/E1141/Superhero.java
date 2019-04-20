package E1141;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.math.BigInteger.valueOf;
import static java.util.stream.Collectors.toList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Superhero {

    public static void main(String[] args) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<String> lines = input.lines().collect(toList());

        String rawHp = lines.get(0).split(" ")[0];
        BigInteger hp = new BigInteger(rawHp);
        List<BigInteger> damages = Arrays.stream(lines.get(1).split(" "))
                                         .map(Integer::valueOf)
                                         .map(BigInteger::valueOf)
                                         .collect(Collectors.toList());
        System.out.println(compute(hp, damages));
    }

    public static BigInteger compute(BigInteger hp, List<BigInteger> damages) {
        BigInteger minutes = ZERO;
        BigInteger sum = ZERO;
        BigInteger max = ZERO;
        for (BigInteger damage : damages) {
            sum = sum.add(damage);
            if (sum.compareTo(max) < 0) {
                max = sum;
            }
        }

        if (sum.compareTo(ZERO) >= 0 && max.negate().compareTo(hp) < 0) {
            return valueOf(-1);
        }
        if (hp.compareTo(max.negate()) > 0) {
            BigInteger times = hp.add(max).subtract(ONE).divide(sum.negate()).add(ONE) ;
            minutes = valueOf(damages.size()).multiply(times);
            hp = hp.add(times.multiply(sum));
        }

        for (BigInteger damage : damages) {
            hp = hp.add(damage);
            minutes = minutes.add(ONE);
            if (hp.compareTo(ZERO) <= 0) {
                return minutes;
            }
        }
        return valueOf(-1);
    }
}
