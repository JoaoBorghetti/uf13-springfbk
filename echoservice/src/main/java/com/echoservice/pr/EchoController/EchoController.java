package com.echoservice.pr.EchoController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EchoController {
    
    @Value("${echomessage:Hello from default!}")
    private String message;

    @GetMapping("/echomessage")
    public @ResponseBody String echoMessage() {
        return message;
    }
}
