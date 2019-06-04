package com.raiffeisen.decisionmaker.controller;

import jaxb.domain.Credit;
import jaxb.domain.CreditOffer;
import jaxb.domain.CreditOfferStatus;
import jaxb.domain.CreditStatus;
import jaxb.domain.Customer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Petr Konovalchuk
 * Time: 2019-06-03 14:14
 */
@Controller
public class SampleController {

    private RabbitTemplate rabbitTemplate;

    public SampleController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping("/emit")
    @ResponseBody
    public String emit() {
        Customer customer = new Customer("Tom@mail.com", "Tom", "NY", null);
        CreditOffer creditOffer = new CreditOffer(null,5L, 23.0,4,"Super credit", CreditOfferStatus.ACTIVE);
        Credit credit = new Credit(null,creditOffer, customer,null, null, CreditStatus.IN_PROGRESS, 12L );
        rabbitTemplate.convertAndSend("query1", credit);
        return "Emit to exchange-example-3";
    }
}