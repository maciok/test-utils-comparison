package com.macpi.test;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static com.macpi.test.UserTestRepository.FIRST_ID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class HamcrestUserTest {

    UserService testee;

    @Test
    void getUser_whenUserIsPresent_returnUser() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        User actualUser = testee.getByIdOrThrow(FIRST_ID);

        // then
        User expectedUser = createFirstUser();
        assertThat(actualUser, equalTo(expectedUser));
    }

    @Test
    void getUser_whenUserIsNotPresent_throw() {
        // no easy way to do this?
    }

    @Test
    void getUser_whenUserIsPresentAndHasDifferentId_returnUser() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        User actualUser = testee.getByIdOrThrow(FIRST_ID);

        // then
        User expectedUser = createFirstUserWithRandomId();
        assertThat(actualUser.getName(), equalTo(expectedUser.getName()));
        assertThat(actualUser.getBirthday(), equalTo(expectedUser.getBirthday()));
        assertThat(actualUser.getPhone().getNumber(), equalTo(expectedUser.getPhone().getNumber()));
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
        assertThat(users, hasItem(firstExpectedUser));
        assertThat(users, not(hasItem(randomUser)));
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
        assertThat(actualUser, equalTo(expectedUser));
    }

    @Test
    void getUser_whenUserIsPresentAndWrappedInOptional_returnUser() {
        // given
        testee = new UserService(new UserTestRepository());

        // when
        Optional<User> maybeActualUser = Optional.of(testee.getByIdOrThrow(FIRST_ID));

        // then
        User expectedUser = createFirstUser();
        assertThat(maybeActualUser.isPresent(), is(true));
        assertThat(maybeActualUser.get(), equalTo(expectedUser));
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
