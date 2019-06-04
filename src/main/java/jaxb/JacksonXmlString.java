package jaxb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jaxb.domain.Credit;
import jaxb.domain.CreditOffer;
import jaxb.domain.CreditOfferStatus;
import jaxb.domain.CreditStatus;
import jaxb.domain.Customer;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * User: Petr Konovalchuk
 * Time: 2019-06-04 14:16
 */
public class JacksonXmlString {

    public static void main(String[] args) throws JsonProcessingException {

        Customer customer = new Customer("Tom@mail.com", "Tom", "NY", null);
        CreditOffer creditOffer = new CreditOffer(null,5L, 23.0,4,"Super credit", CreditOfferStatus.ACTIVE);

        Credit credit = new Credit(null,creditOffer, customer,null, null, CreditStatus.IN_PROGRESS, 12L );

        System.out.println(whenJavaSerializedToXmlStr_thenCorrect(credit));
    }

    public static String whenJavaSerializedToXmlStr_thenCorrect(Credit credit) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = xmlMapper.writeValueAsString(credit);
        return  xml;
    }
}
