package ru.incube.hazmat.config;

public interface DefaultConfig {
    static String empty(String namespace) {
        return "";
    }

    String get(String namespace);
}
