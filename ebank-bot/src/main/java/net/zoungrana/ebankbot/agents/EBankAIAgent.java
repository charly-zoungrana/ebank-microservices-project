package net.zoungrana.ebankbot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class EBankAIAgent {

    private final ChatClient chatClient;

    public EBankAIAgent(ChatClient.Builder chatClient,
                        ChatMemory chatMemory,
                        ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Tu es un assistant chargé de répondre aux questions de l'utilisateur 
                        à propos des clients et des comptes bancaires, en fonction du contexte fourni. Si aucun contexte n'est fourni, réponds par JE NE SAIS PAS. 
                        """)
                .defaultOptions(OpenAiChatOptions.builder()
                        .extraBody(Map.of("include_reasoning", false)))
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();
    }

    public String chat(String query,String conversationId){

        return chatClient.prompt(query)
                .advisors(a->a.param(ChatMemory.CONVERSATION_ID,conversationId) )
                .call()
                .content();
    }
}
