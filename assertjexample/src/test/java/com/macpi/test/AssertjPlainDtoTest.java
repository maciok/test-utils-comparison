package com.macpi.test;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AssertjPlainDtoTest {
    @Test
    void testWithoutEquals() {
        PlainDto someDto = new PlainDto("string field", 123L);
        PlainDto otherDto = new PlainDto("string field", 123L);

        assertThat(someDto.equals(otherDto))
                .isFalse();
        assertThat(someDto)
                .usingRecursiveComparison()
                .isEqualTo(otherDto);
    }
}
