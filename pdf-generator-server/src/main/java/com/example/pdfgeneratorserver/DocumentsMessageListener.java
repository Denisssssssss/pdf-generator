package com.example.pdfgeneratorserver;

import com.itextpdf.text.DocumentException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;


@Component
public class DocumentsMessageListener {

    @RabbitListener(queues = "#{firstQueue.name}", containerFactory = "containerFactory")
    public void onFirstType(Message message) throws FileNotFoundException, DocumentException {
        String content = new String(message.getBody());
        FirstTypeGenerator.generate(content);
    }

    @RabbitListener(queues = "#{secondQueue.name}", containerFactory = "containerFactory")
    public void onSecondType(Message message) throws FileNotFoundException, DocumentException {
        String content = new String(message.getBody());
        SecondTypeGenerator.generate(content);
    }
}
