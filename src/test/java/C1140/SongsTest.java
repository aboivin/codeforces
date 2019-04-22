package C1140;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static C1140.Songs.Song.song;
import java.math.BigInteger;
import java.util.List;
import org.junit.jupiter.api.Test;

class SongsTest {

    @Test
    void name() {
        // Given
        List<Songs.Song> songs = asList(song(4, 7),
                                        song(15, 1),
                                        song(3, 6),
                                        song(6, 8));

        // When
        BigInteger result = Songs.compute(songs, 3);

        // Then
        assertThat(result).isEqualTo(78);
    }

    @Test
    void name2() {
        // Given
        List<Songs.Song> songs = asList(song(12, 31),
                                        song(112, 4),
                                        song(100, 100),
                                        song(13, 55),
                                        song(55, 50));

        // When
        BigInteger result = Songs.compute(songs, 3);

        // Then
        assertThat(result).isEqualTo(10000);
    }

    @Test
    void name3() {
        // Given
        List<Songs.Song> songs = asList(song(12, 31),
                                        song(112, 4),
                                        song(13, 55),
                                        song(55, 50));

        // When
        BigInteger result = Songs.compute(songs, 1);

        // Then
        assertThat(result).isEqualTo(2750);
    }

    @Test
    void name4() {
        // Given
        List<Songs.Song> songs = asList(song(1000, 1),
                                        song(2, 2),
                                        song(3, 3));

        // When
        BigInteger result = Songs.compute(songs, 1);

        // Then
        assertThat(result).isEqualTo(1000);
    }

    @Test
    void name5() {
        // Given
        List<Songs.Song> songs = asList(song(1000, 2));

        // When
        BigInteger result = Songs.compute(songs, 1);

        // Then
        assertThat(result).isEqualTo(2000);
    }

    @Test
    void name6() {
        // Given
        List<Songs.Song> songs = asList(song(10, 2),
                                        song(10, 2),
                                        song(10, 2));

        // When
        BigInteger result = Songs.compute(songs, 3);

        // Then
        assertThat(result).isEqualTo(60);
    }

    @Test
    void name7() {
        // Given
        List<Songs.Song> songs = asList(song(10, 2),
                                        song(10, 2),
                                        song(10, 1));

        // When
        BigInteger result = Songs.compute(songs, 3);

        // Then
        assertThat(result).isEqualTo(40);
    }

    @Test
    void name8() {
        // Given
        List<Songs.Song> songs = asList(song(1, 2),
                                        song(2, 2),
                                        song(3, 2));

        // When
        BigInteger result = Songs.compute(songs, 3);

        // Then
        assertThat(result).isEqualTo(12);
    }

    @Test
    void name9() {
        // Given
        List<Songs.Song> songs = asList(song(1, 2),
                                        song(2, 2),
                                        song(3, 2));

        // When
        BigInteger result = Songs.compute(songs, 2);

        // Then
        assertThat(result).isEqualTo(10);
    }

    @Test
    void name10() {
        // Given
        List<Songs.Song> songs = asList(song(1, 3),
                                        song(2, 2),
                                        song(3, 2));

        // When
        BigInteger result = Songs.compute(songs, 2);

        // Then
        assertThat(result).isEqualTo(10);
    }

    @Test
    void name11() {
        // Given
        List<Songs.Song> songs = asList(song(1, 3),
                                        song(2, 2),
                                        song(3, 2));

        // When
        BigInteger result = Songs.compute(songs, 1);

        // Then
        assertThat(result).isEqualTo(6);
    }
}
