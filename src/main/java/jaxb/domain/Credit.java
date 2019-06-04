package jaxb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * User: Alexandr Veshutov
 * Time: 2019-05-15 18:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Credit {

    private UUID id;

    private CreditOffer creditOffer;

    private Customer customer;

    private LocalDateTime requestDate;

    private LocalDateTime decisionDate;

    private CreditStatus status;

    private Long debt;

}
