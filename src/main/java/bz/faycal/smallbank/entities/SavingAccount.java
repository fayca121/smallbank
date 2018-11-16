package bz.faycal.smallbank.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends Account{
    private double rate;

    public SavingAccount(String accountCode, Date creationDate, double balance, Client client, double rate) {
        super(accountCode, creationDate, balance, client);
        this.rate = rate;
    }
}
