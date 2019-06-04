package jaxb;

import jaxb.domain.Credit;
import jaxb.domain.CreditOffer;
import jaxb.domain.CreditOfferStatus;
import jaxb.domain.CreditStatus;
import jaxb.domain.Customer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * User: Petr Konovalchuk
 * Time: 2019-06-04 10:37
 */
public class jaxbReader {
    public static void main(String[] args) throws JAXBException {
        File file = new File("/Users/ruakop5/IdeaProjects/decision-maker/src/main/java/jaxb/jaxbExample.xml");
        JAXBContext context = JAXBContext.newInstance(Credit.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Credit credit = (Credit) unmarshaller.unmarshal(file);

        System.out.println(credit.getStatus());
        System.out.println("Change_status");
        jaxbReader.setStatus(credit);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(credit, file);
        System.out.println(credit.getStatus());

    }

    public static Credit setStatus(Credit credit){
       ArrayList<CreditStatus> status = new ArrayList<>();
       status.add(CreditStatus.APROVED);
       status.add(CreditStatus.DECLINED);
        int randomIndex = (int) (Math.random() * status.size());
        credit.setStatus(status.get(randomIndex));
        return credit;
    }

}
