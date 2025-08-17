package in.vyashivam.creativerse.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements IChatService {

    private final ChatClient chatClient;

    public ChatService(@Qualifier("chatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String getResponse(String message) {
        return chatClient.
                prompt(message)
                .call()
                .content();
    }

    @Override
    public String getResponseOptions(String message) {
        Prompt prompt = new Prompt(message, OpenAiChatOptions.builder()
                .model("gpt-4-0")
                .temperature(0.4)
                .build());
        ChatClient.CallResponseSpec chatResponse = chatClient.prompt(prompt).call();
        return chatResponse.content();
    }
}
