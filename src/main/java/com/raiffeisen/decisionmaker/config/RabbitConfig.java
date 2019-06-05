package com.raiffeisen.decisionmaker.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2XmlMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * User: Petr Konovalchuk
 * Time: 2019-06-03 14:08
 */

/**
 * Для работы с RabbitMQ нам потребуются следующие бины:
 * — сonnectionFactory — для соединения с RabbitMQ;
 * — rabbitAdmin — для регистрации/отмены регистрации очередей и т.п.;
 * — rabbitTemplate — для отправки сообщений (producer);
 * — myQueue1 —  очередь куда посылаем сообщения;
 * — ListenerContainer — класс, который принимает сообщения (consumer).
 */

@Configuration
public class RabbitConfig {

    //настраиваем соединение с RabbitMQ
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate =  new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(new Jackson2XmlMessageConverter());
        return rabbitTemplate;
    }

    //объявляем очереди
    @Bean
    public Queue fromService1toService2() {
        return new Queue("from service 1 to service 2");
    }

    @Bean
    public Queue fromService2toService1() {
        return new Queue("from service 2 to service 1");
    }

}