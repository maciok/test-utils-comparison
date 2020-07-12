package com.macpi.test;

import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
class User {
    private final UUID id;
    private final String name;
    private final LocalDate birthday;
    private final Phone phone;
}
