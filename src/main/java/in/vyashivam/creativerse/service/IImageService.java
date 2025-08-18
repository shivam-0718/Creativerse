package in.vyashivam.creativerse.service;

import org.springframework.ai.image.ImageResponse;

public interface IImageService {
    ImageResponse generateImage(String message);
}
