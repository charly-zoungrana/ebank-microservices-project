package net.zoungrana.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class EBankAIAgent {

    private final ChatClient chatClient;

    public EBankAIAgent(ChatClient.Builder chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient
                .defaultOptions(OpenAiChatOptions.builder()
                        .extraBody(Map.of("include_reasoning", false)))
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public String chat(String query,String conversationId){

        return chatClient.prompt(query)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,conversationId) )
                .call()
                .content();
    }
}
