package com.example.common.libs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Type {
    @Getter
    @AllArgsConstructor
    public enum TokenType {
        ACCESS("ACCESS"),
        REFRESH("REFRESH");

        private final String prop;
    }

    @Getter
    @AllArgsConstructor
    public enum PrefixType {
        SESSION("SESSION"),
        AUTH("AUTH"),
        USER("USER");

        private final String prop;
    }
}
