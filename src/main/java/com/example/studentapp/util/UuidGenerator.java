package com.example.studentapp.util;

import static java.util.UUID.randomUUID;

public class UuidGenerator {
    public static String uuid() {
        return randomUUID().toString();
    }
}
