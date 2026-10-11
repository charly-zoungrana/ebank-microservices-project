package net.zoungrana.ebankbot.web;

import lombok.RequiredArgsConstructor;
import net.zoungrana.ebankbot.agents.EBankAIAgent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class EBankBotController {

    private final EBankAIAgent ebankAIAgent;


    @GetMapping("/chat")
    public String chat(@RequestParam(name="query",defaultValue = "Bonjour") String query,
                       @RequestParam(name="conversationId",defaultValue="default") String conversationId){

        return ebankAIAgent.chat(query,conversationId);
    }
}
