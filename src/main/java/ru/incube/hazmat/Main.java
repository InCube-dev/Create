package ru.incube.hazmat;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.ModInitializer;
import ru.incube.hazmat.config.SimpleConfig;

@Getter
@Slf4j(topic = "Hazmat")
@RequiredArgsConstructor
public class Main implements ModInitializer {
    public static final String MODID = "hazmat";

    @NonNull
    private final SimpleConfig config = SimpleConfig.of("hazmat").provider(this::provider).request();

    public final int ZoneX = config.getOrDefault("ZoneX", 0);
    public final int ZoneZ = config.getOrDefault("ZoneZ", 0);
    public final int ZoneR = config.getOrDefault("ZoneRadius", 10);

    @Override
    public void onInitialize() {
        log.info("==============");
        // Регистрация
        new Registry();
        log.info("Предметы зарегистрированы");
        //активация зоны
        new Zone(ZoneX, ZoneZ, ZoneR);
        log.info("Зона активна");
        log.info("============== Мод загрузился ==============");
    }

    private String provider(String filename) {
        return """
                # Настройки зоны
                ZoneX=0
                ZoneZ=0
                ZoneRadius=40
                """;
    }
}