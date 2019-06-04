package com.raiffeisen.decisionmaker.config;

import jaxb.domain.Credit;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * User: Petr Konovalchuk
 * Time: 2019-06-03 14:19
 */

/**
 * RabbitMqListener — это обыкновенный компонент(@Component) спринга с методом,
 * помеченным анотацией @RabbitListener. В этом метод будут приходить сообщения.
 * При этом мы можем получать как полное сообщение Message заголовками и телом как массив байт,
 * так и просто сконвертированное тело в том виде, в каком мы его отправляли.
 */

@EnableRabbit
@Component
public class RabbitMqListener {

    @RabbitListener(queues = "query1")
    public void worker1(String credit) throws InterruptedException {
        System.out.println("Credit " + credit);
        Thread.sleep(100);
    }

}
