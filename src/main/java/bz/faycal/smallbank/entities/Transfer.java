package bz.faycal.smallbank.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@NoArgsConstructor
@Entity
@DiscriminatorValue("T")
public class Transfer extends Operation {

    public Transfer(Date operationDate, double amount, Account account) {
        super(operationDate, amount, account);
    }
}
