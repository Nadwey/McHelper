package pl.nadwey.mchelperbot.listener;

import com.google.common.io.CharStreams;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import pl.nadwey.mchelperbot.McHelperBot;
import pl.nadwey.mchelperbot.problem.Problem;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MessageListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        List<Message.Attachment> attachments = event.getMessage().getAttachments();
        if (attachments.size() != 1) return;
        Message.Attachment attachment = attachments.getFirst();

        if (attachment.getSize() > 3 * 1024 * 1024) return;

        try {
            String log = CharStreams.toString(new InputStreamReader(attachment.getProxy().download().get()));
            StringBuilder message = new StringBuilder();

            // TODO: Move this to templates
            for (Problem problem : McHelperBot.getInstance().getProblemManager().getProblems()) {
                message.append("## ").append(problem.getName()).append("\n\n");
                message.append(problem.check(log).getMessage());
                message.append("\n\n");
            }

            event.getMessage().reply(message.toString()).queue();
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
