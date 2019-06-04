package jaxb.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * User: Egor Ovcharenko
 * Time: 2019-05-16 18:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditOffer {

    private UUID id;

    private Long amount;

    private double percent;

    private int durationInDays;

    private String name;

    private CreditOfferStatus creditOfferStatus;
}
