package com.macpi.test;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AssertjSomeAssertionsTest {

    @Test
    void someWeirdStringAssertions() {
        // expect
        assertThat("this long not complicated string")
                .startsWith("this")
                .contains("complicated")
                .doesNotContain("bug");
    }

    @Test
    void someCollectionAssertions() {
        // given
        List<Object> mixedCollection = Lists.newArrayList(
                "string", 1L, new Phone("123")
        );

        // expect
        assertThat(mixedCollection)
                .containsOnlyOnce("string")
                .extracting("class")
                .doesNotContain(Exception.class);

    }

   @Test
   void someOptionalAssertions() {
        // given
       Optional<String> maybeSomeString = Optional.of("it's here");

       // expect
       assertThat(maybeSomeString)
               .isPresent()
               .isInstanceOf(Optional.class)
               .contains("it's here")

               .get()
               .isInstanceOf(String.class);
   }
}
