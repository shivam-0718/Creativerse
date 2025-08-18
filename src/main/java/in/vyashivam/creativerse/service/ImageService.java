package in.vyashivam.creativerse.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    private final OpenAiImageModel imageModel;

    @Autowired
    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String message) {
        ImagePrompt imagePrompt = new ImagePrompt(message);
        return imageModel.call(imagePrompt);
    }

    public ImageResponse generateImageOptions(String message) {
        ImagePrompt imagePrompt = new ImagePrompt(message,
                OpenAiImageOptions.builder()
                .N(2)
                .model("dall-e-2")
                .height(1024)
                .width(1024)
                .build()
        );
        return imageModel.call(imagePrompt);
    }
}
