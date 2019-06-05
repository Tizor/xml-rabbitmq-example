package com.raiffeisen.decisionmaker.dto;

import jaxb.domain.CreditOffer;
import jaxb.domain.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

/**
 * User: Petr Konovalchuk
 * Time: 2019-06-04 17:03
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditDto implements Serializable {

    private UUID id;
    private CreditOffer creditOffer;
    private CreditStatus creditStatus;

}
