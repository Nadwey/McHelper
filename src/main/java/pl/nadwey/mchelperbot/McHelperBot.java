package pl.nadwey.mchelperbot;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import pl.nadwey.mchelperbot.config.ConfigManager;
import pl.nadwey.mchelperbot.listener.MessageListener;
import pl.nadwey.mchelperbot.problem.ProblemManager;
import pl.nadwey.mchelperbot.template.TemplateManager;

@Getter
public class McHelperBot {
    @Getter
    private static McHelperBot instance;

    private final ConfigManager configManager = new ConfigManager();
    private final TemplateManager templateManager = new TemplateManager();
    private final ProblemManager problemManager = new ProblemManager();
    private JDA api;

    public static void main(String[] args) {
        new McHelperBot().setup();
    }

    public McHelperBot() {
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
