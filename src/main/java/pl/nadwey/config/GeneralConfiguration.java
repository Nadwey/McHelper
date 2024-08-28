package pl.nadwey.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

@Getter
public class GeneralConfiguration extends OkaeriConfig {
    @Comment("Token")
    private String token = "TOKEN";
}
