package com.example.pdfgeneratorclient.controllers;

import com.example.pdfgeneratorclient.dto.PdfParamsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DocumentsController {

    public final RabbitTemplate rabbitTemplate;

    public final static String TYPE_1_KEY = "docs.type1";
    public final static String TYPE_2_KEY = "docs.type2";

    @PostMapping(value = "/docs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendData(@RequestBody PdfParamsDto paramsDto) {

        String routing = paramsDto.getType().toLowerCase();
        switch (routing) {
            case "type1":
                routing = TYPE_1_KEY;
                break;
            case "type2":
                routing = TYPE_2_KEY;
                break;
        }
        rabbitTemplate.send(
                "documents_exchange",
                routing,
                new Message((paramsDto.getFirstName() + " " + paramsDto.getLastName() + " " + UUID.randomUUID()).getBytes())
        );
    }
}
