package A1140;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DetectiveBookTest {

    @Test
    void should1() {
        List<Integer> integers = Arrays.asList(1, 3, 3, 6, 7, 6, 8, 8, 9);
        int result = DetectiveBook.compute(integers);

        assertThat(result).isEqualTo(4);
    }

    @Test
    void should2() {
        List<Integer> integers = Arrays.asList(1, 9, 3, 6, 7, 6, 8, 8, 9);
        int result = DetectiveBook.compute(integers);

        assertThat(result).isEqualTo(2);
    }
}
