package com.macpi.test;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

interface UserRepository {

    Optional<User> findById(UUID id);

    Collection<User> getAll();
}
