package org.app.breeze.enums;

import lombok.Getter;

@Getter
public enum ContentType {
    TEXT("text"),
    IMAGE("image");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

}
