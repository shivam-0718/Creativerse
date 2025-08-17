package in.vyashivam.creativerse.controller;

import in.vyashivam.creativerse.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ai")
public class GenAiController {

    private final IChatService service;

    public GenAiController(@Autowired IChatService service) {
        this.service = service;
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
}
