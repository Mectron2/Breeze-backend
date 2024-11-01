package org.app.breeze.enums;

import lombok.Getter;

@Getter
public enum ContentType {
    TEXT("text"),
    IMAGE("image"),
    VIDEO("video");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

}
