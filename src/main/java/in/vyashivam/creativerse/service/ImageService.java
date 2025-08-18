package in.vyashivam.creativerse.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
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
}
