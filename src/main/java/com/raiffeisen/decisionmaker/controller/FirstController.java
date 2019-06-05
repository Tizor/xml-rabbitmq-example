package com.raiffeisen.decisionmaker.controller;

import com.raiffeisen.decisionmaker.dto.CreditDto;
import jaxb.domain.Credit;
import jaxb.domain.CreditOffer;
import jaxb.domain.CreditOfferStatus;
import jaxb.domain.CreditStatus;
import jaxb.domain.Customer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Petr Konovalchuk
 * Time: 2019-06-03 14:14
 */
@Controller
public class FirstController {

    private RabbitTemplate rabbitTemplate;

    public FirstController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping("/first")
    @ResponseBody
    public String emit() {

        Customer customer = new Customer("Tom@mail.com", "Tom", "NY", null);
        CreditOffer creditOffer = new CreditOffer(null,5L, 23.0,4,"Super credit", CreditOfferStatus.ACTIVE);
        Credit creditOne = new Credit(null, creditOffer, customer, null, null, CreditStatus.IN_PROGRESS, 12L);

        CreditDto creditDto = new CreditDto();
        creditDto.setId(creditOne.getId());
        creditDto.setCreditOffer(creditOne.getCreditOffer());
        creditDto.setCreditStatus(creditOne.getCreditStatus());

        rabbitTemplate.convertAndSend("from service 1 to service 2", creditDto);
        return "from service 1 to service 2";
    }
}