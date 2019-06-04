package jaxb.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String email;

    private String name;

    private String address;

    private UUID id;

}

