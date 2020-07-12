package com.macpi.test;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.UUID;

import static java.lang.String.format;

@AllArgsConstructor
class UserService {
    private final UserRepository userRepository;

    User getByIdOrThrow(UUID id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException(format("Cannot find user with id: %s", id)));
    }

    Collection<User> getAll() {
        return userRepository.getAll();
    }
}
