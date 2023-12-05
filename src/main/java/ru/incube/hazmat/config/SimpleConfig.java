package ru.incube.hazmat.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Класс SimpleConfig предназначен для работы с конфигами
 */
@Getter
@Slf4j(topic = "Config")
public class SimpleConfig {
    private final HashMap<String, String> config = new HashMap<>();
    private final ConfigRequest request;
    private boolean broken = false;

    /**
     * Конструктор класса SimpleConfig
     *
     * @param request объект ConfigRequest, содержащий информацию о конфиге
     */
    public SimpleConfig(ConfigRequest request) {
        this.request = request;
        String identifier = "Конфиг '" + request.filename + "'";

        if (!request.file.exists()) {
            log.info("Отсутствует " + identifier + ", генерируем стандартный...");
            try {
                createConfig();
            } catch (IOException e) {
                log.error("Не удалось сгенерировать " + identifier + "!", e);
                broken = true;
            }
        }

        if (!broken) {
            try {
                loadConfig();
            } catch (Exception e) {
                log.error("Не удалось загрузить " + identifier + "!", e);
                broken = true;
            }
        }
    }

    /**
     * Создать объект ConfigRequest
     *
     * @param filename имя конфига
     * @return объект ConfigRequest
     */
    public static ConfigRequest of(String filename) {
        Path path = FabricLoader.getInstance().getConfigDir();
        return new ConfigRequest(path.resolve(filename + ".properties").toFile(), filename);
    }

    /**
     * Создать конфиг
     *
     * @throws IOException в случае ошибки ввода/вывода
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createConfig() throws IOException {
        request.file.getParentFile().mkdirs();
        Files.createFile(request.file.toPath());
        Files.writeString(request.file.toPath(), request.getConfig());
    }

    /**
     * Загрузить конфиг из файла
     *
     * @throws IOException в случае ошибки ввода/вывода
     */
    private void loadConfig() throws IOException {
        try (Scanner scanner = new Scanner(request.file, StandardCharsets.UTF_8)) {
            while (scanner.hasNextLine()) {
                parseConfigEntry(scanner.nextLine());
            }
        }
    }

    /**
     * Метод для разбора строк конфига
     *
     * @param entry строка конфига
     */
    private void parseConfigEntry(String entry) {
        if (!entry.isEmpty() && !entry.startsWith("#")) {
            String[] parts = entry.split("=", 2);
            if (parts.length == 2) {
                config.put(parts[0], parts[1]);
            } else {
                throw new RuntimeException("Ошибка синтаксиса в конфиге!");
            }
        }
    }

    /**
     * Метод для получения значения из конфига или значения по умолчанию
     *
     * @param key ключ
     * @param def значение по умолчанию
     * @return значение из конфига или значение по умолчанию
     */
    public String getOrDefault(String key, String def) {
        return config.getOrDefault(key, def);
    }

    /**
     * Метод для получения значения из конфига или значения по умолчанию
     *
     * @param key ключ
     * @param def значение по умолчанию
     * @return значение из конфигурации или значение по умолчанию
     */
    public int getOrDefault(String key, int def) {
        return Integer.parseInt(config.getOrDefault(key, String.valueOf(def)));
    }

    /**
     * Метод для получения значения из конфига или значения по умолчанию
     *
     * @param key ключ
     * @param def значение по умолчанию
     * @return значение из конфига или значение по умолчанию
     */
    public boolean getOrDefault(String key, boolean def) {
        return Boolean.parseBoolean(config.getOrDefault(key, String.valueOf(def)));
    }

    /**
     * Метод для получения значения из конфига или значения по умолчанию
     *
     * @param key ключ
     * @param def значение по умолчанию
     * @return значение из конфига или значение по умолчанию
     */
    public double getOrDefault(String key, double def) {
        return Double.parseDouble(config.getOrDefault(key, String.valueOf(def)));
    }

    /**
     * Метод для удаления файла конфига
     *
     * @return true, если файл был успешно удален, иначе false
     */
    public boolean delete() {
        log.warn("Конфиг '" + request.filename + "' был удален! Перезапустите игру, чтобы сгенерировать его заново.");
        return request.file.delete();
    }
}