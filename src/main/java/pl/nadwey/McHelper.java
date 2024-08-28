package pl.nadwey;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import pl.nadwey.config.ConfigManager;
import pl.nadwey.listener.MessageListener;
import pl.nadwey.problem.ProblemManager;
import pl.nadwey.template.TemplateManager;

@Getter
public class McHelper {
    @Getter
    private static McHelper instance;

    private final ConfigManager configManager = new ConfigManager();
    private final TemplateManager templateManager = new TemplateManager();
    private final ProblemManager problemManager = new ProblemManager();
    private JDA api;

    public static void main(String[] args) {
        new McHelper().setup();
    }

    public McHelper() {
        instance = this;
    }

    private void setup() {
        api = JDABuilder
                .createDefault(configManager.general().getToken())
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGES)
                .addEventListeners(new MessageListener())
                .build();
    }
}
