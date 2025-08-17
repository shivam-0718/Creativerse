package in.vyashivam.creativerse.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements IChatService {

    private final ChatClient chatClient;

    public ChatService(@Qualifier("chatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getResponse(String prompt) {
        return chatClient.
                prompt(prompt)
                .call()
                .content();
    }
}
