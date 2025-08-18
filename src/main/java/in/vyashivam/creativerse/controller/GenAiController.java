package in.vyashivam.creativerse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import in.vyashivam.creativerse.service.IChatService;
import in.vyashivam.creativerse.service.IImageService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/ai")
public class GenAiController {

    private final IChatService service;
    private final IImageService imageService;

    public GenAiController(@Autowired IChatService service, @Autowired IImageService imageService) {
        this.service = service;
        this.imageService = imageService;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> getResponse(@RequestBody String message) {
        String response = service.getResponse(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/chat-options")
    public ResponseEntity<String> getResponseOptions(@RequestBody String message) {
        String response = service.getResponseOptions(message);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/image")
    public ResponseEntity<String> getImage(@RequestBody String message) {
        ImageResponse imageResponse = imageService.generateImage(message);
        String url = imageResponse.getResult().getOutput().getUrl();
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @PostMapping("/image-options")
    public ResponseEntity<String> getImageOptions(@RequestBody String message) throws JsonProcessingException {
        ImageResponse imageResponse = imageService.generateImageOptions(message);

        List<String> imageUrls = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();

        String urls = new ObjectMapper().writeValueAsString(imageUrls);
        return new ResponseEntity<>(urls, HttpStatus.OK);
    }
}
