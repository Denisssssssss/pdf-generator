package com.example.pdfgeneratorserver;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("documents_exchange");
    }

    @Bean
    public Queue firstQueue() {
        return new Queue("type1.queue");
    }

    @Bean
    public Queue secondQueue() {
        return new Queue("type2.queue");
    }

    @Bean
    public Binding bindingFirst(DirectExchange directExchange) {
        return BindingBuilder.bind(firstQueue()).to(directExchange).with("docs.type1");
    }

    @Bean
    public Binding bindingSecond(DirectExchange directExchange) {
        return BindingBuilder.bind(secondQueue()).to(directExchange).with("docs.type2");
    }

    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> containerFactory(ConnectionFactory rabbitConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(rabbitConnectionFactory);
        factory.setPrefetchCount(10);
        factory.setConcurrentConsumers(5);
        return factory;
    }

}
