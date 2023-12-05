package ru.incube.hazmat.config;

import java.io.File;

public class ConfigRequest {
    public final File file;
    public final String filename;
    private DefaultConfig provider;

    public ConfigRequest(File file, String filename) {
        this.file = file;
        this.filename = filename;
        this.provider = DefaultConfig::empty;
    }

    /**
     * Перевод:
     * Устанавливает провайдера конфигурации по умолчанию, используемого для генерации
     * конфигурации, если он отсутствует.
     *
     * @param provider поставщик конфигурации по умолчанию
     * @return текущий объект запроса конфигурации
     */
    public ConfigRequest provider(DefaultConfig provider) {
        this.provider = provider;
        return this;
    }

    /**
     * Loads the config from the filesystem.
     *
     * @return config object
     * @see SimpleConfig
     */
    public SimpleConfig request() {
        return new SimpleConfig(this);
    }

    public String getConfig() {
        return provider.get(filename) + "\n";
    }
}