package com.raiffeisen.decisionmaker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jaxb.domain.Credit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
public class RabbitMqListener implements MessageListener {

//    @RabbitListener(queues = "query1")
//    public void worker1(String credit) throws InterruptedException {
//        System.out.println("Credit " + credit);
//        Thread.sleep(100);
//    }

    @Override
    @RabbitListener(queues = "query1")
    public void onMessage(Message message) {
        String credit = new String(message.getBody());
        System.out.println(credit);

        Document doc = Jsoup.parse(credit, "", Parser.xmlParser());
        System.out.println(doc.select("creditStatus").text());

        XmlMapper xmlMapper = new XmlMapper();
        try {
            Credit value = xmlMapper.readValue(credit, Credit.class);
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
