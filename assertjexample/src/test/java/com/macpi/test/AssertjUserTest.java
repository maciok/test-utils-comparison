package com.macpi.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static com.macpi.test.UserTestRepository.FIRST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AssertjUserTest {

    UserService testee;

    @Test
    void getUser_whenUserIsPresent_returnUser() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        User actualUser = testee.getByIdOrThrow(FIRST_ID);

        // then
        User expectedUser = createFirstUser();
        assertThat(actualUser)
                .isEqualTo(expectedUser);
    }

    @Test
    void getUser_whenUserIsNotPresent_throw() {
        // given
        testee = new UserService(new UserTestRepository());

        // expect
        assertThatThrownBy(() -> testee.getByIdOrThrow(UUID.randomUUID()))
                .hasMessageContaining("Cannot find user with id")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getUser_whenUserIsNotPresent_throw_secondWay() {
        // given
        testee = new UserService(new UserTestRepository());

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> testee.getByIdOrThrow(UUID.randomUUID()))
                .withMessageContaining("Cannot find user with id");
    }

    @Test
    void getUser_whenUserIsPresentAndHasDifferentId_returnUser() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        User actualUser = testee.getByIdOrThrow(FIRST_ID);

        // then
        User expectedUser = createFirstUserWithRandomId();
        assertThat(actualUser)
                .isEqualToIgnoringGivenFields(expectedUser, "id");
    }

    @Test
    void getAll_whenAllReturned_atLeastOneIsKnownAndRandomIsNotPresent() {
        // given
        testee = new UserService(new UserTestRepository());
        User firstExpectedUser = createFirstUser();
        User randomUser = createRandomUser();

        // when
        Collection<User> users = testee.getAll();

        // then
        assertThat(users)
                .contains(firstExpectedUser)
                .doesNotContain(randomUser);
    }

    @Test
    @Disabled
    void getUser_whenUserIsPresent_returnUser_errorCase() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        User actualUser = testee.getByIdOrThrow(FIRST_ID);

        // then
        User expectedUser = createRandomUser();
        assertThat(actualUser)
                .isEqualTo(expectedUser);
    }

    @Test
    void getUser_whenUserIsPresentAndWrappedInOptional_returnUser() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        Optional<User> maybeActualUser = Optional.of(testee.getByIdOrThrow(FIRST_ID));

        // then
        User expectedUser = createFirstUser();
        assertThat(maybeActualUser)
                .isPresent()
                .contains(expectedUser);
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
