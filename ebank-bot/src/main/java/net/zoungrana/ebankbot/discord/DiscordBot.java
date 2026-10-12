package net.zoungrana.ebankbot.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.zoungrana.ebankbot.agents.EBankAIAgent;
import org.springframework.ai.chat.prompt.Prompt;

@DiscordController
@RequiredArgsConstructor
public class DiscordBot {
    private final EBankAIAgent ebankAIAgent;

    @DiscordMapping
    private void perform(MessageReceivedEvent event){
        if(event.getAuthor().isBot()) return;
        String query=event.getMessage().getContentRaw();
        String conversationId=event.getAuthor().getId();
        String response=ebankAIAgent.chat(new Prompt(query),conversationId);
        event.getChannel().sendMessage(response).queue();

    }
}
