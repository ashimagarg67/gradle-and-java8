package com.cmartin.learn;

public enum PathConstants {

    JAVA_SRC_PATH("src/main/java/"),
    JAVA_PKG_PATH("com/cmartin/learn/types/");

    private final String value;

    PathConstants(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
