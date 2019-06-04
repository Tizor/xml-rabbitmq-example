package jaxb;

import jaxb.domain.Credit;
import jaxb.domain.CreditOffer;
import jaxb.domain.CreditOfferStatus;
import jaxb.domain.CreditStatus;
import jaxb.domain.Customer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * User: Petr Konovalchuk
 * Time: 2019-06-04 10:07
 */
public class jaxbWriter {
    public static void main(String[] args) throws JAXBException {

        Customer customer = new Customer("Tom@mail.com", "Tom", "NY", null);
        CreditOffer creditOffer = new CreditOffer(null,5L, 23.0,4,"Super credit", CreditOfferStatus.ACTIVE);

        Credit credit = new Credit(null,creditOffer, customer,null, null, CreditStatus.IN_PROGRESS, 12L );

        File file = new File("/Users/ruakop5/IdeaProjects/decision-maker/src/main/java/jaxb/jaxbExample.xml");
        JAXBContext context = JAXBContext.newInstance(Credit.class);

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(credit, file);
        marshaller.marshal(credit, System.out);
    }
}
