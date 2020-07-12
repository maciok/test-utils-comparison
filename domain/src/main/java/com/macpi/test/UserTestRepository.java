package com.macpi.test;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
class UserTestRepository implements UserRepository {
    static final UUID FIRST_ID = UUID.fromString("34d764f0-c22b-4d2d-b4e3-440c65b3b6e5");
    static final UUID SECOND_ID = UUID.fromString("906de967-d2cf-424c-be0b-144ce69d29b2");
    static final UUID THIRD_ID = UUID.fromString("8eede27d-991e-4a75-8f6a-b571c87a0c7d");

    private final User user1 = new User(FIRST_ID, "Jan Kowalski", LocalDate.of(1990,10, 10), new Phone("+48606023420"));
    private final User user2 = new User(SECOND_ID, "Tom Holland", LocalDate.of(1985,5, 24), new Phone("+48606023420"));
    private final User user3 = new User(THIRD_ID, "Ryszard Więckiewicz", LocalDate.of(1972,11, 3), new Phone("+48606023420"));

    private final Map<UUID, User> testStorage = ImmutableMap.of(
            FIRST_ID, user1,
            SECOND_ID, user2,
            THIRD_ID, user3
    );

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(testStorage.getOrDefault(id,null));
    }

    @Override
    public Collection<User> getAll() {
        return testStorage.values();
    }
}
