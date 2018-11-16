package bz.faycal.smallbank.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("CA")
public class CurrentAccount extends Account{
    private double overdraft;

    public CurrentAccount(String accountCode, Date creationDate, double balance, Client client, double overdraft) {
        super(accountCode, creationDate, balance, client);
        this.overdraft = overdraft;
    }
}
