package in.vyashivam.creativerse.configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AiConfiguration {

    //Implementation of ChatClient bean through OpenAiChatModel for this project
    @Bean("chatClient")
    public ChatClient createChatClientBean(OpenAiChatModel openAiChatModel) {
        return ChatClient.create(openAiChatModel);
    }

    @Bean("chatMemory")
    public ChatMemory createChatMemoryBean() {
        return MessageWindowChatMemory.builder().build();
    }

    //Implementation of ChatClient bean for chat recall via Advisors
    @Bean("chatClientMemory")
    @Primary
    public ChatClient createChatClientWithMemory(ChatClient.Builder builder, @Qualifier("chatMemory") ChatMemory chatMemory) {
        return builder.defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build()).build();
    }
}
