package ru.vkprofi.hazmat;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.vkprofi.hazmat.config.SimpleConfig;

/* =====================================
 * Мод написан VkProfi для InCube Create
 * =====================================
 */
public class main implements ModInitializer {
    public static final String MODID = "hazmat"; // мод ID
    public static Logger ModLog = LogManager.getLogger("Hazmat"); // Log4j для вывода в консоль от мода
    private static final Logger log = LogManager.getLogger("[Hazmat] (main)");  // Log4j для вывода в консоль от класса

    @Override
    public void onInitialize() {
        String output = "\n\n==============";
        // Регистрация
        new registry();
        output+="\nПредметы зарегестрированны";
        //активация зоны
        new zone(ZoneX,ZoneZ,ZoneR);
        output+="\nЗона активна";
        // Для консоли
        ModLog.info(output + "\n==============\nМод загрузился\n==============\n");

    }

    // Конфиги
    SimpleConfig config = SimpleConfig.of("hazmat").provider(this::provider).request();
    private String provider( String filename ) {
        return """
                #======================
                #
                # Mod creator: VkProfi
                #
                #======================
                
                ZoneX: 0
                ZoneZ: 0
                ZoneRadius: 40
                """;
    }
    public final int ZoneX = config.getOrDefault("ZoneX",0);
    public final int ZoneZ = config.getOrDefault("ZoneZ",0);
    public final int ZoneR = config.getOrDefault("ZoneRadius",10);
}
