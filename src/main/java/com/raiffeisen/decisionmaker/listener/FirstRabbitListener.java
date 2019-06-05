package com.raiffeisen.decisionmaker.listener;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.raiffeisen.decisionmaker.dto.CreditDto;
import jaxb.domain.CreditStatus;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

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
public class FirstRabbitListener {

    private RabbitTemplate rabbitTemplate;

    public FirstRabbitListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "from service 1 to service 2")
    public void from1to2(Message message) throws IOException {

        String from1to2 = new String(message.getBody());
        System.out.println(from1to2);
        XmlMapper xmlMapper = new XmlMapper();
        CreditDto firstCreditDto = xmlMapper.readValue(from1to2, CreditDto.class);
        setStatus(firstCreditDto);
//        System.out.println(firstCreditDto);
        rabbitTemplate.convertAndSend("from service 2 to service 1", firstCreditDto);
    }

    @RabbitListener(queues = "from service 2 to service 1")
    public void from2to1(Message message) throws IOException {

        String from2to1 = new String(message.getBody());
//        System.out.println(from2to1);
        XmlMapper xmlMapper = new XmlMapper();
        CreditDto secondCreditDto = xmlMapper.readValue(from2to1, CreditDto.class);

        System.out.println(secondCreditDto);
    }

    public CreditDto setStatus(CreditDto firstCreditDto) {
        ArrayList<CreditStatus> status = new ArrayList<>();
        status.add(CreditStatus.APROVED);
        status.add(CreditStatus.DECLINED);
        int randomIndex = (int) (Math.random() * status.size());
        firstCreditDto.setCreditStatus(status.get(randomIndex));
        return firstCreditDto;
    }


}

//    @RabbitListener(queues = "from service 1 to service 2")
//    public void onMessage(Message message) throws IOException {
//
//        String xmlResultFromRabbiMQ = new String(message.getBody());
//        System.out.println(xmlResultFromRabbiMQ);
//        XmlMapper xmlMapper = new XmlMapper();
//        firstCreditDto = xmlMapper.readValue(xmlResultFromRabbiMQ, CreditDto.class);
//        System.out.println(firstCreditDto);
//
//    }


//
//    XmlMapper xmlMapper = new XmlMapper();
//    SimpleBean value
//            = xmlMapper.readValue("<SimpleBean><x>1</x><y>2</y></SimpleBean>", SimpleBean.class);

//    @Override
//    @RabbitListener(queues = "from service 1 to service 2")
//    public void onMessage(Message message) {
//        String credit = new String(message.getBody());
//        System.out.println(credit);
//
//        Document doc = Jsoup.parse(credit, "", Parser.xmlParser());
//        System.out.println(doc.select("creditStatus").text());
//
//        XmlMapper xmlMapper = new XmlMapper();
//        try {
//            Credit value = xmlMapper.readValue(credit, Credit.class);
//            System.out.println(value);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


