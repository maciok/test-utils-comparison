package com.macpi.test


import spock.lang.Ignore
import spock.lang.Specification

import java.time.LocalDate

import static com.macpi.test.UserTestRepository.FIRST_ID

class GroovyUserTest extends Specification {
    UserService testee

    def 'get user when user is present'() {
        given:
        testee = new UserService(new UserTestRepository())

        when:
        def actualUser = testee.getByIdOrThrow(FIRST_ID)

        then:
        def expectedUser = createFirstUser()
        actualUser == expectedUser
    }

    def 'get user - when user is not present should throw'() {
        given:
        testee = new UserService(new UserTestRepository())
        when:
        testee.getByIdOrThrow(UUID.randomUUID())
        then:
        Exception thrownException = thrown(IllegalArgumentException)
        thrownException.message.contains("Cannot find user with id")
    }

   def 'get user - when user is present and has different id'() {
       given:
       testee = new UserService(new UserTestRepository());
       def expectedValues = [
               "name": "Jan Kowalski",
               "birthday": LocalDate.of(1990, 10, 10),
               "phone": new Phone("+48606023420")
       ]

       when:
       User actualUser = testee.getByIdOrThrow(FIRST_ID);

       then:
       actualUser.properties.subMap(expectedValues.keySet()) == expectedValues
   }

    @Ignore
    def 'get user - when user is present return user #errorCase'() {
        given:
        testee = new UserService(new UserTestRepository())

        when:
        User actualUser = testee.getByIdOrThrow(FIRST_ID)

        then:
        User expectedUser = createRandomUser()
        actualUser == expectedUser
    }

    private User createRandomUser() {
        return new User(
                UUID.randomUUID(),
                "Jan Someone",
                LocalDate.of(1990, 10, 10),
                new Phone("+48606023420")
        )
    }

    private User createFirstUser() {
        return new User(
                FIRST_ID,
                "Jan Kowalski",
                LocalDate.of(1990, 10, 10),
                new Phone("+48606023420")
        )
    }
}
