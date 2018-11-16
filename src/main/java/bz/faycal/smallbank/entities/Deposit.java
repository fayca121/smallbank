package bz.faycal.smallbank.entities;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@NoArgsConstructor
@Entity
@DiscriminatorValue("D")
public class Deposit extends Operation {

    public Deposit(Date operationDate, double amount, Account account) {
        super(operationDate, amount, account);
    }
}
