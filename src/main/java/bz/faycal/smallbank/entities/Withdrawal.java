package bz.faycal.smallbank.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@NoArgsConstructor
@Entity
@DiscriminatorValue("W")
public class Withdrawal extends Operation {

    public Withdrawal(Date operationDate, double amount, Account account) {
        super(operationDate, amount, account);
    }
}
