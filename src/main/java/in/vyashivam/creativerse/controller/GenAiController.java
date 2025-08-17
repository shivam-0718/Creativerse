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
    public ResponseEntity<String> getResponse(@RequestBody String prompt) {
        String response = service.getResponse(prompt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
