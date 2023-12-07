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
     * Устанавливает провайдера конфига по умолчанию, используемого для генерации
     * конфига, если он отсутствует.
     *
     * @param provider провайдер конфигурации по умолчанию
     * @return текущий объект запроса конфига
     */
    public ConfigRequest provider(DefaultConfig provider) {
        this.provider = provider;
        return this;
    }

    /**
     * Загружает конфиг из системы.
     *
     * @return конфиг
     * @see SimpleConfig
     */
    public SimpleConfig request() {
        return new SimpleConfig(this);
    }

    public String getConfig() {
        return provider.get(filename) + "\n";
    }
}