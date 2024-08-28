package pl.nadwey.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.serdes.standard.StandardSerdes;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class ConfigManager {
    private final GeneralConfiguration general;

    public ConfigManager() {
        general = createConfig(GeneralConfiguration.class, "config.yml");
    }

    private <T extends OkaeriConfig> T createConfig(Class<T> configClass, String filename) {
        return eu.okaeri.configs.ConfigManager.create(configClass, (it) -> {
            it.withConfigurer(new YamlSnakeYamlConfigurer(), new StandardSerdes());
            it.withBindFile(filename);
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load(true);
        });
    }
}
