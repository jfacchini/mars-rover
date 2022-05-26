package com.rover;

import com.rover.MarsRover;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MarsRoverShould {
    @ParameterizedTest
    @CsvSource({"1, 2, N, '1:2:N'", "2, 3, S, '2:3:S'"})
    void return_initial_position_of_rover_without_any_command(int x, int y, String cardinal, String expectedState) {
        String emptyCommand = "";
        assertEquals(expectedState, new MarsRover(x, y, cardinal).execute(emptyCommand));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, N, M, '1:3:N'",
        "1, 2, N, MM, '1:4:N'",
        "1, 4, S, MM, '1:2:S'"
    })
    void move_vertically(int x, int y, String cardinal, String commands, String expectedState) {
        assertEquals(expectedState, new MarsRover(x, y, cardinal).execute(commands));
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, E, M, '2:2:E'",
        "1, 2, E, MM, '3:2:E'",
        "3, 2, W, MM, '1:2:W'"
    })
    void move_horizontally(int x, int y, String cardinal, String commands, String expectedState) {
        assertEquals(expectedState, new MarsRover(x, y, cardinal).execute(commands));
    }

    @ParameterizedTest
    @CsvSource({
        "N, R, E",
        "N, RR, S",
        "N, RRR, W",
        "N, RRRR, N",
        "N, L, W",
        "N, LL, S",
        "N, LLL, E",
        "N, LLLL, N"
    })
    void turns_to_the_expected_direction(String cardinal, String commands, String expectedCardinal) {
        assertEquals(String.format("0:0:%s",expectedCardinal), new MarsRover(0, 0, cardinal).execute(commands));
    }
}