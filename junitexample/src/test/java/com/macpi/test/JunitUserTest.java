package com.macpi.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static com.macpi.test.UserTestRepository.FIRST_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JunitUserTest {

    UserService testee;

    @Test
    void getUser_whenUserIsPresent_returnUser() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        User actualUser = testee.getByIdOrThrow(FIRST_ID);

        // then
        User expectedUser = createFirstUser();
        assertEquals(actualUser, expectedUser);
    }

    @Test
    void getUser_whenUserIsNotPresent_throw() {
        // given
        testee = new UserService(new UserTestRepository());

        // expect
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> testee.getByIdOrThrow(UUID.randomUUID())
        );
        assertTrue(thrown.getMessage().contains("Cannot find user with id"));
    }

    @Test
    @Disabled
    void getUser_whenUserIsPresent_returnUser_errorCase() {
        // given
        testee = new UserService(new UserTestRepository());
        User expectedUser = createRandomUser();

        // when
        User actualUser = testee.getByIdOrThrow(FIRST_ID);

        // then
        assertEquals(expectedUser, actualUser);
    }

    @Test
    void getUser_whenUserIsPresentAndWrappedInOptional_returnUser() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        Optional<User> maybeActualUser = Optional.of(testee.getByIdOrThrow(FIRST_ID));

        // then
        User expectedUser = createFirstUser();
        assertTrue(maybeActualUser.isPresent());
        assertEquals(expectedUser, maybeActualUser.get());
    }

    private User createFirstUserWithRandomId() {
        return new User(
                UUID.randomUUID(),
                "Jan Kowalski",
                LocalDate.of(1990, 10, 10),
                new Phone("+48606023420")
        );

    }

    private User createRandomUser() {
        return new User(
                UUID.randomUUID(),
                "Jan Someone",
                LocalDate.of(1990, 10, 10),
                new Phone("+48606023420")
        );
    }

    private User createFirstUser() {
        return new User(
                FIRST_ID,
                "Jan Kowalski",
                LocalDate.of(1990, 10, 10),
                new Phone("+48606023420")
        );
    }
}
