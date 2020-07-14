package com.macpi.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class JunitCountingService {
    CountingService countingService = new CountingService();

    @DisplayName("Calculate some hard math")
    @ParameterizedTest(name = "==> multiplication of {0} and {1} is equal to {2}")
    @MethodSource("argumentsProvider")
    void multiply_x_times_y_shouldReturn_result(int x, int y, int result) {
        // expect
        assertEquals(countingService.multiply(x, y), result);
    }

    private static Stream<Arguments> argumentsProvider() {
        return Stream.of(
                arguments(1, 2, 2),
                arguments(22, 4, 88),
                arguments(10, 10, 10)
        );
    }
}